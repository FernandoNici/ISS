package br.com.condominio.controller;

import br.com.condominio.dao.CondominioDAO;
import br.com.condominio.model.Condominio;
import br.com.condominio.model.Condomino;
import br.com.condominio.utils.CpfValidator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nando
 */
public class CondominoControllerTest {

  private static final String URL_CONSULTA = "ConsultaCondomino?faces-redirect=true";
  private static final String URL_CADASTRO = "CadastroCondomino";
  
  public CondominoControllerTest() {
  }
  
  @Test
    public void testeAdicionarCondominoComCPFValido() {
        Condomino condomino = new Condomino();

        condomino.setApartamento(null);
        condomino.setAtivo(true);
        condomino.setCPF("010.928.669-30");
        condomino.setCondominio(new Condominio());
        condomino.setNome("nando");
        condomino.setRG("111111111");
        condomino.setSobreNome("nici");
        condomino.setTelefone("4432292141");

        CondominoController controller = new CondominoController();
        controller.setCondomino(condomino);
        String result = controller.AdicionarCondomino();
        controller.RemoverCondomino();
        assertEquals(URL_CONSULTA, result);
    }
    @Test
    public void testeAdicionarCondominoComCPFInvalido() {
        Condomino condomino = new Condomino();
        String result;

        condomino.setApartamento(null);
        condomino.setAtivo(true);
        condomino.setCPF("1");
        condomino.setCondominio(getCondominio());
        condomino.setNome("nando");
        condomino.setRG("111111111");
        condomino.setSobreNome("nici");
        condomino.setTelefone("4432292141");

        CondominoController controller = new CondominoController();
        controller.setCondomino(condomino);
        if(!CpfValidator.isCPF(condomino.getCPF())){
           result = URL_CADASTRO;
        } 
        else{
            result = controller.AdicionarCondomino();
        }
        assertEquals(URL_CONSULTA, result);
        removerCondominioTeste();
    }
    
    private Condominio getCondominio() {
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
        } else {
            return condominioDAO.getLista("condominio_teste", "ativo").get(0);
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
