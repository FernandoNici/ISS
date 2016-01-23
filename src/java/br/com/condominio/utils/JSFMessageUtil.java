package br.com.condominio.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Massao
 */
public class JSFMessageUtil {

  public JSFMessageUtil() {
  }

  private static final long serialVersionUID = 1L;

  private static void addMessage(String componentId, String mensagem, FacesMessage.Severity severity) {
    FacesMessage msg = new FacesMessage(severity, mensagem, mensagem);
    FacesContext.getCurrentInstance().addMessage(componentId, msg);
  }

  public static void addMessageError(String componentId, String mensagem) {
    addMessage(componentId, mensagem, FacesMessage.SEVERITY_ERROR);
  }

  public static void addMessageError(String mensagem) {
    addMessage(null, mensagem, FacesMessage.SEVERITY_ERROR);
  }

  public static void addMessageInfo(String componentId, String mensagem) {
    addMessage(componentId, mensagem, FacesMessage.SEVERITY_INFO);
  }

  public static void addMessageInfo(String mensagem) {
    addMessage(null, mensagem, FacesMessage.SEVERITY_INFO);
  }
}
