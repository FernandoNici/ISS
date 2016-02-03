package br.com.condominio.dao;

import br.com.condominio.model.Apartamento;
import br.com.condominio.model.Condominio;
import br.com.condominio.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Massao
 */
public class ApartamentoDAO {

  private Session sessao;
  private Transaction transacao;
  private List<Apartamento> lista;

  public ApartamentoDAO() {
  }

  public void adicionar(Apartamento ap) {
    try {
      sessao = HibernateUtil.getSessionFactory().openSession();
      transacao = sessao.beginTransaction();

      sessao.save(ap);
      transacao.commit();
    } catch (Exception e) {
      e.printStackTrace();
      transacao.rollback();
    } finally {
      sessao.close();
    }
  }

  public void deletar(Apartamento ap) {
    try {
      sessao = HibernateUtil.getSessionFactory().openSession();
      transacao = sessao.beginTransaction();
      sessao.delete(ap);
      transacao.commit();
    } catch (Exception e) {
      transacao.rollback();
      throw e;
    } finally {
      sessao.close();
    }
  }

  public void atualizar(Apartamento ap) {
    try {
      sessao = HibernateUtil.getSessionFactory().openSession();
      transacao = sessao.beginTransaction();
      sessao.update(ap);
      transacao.commit();
    } catch (Exception e) {
      e.printStackTrace();
      transacao.rollback();
    } finally {
      sessao.close();
    }
  }

  public List<Apartamento> getLista(String filtro) {
    Criterion filtroNome;
    sessao = HibernateUtil.getSessionFactory().openSession();
    transacao = sessao.beginTransaction();
    Criteria criteria = sessao.createCriteria(Apartamento.class);
    filtroNome = Restrictions.like("numero", filtro + "%");
    criteria.add(filtroNome);
    criteria.add(Restrictions.eq("ativo", true));
    this.lista = criteria.list();
    return this.lista;
  }

  public List<Apartamento> getListaDoCondominio(Condominio condominio) {
    Criterion filtroCondominio;
    sessao = HibernateUtil.getSessionFactory().openSession();
    transacao = sessao.beginTransaction();
    Criteria criteria = sessao.createCriteria(Apartamento.class);
    filtroCondominio = Restrictions.eq("condominio", condominio);
    criteria.add(filtroCondominio);
    this.lista = criteria.list();
    return this.lista;
  }
}
