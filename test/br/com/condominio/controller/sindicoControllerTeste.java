package br.com.condominio.controller;

import br.com.condominio.model.Sindico;
import br.com.condominio.dao.SindicoDAO;
import br.com.condominio.model.Condominio;
import br.com.condominio.dao.CondominioDAO;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/*usados para mock*/
import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.easymock.Capture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;



@RunWith(PowerMockRunner.class)
@PrepareForTest({ FacesContext.class })
public class sindicoControllerTeste {
    
    
    private final SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
    private static final String URL_CONSULTA = "ConsultaSindico?faces-redirect=true";
    private static final String URL_CADASTRO = "cadastroSindico";
    
    private SindicoController sindicoController;
    
    private FacesContext facesContext;
    private ExternalContext externalContext;
    
       
    
    public sindicoControllerTeste() {
    }
    
        
    @Before
    public void setUp() throws Exception {
        sindicoController = new SindicoController();
        
        PowerMock.mockStatic(FacesContext.class);

        facesContext = createMock(FacesContext.class);
        externalContext = createMock(ExternalContext.class);
    }
    
    @After
    public void tearDown() {
        
    }
    
    @Test
    public void testManutencaoSindico() throws ParseException{
        
        Capture<String> clientIdCapture = new Capture<String>();
        Capture<FacesMessage> facesMessageCapture = new Capture<FacesMessage>();
        
        expect(FacesContext.getCurrentInstance()).andReturn(facesContext).once();
        
        
        facesContext.addMessage(capture(clientIdCapture),
                capture(facesMessageCapture));
        expectLastCall().once();
        
        PowerMock.replay(FacesContext.class);
        replay(facesContext);

        //todo
        
        
        
        PowerMock.verify(FacesContext.class);
        verify(facesContext);
        
        assertNull(clientIdCapture.getValue());
        
        FacesMessage captured = facesMessageCapture.getValue();
        assertEquals(FacesMessage.SEVERITY_ERROR, captured.getSeverity());
        
        assertEquals("error", captured.getSummary());
        assertEquals("something went wrong", captured.getDetail());

        
        
        
        
        
        /*
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
        */
        /*SindicoController sindicoController = new SindicoController();
        sindicoController.carregarEntidade(sindico);
        String result = sindicoController.manutencaoSindico();
        sindicoController.excluirEntidade(sindico);
        assertEquals(URL_CONSULTA, result);*/
    }
    
    
}
