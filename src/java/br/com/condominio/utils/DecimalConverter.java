package br.com.condominio.utils;

import java.text.NumberFormat;
import java.util.Locale;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Massao
 */
@FacesConverter("DecimalConverter")
public class DecimalConverter implements Converter {

  @Override
  public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) throws ConverterException {
    if (valor == null || valor.toString().trim().equals("")) {
      return 0.0d;
    } else {
      try {
        return Double.parseDouble(valor.replace(",", "."));
      } catch (Exception e) {
        return 0.0d;
      }
    }
  }

  @Override
  public String getAsString(FacesContext facesContext, UIComponent component, Object valor) throws ConverterException {
    if (valor == null || valor.toString().trim().equals("")) {
      return "0.00";
    } else {
      NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));
      numberFormat.setMinimumFractionDigits(2);
      return numberFormat.format(Double.valueOf(valor.toString()));
    }
  }
}
