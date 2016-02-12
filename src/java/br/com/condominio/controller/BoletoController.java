package br.com.condominio.controller;

import br.com.condominio.interfaceBoleto.Boleto;
import br.com.condominio.interfaceBoleto.BoletoBuilder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Massao
 */
@ManagedBean
@RequestScoped
public class BoletoController {

  private BoletoBuilder builder;
  private Boleto boleto;
  
  public BoletoController(Boleto boleto){
      this.boleto = boleto;
  }
  
  public void gerarBoleto() throws JRException, FileNotFoundException{
      
      //ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
      //ServletContext context = (ServletContext) externalContext.getContext();
      Map<String, Object> parametros = new HashMap<>();
      JasperReport report = JasperCompileManager.compileReport("boletos/boleto1.jrxml");
      parametros.put("logo", boleto.getLogo());
      JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(Arrays.asList(boleto)));
      JasperExportManager.exportReportToPdfFile(print, "boletos/boleto.pdf");
  }
  
}

