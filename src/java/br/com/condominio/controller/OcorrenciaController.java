package br.com.condominio.controller;

import br.com.condominio.dao.OcorrenciaDAO;
import br.com.condominio.model.Ocorrencia;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class OcorrenciaController {
  private Ocorrencia ocorrencia = new Ocorrencia();
  private OcorrenciaDAO ocorrenciaDao = new OcorrenciaDAO();
  public String filtro;
  private List<Ocorrencia> lista;
  

  public OcorrenciaController() {
    setFiltro("");
  }

  public String manutencaoOcorrencia() {
    ocorrenciaDao.salvar(ocorrencia);
    return "CadastroOcorrencia?faces-redirect=true";
  }

  public String removerOcorrencia() {
    ocorrenciaDao.deletar(ocorrencia);
    return "CadastroOcorrencia?faces-redirect=true";
  }

  public String editaOcorrencia() {
    ocorrenciaDao.atualizar(ocorrencia);
    return "CadastroOcorrencia?faces-redirect=true";
  }

  public String novoOcorrencia() {
    ocorrencia.setCondominio(null);
    ocorrencia.setData(null);
    ocorrencia.setDescricao("");
    return "CadastroOcorrencia?faces-redirect=true";
  }

  public String carregarEntidade(Ocorrencia ocorrencia) {
    this.ocorrencia = ocorrencia;
    return "CadastroOcorrencia";
  }

  public String excluirEntidade(Ocorrencia ocorrencia) {
    ocorrenciaDao.deletar(ocorrencia);
    return "CadastroOcorrencia?faces-redirect=true";
  }

  public Ocorrencia getOcorrencia() {
    return ocorrencia;
  }

  public void setOcorrencia(Ocorrencia ocorrencia) {
    this.ocorrencia = ocorrencia;
  }

  public String getFiltro() {
    return filtro;
  }

  public void setFiltro(String filtro) {
    this.filtro = filtro;
  }
}
