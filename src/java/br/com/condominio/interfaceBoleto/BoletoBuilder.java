package br.com.condominio.interfaceBoleto;
import br.com.condominio.model.boleto.BoletoBBBuilder;
import java.io.FileInputStream;
import java.util.Calendar;
/**
 *
 * @author Nando
 */
public interface BoletoBuilder {
	BoletoBuilder adicionarSacado(String sacado);
	BoletoBuilder adicionarCedente(String cedente);
	BoletoBuilder adicionarValor(double valor);
	BoletoBuilder adicionarVencimento(Calendar vencimento);
        BoletoBuilder adicionarNossoNumero(String nossoNumero);
        BoletoBuilder adicionarAceite(String aceite);
        
        BoletoBuilder adicionarNumeroDoDocumento(String numeroDocumento);
        BoletoBuilder adicionarLocalPagamento(String localPagamento);
        BoletoBuilder adicionarCarteira(String tipoCarteira);
        BoletoBuilder adicionarQuantidadeMoeda(String qtdeMoeda);
        BoletoBuilder adicionarEspecieMoeda(String especieMoeda);
        BoletoBuilder adicionarInstrucao1(String instrucao1);
        BoletoBuilder adicionarInstrucao2(String instrucao2);
        BoletoBuilder adicionarDocumentoCedente(String documentoCedente);
        BoletoBuilder adicionarValorCobrado(double valorCobrado);
        BoletoBuilder adicionarAgenciaECodigoBeneficiario(String agenciaECodigoBeneficiario);
        BoletoBuilder adicionarNumeroFormatadoComDigito(String numeroFormatadoComDigito);
        BoletoBuilder adicionarDataDocumento(Calendar dataDocumento);
        BoletoBuilder adicionarEspecieDocumento(String especieDocumento);
        BoletoBuilder adicionarDocumentoSacado(String documentoSacado);
        BoletoBuilder adicionarNumeroConvenio(String numeroConvenio);
        BoletoBuilder adicionarLogo();
        BoletoBuilder adicionarCodigoDeBarras();
        BoletoBuilder adicionarLinhaDigitavel();
 
              
	Boleto build();
}
