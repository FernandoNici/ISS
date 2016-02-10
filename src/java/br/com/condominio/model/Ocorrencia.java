package br.com.condominio.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "OCORRENCIA")
public class Ocorrencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idOcor;

    @OneToOne(cascade = CascadeType.ALL)
    private Condominio condominio;
    private String descricao;
    private boolean ativo;

    @Temporal(TemporalType.DATE)
    private Date data;

    private long idPai;
    private long idFechamentoMensal;

    public Ocorrencia() {
        super();
    }

    public long getIdOcor() {
        return idOcor;
    }

    public void setIdOcor(long idOcor) {
        this.idOcor = idOcor;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public long getIdPai() {
        return idPai;
    }

    public void setIdPai(long idPai) {
        this.idPai = idPai;
    }

    public long getIdFechamentoMensal() {
        return idFechamentoMensal;
    }

    public void setIdFechamentoMensal(long idFechamentoMensal) {
        this.idFechamentoMensal = idFechamentoMensal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (int) (this.idOcor ^ (this.idOcor >>> 32));
        hash = 23 * hash + Objects.hashCode(this.condominio);
        hash = 23 * hash + Objects.hashCode(this.descricao);
        hash = 23 * hash + (this.ativo ? 1 : 0);
        hash = 23 * hash + Objects.hashCode(this.data);
        hash = 23 * hash + (int) (this.idPai ^ (this.idPai >>> 32));
        hash = 23 * hash + (int) (this.idFechamentoMensal ^ (this.idFechamentoMensal >>> 32));
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
        final Ocorrencia other = (Ocorrencia) obj;
        if (this.idOcor != other.idOcor) {
            return false;
        }
        if (!Objects.equals(this.condominio, other.condominio)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (this.ativo != other.ativo) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (this.idPai != other.idPai) {
            return false;
        }
        if (this.idFechamentoMensal != other.idFechamentoMensal) {
            return false;
        }
        return true;
    }
}
