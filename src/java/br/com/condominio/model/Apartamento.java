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

/**
 *
 * @author Massao
 */
@Entity
public class Apartamento implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String bloco;
  private String numero;
  @Temporal(TemporalType.DATE)
  private Date dataCadastro;
  private boolean ativo;

  public Apartamento() {
    setDataCadastro(Calendar.getInstance().getTime());
    setAtivo(true);
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
}
