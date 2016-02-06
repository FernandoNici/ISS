package br.com.condominio.dao;

import br.com.condominio.model.Apartamento;
import br.com.condominio.model.Condominio;
import br.com.condominio.model.Lancamento;
import br.com.condominio.utils.HibernateUtil;
import br.com.condominio.utils.Status;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class LancamentoDAO {

  private Session sessao;
  private Transaction transacao;
  private List<Lancamento> lista;

  private boolean filtroAtivo = true;

  public LancamentoDAO() {
    super();
  }

  public void salvar(Lancamento lancto) {
    try {
      sessao = HibernateUtil.getSessionFactory().openSession();
      transacao = sessao.beginTransaction();
      
      sessao.save(lancto);
      transacao.commit();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      sessao.close();
    }
  }

  public void deletar(Lancamento lancto) {
    try {
      sessao = HibernateUtil.getSessionFactory().openSession();
      transacao = sessao.beginTransaction();
      lancto.setAtivo(false);
      sessao.update(lancto);
      transacao.commit();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      sessao.close();
    }
  }

  public void atualizar(Lancamento lancto) {
    try {
      sessao = HibernateUtil.getSessionFactory().openSession();
      transacao = sessao.beginTransaction();
      sessao.update(lancto);
      transacao.commit();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      sessao.close();
    }
  }

  public List<Lancamento> getLista(String filtro, String pago) {
    Criterion filtroDescricao;
    sessao = HibernateUtil.getSessionFactory().openSession();
    transacao = sessao.beginTransaction();
    Criteria criteria = sessao.createCriteria(Lancamento.class);

    filtroDescricao = Restrictions.like("descricao", "%" + filtro + "%");
    criteria.add(filtroDescricao);
    //pago = pago.toLowerCase();
    //if(!pago.contains("ambos")) criteria.add(Restrictions.eq("ativo",pago.contains("true")));

    this.lista = criteria.list();
    return lista;
  }

  public boolean isFiltroAtivo() {
    return filtroAtivo;
  }

  public void setFiltroAtivo(boolean filtroAtivo) {
    this.filtroAtivo = filtroAtivo;
  }

  public List<Lancamento> getListaDoApartamento(Apartamento apartamento, Status statusTipoDebito, Status statusBaixa) {
    if ((apartamento != null) && (apartamento.getId() != 0)) {
      Criterion filtro;
      sessao = HibernateUtil.getSessionFactory().openSession();
      transacao = sessao.beginTransaction();
      Criteria criteria = sessao.createCriteria(Lancamento.class);
      sessao.enableFilter("lancamentosStatusAtivo").setParameter("statusAtivo", filtroAtivo);
      if (statusTipoDebito != Status.NULO) {
        sessao.enableFilter("lancamentosTipoDebito").setParameter("tipoDebito", statusTipoDebito.toBoolean());
      }

      if (statusBaixa != Status.NULO) {
        sessao.enableFilter("lancamentosStatusBaixa").setParameter("statusBaixa", statusBaixa.toBoolean());
      }

      filtro = Restrictions.eq("apartamento", apartamento);
      criteria.add(filtro);

      this.lista = criteria.list();
      return lista;
    }
    return null;
  }

  public List<Lancamento> getListaDoCondominio(Condominio condominio, Status statusTipoDebito, Status statusBaixa) {
    if ((condominio != null) && (condominio.getId() != 0)) {
      sessao = HibernateUtil.getSessionFactory().openSession();
      transacao = sessao.beginTransaction();
      Criteria criteria = sessao.createCriteria(Lancamento.class);
      sessao.enableFilter("lancamentosStatusAtivo").setParameter("statusAtivo", filtroAtivo);
      if (statusTipoDebito != Status.NULO) {
        sessao.enableFilter("lancamentosTipoDebito").setParameter("tipoDebito", statusTipoDebito.toBoolean());
      }

      if (statusBaixa != Status.NULO) {
        sessao.enableFilter("lancamentosStatusBaixa").setParameter("statusBaixa", statusBaixa.toBoolean());
      }

      criteria.add(Restrictions.eq("condominio", condominio));
      criteria.add(Restrictions.eq("apartamento_id", null));

      this.lista = criteria.list();
      return lista;
    }
    return null;
  }

  public List<Lancamento> getListaDoCondominio(Condominio condominio, Status statusTipoDebito, Date mesReferencia) {
    if ((condominio != null) && (condominio.getId() != 0)) {
      Date dataInicial;
      Date dataFinal;

      sessao = HibernateUtil.getSessionFactory().openSession();
      transacao = sessao.beginTransaction();
      Criteria criteria = sessao.createCriteria(Lancamento.class);
      sessao.enableFilter("lancamentosStatusAtivo").setParameter("statusAtivo", filtroAtivo);
      if (statusTipoDebito != Status.NULO) {
        sessao.enableFilter("lancamentosTipoDebito").setParameter("tipoDebito", statusTipoDebito.toBoolean());
      }

      Calendar calendar = Calendar.getInstance();
      calendar.setTime(mesReferencia);
      calendar.add(Calendar.DAY_OF_MONTH, -calendar.get(Calendar.DAY_OF_MONTH));
      calendar.add(Calendar.DAY_OF_YEAR, 1);
      dataInicial = calendar.getTime();
      calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
      dataFinal = calendar.getTime();

      criteria.add(Restrictions.eq("condominio", condominio));
      criteria.add(Restrictions.ge("vencimento", dataInicial));
      criteria.add(Restrictions.le("vencimento", dataFinal));
      criteria.add(Restrictions.isNull("apartamento"));
      criteria.add(Restrictions.eq("idFechamentoMensal", (long)0));
      criteria.add(Restrictions.eq("idPai", (long)0));
      this.lista = criteria.list();
      return lista;
    }
    return null;
  }
}
