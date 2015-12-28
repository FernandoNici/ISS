/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.condominio.controller;

import br.com.condominio.model.Apartamento;
import br.com.condominio.dao.ApartamentoDAO;
import br.com.condominio.model.Condominio;
import br.com.condominio.model.Condomino;
import br.com.condominio.dao.CondominoDAO;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Nando
 */
@ManagedBean
@SessionScoped
public class CondominoController {

    private Condomino condomino = new Condomino();
    private CondominoDAO condominoDAO = new CondominoDAO();
    private UsuarioFacade usuface =  new UsuarioFacade(condomino);
    private String filtro;
    private List<Condomino> lista;
    private List<Condominio> listaCondominios;
    private List<Apartamento> listaApartamentos;
    private ApartamentoDAO apartamentoDAO = new ApartamentoDAO();
    private CondominioController condominioController = new CondominioController();
    
    public CondominoController() {
        condomino.setAtivo(true);
        setFiltro("");
    }

    public String AdicionarCondomino(){
        condominoDAO.salvar(condomino);
        usuface.CriaUsuarioCondomino();
        return "ConsultaCondomino?faces-redirect=true";
    }
    
    public String ConfirmarCondomino(){
        if (condomino.getId() == 0){
            if ( VerificaCPF(condomino.getCPF())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF Já Cadastrado", "Contact admin."));
                return "CadastroCondomino";
            }
            return AdicionarCondomino();
        } else {
            return EditaCondomino();
        }
    }
    
    public String RemoverCondomino(){
        condominoDAO.deletar(condomino);
        return "ConsultaCondomino";
    }   
    public String EditaCondomino(){
        condominoDAO.atualizar(condomino);
        return "ConsultaCondomino";
    }
    
    public List<Condomino> listaCondominos(){
        lista = condominoDAO.getLista(filtro);
        return this.lista;
    }
    
     public List<Apartamento> listaApartamento(){
        this.listaApartamentos = apartamentoDAO.getListaDoCondominio(condomino.getCondominio());
        return this.listaApartamentos;
    }
    
    public String novoCondomino(){
        condomino.setNome(null);
        condomino.setSobreNome(null);        
        condomino.setTelefone(null);
        condomino.setCPF(null);
        condomino.setRG(null);
        condomino.setApartamento(null);
        return "CadastroCondomino";
    }
    
     public String carregarEntidade(Condomino condomino){
        this.condomino = condomino;
        return "CadastroCondomino";
    }
     
    public Condomino getCondomino() {
        return condomino;
    }

    public void setCondomino(Condomino condomino) {
        this.condomino = condomino;
    }

    public String getFiltro() {
        return filtro;
    }
    
    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }
    public List<Condominio> listaCondominios(){
        listaCondominios = condominioController.listaCondominios();
        return this.listaCondominios;
    }
    
     private boolean VerificaCPF(String cpf) {
       lista = condominoDAO.getLista("");
        for (Condomino cond : lista) {
            if (cond.getCPF().contains(cpf)) {
                return true;
            }
        }
        return false;
    }

    public List<Apartamento> getListaApartamentos() {
        return listaApartamentos;
    }
}
