package br.com.condominio.controller;

import br.com.condominio.model.Condomino;
import br.com.condominio.model.Funcionario;
import br.com.condominio.model.Sindico;
import br.com.condominio.model.Usuario;

public class UsuarioFacade{
    private Condomino condomino;
    private Funcionario funcionario;
    private Sindico sindico;
    private UsuarioController usuarioController = new UsuarioController();
    private Usuario usuario = new Usuario();
    
    public UsuarioFacade(Condomino condomino){
       this.condomino = condomino;
    }
    public UsuarioFacade(Funcionario funcionario){
       this.funcionario = funcionario;
    }
    public UsuarioFacade(Sindico sindico){
       this.sindico = sindico;
    }
    
    public void CriaUsuarioCondomino(){
       usuario.setLogin(condomino.getCPF());
       usuario.setSenha("123");
       usuario.setAcesso("Condomino");
       usuarioController.AdicionarUsuario( usuario );
    }
    public void CriaUsuarioSindico(){
       usuario = new Usuario();
       usuario.setLogin(sindico.getCpf());
       usuario.setSenha("123");
       usuario.setAcesso("Sindico");
       usuarioController.AdicionarUsuario( usuario );
    }
    public void CriaUsuarioFuncionario(){
       usuario = new Usuario();
       usuario.setLogin(funcionario.getCpf());
       usuario.setSenha("123");
       usuario.setAcesso("Funcionario");
       usuarioController.AdicionarUsuario( usuario );
    }
}
