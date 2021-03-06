package br.com.condominio.controller;

import br.com.condominio.model.Condominio;
import br.com.condominio.dao.CondominioDAO;
import br.com.condominio.utils.JSFMessageUtil;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class CondominioController {

  private Condominio condominio = new Condominio();
  private CondominioDAO condominioDAO = new CondominioDAO();
  public String filtro;
  public String ativo;
  private List<Condominio> lista;

  public CondominioController() {
    setFiltro("");
    setAtivo("true");
  }

  public String getAtivo() {
    return ativo;
  }

  public void setAtivo(String ativo) {
    this.ativo = ativo;
  }

  public String manutencaoCondomino() {
    if (condominio.getId() == 0) {
      condominioDAO.salvar(condominio);
    } else {
      condominioDAO.atualizar(condominio);
    }
    return "ConsultaCondominio?faces-redirect=true";
  }

  public String RemoverCondominio() {
    try {
    condominioDAO.deletar(condominio);
    return "ConsultaCondominio?faces-redirect=true";
    } catch (Exception e) {
      System.out.println("RemoverCondominio");
      JSFMessageUtil.addMessageError(null, e.getCause().getMessage());
      return "";
    }
  }

  public String EditaCondominio() {
    condominioDAO.atualizar(condominio);
    return "ConsultaCondominio?faces-redirect=true";
  }

  public List<Condominio> listaCondominios() {
    lista = condominioDAO.getLista(filtro, ativo);
    return this.lista;
  }

  public String novoCondominio() {
    condominio.setNome(null);
    condominio.setAndares(0);
    condominio.setAptosPorAndar(0);
    condominio.setBairro(null);
    condominio.setCep(null);
    condominio.setMunicipio(null);
    return "CadastroCondominio?faces-redirect=true";
  }

  public String carregarEntidade(Condominio condominio) {
    this.condominio = condominio;
    return "CadastroCondominio";
  }

  public String excluirEntidade(Condominio condominio) {
    try {
      condominioDAO.deletar(condominio);
      return "ConsultaCondominio?faces-redirect=true";
    } catch (Exception e) {
      JSFMessageUtil.addMessageError(null, e.getCause().getMessage());
      return "";
    }
  }

  public Condominio getCondominio() {
    return condominio;
  }

  public void setCondominio(Condominio condominio) {
    this.condominio = condominio;
  }

  public String getFiltro() {
    return filtro;
  }

  public void setFiltro(String filtro) {
    this.filtro = filtro;
  }
}
