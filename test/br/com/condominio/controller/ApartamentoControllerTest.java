package br.com.condominio.controller;

import br.com.condominio.model.Apartamento;
import br.com.condominio.dao.CondominioDAO;
import br.com.condominio.dao.CondominoDAO;
import br.com.condominio.model.Condominio;
import br.com.condominio.model.Condomino;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Massao
 */
public class ApartamentoControllerTest {

  private final SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
  private static final String URL_CONSULTA = "ConsultaApartamento?faces-redirect=true";

  public ApartamentoControllerTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
    System.out.println("Removendo Condômino de teste...");
    removerCondominoTeste();
    System.out.println("Condômino de teste removido...");
    System.out.println("Removendo Condomínio de teste...");
    removerCondominioTeste();
    System.out.println("Condominio de teste removido.");
  }

  @Test
  public void testeAdicionarApartamento_Erro_DataInvalida() throws ParseException {
    System.out.println("adicionarApartamento_Erro_DataInvalida");
    System.out.println("Criando novo apartamento...");
    Apartamento apartamento = new Apartamento();
    apartamento.setAtivo(true);
    apartamento.setBloco("BL99");
    apartamento.setCondominio(null);
    apartamento.setCondomino(null);
    apartamento.setDataCadastro(formater.parse("123"));
    apartamento.setId(Long.parseLong("1"));
    apartamento.setNumero("10000");
    System.out.println("Novo apartamento criado...");
    System.out.println("Instanciando controlador...");
    ApartamentoController instance = new ApartamentoController();
    System.out.println("Controlador instanciado...");
    instance.setApartamento(apartamento);
    System.out.println("Persistindo novo apartamento...");
    String result = instance.manutencaoApartamento();
    System.out.println("Novo apartamento persistido...");
    System.out.println("Removendo apartamento persistido...");
    instance.removerEntidade(apartamento);
    System.out.println("Apartamento removido.");
    assertEquals(URL_CONSULTA, result);
  }

  @Test
  public void testeAdicionarApartamento_OK_SemCondominio_SemCondomino() {
    System.out.println("adicionarApartamento_OK_SemCondominio_SemCondomino");
    System.out.println("Criando novo apartamento...");
    Apartamento apartamento = new Apartamento();
    apartamento.setAtivo(true);
    apartamento.setBloco("BL99");
    apartamento.setCondominio(null);
    apartamento.setCondomino(null);
    apartamento.setDataCadastro(new Date());
    apartamento.setNumero("100");
    System.out.println("Novo apartamento criado...");
    System.out.println("Instanciando controlador...");
    ApartamentoController instance = new ApartamentoController();
    System.out.println("Controlador instanciado...");
    instance.setApartamento(apartamento);
    System.out.println("Persistindo novo apartamento...");
    String result = instance.manutencaoApartamento();
    System.out.println("Novo apartamento persistido...");
    System.out.println("Removendo apartamento persistido...");
    instance.removerEntidade(apartamento);
    System.out.println("Apartamento removido.");
    assertEquals(URL_CONSULTA, result);
  }

  @Test
  public void testeAdicionarApartamento_OK_ComCondominio_ComCondomino() {    
    System.out.println("adicionarApartamento_OK_ComCondominio_ComCondomino");   
    System.out.println("Criando novo apartamento..."); 
    Apartamento apartamento = new Apartamento();
    apartamento.setAtivo(true);
    apartamento.setBloco("BL01");
    apartamento.setCondominio(getCondominio());
    apartamento.setCondomino(getCondomino());
    apartamento.setDataCadastro(new Date());
    apartamento.setNumero("101");
    System.out.println("Novo apartamento criado...");
    System.out.println("Instanciando controlador...");
    ApartamentoController instance = new ApartamentoController();
    System.out.println("Controlador instanciado...");
    instance.setApartamento(apartamento);
    System.out.println("Persistindo novo apartamento...");
    String result = instance.manutencaoApartamento();
    System.out.println("Novo apartamento persistido...");
    System.out.println("Removendo apartamento persistido...");
    instance.removerEntidade(apartamento);
    System.out.println("Apartamento removido.");
    assertEquals(URL_CONSULTA, result);
  }

  private Condominio getCondominio(){
    CondominioDAO condominioDAO = new CondominioDAO();
    Condominio condominio = new Condominio();
    if (condominioDAO.getLista("condominio_teste", "ativo").isEmpty()) {
    condominio.setAndares(1);
    condominio.setAptosPorAndar(4);
    condominio.setAtivo(true);
    condominio.setBairro("Centro");
    condominio.setCep("99999-999");
    condominio.setEndereco("Av. Central");
    condominio.setMunicipio("Maringá");
    condominio.setNome("condominio_teste");
    condominioDAO.salvar(condominio);
    return condominio;
    } else{
      return condominioDAO.getLista("condominio_teste", "ativo").get(0);
    }    
  }

  private Condomino getCondomino(){
    CondominoDAO condominoDAO = new CondominoDAO();
    Condomino condomino = new Condomino();
    if (condominoDAO.getLista("Massao").isEmpty()) {
    condomino.setApartamento(null);
    condomino.setAtivo(true);
    condomino.setCPF("72586714202");
    condomino.setCondominio(getCondominio());
    condomino.setNome("Massao");
    condomino.setRG("111111111");
    condomino.setSobreNome("Itikawa");
    condomino.setTelefone("4444444444");
    condominoDAO.salvar(condomino);
    return condomino;
    } else{
      return condominoDAO.getLista("Massao").get(0);
    }    
  }
  
  private void removerCondominoTeste(){
    CondominoDAO condominoDAO = new CondominoDAO();
    if (!condominoDAO.getLista("Massao").isEmpty()) {
      Condomino condomino = condominoDAO.getLista("Massao").get(0);
      condominoDAO.deletar(condomino);
    }    
  }
  
  private void removerCondominioTeste(){
    CondominioDAO condominioDAO = new CondominioDAO();
    if (!condominioDAO.getLista("condominio_teste", "ativo").isEmpty()) {
      Condominio condominio = condominioDAO.getLista("condominio_teste", "ativo").get(0);
      condominioDAO.deletar(condominio);
    }    
  }
}
