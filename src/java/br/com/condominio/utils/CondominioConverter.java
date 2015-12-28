package br.com.condominio.utils;

import br.com.condominio.dao.CondominioDAO;
import br.com.condominio.model.Condominio;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="CondominioConverter", forClass=Condominio.class)
public class CondominioConverter implements Converter {

  private CondominioDAO condominioDAO = new CondominioDAO();
  private List<Condominio> listaCondominios = condominioDAO.getLista("","true");
  private Condominio condominio = new Condominio();
  private Long id;

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String nome) {
     
    for (Condominio cond : listaCondominios) {
        id = cond.getId();
        if(id.toString().contains(nome)){
            return cond;
        }
    }
    
    FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na conversão", "Condomino inexistente na lista");
    return null;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    if (value instanceof Condominio && value != null) {
      condominio = (Condominio) value;
      id = condominio.getId();
      return id.toString();
    }
    return null;
  }

}
