package br.com.condominio.controller;

import br.com.condominio.dao.LancamentoDAO;
import br.com.condominio.interfaceBoleto.Boleto;
import br.com.condominio.interfaceBoleto.BoletoBuilder;
import br.com.condominio.model.Apartamento;
import br.com.condominio.model.Condominio;
import br.com.condominio.model.Condomino;
import br.com.condominio.model.Lancamento;
import br.com.condominio.model.Usuario;
import br.com.condominio.model.boleto.BoletoBB;
import br.com.condominio.model.boleto.BoletoBBBuilder;
import br.com.condominio.utils.JSFMessageUtil;
import br.com.condominio.utils.SessaoJSF;
import br.com.condominio.utils.Status;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean
@SessionScoped
public class BoletoController {

  private BoletoBuilder boletoBuilder;
  private Boleto boleto;
  private Date mesReferencia;
  private Lancamento lancamento;
  private LancamentoDAO lancamentoDAO = new LancamentoDAO();
  private Usuario usuario;
  private static final String URL_INDEX = "Index?faces-redirect=true";
  
  private Condominio condominio;
  private Condomino condomino;
  private Apartamento apartamento;
  private List<Lancamento> lista;
  private CondominioController condominioController = new CondominioController();
  private ApartamentoController apartamentoController = new ApartamentoController();
  private CondominoController condominoController = new CondominoController();
  
  public BoletoController(){
      this(new BoletoBB());    
  }
  
  public BoletoController(Boleto boleto){
      this.boleto = boleto;
  }
  
  public String preparaEGeraBoleto() throws JRException{
      this.usuario = (Usuario)SessaoJSF.get("usuario");
      Calendar vencimento = new GregorianCalendar();
      this.condominio = getCondominioUsuario(); 
      lista = lancamentoDAO.getListaDoCondominio(condominio, Status.FALSO, mesReferencia);
      
      if (lista == null || lista.isEmpty()) {
        JSFMessageUtil.addMessageInfo("Nenhum lançamento encontrado.");
        return null;
      }
      for (Lancamento lanca : lista) {
          if (lanca.getApartamento().equals(apartamento)) {
              this.lancamento = lanca;
              break;
          }
      }
      vencimento.setTime(lancamento.getVencimento());

      boletoBuilder = new BoletoBBBuilder();
      boletoBuilder.adicionarCedente(this.lancamento.getCondominio().getNome())
              .adicionarDocumentoCedente("59.611.567/0001-47")
              .adicionarSacado(condomino.getNome())
              .adicionarNossoNumero("123456")
              .adicionarDocumentoSacado(condomino.getCPF())
              .adicionarValor(this.lancamento.getValor())
              .adicionarVencimento(vencimento)
              .adicionarValorCobrado(this.lancamento.getValor())
              .adicionarDataDocumento(Calendar.getInstance())
              .adicionarLogo()
              .adicionarCodigoDeBarras()
              .adicionarLinhaDigitavel();

      this.boleto = boletoBuilder.build();
      gerarBoleto();
      JSFMessageUtil.addMessageInfo("Boleto gerado com sucesso");
      return URL_INDEX;
  }
  
    public void gerarBoleto() throws JRException {
        Map<String, Object> parametros = new HashMap<>();
        JasperReport report = JasperCompileManager.compileReport("boletos/boleto1.jrxml");
        parametros.put("logo", boleto.getLogo());
        JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(Arrays.asList(boleto)));
        JasperExportManager.exportReportToPdfFile(print, "boletos/boleto.pdf");
    }

    public Apartamento getApartamentoUsuario() {
        this.condominio = getCondominioUsuario();
        for (Apartamento ap : apartamentoController.listaApartamento()) {
            if (ap.getCondominio().equals(condominio)) {
                if (ap.getCondomino().getCPF().equals(usuario.getLogin())) {
                    return ap;
                }
            }
        }
        return null;
    }

    public Condominio getCondominioUsuario() {
        this.condomino = getCondominoUsuario();
        System.out.println(this.condomino);
        for (Condominio cond : condominioController.listaCondominios()) {
            if (cond.equals(condomino.getCondominio())) {
                return cond;
            }
        }
        return null;
    }

    public Condomino getCondominoUsuario() {
        for (Condomino cond : condominoController.listaCondominos()) {
            if (cond.getCPF().equals(usuario.getLogin())) {
                return cond;
            }
        }
        return null;
    }

    public Date getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(Date mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

   
}

