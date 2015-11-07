package br.com.condominio.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
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
    private String sindico;

    public Condominio(String nome, int andares, int aptosPorAndar, String cep, String Municipio, String endereco, String bairro, String sindico) {
        this.nome = nome;
        this.andares = andares;
        this.aptosPorAndar = aptosPorAndar;
        this.cep = cep;
        this.Municipio = Municipio;
        this.endereco = endereco;
        this.bairro = bairro;
        this.sindico = sindico;
    }

    public Condominio() {
        super();
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

    public String getSindico() {
        return sindico;
    }

    public void setSindico(String sindico) {
        this.sindico = sindico;
    }

    
    
    

    
    
    
    
    
}
