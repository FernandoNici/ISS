package br.com.condominio.controller;

import br.com.condominio.model.Usuario;
import br.com.condominio.dao.UsuarioDAO;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class LoginController{

    public String username;
    public String senha;
    private List<Usuario> lista;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private UsuarioController usuarioController = new UsuarioController();
    private String filtro;

    public LoginController() {
        setFiltro("");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Usuario> listaUsuarios(){
        lista = usuarioDAO.getLista(filtro);
        return this.lista;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }
    
    public String ValidarLogin() {
        setFiltro("");
        this.lista = listaUsuarios();

        for (Usuario u : this.lista){
            if(u.getLogin().equals(this.username) && u.getSenha().equals(this.senha)){
                usuarioController.setUsuario(u);
                return "Index?faces-redirect=true";
            }
        }

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login", "Senha incorreta para este usuário! Verifique.");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        return "Login?faces-redirect=true";
    }
        
}
