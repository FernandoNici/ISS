package br.com.condominio.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SINDICO")
public class Sindico  implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String nome;
    @Temporal(TemporalType.DATE)
    private Date dataNasc;
    private String telefone;
    private String celular;
    private String rg;
    private String cep;
    
    @Column(unique = true)
    private String cpf;
    private String Municipio;
    private String endereco;
    private String bairro;
    private boolean ativo;
    
    @OneToOne
    private Condominio condominio;

    public Sindico() {
        super();
        this.ativo = true;
    }
    
        public String getCpf() {
        return cpf;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
 
        
        
    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }
    
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Condominio getCondomino() {
        return condominio;
    }

    public void setCondomino(Condominio condominio) {
        this.condominio = condominio;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(String Municipio) {
        this.Municipio = Municipio;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Override
    public String toString() {
        return "Sindico{" + "nome=" + nome + ", dataNasc=" + dataNasc + ", telefone=" + telefone + ", celular=" + celular + ", rg=" + rg + ", cep=" + cep + ", cpf=" + cpf + ", Municipio=" + Municipio + ", endereco=" + endereco + ", bairro=" + bairro + ", ativo=" + ativo + ", condominio=" + condominio + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nome);
        hash = 97 * hash + Objects.hashCode(this.dataNasc);
        hash = 97 * hash + Objects.hashCode(this.telefone);
        hash = 97 * hash + Objects.hashCode(this.celular);
        hash = 97 * hash + Objects.hashCode(this.rg);
        hash = 97 * hash + Objects.hashCode(this.cep);
        hash = 97 * hash + Objects.hashCode(this.cpf);
        hash = 97 * hash + Objects.hashCode(this.Municipio);
        hash = 97 * hash + Objects.hashCode(this.endereco);
        hash = 97 * hash + Objects.hashCode(this.bairro);
        hash = 97 * hash + (this.ativo ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.condominio);
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
        final Sindico other = (Sindico) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.dataNasc, other.dataNasc)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (!Objects.equals(this.celular, other.celular)) {
            return false;
        }
        if (!Objects.equals(this.rg, other.rg)) {
            return false;
        }
        if (!Objects.equals(this.cep, other.cep)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.Municipio, other.Municipio)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        if (!Objects.equals(this.bairro, other.bairro)) {
            return false;
        }
        if (this.ativo != other.ativo) {
            return false;
        }
        if (!Objects.equals(this.condominio, other.condominio)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
