package br.com.condominio.model;

import br.com.condominio.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Nando
 */
public class CondominoDAO {
    private Session sessao;
    private Transaction transacao;
    private List<Condomino> lista;
    
    public CondominoDAO() {
    }
    
    public void salvar(Condomino c){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
        
            sessao.save( c );
            transacao.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{ sessao.close(); }
    }

    public void deletar(Condomino c){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
        
            sessao.delete( c );
            transacao.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{ sessao.close(); }
    }
    
    public void atualizar(Condomino c){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
        
            sessao.update( c );
            transacao.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{ sessao.close(); }
    }

    public List<Condomino> getLista(String filtro) {
        Criterion filtroNome;
        sessao = HibernateUtil.getSessionFactory().openSession();
        transacao = sessao.beginTransaction();
        Criteria criteria = sessao.createCriteria(Condomino.class);
        filtroNome = Restrictions.like("nome",filtro+"%");
        criteria.add(filtroNome);
        this.lista = criteria.list();
        return lista;
    }
    
    
}
