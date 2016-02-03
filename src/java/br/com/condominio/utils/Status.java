package br.com.condominio.utils;

/**
 *
 * @author Massao
 */
public enum Status {
  
    NULO(-1), FALSO(0), VERDADEIRO(1);
    private final int status;

    Status(int st) {
      this.status = st;
    }

    int valor() {
      return status;
    }

    public static Status getStatus(int valor) {
      for (Status st : Status.values()) {
        if (valor == st.valor()) {
          return st;
        }
      }
      throw new IllegalArgumentException("Status inválido.");
    }
    
    public boolean toBoolean(){
      return (getStatus(this.status) == VERDADEIRO);
    }
    
}
