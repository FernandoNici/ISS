/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.condominio.utils;

import br.com.condominio.model.Apartamento;
import br.com.condominio.dao.ApartamentoDAO;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Nando
 */
@FacesConverter(value="ApartamentoConverter", forClass=Apartamento.class)
public class ApartamentoConverter implements Converter{
  private ApartamentoDAO apartamentoDAO = new ApartamentoDAO();
  private List<Apartamento> listaApartamento = apartamentoDAO.getLista("");
  private Apartamento apartamento = new Apartamento();
  private Long id;

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String nome) {
     
    for (Apartamento apt : listaApartamento) {
        id = apt.getId();
        if(id.toString().contains(nome)){
            return apt;
        }
    }
    
    FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na conversão", "Apartamento inexistente na lista");
    return null;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    if (value instanceof Apartamento) {
      apartamento = (Apartamento) value; 
      id = apartamento.getId();
      return id.toString();
    }
    return null;
  }
}
