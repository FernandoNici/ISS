package br.com.condominio.model;

import java.io.Serializable;
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
    
    @OneToOne
    private Sindico sindico;
    
    private boolean ativo;

    public Condominio(String nome, int andares, int aptosPorAndar, String cep, String Municipio, String endereco, String bairro, Sindico sindico) {
        this.nome = nome;
        this.andares = andares;
        this.aptosPorAndar = aptosPorAndar;
        this.cep = cep;
        this.Municipio = Municipio;
        this.endereco = endereco;
        this.bairro = bairro;
        this.sindico = sindico;
        this.ativo = true;
    }

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

    public Sindico getSindico() {
        return sindico;
    }

    public void setSindico(Sindico sindico) {
        this.sindico = sindico;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Condominio{" + "id=" + id + ", nome=" + nome + ", andares=" + andares + ", aptosPorAndar=" + aptosPorAndar + ", cep=" + cep + ", Municipio=" + Municipio + ", endereco=" + endereco + ", bairro=" + bairro + ", sindico=" + sindico + ", ativo=" + ativo + '}';
    }
    
}
