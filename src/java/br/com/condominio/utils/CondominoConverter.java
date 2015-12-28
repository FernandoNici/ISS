package br.com.condominio.utils;

import br.com.condominio.dao.CondominoDAO;
import br.com.condominio.model.Condomino;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Massao
 */
@FacesConverter("br.com.condominio.utils.CondominoConverter")
public class CondominoConverter implements Converter {

  private CondominoDAO condominoDAO = new CondominoDAO();
  private List<Condomino> listaCondominos = condominoDAO.getLista("");
  private Condomino condomino = new Condomino();

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String nome) {
    for (Condomino listaCondomino : listaCondominos) {
      if (listaCondomino.getNome().equals(nome)) {
        return listaCondomino;
      }
    }
    FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na conversão", "Condomino inexistente na lista");
    return null;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    if (value instanceof Condomino && value != null) {
      condomino = (Condomino) value;
      return condomino.getNome();
    }
    return null;
  }

}
