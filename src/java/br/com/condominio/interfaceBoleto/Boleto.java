package br.com.condominio.interfaceBoleto;

import java.io.FileInputStream;
import java.util.Calendar;

/**
 *
 * @author Nando
 */

public interface Boleto {
    String getSacado();
    String getCedente();
    String getNumeroDoDocumento();
    Calendar getVencimento();
    String getNossoNumero();
    String getLocalPagamento();
    String getCarteira();
    String getQuantidadeMoeda();
    String getEspecieMoeda();
    double getValor();
    String getInstrucao1();
    String getInstrucao2();
    String getLinhaDigitavel();
    double getValorDesconto();
    Calendar getDataProcessamento();
    String getAceite();
    String getDocumentoCedente();
    double getValorCobrado();
    String getAgenciaECodigoBeneficiario();
    String getNumeroFormatadoComDigito();
    Calendar getDataDocumento();
    String getEspecieDocumento();
    String getDocumentoSacado();
    FileInputStream getLogo();
    String getCodigoDeBarras();
    
}
