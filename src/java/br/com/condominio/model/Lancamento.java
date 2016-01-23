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
@Table(name = "LANCAMENTOS")
public class Lancamento implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idLanc;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Condominio condominio;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Apartamento apartamento;
    
    private String descricao;
    private double valor;
    private boolean debito;
    private boolean pago;
    private boolean ativo;
    
    @Temporal(TemporalType.DATE)
    private Date vencimento;
       

    public Lancamento() {
        super();
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

       
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public long getIdLanc() {
        return idLanc;
    }

    public void setIdLanc(long idLanc) {
        this.idLanc = idLanc;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }

    public Apartamento getApartamento() {
        return apartamento;
    }

    public void setApartamento(Apartamento apartamento) {
        this.apartamento = apartamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isDebito() {
        return debito;
    }

    public void setDebito(boolean debito) {
        this.debito = debito;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (int) (this.idLanc ^ (this.idLanc >>> 32));
        hash = 89 * hash + Objects.hashCode(this.condominio);
        hash = 89 * hash + Objects.hashCode(this.apartamento);
        hash = 89 * hash + Objects.hashCode(this.descricao);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 89 * hash + (this.debito ? 1 : 0);
        hash = 89 * hash + (this.pago ? 1 : 0);
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
        final Lancamento other = (Lancamento) obj;
        if (this.idLanc != other.idLanc) {
            return false;
        }
        if (!Objects.equals(this.condominio, other.condominio)) {
            return false;
        }
        if (!Objects.equals(this.apartamento, other.apartamento)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (this.debito != other.debito) {
            return false;
        }
        if (this.pago != other.pago) {
            return false;
        }
        return true;
    }
    
    
    
    

    
    
    
}
