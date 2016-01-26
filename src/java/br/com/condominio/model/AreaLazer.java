package br.com.condominio.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.SQLDelete;

/**
 *
 * @author Massao
 */
@Entity
@SQLDelete(sql = " UPDATE AREALAZER SET ATIVO = 0 WHERE ID = ? ")
public class AreaLazer implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private boolean ativo = true;
  @Column(length = 50)
  private String nome;
  @Column(length = 250)
  private String descricao;
  private double valor = 0;
  @Temporal(TemporalType.DATE)
  private Date dataCadastro = new Date();
  @OneToOne
  private Condominio condominio;

  public AreaLazer() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public Date getDataCadastro() {
    return dataCadastro;
  }

  public void setDataCadastro(Date dataCadastro) {
    this.dataCadastro = dataCadastro;
  }

  public Condominio getCondominio() {
    return condominio;
  }

  public void setCondominio(Condominio condominio) {
    this.condominio = condominio;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 71 * hash + Objects.hashCode(this.condominio);
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
    final AreaLazer other = (AreaLazer) obj;
    if (!Objects.equals(this.condominio, other.condominio)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "AreaLazer{" + "id=" + id + ", ativo=" + ativo + ", nome=" + nome
            + ", descricao=" + descricao + ", valor=" + valor + "}";
  }
}
