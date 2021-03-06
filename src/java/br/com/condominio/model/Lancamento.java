package br.com.condominio.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

@Entity
@Table(name = "LANCAMENTOS")
@Filters({
  @Filter(name = "lancamentosStatusAtivo", condition = "ativo = :statusAtivo"),
  @Filter(name = "lancamentosStatusBaixa", condition = "pago = :statusBaixa"),
  @Filter(name = "lancamentosTipoDebito", condition = "debito = :tipoDebito")
})
@FilterDefs({
  @FilterDef(name = "lancamentosStatusAtivo", parameters = {
    @ParamDef(name = "statusAtivo", type = "boolean")}),
  @FilterDef(name = "lancamentosStatusBaixa", parameters = {
    @ParamDef(name = "statusBaixa", type = "boolean")}),
  @FilterDef(name = "lancamentosTipoDebito", parameters = {
    @ParamDef(name = "tipoDebito", type = "boolean")})
})
public class Lancamento implements Serializable {

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

  private long idPai;
  private long idFechamentoMensal;

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

  public long getIdPai() {
    return idPai;
  }

  public void setIdPai(long idPai) {
    this.idPai = idPai;
  }

  public long getIdFechamentoMensal() {
    return idFechamentoMensal;
  }

  public void setIdFechamentoMensal(long idFechamentoMensal) {
    this.idFechamentoMensal = idFechamentoMensal;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 17 * hash + (int) (this.idLanc ^ (this.idLanc >>> 32));
    hash = 17 * hash + Objects.hashCode(this.condominio);
    hash = 17 * hash + Objects.hashCode(this.apartamento);
    hash = 17 * hash + Objects.hashCode(this.descricao);
    hash = 17 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
    hash = 17 * hash + (this.debito ? 1 : 0);
    hash = 17 * hash + (this.pago ? 1 : 0);
    hash = 17 * hash + (int) (this.idPai ^ (this.idPai >>> 32));
    hash = 17 * hash + (int) (this.idFechamentoMensal ^ (this.idFechamentoMensal >>> 32));
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
    if (this.idPai != other.idPai) {
      return false;
    }
    if (this.idFechamentoMensal != other.idFechamentoMensal) {
      return false;
    }
    return true;
  }

}
