package br.com.condominio.controller;

import br.com.condominio.model.Sindico;
import br.com.condominio.dao.SindicoDAO;
import br.com.condominio.model.Condominio;
import br.com.condominio.dao.CondominioDAO;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class sindicoControllerTeste {
    
    private final SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
    private static final String URL_CONSULTA = "ConsultaSindico?faces-redirect=true";
    private static final String URL_CADASTRO = "cadastroSindico";
    
    
    public sindicoControllerTeste() {
        super();
    }
        
    @Before
    public void setUp() throws Exception {
    }
    
    @After
    public void tearDown() {
        
    }
    
    @Test
    public void testManutencaoSindico_cpfJaCadastrado() throws ParseException{
        System.out.println("Teste de sindico com cpf ja cadastradado");
        Sindico sindico = new Sindico();
        sindico.setId(Long.parseLong("1"));
        sindico.setNome("SindicoTeste");
        sindico.setDataNasc(formater.parse("01/01/1991"));
        sindico.setTelefone("(44)3024-9581");
        sindico.setCelular("(44)9985-6057");
        sindico.setRg("10.399.789.5");
        sindico.setCep("87025");
        sindico.setCpf("068.209.029-80");
        sindico.setMunicipio("Maringa");
        sindico.setEndereco("rua terere");
        sindico.setBairro("zona 7");
        sindico.setCondominio(null);
        SindicoController sindicoController = new SindicoController();
        sindicoController.carregarEntidade(sindico);
        String result = sindicoController.manutencaoSindico();
        sindicoController.excluirEntidade(sindico);
        assertEquals(URL_CONSULTA, result);
    }
    
    @Test
    public void testManutencaoSindico_cpfInvalido() throws ParseException{
        System.out.println("Teste de sindico com cpf ja invalido");
        Sindico sindico = new Sindico();
        sindico.setId(Long.parseLong("1"));
        sindico.setNome("SindicoTeste");
        sindico.setDataNasc(formater.parse("01/01/1991"));
        sindico.setTelefone("(44)3024-9581");
        sindico.setCelular("(44)9985-6057");
        sindico.setRg("10.399.789.5");
        sindico.setCep("87025");
        sindico.setCpf("111.111.111-11");
        sindico.setMunicipio("Maringa");
        sindico.setEndereco("rua terere");
        sindico.setBairro("zona 7");
        sindico.setCondominio(null);
        SindicoController sindicoController = new SindicoController();
        sindicoController.carregarEntidade(sindico);
        String result = sindicoController.manutencaoSindico();
        sindicoController.excluirEntidade(sindico);
        assertEquals(URL_CONSULTA, result);
    }
    
    public void testManutencaoSindico_cpf() throws ParseException{
        System.out.println("Teste de sindico");
        Sindico sindico = new Sindico();
        sindico.setId(Long.parseLong("1"));
        sindico.setNome("SindicoTeste");
        sindico.setDataNasc(formater.parse("01/01/1991"));
        sindico.setTelefone("(44)3024-9581");
        sindico.setCelular("(44)9985-6057");
        sindico.setRg("10.399.789.5");
        sindico.setCep("87025");
        sindico.setCpf("080.880.599.17");
        sindico.setMunicipio("Maringa");
        sindico.setEndereco("rua terere");
        sindico.setBairro("zona 7");
        sindico.setCondominio(null);
        SindicoController sindicoController = new SindicoController();
        sindicoController.carregarEntidade(sindico);
        String result = sindicoController.manutencaoSindico();
        sindicoController.excluirEntidade(sindico);
        assertEquals(URL_CONSULTA, result);
    }
}
