package br.com.condominio.controller;

import br.com.condominio.model.Apartamento;
import br.com.condominio.model.ApartamentoDAO;
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
    if (apartamento.getId() == 0) {
      adicionarApartamento();
    } else {
      atualizarApartamento();
    }

    return "consulta_apartamento?faces-redirect=true";
  }

  public String adicionarApartamento() {
    apartamentoDAO.adicionar(apartamento);
    return "consulta_apartament?faces-redirect=trueo";
  }

  public String deletarApartamento() {
    apartamentoDAO.deletar(apartamento);
    return "consulta_apartamento?faces-redirect=true";
  }

  public String atualizarApartamento() {
    apartamentoDAO.atualizar(apartamento);
    return "consulta_apartamento?faces-redirect=true";
  }

  public String novoApartamento() {
    apartamento.setBloco("");
    apartamento.setDataCadastro(Calendar.getInstance().getTime());
    apartamento.setNumero("");
    return "cadastro_apartamento?faces-redirect=true";
  }

  public List<Apartamento> listaApartamento() {
    lista = apartamentoDAO.getLista(filtro);
    return this.lista;
  }

  public String carregarEntidade(Apartamento ap) {
    this.apartamento = ap;
    return "cadastro_apartamento";
  }

  public String removerEntidade(Apartamento ap) {
    apartamentoDAO.deletar(ap);
    return "consulta_apartamento?faces-redirect=true";
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

}
