package br.com.condominio.dao;

import br.com.condominio.model.Ocorrencia;
import br.com.condominio.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class OcorrenciaDAO {
    private Session sessao;
    private Transaction transacao;
    private List<Ocorrencia> lista;

    public OcorrenciaDAO() {
        super();
    }
            
    public void salvar(Ocorrencia ocorrencia){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
        
            sessao.save( ocorrencia );
            transacao.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{ sessao.close();}
    }

    public void deletar(Ocorrencia ocorrencia){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            ocorrencia.setAtivo(false);
            sessao.update( ocorrencia );
            transacao.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{ sessao.close(); }
    }
    
    public void atualizar(Ocorrencia ocorrencia){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
        
            sessao.update( ocorrencia );
            transacao.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{ sessao.close();}
    }
}

