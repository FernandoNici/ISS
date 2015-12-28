package br.com.condominio.dao;

import br.com.condominio.model.Funcionario;
import br.com.condominio.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class FuncionarioDAO {
    private Session sessao;
    private Transaction transacao;
    private List<Funcionario> lista;

    public FuncionarioDAO() {
        super();
    }
            
    public void salvar(Funcionario func){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            sessao.save( func );
            transacao.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{ sessao.close();}
    }

    public void deletar(Funcionario func){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            func.setAtivo(false);
            sessao.update( func );
            transacao.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{ sessao.close(); }
    }
    
    public void atualizar(Funcionario func){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
        
            sessao.update( func );
            transacao.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{ sessao.close();}
    }

    public List<Funcionario> getLista(String filtro,String ativ) {
        Criterion filtroNome;
        sessao = HibernateUtil.getSessionFactory().openSession();
        transacao = sessao.beginTransaction();
        Criteria criteria = sessao.createCriteria(Funcionario.class);
        filtroNome = Restrictions.like("nome","%"+filtro+"%");
        criteria.add(filtroNome);
        ativ = ativ.toLowerCase();
        if(!ativ.contains("ambos")) criteria.add(Restrictions.eq("ativo",ativ.contains("true")));
        this.lista = criteria.list();
        return lista;
    }
}

