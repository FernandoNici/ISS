package br.com.condominio.utils;

import br.com.condominio.model.Sindico;
import br.com.condominio.dao.CondominioDAO;
import br.com.condominio.model.Condominio;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="SindicoConverter", forClass=Sindico.class)
public class SindicoConverter implements Converter {

  private CondominioDAO sindicoDAO = new CondominioDAO();
  private List<Condominio> listaCondominios = sindicoDAO.getLista("","true");
  private Condominio sindico = new Condominio();
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
      sindico = (Condominio) value;
      id = sindico.getId();
      return id.toString();
    }
    return null;
  }

}