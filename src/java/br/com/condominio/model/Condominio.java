package br.com.condominio.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CONDOMINIO")
public class Condominio implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    private int andares;
    private int aptosPorAndar;
    private String cep;
    private String Municipio;
    private String endereco;
    private String bairro;
    
    private boolean ativo;

    public Condominio() {
        super();
        this.ativo = true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
       
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAndares() {
        return andares;
    }

    public void setAndares(int andares) {
        this.andares = andares;
    }

    public int getAptosPorAndar() {
        return aptosPorAndar;
    }

    public void setAptosPorAndar(int aptosPorAndar) {
        this.aptosPorAndar = aptosPorAndar;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Condominio{" + "id=" + id + ", nome=" + nome + ", andares=" + andares + ", aptosPorAndar=" + aptosPorAndar + ", cep=" + cep + ", Municipio=" + Municipio + ", endereco=" + endereco + ", bairro=" + bairro + ", ativo=" + ativo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 71 * hash + Objects.hashCode(this.nome);
        hash = 71 * hash + this.andares;
        hash = 71 * hash + this.aptosPorAndar;
        hash = 71 * hash + Objects.hashCode(this.cep);
        hash = 71 * hash + Objects.hashCode(this.Municipio);
        hash = 71 * hash + Objects.hashCode(this.endereco);
        hash = 71 * hash + Objects.hashCode(this.bairro);
//        hash = 71 * hash + Objects.hashCode(this.sindico);
        hash = 71 * hash + (this.ativo ? 1 : 0);
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
        final Condominio other = (Condominio) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (this.andares != other.andares) {
            return false;
        }
        if (this.aptosPorAndar != other.aptosPorAndar) {
            return false;
        }
        if (!Objects.equals(this.cep, other.cep)) {
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
//        if (!Objects.equals(this.sindico, other.sindico)) {
//            return false;
//        }
        return true;
    }
    
    
    
}
