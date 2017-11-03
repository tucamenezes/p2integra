package br.com.p2.hibernate;

import java.io.Serializable;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
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
	
	

}
