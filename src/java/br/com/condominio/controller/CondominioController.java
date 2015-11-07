package br.com.condominio.controller;

import br.com.condominio.model.Condominio;
import br.com.condominio.model.CondominioDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class CondominioController {
    private Condominio condominio = new Condominio();
    private CondominioDAO condominioDAO = new CondominioDAO();
    public String filtro;
    private List<Condominio> lista;
    
    public CondominioController() {
        setFiltro("");
    }

    public String AdicionarCondominio(){
        condominioDAO.salvar(condominio);
        return "consulta_condominio";
    }
    public String RemoverCondominio(){
        condominioDAO.deletar(condominio);
        return "consulta_condominio";
    }
    public String EditaCondominio(){
        condominioDAO.atualizar(condominio);
        return "consulta_condominio";
    }
    
    public List<Condominio> listaCondominios(){
        lista = condominioDAO.getLista(filtro);
        return this.lista;
    }
    public String novoCondominio(){
        /*
        condominio.setId(null);
        condominio.setNome(null);
        condominio.setSobreNome(null);        
        condominio.setTelefone(null);
        condominio.setSenha(null);
        condominio.setLogin(null);
        condominio.setCPF(null);
        condominio.setRG(null);
        condominio.setApartamento(null);
 */
        return "cadastro_condominio";
    }
    
     public String carregarEntidade(Condominio condominio){
        this.condominio = condominio;
        return "cadastro_condominio";
    }
     public String excluirEntidade(Condominio condominio){
        condominioDAO.deletar(condominio);
        return "consulta_condominio";
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
