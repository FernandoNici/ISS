package br.com.condominio.controller;

import br.com.condominio.dao.*;
import br.com.condominio.model.*;
import br.com.condominio.utils.JSFMessageUtil;
import br.com.condominio.utils.Status;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Massao
 */
@ManagedBean
@ViewScoped
public class FechamentoMensalController implements Serializable {

  private static final String URL_FECHAMENTOMENSAL = "FechamentoMensal?faces-redirect=true";
  private final LancamentoDAO lancamentoDAO = new LancamentoDAO();
  private Condominio condominio = new Condominio();
  private List<Lancamento> lancamentosSelecionados;
  private List<Lancamento> lista;
  private Date mesFechamento = new Date();

  private boolean insereLancamentoNoApartamento(Apartamento ap, Lancamento lanc, double valor) {
    try {
      Lancamento novoLancamento = new Lancamento();
      novoLancamento.setAtivo(true);
      novoLancamento.setDebito(false);
      novoLancamento.setPago(false);
      novoLancamento.setDescricao(lanc.getDescricao());
      novoLancamento.setValor(valor);
      novoLancamento.setVencimento(lanc.getVencimento());
      novoLancamento.setIdPai(lanc.getIdLanc());
      novoLancamento.setApartamento(ap);
      lancamentoDAO.salvar(novoLancamento);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  private boolean atualizarIdFechamentoMensalNosLancamentos(List<Lancamento> lancamentos, long idFechamentoMensal) {
    try {
      for (Lancamento lanc : lancamentos) {
        lanc.setIdFechamentoMensal(idFechamentoMensal);
        lanc.setAtivo(false);
        lancamentoDAO.atualizar(lanc);
      }

      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  private boolean ratearNosApartamentos(Lancamento lancamento) {
    boolean concluidoComSucesso = true;
    try {
      List<Apartamento> apartamentos;
      ApartamentoDAO apartamentoDAO = new ApartamentoDAO();
      apartamentos = apartamentoDAO.getListaDoCondominio(condominio);
      if (!apartamentos.isEmpty()) {        
        double valorRateado = ( lancamento.getValor() / apartamentos.size());
        for (Apartamento apartamento : apartamentos) {
          concluidoComSucesso = (insereLancamentoNoApartamento(apartamento, lancamento, valorRateado) && concluidoComSucesso);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      concluidoComSucesso = false;
    }

    return concluidoComSucesso;
  }

  private Lancamento criaLancamentoMensal(List<Lancamento> lancamentos) {
    try {
      double valor = 0;
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      Date vencimento = df.parse("01/01/1950");
      df = new SimpleDateFormat("MM/yyyy");

      for (Lancamento lanc : lancamentos) {
        valor += lanc.getValor();
        if (lanc.getVencimento().after(vencimento)) {
          vencimento = lanc.getVencimento();
        }
      }

      Lancamento novoFechamentoMensal = new Lancamento();
      novoFechamentoMensal.setAtivo(true);
      novoFechamentoMensal.setDebito(false);
      novoFechamentoMensal.setPago(false);
      novoFechamentoMensal.setApartamento(null);
      novoFechamentoMensal.setCondominio(condominio);
      novoFechamentoMensal.setDescricao(String.format("Fechamento mensal %s", df.format(vencimento)));
      novoFechamentoMensal.setValor(valor);
      novoFechamentoMensal.setVencimento(vencimento);
      lancamentoDAO.salvar(novoFechamentoMensal);
      return novoFechamentoMensal;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public String gerarFechamentoMensal(List<Lancamento> lancamentos) {
    try {
      if (lancamentos == null || lancamentos.isEmpty()) {
        JSFMessageUtil.addMessageInfo("Nenhum lançamento selecionado para o fechamento mensal, selecione antes de gerar.");
        return null;
      }
      
      Lancamento fechamentoMensal = criaLancamentoMensal(lancamentos);
      if (fechamentoMensal != null) {
        if (atualizarIdFechamentoMensalNosLancamentos(lancamentos, fechamentoMensal.getIdLanc())) {
          if (ratearNosApartamentos(fechamentoMensal)) {
            return URL_FECHAMENTOMENSAL;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return null;
  }

  public List<Lancamento> listaLancamentos() {
    this.lista = lancamentoDAO.getListaDoCondominio(condominio, Status.VERDADEIRO, mesFechamento);
    return this.lista;
  }

  public List<Lancamento> getLancamentosSelecionados() {
    return lancamentosSelecionados;
  }

  public void setLancamentosSelecionados(List<Lancamento> lancamentosSelecionados) {
    this.lancamentosSelecionados = lancamentosSelecionados;
  }

  public Date getMesFechamento() {
    return mesFechamento;
  }

  public void setMesFechamento(Date mesFechamento) {
    this.mesFechamento = mesFechamento;
  }

  public Condominio getCondominio() {
    return condominio;
  }

  public void setCondominio(Condominio condominio) {
    this.condominio = condominio;
  }

  public void check(SelectEvent event) {

  }
}
