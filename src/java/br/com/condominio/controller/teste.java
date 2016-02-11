/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.condominio.controller;

import br.com.condominio.interfaceBoleto.Boleto;
import br.com.condominio.interfaceBoleto.BoletoBuilder;
import br.com.condominio.model.boleto.BoletoBBBuilder;
import java.io.FileNotFoundException;
import java.util.Calendar;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Nando
 */
public class teste {
    public static void main(String[] args)throws JRException, FileNotFoundException{
        BoletoBuilder boletoBuilder = new BoletoBBBuilder();
        boletoBuilder.adicionarCedente("Condomínio UEM")
            .adicionarNossoNumero("123456")
            .adicionarSacado("Fernando")
            .adicionarValor(155)
            .adicionarVencimento(Calendar.getInstance())
            .adicionarValorCobrado(156)
            .adicionarDataDocumento(Calendar.getInstance())
            .adicionarDocumentoSacado("123.123.123-33")
            .adicionarLogo()
            .adicionarCodigoDeBarras()
            .adicionarLinhaDigitavel();
                    
                
        Boleto boleto = boletoBuilder.build();
        BoletoController boletoController = new BoletoController(boleto);

        boletoController.gerarBoleto();
    }
}
