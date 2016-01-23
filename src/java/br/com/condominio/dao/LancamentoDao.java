package br.com.condominio.dao;

import br.com.condominio.model.Lancamento;
import br.com.condominio.utils.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class LancamentoDao {
    private Session sessao;
    private Transaction transacao;
    private List<Lancamento> lista;

    public LancamentoDao() {
        super();
    }
            
    public void salvar(Lancamento lancto){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
        
            sessao.save( lancto );
            transacao.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{ sessao.close();}
    }

    public void deletar(Lancamento lancto){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            lancto.setAtivo(false);
            sessao.update( lancto );
            transacao.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{ sessao.close(); }
    }
    
    public void atualizar(Lancamento lancto){
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
        
            sessao.update( lancto );
            transacao.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{ sessao.close();}
    }

    public List<Lancamento> getLista(String filtro,String pago) {
        Criterion filtroDescricao;
        sessao = HibernateUtil.getSessionFactory().openSession();
        transacao = sessao.beginTransaction();
        Criteria criteria = sessao.createCriteria(Lancamento.class);
        
        filtroDescricao = Restrictions.like("descricao","%"+filtro+"%");
        criteria.add(filtroDescricao);
        //pago = pago.toLowerCase();
        //if(!pago.contains("ambos")) criteria.add(Restrictions.eq("ativo",pago.contains("true")));
        
        this.lista = criteria.list();
        return lista;
    }
}
