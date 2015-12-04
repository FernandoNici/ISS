/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.condominio.controller;

import br.com.condominio.model.Condomino;
import br.com.condominio.model.CondominoDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Nando
 */
@ManagedBean
@RequestScoped
public class CondominoController {

    private Condomino condomino = new Condomino();
    private CondominoDAO condominoDAO = new CondominoDAO();
    public String filtro;
    private List<Condomino> lista;
    
    public CondominoController() {
        setFiltro("");
    }

    public String AdicionarCondomino(){    
        condominoDAO.salvar(condomino);
        return "consulta_condomino?faces-redirect=true";
    }
    
    public String ConfirmarCondomino(){
        lista = listaCondominos();
        if (lista.contains(condomino)) {
            return EditaCondomino();
        }
        return AdicionarCondomino();
    }
    
    public String RemoverCondomino(){
        condominoDAO.deletar(condomino);
        return "consulta_condomino";
    }
    public String EditaCondomino(){
        condominoDAO.atualizar(condomino);
        return "consulta_condomino";
    }
    
    public List<Condomino> listaCondominos(){
        lista = condominoDAO.getLista(filtro);
        return this.lista;
    }
    public String novoCondomino(){
        condomino.setId(null);
        condomino.setNome(null);
        condomino.setSobreNome(null);        
        condomino.setTelefone(null);
        condomino.setCPF(null);
        condomino.setRG(null);
        condomino.setApartamento(null);
 
        return "cadastro_condomino";
    }
    
     public String carregarEntidade(Condomino condomino){
        this.condomino = condomino;
        return "cadastro_condomino";
    }
     public String excluirEntidade(Condomino condomino){
        condominoDAO.deletar(condomino);
        return "consulta_condomino";
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
    
}
