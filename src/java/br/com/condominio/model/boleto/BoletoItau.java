package br.com.condominio.model.boleto;
import br.com.condominio.interfaceBoleto.Boleto;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Nando
 */
public class BoletoItau implements Boleto{
    private String sacado;
    private String cedente;
    private double valor;
    private String vencimento;
    private String nossoNumero;
    private String numeroDoDocumento;
    private String localPagamento;
    private String cateira;
    private double quantidadeDeMoeda;
    private String especieMoeda;
    private String instrucao1;
    private String instrucao2;
    private String aceite;
    private String dataProcessamento;
    private float valorDesconto;
    private String linhaDigitavel;
    private String codigoDeBarras;
    
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

    public void setVencimento(Calendar vencimento) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.vencimento = sdf.format(vencimento);
    }

    @Override
    public String getNossoNumero() {
        return nossoNumero;
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

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public void setNumeroDoDocumento(String numeroDoDocumento) {
        this.numeroDoDocumento = numeroDoDocumento;
    }

    public void setLocalPagamento(String localPagamento) {
        this.localPagamento = localPagamento;
    }

    public void setCateira(String cateira) {
        this.cateira = cateira;
    }

    public void setQuantidadeDeMoeda(double quantidadeDeMoeda) {
        this.quantidadeDeMoeda = quantidadeDeMoeda;
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

    public String getAceite() {
        return aceite;
    }

    public void setAceite(String aceite) {
        this.aceite = aceite;
    }

    public void setDataProcessamento(String dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }

    public void setValorDesconto(float valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public String getLinhaDigitavel() {
        return linhaDigitavel;
    }

    public void setLinhaDigitavel(String linhaDigitavel) {
        this.linhaDigitavel = linhaDigitavel;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    @Override
    public Calendar getVencimento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Calendar getDataProcessamento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCarteira() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getQuantidadeMoeda() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDocumentoCedente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getValorCobrado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAgenciaECodigoBeneficiario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNumeroFormatadoComDigito() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Calendar getDataDocumento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEspecieDocumento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDocumentoSacado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FileInputStream getLogo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getValorDesconto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
