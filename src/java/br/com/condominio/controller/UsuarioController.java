package br.com.condominio.controller;

import br.com.condominio.model.Usuario;
import br.com.condominio.model.UsuarioDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

public class UsuarioController {
    private UsuarioDAO usuarioDao;
    private String filtro;
    private List<Usuario> lista;
    
    public UsuarioController() {
        setFiltro("");
    }
    
    public String AdicionarUsuario(Usuario usuario){        
        usuarioDao.salvar(usuario);
        return "consulta_usuario?faces-redirect=true";
    }
    
    public String RemoverUsuario(Usuario usuario){
        usuarioDao.deletar(usuario);
        return "consulta_usuario";
    }
    public String EditaUsuario(Usuario usuario){
        usuarioDao.atualizar(usuario);
        return "consulta_usuario";
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
    
}