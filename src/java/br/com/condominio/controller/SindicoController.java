package br.com.condominio.controller;

import br.com.condominio.model.Sindico;
import br.com.condominio.model.SindicoDAO;
import java.util.List;
import java.util.Objects;
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
        sindico.setLogin(sindico.getCpf());
        sindico.setSenha("123456");
        sindico.setAcesso("ROLE_USER");
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.sindico);
        hash = 19 * hash + Objects.hashCode(this.sindicoDAO);
        hash = 19 * hash + Objects.hashCode(this.filtro);
        hash = 19 * hash + Objects.hashCode(this.ativo);
        hash = 19 * hash + Objects.hashCode(this.lista);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SindicoController other = (SindicoController) obj;
        if (!Objects.equals(this.sindico, other.sindico)) {
            return false;
        }
        if (!Objects.equals(this.sindicoDAO, other.sindicoDAO)) {
            return false;
        }
        if (!Objects.equals(this.filtro, other.filtro)) {
            return false;
        }
        if (!Objects.equals(this.ativo, other.ativo)) {
            return false;
        }
        if (!Objects.equals(this.lista, other.lista)) {
            return false;
        }
        return true;
    }   
}
