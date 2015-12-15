package br.com.condominio.controller;

import br.com.condominio.model.Usuario;
import br.com.condominio.model.UsuarioDAO;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.context.RequestContext;

@ManagedBean
@RequestScoped
public class LoginController{

    public String username;
    public String senha;
    private Usuario usuario;
    private List<Usuario> lista;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private String filtro;

    public LoginController() {
       // setUsername("");
       // setSenha("");
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
        //  lista = usuarioDAO.getLista("");
        
        
//        lista = sindicoDAO.getLista("", "true");
//        for (Sindico sindico : lista) {
//            if (sindico.getCpf().contains(cpf)) {
//                return true;
//            }
//        }
//        return false;
        
        for (Usuario u : this.lista){
            if(u.getLogin().equals(this.username) && u.getSenha().equals(this.senha)){
                  return "index";
            }
        }
//      
        
        
//        for (Usuario user : this.listaUsuarios()) {
//            if (user.getLogin().equals(username) && user.getSenha().equals(senha)) {
//                usuario = user;
//                return "index";
//            }
//        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login", "Senha incorreta para este usuário! Verifique.");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        return "login";
    }
        
}
