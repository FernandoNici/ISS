package br.com.condominio.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SINDICO")
@PrimaryKeyJoinColumn(name="id")

public class Sindico extends Usuario{
    private String nome;
    @Temporal(TemporalType.DATE)
    private Date dataNasc;
    private String telefone;
    private String celular;
    private String rg;
    private String cep;
    private String cpf;
    private String Municipio;
    private String endereco;
    private String bairro;
    private boolean ativo;
    
    @OneToOne
    private Condominio condominio;

    public String getCpf() {
        return cpf;
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
    
    
    
}
