package br.com.condominio.dao;

import br.com.condominio.model.Usuario;
import br.com.condominio.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class UsuarioDAO {
    private Session sessao;
    private Transaction transacao;
    private List<Usuario> lista;

    public UsuarioDAO() {
    }
    
    public void salvar(Usuario user){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            sessao.save( user );
            transacao.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{ sessao.close(); }
    }
    
            
    public void deletar(Usuario user){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            sessao.update( user );
            transacao.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{ sessao.close(); }
    }
    
    public void atualizar(Usuario user){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
        
            sessao.update( user);
            transacao.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{ sessao.close();}
    }

    public List<Usuario> getLista(String filtro) {
        Criterion filtroNome;
        sessao = HibernateUtil.getSessionFactory().openSession();
        transacao = sessao.beginTransaction();
        Criteria criteria = sessao.createCriteria(Usuario.class);
        filtroNome = Restrictions.like("login","%"+filtro+"%");
        criteria.add(filtroNome);
        this.lista = criteria.list();
        return lista;
    }
}
