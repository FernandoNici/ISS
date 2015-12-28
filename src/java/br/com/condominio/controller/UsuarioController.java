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
public class UsuarioController {
    private UsuarioDAO usuarioDao = new UsuarioDAO();
    private String filtro;
    private Usuario usuario;
    private List<Usuario> lista;
    public String username;
    public String senha;
    
    public UsuarioController() {
        setFiltro("");
    }
    
    public String AdicionarUsuario(Usuario usuario){        
        usuarioDao.salvar(usuario);
        return "ConsultaUsuario?faces-redirect=true";
    }
    
    public String RemoverUsuario(Usuario usuario){
        usuarioDao.deletar(usuario);
        return "ConsultaUsuario";
    }
    public String EditaUsuario(Usuario usuario){
        usuarioDao.atualizar(usuario);
        return "ConsultaUsuario";
    }

    public String getFiltro() {
        return filtro;
    }
    
    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }
    public List<Usuario> listaUsuarios(){
        lista = usuarioDao.getLista(filtro);
        return this.lista;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
      public String ValidarLogin() {
        setFiltro("");
        this.lista = listaUsuarios();

        for (Usuario u : this.lista){
            if(u.getLogin().equals(this.username) && u.getSenha().equals(this.senha)){
                this.setUsuario(u);
                return "Index?faces-redirect=true";
            }
        }

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login", "Senha incorreta para este usuário! Verifique.");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        return "Login?faces-redirect=true";
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
      
    
    
}