package br.com.condominio.dao;

import br.com.condominio.model.Sindico;
import br.com.condominio.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class SindicoDAO {
    private Session sessao;
    private Transaction transacao;
    private List<Sindico> lista;

    public SindicoDAO() {
        super();
    }
            
    public void salvar(Sindico sindico){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
        
            sessao.save( sindico );
            transacao.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{ sessao.close();}
    }

    public void deletar(Sindico sindico){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            sindico.setAtivo(false);
            sessao.update( sindico );
            transacao.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{ sessao.close(); }
    }
    
    public void atualizar(Sindico sindico){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
        
            sessao.update( sindico );
            transacao.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{ sessao.close();}
    }

    public List<Sindico> getLista(String filtro,String ativ) {
        Criterion filtroNome;
        sessao = HibernateUtil.getSessionFactory().openSession();
        transacao = sessao.beginTransaction();
        Criteria criteria = sessao.createCriteria(Sindico.class);
        filtroNome = Restrictions.like("nome","%"+filtro+"%");
        criteria.add(filtroNome);
        ativ = ativ.toLowerCase();
        if(!ativ.contains("ambos")) criteria.add(Restrictions.eq("ativo",ativ.contains("true")));
        this.lista = criteria.list();
        return lista;
    }
}

