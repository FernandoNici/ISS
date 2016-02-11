package br.com.condominio.model.boleto;

import br.com.condominio.interfaceBoleto.Boleto;
import br.com.condominio.interfaceBoleto.BoletoBuilder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;
/**
 *
 * @author Nando
 */
public class BoletoBBBuilder implements BoletoBuilder{
    private static final String CARTEIRA_16 = "16";
    private static final String NUMERO_BB = "001";
    private static final String ESPECIE_MOEDA = "R$";//REAL codigo 9
    private static final String DIGITO_NUMERO_BB = "9";
    private static final String NUMERO_CONVENIO = "991234";
    private static final String TIPO_MODALIDADE_COBRANCA_CARTEIRA_SEM_REGISTRO = "21";
    private static final String ZERO = "0";
    private static final int DIGITO_VERIFICADOR = 4;
    
    private BoletoBB boleto = new BoletoBB();

    public BoletoBBBuilder() {
        this.adicionarCarteira(CARTEIRA_16)
        .adicionarAgenciaECodigoBeneficiario("001/9113")
        .adicionarNumeroFormatadoComDigito(NUMERO_BB + " - " + DIGITO_NUMERO_BB)
        .adicionarNumeroConvenio(NUMERO_CONVENIO)
        .adicionarEspecieMoeda(ESPECIE_MOEDA)
        .adicionarInstrucao1("1-Não receber após o vencimento")
        .adicionarInstrucao2("")
        .adicionarQuantidadeMoeda("")
        .adicionarAceite("S")
        .adicionarLocalPagamento("Pagável em qualquer banco até o vencimento.")
        .adicionarEspecieDocumento("F");
    }
    
    @Override
    public BoletoBuilder adicionarSacado(String sacado) {
        boleto.setSacado(sacado);
        return this;
    }

    @Override
    public BoletoBuilder adicionarCedente(String cedente) {
        boleto.setCedente(cedente);
        return this;
    }

    @Override
    public BoletoBuilder adicionarValor(double valor) {
        boleto.setValor(valor);
        return this;
    }

    @Override
    public BoletoBuilder adicionarVencimento(Calendar vencimento) {
        boleto.setVencimento(vencimento);
        return this;
    }

    @Override
    public Boleto build() {
        return boleto;
    }

    @Override
    public BoletoBuilder adicionarNossoNumero(String nossoNumero) {
        boleto.setNossoNumero(nossoNumero);
        return this;
    }

    @Override
    public BoletoBuilder adicionarNumeroDoDocumento(String numeroDocumento) {
        boleto.setNumeroDoDocumento(numeroDocumento);
        return this;
    }

    @Override
    public BoletoBuilder adicionarLocalPagamento(String localPagamento) {
        boleto.setLocalPagamento(localPagamento);
        return this;
    }

    @Override
    public BoletoBuilder adicionarCarteira(String tipoCarteira) {
        boleto.setCarteira(tipoCarteira);
        return this;
    }

    @Override
    public BoletoBuilder adicionarQuantidadeMoeda(String qtdeMoeda) {
        boleto.setQuantidadeMoeda(qtdeMoeda);
        return this;
    }

    @Override
    public BoletoBuilder adicionarEspecieMoeda(String especieMoeda) {
        boleto.setEspecieMoeda(especieMoeda);
        return this;
    }

    @Override
    public BoletoBuilder adicionarInstrucao1(String instrucao1) {
        boleto.setInstrucao1(instrucao1);
        return this;
    }

    @Override
    public BoletoBuilder adicionarInstrucao2(String instrucao2) {
        boleto.setInstrucao2(instrucao2);
        return this;
    }

    @Override
    public BoletoBuilder adicionarDocumentoCedente(String documentoCedente) {
        boleto.setDocumentoCedente(documentoCedente);
        return this;
    }

    @Override
    public BoletoBuilder adicionarValorCobrado(double valorCobrado) {
        boleto.setValorCobrado(valorCobrado);
        return this;
    }

    @Override
    public BoletoBuilder adicionarAgenciaECodigoBeneficiario(String agenciaECodigoBeneficiario) {
        boleto.setAgenciaECodigoBeneficiario(agenciaECodigoBeneficiario);
        return this;
    }

    @Override
    public BoletoBuilder adicionarNumeroFormatadoComDigito(String numeroFormatadoComDigito) {
        boleto.setNumeroFormatadoComDigito(numeroFormatadoComDigito);
        return this;
    }

    @Override
    public BoletoBuilder adicionarDataDocumento(Calendar dataDocumento) {
        boleto.setDataDocumento(dataDocumento);
        return this;
    }

    @Override
    public BoletoBuilder adicionarEspecieDocumento(String especieDocumento) {
        boleto.setEspecieDocumento(especieDocumento);
        return this;
    }

    @Override
    public BoletoBuilder adicionarDocumentoSacado(String documentoSacado) {
        boleto.setDocumentoSacado(documentoSacado);
        return this;
    }
    
    @Override
    public BoletoBuilder adicionarAceite(String aceite) {
        boleto.setAceite(aceite);
        return this;
    }
    
    @Override
    public BoletoBuilder adicionarNumeroConvenio(String numeroConvenio) {
        boleto.setNumeroConvenio(numeroConvenio);
        return this;
    }
    
    @Override
    public BoletoBuilder adicionarLinhaDigitavel() {
        StringBuilder linhaDigitavel = new StringBuilder();
        linhaDigitavel.append(boleto.getCodigoDeBarras());
        boleto.setLinhaDigitavel(formataLinhaDigitavel(linhaDigitavel));
        return this;
    }

    @Override
    public BoletoBuilder adicionarLogo() {
        try {
            boleto.setLogo(new FileInputStream("logos/001.png"));
        } catch (FileNotFoundException ex) {
            System.out.println("Aquivo não encontrado");
        }
        return this;
    }

    @Override
    public BoletoBuilder adicionarCodigoDeBarras() {
        StringBuilder codigo = new StringBuilder(44);
        codigo.append(NUMERO_BB);
        codigo.append(DIGITO_NUMERO_BB);
        codigo.append(getFatorVencimento());
        codigo.append(getValorFormatado());
        codigo.append(setZerosEsquerda( boleto.getNumeroConvenio(), 6));
        codigo.append(setZerosEsquerda( boleto.getNossoNumero(), 17));
        codigo.append(TIPO_MODALIDADE_COBRANCA_CARTEIRA_SEM_REGISTRO);
        codigo.insert(4, DIGITO_VERIFICADOR);
            
        boleto.setCodigoDeBarras(codigo.toString());
        return this;
    }
    public String getFatorVencimento() {
        Calendar dataBase = Calendar.getInstance();
        dataBase.set(Calendar.DAY_OF_MONTH, 7);
        dataBase.set(Calendar.MONTH, 10 - 1);
        dataBase.set(Calendar.YEAR, 1997);
        dataBase.set(Calendar.HOUR_OF_DAY, 0);
        dataBase.set(Calendar.MINUTE, 0);
        dataBase.set(Calendar.SECOND, 0);
        dataBase.set(Calendar.MILLISECOND, 0);

        Calendar vencimentoSemHoras = Calendar.getInstance();

        vencimentoSemHoras.set(Calendar.DAY_OF_MONTH, boleto.getVencimento().get(Calendar.DAY_OF_MONTH));
        vencimentoSemHoras.set(Calendar.MONTH, boleto.getVencimento().get(Calendar.MONTH));
        vencimentoSemHoras.set(Calendar.YEAR, boleto.getVencimento().get(Calendar.YEAR));
        vencimentoSemHoras.set(Calendar.HOUR_OF_DAY, 0);
        vencimentoSemHoras.set(Calendar.MINUTE, 0);
        vencimentoSemHoras.set(Calendar.SECOND, 0);
        vencimentoSemHoras.set(Calendar.MILLISECOND, 0);

        long diferencasEmMiliSegundos = vencimentoSemHoras.getTimeInMillis() - dataBase.getTimeInMillis();
        long diferencasEmDias = diferencasEmMiliSegundos / (1000 * 60 * 60 * 24);

        return String.valueOf((int) diferencasEmDias);
    }
    
    public String setZerosEsquerda(String input, int size) {
        StringBuilder sb = new StringBuilder(size);

        for (int i = size - input.length(); i > 0; i--) {
            sb.append(ZERO);
        }
        sb.append(input);
        return sb.toString();
    }
    
    public String getValorFormatado() {
        return String.format("%011.2f", boleto.getValor()).replaceAll("[^0-9]", "");
    }

    public String formataLinhaDigitavel(StringBuilder linhaDigitavel) {
        linhaDigitavel.insert(5, '.');
        linhaDigitavel.insert(11, "  ");
        linhaDigitavel.insert(18, '.');
        linhaDigitavel.insert(25, "  ");
        linhaDigitavel.insert(32, '.');
        linhaDigitavel.insert(39, "  ");
        linhaDigitavel.insert(42, "  ");
        return linhaDigitavel.toString();
    }

    
}
