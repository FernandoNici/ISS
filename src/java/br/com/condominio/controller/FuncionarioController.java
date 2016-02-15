package br.com.condominio.controller;

import br.com.condominio.model.Funcionario;
import br.com.condominio.dao.FuncionarioDAO;
import br.com.condominio.utils.CpfValidator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class FuncionarioController {

    private Funcionario funcionario = new Funcionario();
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private UsuarioFacade usuface = new UsuarioFacade(funcionario);
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

    public String manutencaoFuncionario() {
        if (VerificaCpf(funcionario)) {
            try {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cpf JÃ¡ Cadastrado", "Contact admin."));
                return "CadastroSindico";
            } catch (NullPointerException e) {
                return "Cpf JÃ¡ Cadastrado(manutencaoSindico)";
            }

        }

        if (!CpfValidator.isCPF(funcionario.getCpf())) {
            try {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Por Favor Digite um CPF valido", ""));
                return "CadastroSindico";

            } catch (NullPointerException e) {
                return "Cpf invalido(manutencaoSincico)";
            }
        }

        if (funcionario.getId() == 0) {
            funcionarioDAO.salvar(funcionario);
            usuface.CriaUsuarioFuncionario();
        } else {
            funcionarioDAO.atualizar(funcionario);
        }
        return "ConsultaFuncionario?faces-redirect=true";
    }

    public String RemoverFuncionario() {
        funcionarioDAO.deletar(funcionario);
        return "ConsultaFuncionario?faces-redirect=true";
    }

    public String EditaFuncionario() {
        funcionarioDAO.atualizar(funcionario);
        return "ConsultaFuncionario?faces-redirect=true";
    }

    public List<Funcionario> listaFuncionarios() {
        lista = funcionarioDAO.getLista(filtro, ativo);
        return this.lista;
    }

    public String novoFuncionario() {
        funcionario.setNome(null);
        funcionario.setDataNasc(null);
        funcionario.setTelefone(null);
        funcionario.setCelular(null);
        funcionario.setEndereco(null);
        funcionario.setBairro(null);
        funcionario.setCep(null);
        funcionario.setMunicipio(null);
        return "CadastroFuncionario?faces-redirect=true";
    }

    public String carregarEntidade(Funcionario funcionario) {
        this.funcionario = funcionario;
        return "CadastroFuncionario";
    }

    public String excluirEntidade(Funcionario funcionario) {
        funcionarioDAO.deletar(funcionario);
        return "ConsultaFuncionario?faces-redirect=true";
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

    private boolean VerificaCpf(Funcionario func) {
        lista = funcionarioDAO.getLista("", "true");
        for (Funcionario funcionario : lista) {
            if (funcionario.getCpf().contains(func.getCpf()) && (funcionario.getId() != func.getId())) {
                return true;
            }
        }
        return false;
    }
}
