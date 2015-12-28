package br.com.condominio.dao;

import br.com.condominio.model.Condominio;
import br.com.condominio.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class CondominioDAO {
    private Session sessao;
    private Transaction transacao;
    private List<Condominio> lista;

    public CondominioDAO() {
        super();
    }
            
    public void salvar(Condominio cond){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
        
            sessao.save( cond );
            transacao.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{ sessao.close();}
    }

    public void deletar(Condominio cond){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            cond.setAtivo(false);
            sessao.update( cond );
            transacao.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{ sessao.close(); }
    }
    
    public void atualizar(Condominio cond){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
        
            sessao.update( cond );
            transacao.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{ sessao.close();}
    }

    public List<Condominio> getLista(String filtro,String ativ) {
        Criterion filtroNome;
        sessao = HibernateUtil.getSessionFactory().openSession();
        transacao = sessao.beginTransaction();
        Criteria criteria = sessao.createCriteria(Condominio.class);
        filtroNome = Restrictions.like("nome","%"+filtro+"%");
        criteria.add(filtroNome);
        ativ = ativ.toLowerCase();
        if(!ativ.contains("ambos")) criteria.add(Restrictions.eq("ativo",ativ.contains("true")));
        this.lista = criteria.list();
        return lista;
    }
}
