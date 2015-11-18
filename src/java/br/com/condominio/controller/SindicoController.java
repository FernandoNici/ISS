package br.com.condominio.controller;

import br.com.condominio.model.Sindico;
import br.com.condominio.model.SindicoDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class SindicoController {
    private Sindico sindico = new Sindico();
    private SindicoDAO sindicoDAO = new SindicoDAO();
    public String filtro;
    public String ativo;
    private List<Sindico> lista;
    
    
    
    public SindicoController() {
        setFiltro("");       
        setAtivo("true");
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }
    
    public String manutencaoSindico(){
        //System.out.println(sindico.)
        //System.out.println(sindico.get      );
        
        //System.out.println("here");
        //System.out.println(sindico.getCondominio());
        
        //if(sindico.getId()==0)
        sindicoDAO.salvar(sindico);
        //else sindicoDAO.atualizar(sindico);
        //return "consulta_sindico?faces-redirect=true";
        return "consulta_sindico?faces-redirect=true";
    }
   
    public String RemoverSindico(){
        sindicoDAO.deletar(sindico);
        return "consulta_sindico?faces-redirect=true";
    }
    
    public String EditaSindico(){
        sindicoDAO.atualizar(sindico);
        return "consulta_sindico?faces-redirect=true";
    }
    
    public List<Sindico> listaSindicos(){
        lista = sindicoDAO.getLista(filtro,ativo);
        return this.lista;
    }
    public String novoSindico(){
        sindico.setNome(null);
        sindico.setDataNasc(null);
        sindico.setTelefone(null);        
        sindico.setCelular(null);
        sindico.setEndereco(null);
        sindico.setBairro(null);
        sindico.setCep(null);
        sindico.setMunicipio(null);
        return "cadastro_sindico?faces-redirect=true";
    }
    
     public String carregarEntidade(Sindico sindico){
        this.sindico = sindico;
        return "cadastro_sindico";
    }
     public String excluirEntidade(Sindico sindico){
        sindicoDAO.deletar(sindico);
        return "consulta_sindico?faces-redirect=true";
    }
     
    public Sindico getSindico() {
        return sindico;
    }

    public void setSindico(Sindico sindico) {
        this.sindico = sindico;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }
}
