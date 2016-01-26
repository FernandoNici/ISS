package br.com.condominio.controller;

import br.com.condominio.dao.AreaLazerDAO;
import br.com.condominio.model.AreaLazer;
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
public class AreaLazerController implements Serializable {

  private static final String URL_CONSULTA = "ConsultaAreaLazer?faces-redirect=true";
  private static final String URL_CADASTRO = "CadastroAreaLazer?faces-redirect=true";
  private AreaLazer areaLazer = new AreaLazer();
  private AreaLazerDAO areaLazerDAO = new AreaLazerDAO();
  private String filtro;
  private List<AreaLazer> lista;

  public AreaLazerController() {
    filtro = "";
  }

  public String getFiltro() {
    return filtro;
  }

  public void setFiltro(String filtro) {
    this.filtro = filtro;
  }

  public AreaLazer getAreaLazer() {
    return areaLazer;
  }

  public void setAreaLazer(AreaLazer areaLazer) {
    this.areaLazer = areaLazer;
  }

  public String novaAreaLazer() {
    areaLazer.setDataCadastro(Calendar.getInstance().getTime());
    return URL_CADASTRO;
  }

  public String manutencaoAreaLazer() {
    if (areaLazer.getId() == 0) {
      return adicionarAreaLazer();
    }
    return atualizarAreaLazer();
  }

  private String adicionarAreaLazer() {
    areaLazerDAO.adicionar(areaLazer);
    return URL_CONSULTA;
  }

  private String atualizarAreaLazer() {
    areaLazerDAO.atualizar(areaLazer);
    return URL_CONSULTA;
  }

  public String carregarEntidade(AreaLazer area) {
    this.areaLazer = area;
    return "CadastroAreaLazer";
  }

  public String removerEntidade(AreaLazer area) {
    try {
      areaLazerDAO.deletar(area);
      return URL_CONSULTA;
    } catch (Exception e) {
      JSFMessageUtil.addMessageError(null, e.getCause().getMessage());
      return "";
    }
  }

  public String deletarAreaLazer() {
    return removerEntidade(this.areaLazer);
  }

  public List<AreaLazer> listaAreaLazer() {
    this.lista = areaLazerDAO.getLista(filtro);
    return this.lista;
  }
}
