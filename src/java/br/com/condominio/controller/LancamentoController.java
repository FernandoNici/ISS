package br.com.condominio.controller;

import br.com.condominio.model.Lancamento;
import br.com.condominio.dao.LancamentoDAO;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class LancamentoController {
    private Lancamento lancamento = new Lancamento();
    private LancamentoDAO lancamentoDao = new LancamentoDAO();
    public String filtro;
    public String pago;
    private List<Lancamento> lista;
    
    public LancamentoController() {
        setFiltro("");        
        setPago("Ambos");
    }
    
    public boolean DataLancValidator(Date data){
        Calendar hoje = Calendar.getInstance();
        hoje.set(hoje.HOUR_OF_DAY,0);
        hoje.set(hoje.MINUTE,0);
        hoje.set(hoje.SECOND,0);
        hoje.set(hoje.MILLISECOND,0);
        
        if(data.compareTo(hoje.getTime()) < 0){
            lancamento.setVencimento(null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Vencimento Deve Ser Maior ou igual a hoje", "Informe um novo vencimento"));
            return false;
        }
        return true;
    }
        
    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String manutencaoLancamento(){
        if(!DataLancValidator(lancamento.getVencimento())) return "LancamentoManual";
        lancamentoDao.salvar(lancamento);
        return "GerenciamentoDespesas?faces-redirect=true";
    }
    public String RemoverLancamento(){
        lancamentoDao.deletar(lancamento);
        return "GerenciamentoDespesas?faces-redirect=true";
    }
    
    public String EditaLancamento(){
        lancamentoDao.atualizar(lancamento);
        return "GerenciamentoDespesas?faces-redirect=true";
    }
    
    public List<Lancamento> listaLancamentos(){
        lista = lancamentoDao.getLista(filtro,pago);
        return this.lista;
    }
    
    public String novoLancamento(){
        lancamento.setDescricao(null);
        lancamento.setValor(0);
        lancamento.setVencimento(null);
        return "LancamentoManual?faces-redirect=true";
    }
    
    public String pagarLancamento(Lancamento lancamento){
        lancamento.setPago(true);
        lancamentoDao.atualizar(lancamento);
        
        return "GerenciamentoDespesas?faces-redirect=true";
    }
    
     public String carregarEntidade(Lancamento lancamento){
        this.lancamento = lancamento;
        return "CadastroLancamento";
    }
     public String excluirEntidade(Lancamento lancamento){
        lancamentoDao.deletar(lancamento);
        return "GerenciamentoDespesas?faces-redirect=true";
    }
     
    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }
    
    public String fechamentoMensal(){
      return "FechamentoMensal?faces-redirect=true";
    }
}
