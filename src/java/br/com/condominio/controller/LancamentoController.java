package br.com.condominio.controller;

import br.com.condominio.model.Lancamento;
import br.com.condominio.dao.LancamentoDAO;
import br.com.condominio.model.Apartamento;
import br.com.condominio.utils.SessaoJSF;
import br.com.condominio.utils.Status;
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
  private Apartamento apartamento = new Apartamento();

  public LancamentoController() {
    setFiltro("");
    setPago("Ambos");    
    apartamento = (Apartamento)SessaoJSF.get("apartamento");
  }

  public boolean dataLancValidator(Date data) {
    Calendar hoje = Calendar.getInstance();
    hoje.set(hoje.HOUR_OF_DAY, 0);
    hoje.set(hoje.MINUTE, 0);
    hoje.set(hoje.SECOND, 0);
    hoje.set(hoje.MILLISECOND, 0);

    if (data.compareTo(hoje.getTime()) < 0) {
      lancamento.setVencimento(null);
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Vencimento Deve Ser Maior ou igual a hoje", "Informe um novo vencimento"));
      return false;
    }
    return true;
  }
  
  public boolean valorLancValidator(Double valor){
      if(valor <= 0.0) return false;
      return true;
  }

  public String getPago() {
    return pago;
  }

  public void setPago(String pago) {
    this.pago = pago;
  }

  public String manutencaoLancamento() {
    if (!dataLancValidator(lancamento.getVencimento())||!valorLancValidator(lancamento.getValor())){
      return "LancamentoManual";
    }
    
    lancamentoDao.salvar(lancamento);
    return "GerenciamentoDespesas?faces-redirect=true";
  }

  public String removerLancamento() {
    lancamentoDao.deletar(lancamento);
    return "GerenciamentoDespesas?faces-redirect=true";
  }

  public String editaLancamento() {
    lancamentoDao.atualizar(lancamento);
    return "GerenciamentoDespesas?faces-redirect=true";
  }

  public List<Lancamento> listaLancamentos() {
    lista = lancamentoDao.getLista(filtro, pago);
    return this.lista;
  }

  public List<Lancamento> listaLancamentos(Apartamento ap) {
    lista = lancamentoDao.getListaDoApartamento(ap, Status.FALSO, Status.NULO);
    return this.lista;
  }

  public String novoLancamento() {
    lancamento.setDescricao(null);
    lancamento.setValor(0);
    lancamento.setVencimento(null);
    return "LancamentoManual?faces-redirect=true";
  }

  public String pagarLancamento(Lancamento lancamento) {
    lancamento.setPago(true);
    lancamentoDao.atualizar(lancamento);
    if (lancamento.getIdPai() > 0) {
      atualizarFechamentoMensal(lancamento);
    }
    return "";
  }

  public String carregarEntidade(Lancamento lancamento) {
    this.lancamento = lancamento;
    return "CadastroLancamento";
  }

  public String excluirEntidade(Lancamento lancamento) {
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

  public String fechamentoMensal() {
    return "FechamentoMensal?faces-redirect=true";
  }

  private void atualizarFechamentoMensal(Lancamento lancto) {
    if (lancamentoDao.getListaLancamentosGeradosFechamentoMensal(lancto, false).isEmpty()) {
      Lancamento fechamentoMensal = lancamentoDao.getLancamento(lancto.getIdPai());
      pagarLancamento(fechamentoMensal);
      List<Lancamento> listaLancamentosDoFechamento;
      listaLancamentosDoFechamento = lancamentoDao.getListaLancamentosDoFechamentoMensal(fechamentoMensal.getIdLanc(), false);
      for (Lancamento lancamento : listaLancamentosDoFechamento){
        pagarLancamento(lancamento);
      }
    }
  }

  public Apartamento getApartamento() {
    return apartamento;
  }

  public void setApartamento(Apartamento apartamento) {
    this.apartamento = apartamento;
  }
}
