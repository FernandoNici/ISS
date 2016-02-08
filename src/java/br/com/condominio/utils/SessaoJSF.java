package br.com.condominio.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessaoJSF {

  public static boolean set(Object objeto, String nome) {
    try {
      FacesContext fc = FacesContext.getCurrentInstance();
      HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
      session.setAttribute(nome, objeto);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public static Object get(String nome) {
    try {
      HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
      HttpSession session = (HttpSession) request.getSession();
      return session.getAttribute(nome);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
