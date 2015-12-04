package br.com.condominio.controller;

import br.com.condominio.model.Funcionario;
import br.com.condominio.model.FuncionarioDAO;
import java.util.List;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class FuncionarioController {
    private Funcionario funcionario = new Funcionario();
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    public String filtro;
    public String ativo;
    private List<Funcionario> lista;
    
    public FuncionarioController() {
        setFiltro("");       
        setAtivo("true");
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }
    
    public String manutencaoFuncionario(){
        if (funcionario.getId() == 0) {
            if (VerificaCpf(funcionario.getCpf())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cpf Já Cadastrado", "Contact admin."));
                return "cadastro_sindico";
            }
            funcionarioDAO.salvar(funcionario);
        }   
        else {
            funcionarioDAO.atualizar(funcionario);
        }
        return "consulta_funcionario?faces-redirect=true";
    }
   
    public String RemoverFuncionario(){
        funcionarioDAO.deletar(funcionario);
        return "consulta_funcionario?faces-redirect=true";
    }
    
    public String EditaFuncionario(){
        funcionarioDAO.atualizar(funcionario);
        return "consulta_funcionario?faces-redirect=true";
    }
    
    public List<Funcionario> listaFuncionarios(){
        lista = funcionarioDAO.getLista(filtro,ativo);
        return this.lista;
    }
    public String novoFuncionario(){
        funcionario.setNome(null);
        funcionario.setDataNasc(null);
        funcionario.setTelefone(null);        
        funcionario.setCelular(null);
        funcionario.setEndereco(null);
        funcionario.setBairro(null);
        funcionario.setCep(null);
        funcionario.setMunicipio(null);
        return "cadastro_funcionario?faces-redirect=true";
    }
    
     public String carregarEntidade(Funcionario funcionario){
        this.funcionario = funcionario;
        return "cadastro_funcionario";
    }
     public String excluirEntidade(Funcionario funcionario){
        funcionarioDAO.deletar(funcionario);
        return "consulta_funcionario?faces-redirect=true";
    }
     
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    private boolean VerificaCpf(String cpf) {
        lista = funcionarioDAO.getLista("", "true");
        for (Funcionario funcionario : lista) {
            if (funcionario.getCpf().contains(cpf)) {
                return true;
            }
        }
        return false;
    }
}
