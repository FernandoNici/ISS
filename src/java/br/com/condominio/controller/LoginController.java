package br.com.condominio.controller;

import br.com.condominio.model.Usuario;
import br.com.condominio.model.UsuarioDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class LoginController{

    public String username;
    public String senha;
    private Usuario usuario;
    private List<Usuario> lista;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public LoginController() {
        setUsername("");
        setSenha("");
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
        lista = usuarioDAO.getLista("");
        return this.lista;
    }
    public String ValidarLogin() {
        for (Usuario user : this.listaUsuarios()) {
            if (user.getLogin().equals(username) && user.getSenha().equals(senha)) {
                usuario = user;
                return "index";
            }
        }
        return null;
    }
    
}
