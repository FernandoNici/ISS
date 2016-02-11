package br.com.condominio.model.boleto;
import br.com.condominio.interfaceBoleto.Boleto;
import java.io.FileInputStream;
import java.util.Calendar;

/**
 *
 * @author Nando
 */
public class BoletoBB implements Boleto{
    private String sacado;
    private String cedente;
    private double valor;
    private double valorCobrado;
    private Calendar vencimento;
    private String nossoNumero;
    private String numeroDoDocumento;
    private String localPagamento;
    private String carteira;
    private String quantidadeMoeda;
    private String especieMoeda;
    private String especieDocumento;
    private String instrucao1;
    private String instrucao2;
    private String aceite;
    private Calendar dataProcessamento;
    private Calendar dataDocumento;
    private double valorDesconto;
    private String linhaDigitavel;
    private String codigoDeBarras;
    private String documentoCedente;
    private String agenciaECodigoBeneficiario;
    private String numeroFormatadoComDigito;
    private String documentoSacado;
    private FileInputStream logo;
    private String numeroConvenio;
    
    @Override
    public String getSacado() {
        return sacado;
    }

    public void setSacado(String sacado) {
        this.sacado = sacado;
    }

    @Override
    public String getCedente() {
        return cedente;
    }

    public void setCedente(String cedente) {
        this.cedente = cedente;
    }

    @Override
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public Calendar getVencimento() {
        return vencimento;
    }

    public void setVencimento(Calendar vencimento) {
        this.vencimento = vencimento;
    }

    @Override
    public String getNossoNumero() {
        return nossoNumero;
    }

    public void setNossoNumero(String nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    @Override
    public String getNumeroDoDocumento() {
        return String.valueOf(nossoNumero);
    }

    @Override
    public String getLocalPagamento() {
        return localPagamento;
    }

    @Override
    public String getCarteira() {
        return carteira;
    }

    @Override
    public String getQuantidadeMoeda() {
        return quantidadeMoeda;
    }

    @Override
    public String getEspecieMoeda() {
        return especieMoeda;
    }

    @Override
    public String getInstrucao1() {
        return instrucao1;
    }

    @Override
    public String getInstrucao2() {
        return instrucao2;
    }

    public void setNumeroDoDocumento(String numeroDoDocumento) {
        this.numeroDoDocumento = numeroDoDocumento;
    }

    public void setLocalPagamento(String localPagamento) {
        this.localPagamento = localPagamento;
    }

    public void setCarteira(String carteira) {
        this.carteira = carteira;
    }

    public void setQuantidadeMoeda(String quantidadeMoeda) {
        this.quantidadeMoeda = quantidadeMoeda;
    }

    public void setEspecieMoeda(String especieMoeda) {
        this.especieMoeda = especieMoeda;
    }

    public void setInstrucao1(String instrucao1) {
        this.instrucao1 = instrucao1;
    }

    public void setInstrucao2(String instrucao2) {
        this.instrucao2 = instrucao2;
    }

    @Override
    public String getAceite() {
        return aceite;
    }

    public void setAceite(String aceite) {
        this.aceite = aceite;
    }

    @Override
    public Calendar getDataProcessamento() {
        return dataProcessamento;
    }

    public void setDataProcessamento(Calendar dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }

    @Override
    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(float valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    @Override
    public String getLinhaDigitavel() {
        return linhaDigitavel;
    }

    public void setLinhaDigitavel(String linhaDigitavel) {
        this.linhaDigitavel = linhaDigitavel;
    }

    @Override
    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public void setDocumentoCedente(String documentoCedente) {
        this.documentoCedente = documentoCedente;
    }

    @Override
    public String getDocumentoCedente() {
        return documentoCedente;
    }

    public void setValorCobrado(double valorCobrado) {
        this.valorCobrado = valorCobrado;
    }

    @Override
    public double getValorCobrado() {
        return valorCobrado;
    }
    
    @Override
    public String getAgenciaECodigoBeneficiario() {
        return agenciaECodigoBeneficiario;
    }

    public void setAgenciaECodigoBeneficiario(String agenciaECodigoBeneficiario) {
        this.agenciaECodigoBeneficiario = agenciaECodigoBeneficiario;
    }

    @Override
    public String getNumeroFormatadoComDigito() {
        return numeroFormatadoComDigito;
    }

    public void setNumeroFormatadoComDigito(String numeroFormatadoComDigito) {
        this.numeroFormatadoComDigito = numeroFormatadoComDigito;
    }

    @Override
    public Calendar getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(Calendar dataDocumento) {
        this.dataDocumento = dataDocumento;
    }

    @Override
    public String getEspecieDocumento() {
        return especieDocumento;
    }

    public void setEspecieDocumento(String especieDocumento) {
        this.especieDocumento = especieDocumento;
    }

    @Override
    public String getDocumentoSacado() {
        return documentoSacado;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public void setDocumentoSacado(String documentoSacado) {
        this.documentoSacado = documentoSacado;
    }

    public void setLogo(FileInputStream logo) {
        this.logo = logo;
    }

    @Override
    public FileInputStream getLogo() {
        return logo;
    }

    public String getNumeroConvenio() {
        return numeroConvenio;
    }

    public void setNumeroConvenio(String numeroConvenio) {
        this.numeroConvenio = numeroConvenio;
    }
    
    
    
    
}
