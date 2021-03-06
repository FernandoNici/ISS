package br.com.condominio.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 *
 * @author Massao
 */
@Entity
@SQLDelete(sql = " UPDATE APARTAMENTO SET ATIVO = 0 WHERE ID = ? ")
@Where(clause = "ativo = 1")
public class Apartamento implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String bloco;
  private String numero;
  @Temporal(TemporalType.DATE)
  private Date dataCadastro = new Date();
  private boolean ativo = true;
  @OneToOne
  private Condominio condominio;
  @OneToOne
  private Condomino condomino;

  public Condomino getCondomino() {
    return condomino;
  }

  public void setCondomino(Condomino condomino) {
    this.condomino = condomino;
  }

  public Apartamento() {
    setDataCadastro(Calendar.getInstance().getTime());
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getBloco() {
    return bloco;
  }

  public void setBloco(String bloco) {
    this.bloco = bloco;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public Date getDataCadastro() {
    return dataCadastro;
  }

  public void setDataCadastro(Date dataCadastro) {
    this.dataCadastro = dataCadastro;
  }

  public boolean isAtivo() {
    return ativo;
  }

  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
  }

  @Override
  public String toString() {
    return "Apartamento{" + "id=" + id + ", bloco=" + bloco + ", numero=" + numero + ", dataCadastro=" + dataCadastro + ", ativo=" + ativo + '}';
  }

  public Condominio getCondominio() {
    return condominio;
  }

  public void setCondominio(Condominio condominio) {
    this.condominio = condominio;
  }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Apartamento other = (Apartamento) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
  
}
