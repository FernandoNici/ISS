package br.com.condominio.dao;

import br.com.condominio.model.AreaLazer;
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
public class AreaLazerDAO {

  private Session sessao;
  private Transaction transacao;
  private List<AreaLazer> lista;

  public AreaLazerDAO() {
  }

  public void adicionar(AreaLazer areaLazer) {
    try {
      sessao = HibernateUtil.getSessionFactory().openSession();
      transacao = sessao.beginTransaction();
      sessao.save(areaLazer);
      transacao.commit();
    } catch (Exception e) {
      e.printStackTrace();
      transacao.rollback();
    } finally {
      sessao.close();
    }
  }

  public void deletar(AreaLazer areaLazer) {
    try {
      sessao = HibernateUtil.getSessionFactory().openSession();
      transacao = sessao.beginTransaction();
      sessao.delete(areaLazer);
      transacao.commit();
    } catch (Exception e) {
      transacao.rollback();
      throw e;
    } finally {
      sessao.close();
    }
  }

  public void atualizar(AreaLazer areaLazer) {
    try {
      sessao = HibernateUtil.getSessionFactory().openSession();
      transacao = sessao.beginTransaction();
      sessao.update(areaLazer);
      transacao.commit();
    } catch (Exception e) {
      e.printStackTrace();
      transacao.rollback();
    } finally {
      sessao.close();
    }
  }

  public List<AreaLazer> getLista(String filtro) {
    Criterion filtroNome;
    sessao = HibernateUtil.getSessionFactory().openSession();
    transacao = sessao.beginTransaction();
    Criteria criteria = sessao.createCriteria(AreaLazer.class);
    filtroNome = Restrictions.like("nome", filtro + "%");
    criteria.add(filtroNome);
    criteria.add(Restrictions.eq("ativo", true));
    this.lista = criteria.list();
    return this.lista;
  }
}
