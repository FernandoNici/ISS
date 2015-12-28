package br.com.condominio.controller;

import br.com.condominio.model.Sindico;
import br.com.condominio.dao.SindicoDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean
@RequestScoped
public class SindicoController {

    private Sindico sindico = new Sindico();
    private SindicoDAO sindicoDAO = new SindicoDAO();
    private UsuarioFacade usuface =  new UsuarioFacade(sindico);
    public String filtro;
    public String ativo;
    public Date data;
    private List<Sindico> lista;

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();

        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }

    public SindicoController() {
        setFiltro("");
        setAtivo("true");
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public Date getData() {
        this.data = data;
        return data;
    }

    public void setData(Date data) {
    }

    public String manutencaoSindico() {
        if (sindico.getId() == 0) {
            if (VerificaCpf(sindico.getCpf())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cpf Já Cadastrado", "Contact admin."));
                return "CadastroSindico";
            }
            sindicoDAO.salvar(sindico);
            usuface.CriaUsuarioSindico();
        } else {
            sindicoDAO.atualizar(sindico);
        }
       return "ConsultaSindico?faces-redirect=true";
    }

    public String RemoverSindico() {
        sindicoDAO.deletar(sindico);
        return "ConsultaSindico?faces-redirect=true";
    }

    public boolean VerificaCpf(String cpf) {
        lista = sindicoDAO.getLista("", "true");
        for (Sindico sindico : lista) {
            if (sindico.getCpf().contains(cpf)) {
                return true;
            }
        }
        return false;
    }

    public String EditaSindico() {
        sindicoDAO.atualizar(sindico);
        return "ConsultaSindico?faces-redirect=true";
    }

    public List<Sindico> listaSindicos() {
        lista = sindicoDAO.getLista(filtro, ativo);
        return this.lista;
    }

    public String novoSindico() {
        sindico.setNome(null);
        sindico.setDataNasc(null);
        sindico.setTelefone(null);
        sindico.setCelular(null);
        sindico.setEndereco(null);
        sindico.setBairro(null);
        sindico.setCep(null);
        sindico.setMunicipio(null);
        return "CadastroSindico?faces-redirect=true";
    }

    public String carregarEntidade(Sindico sindico) {
        this.sindico = sindico;
        return "CadastroSindico";
    }

    public String excluirEntidade(Sindico sindico) {
        sindicoDAO.deletar(sindico);
        return "ConsultaSindico?faces-redirect=true";
    }

    public Sindico getSindico() {
        return sindico;
    }

    public void setSindico(Sindico sindico) {
        this.sindico = sindico;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.sindico);
        hash = 19 * hash + Objects.hashCode(this.sindicoDAO);
        hash = 19 * hash + Objects.hashCode(this.filtro);
        hash = 19 * hash + Objects.hashCode(this.ativo);
        hash = 19 * hash + Objects.hashCode(this.lista);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SindicoController other = (SindicoController) obj;
        if (!Objects.equals(this.sindico, other.sindico)) {
            return false;
        }
        if (!Objects.equals(this.sindicoDAO, other.sindicoDAO)) {
            return false;
        }
        if (!Objects.equals(this.filtro, other.filtro)) {
            return false;
        }
        if (!Objects.equals(this.ativo, other.ativo)) {
            return false;
        }
        if (!Objects.equals(this.lista, other.lista)) {
            return false;
        }
        return true;
    }
}
