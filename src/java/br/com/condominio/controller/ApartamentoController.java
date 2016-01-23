package br.com.condominio.controller;

import br.com.condominio.model.Apartamento;
import br.com.condominio.dao.ApartamentoDAO;
import br.com.condominio.utils.JSFMessageUtil;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Massao
 */
@ManagedBean
@RequestScoped
public class ApartamentoController implements Serializable {

  private Apartamento apartamento = new Apartamento();
  private ApartamentoDAO apartamentoDAO = new ApartamentoDAO();
  public String filtro;
  private List<Apartamento> lista;

  public ApartamentoController() {
    setFiltro("");
  }

  public String manutencaoApartamento() {
    System.out.println(apartamento);
    if ((apartamento.getId() == 0)) {
      adicionarApartamento();
    } else {
      atualizarApartamento();
    }

    return "ConsultaApartamento?faces-redirect=true";
  }

  public String adicionarApartamento() {
    apartamentoDAO.adicionar(apartamento);
    return "ConsultaApartament?faces-redirect=trueo";
  }

  public String deletarApartamento() {
    return removerEntidade(apartamento);
  }

  public String atualizarApartamento() {
    apartamentoDAO.atualizar(apartamento);
    return "ConsultaApartamento?faces-redirect=true";
  }

  public String novoApartamento() {
    apartamento.setBloco("");
    apartamento.setDataCadastro(Calendar.getInstance().getTime());
    apartamento.setNumero("");
    return "CadastroApartamento?faces-redirect=true";
  }

  public List<Apartamento> listaApartamento() {
    lista = apartamentoDAO.getLista(filtro);
    return this.lista;
  }

  public String carregarEntidade(Apartamento ap) {
    this.apartamento = ap;
    return "CadastroApartamento";
  }

  public String removerEntidade(Apartamento ap) {
    try {
      apartamentoDAO.deletar(ap);
      return "ConsultaApartamento?faces-redirect=true";
    } catch (Exception e) {
      JSFMessageUtil.addMessageError(null, e.getCause().getMessage());
      return "";
    }
  }

  public String getFiltro() {
    return filtro;
  }

  public void setFiltro(String filtro) {
    this.filtro = filtro;
  }

  public Apartamento getApartamento() {
    return apartamento;
  }

  public void setApartamento(Apartamento apartamento) {
    this.apartamento = apartamento;
  }

  public boolean existeApartamento() {
    for (Apartamento ap : this.listaApartamento()) {
      if (ap.getBloco().equals(apartamento.getBloco()) && ap.getNumero().equals(apartamento.getNumero())) {
        apartamento = ap;
        return true;
      }
    }
    return false;
  }

}
