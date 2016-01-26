/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.condominio.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author Massao
 */
public class MySQLUtil {

  private static String DRIVER_NAME = "com.mysql.jdbc.Driver";
  private static String URL = "jdbc:mysql://localhost/condominio";
  private static String USER = "root";
  private static String PASSWORD = "root";

  static {
    try {
      Class.forName(DRIVER_NAME).newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static Connection getConnection() throws SQLException {
    AnnotationConfiguration conf = new AnnotationConfiguration().configure("hibernate.cfg.xml");
    DRIVER_NAME = conf.getProperty("hibernate.connection.driver_class");
    URL = conf.getProperty("hibernate.connection.url");
    USER = conf.getProperty("hibernate.connection.username");
    PASSWORD = conf.getProperty("hibernate.connection.password");
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }
  
  public static void executeSQL(String sql) throws SQLException {
    Connection conexao = getConnection();
    Statement statement = conexao.createStatement();
    statement.executeUpdate(sql);
  }

  public static void createTriggers() throws SQLException {
    executeSQL("DROP TRIGGER IF EXISTS CONDOMINIO_BEFORE_UPDATE; ");
    executeSQL("CREATE TRIGGER CONDOMINIO_BEFORE_UPDATE BEFORE UPDATE ON CONDOMINIO FOR EACH ROW BEGIN DECLARE RESULTADO INTEGER DEFAULT 0; IF ((OLD.ATIVO = TRUE) AND (NEW.ATIVO = FALSE)) THEN SELECT COUNT(ID) FROM APARTAMENTO A WHERE (A.CONDOMINIO_ID = OLD.ID AND A.ATIVO = TRUE) INTO RESULTADO; IF (RESULTADO > 0) THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'EXISTE(M) APARTAMENTO(S) NESSE CONDOMÍNIO, FAVOR VERIFIQUE O CADASTRO DE APARTAMENTOS'; ELSE SELECT COUNT(ID) FROM SINDICO A WHERE (A.CONDOMINIO_ID = OLD.ID AND A.ATIVO = TRUE) INTO RESULTADO; IF (RESULTADO > 0) THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'EXISTE(M) SINDICO(S) NESSE CONDOMÍNIO, FAVOR VERIFIQUE O CADASTRO DE SINDICOS'; ELSE SELECT COUNT(ID) FROM CONDOMINO A WHERE (A.CONDOMINIO_ID = OLD.ID AND A.ATIVO = TRUE) INTO RESULTADO; IF (RESULTADO > 0) THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'EXISTE(M) CONDÔMINO(S) NESSE CONDOMÍNIO, FAVOR VERIFIQUE O CADASTRO DE CONDÔMINOS'; ELSE SELECT COUNT(ID) FROM FUNCIONARIO A WHERE (A.CONDOMINIO_ID = OLD.ID AND A.ATIVO = TRUE) INTO RESULTADO; IF (RESULTADO > 0) THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'EXISTE(M) FUNCIONÁRIO(S) NESSE CONDOMÍNIO, FAVOR VERIFIQUE O CADASTRO DE FUNCIONÁRIOS'; ELSE SELECT COUNT(ID) FROM AREALAZER WHERE (A.CONDOMINIO_ID = OLD.ID AND A.ATIVO = TRUE) INTO RESULTADO; IF (RESULTADO > 0) THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'EXISTE(M) ÀREA(S) DE LAZER NESSE CONDOMÍNIO, FAVOR VERIFIQUE O CADASTRO DE ÀREAS DE LAZER'; END IF; END IF; END IF; END IF; END IF;	END IF; END; ");
    executeSQL("DROP TRIGGER IF EXISTS APARTAMENTO_BEFORE_UPDATE; ");
    executeSQL("CREATE TRIGGER APARTAMENTO_BEFORE_UPDATE BEFORE UPDATE ON APARTAMENTO FOR EACH ROW BEGIN DECLARE RESULTADO INTEGER DEFAULT 0; IF ((OLD.ATIVO = TRUE) AND (NEW.ATIVO = FALSE)) THEN SELECT COUNT(ID) FROM CONDOMINO A WHERE (A.APARTAMENTO_ID = OLD.ID AND A.ATIVO = TRUE) INTO RESULTADO; IF (RESULTADO > 0) THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'EXISTE(M) CONDÔMINO(S) NESSE CONDOMÍNIO, FAVOR VERIFIQUE O CADASTRO DE CONDÔMINOS'; END IF; END IF; END; ");
  }
}
