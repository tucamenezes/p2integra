package br.com.p2.hibernate;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.SessionFactoryImplementor;

public class HibernateUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static String JAVA_COMP_JDBC_DATASORUCE = "java:/comp/env/jdbc/datasouce";
	private static SessionFactory sessionFactory = buildSessionFactory();


	private static SessionFactory buildSessionFactory() {
		
	  try {
		//verifica sa a sessao do hibernate ja foi iniciada
		  if (sessionFactory==null) {
			sessionFactory = (new Configuration()).configure().buildSessionFactory();
		  }
	  
		  return sessionFactory;
		
	    } catch (Exception e) {
	     	e.printStackTrace();
		    throw new ExceptionInInitializerError();
	    }

	  
	}
	
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	
	/**
     * Pega a conexao corrente da
     * @return Connection SQL
     * @throws SQLException
     */
    public static Connection getConnectionProvider() throws SQLException {
    	
    	  return ((SessionFactoryImplementor) sessionFactory).getConnectionProvider().getConnection();
    }
    
    
    /**
     * 
     * @return Connection no initialcontext java:/comp/env/jdbc/datasource
     * @throws Exception
     */
    
    public static Connection getConnection() throws Exception {
    	   
    	   InitialContext contex =  new InitialContext();
    	   DataSource ds = (DataSource) contex.lookup(JAVA_COMP_JDBC_DATASORUCE);
    	   
    	   return ds.getConnection();	

    }
    
	
	

}
