package br.com.condominio.model;

import java.io.Serializable;
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
public class Apartamento implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String bloco;
  private String numero;
  @OneToOne
  private Condomino condomino;
  @Temporal(TemporalType.DATE)
  private Date dataentrada;

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

  public Condomino getCondomino() {
    return condomino;
  }

  public void setCondomino(Condomino condomino) {
    this.condomino = condomino;
  }

  public Date getDataentrada() {
    return dataentrada;
  }

  public void setDataentrada(Date dataentrada) {
    this.dataentrada = dataentrada;
  }
}
