package br.com.p2.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.p2.model.Contas;
import br.com.p2.model.Usuarios;


public class HibernateUtilHQL implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	public static List<?> getListSqlHQL(String sqlHQL)  throws Exception {

		StringBuilder query = new StringBuilder();
		//Monta a query generica 
		query.append(sqlHQL);
		List<?> lista = sessionFactory.getCurrentSession().createQuery(query.toString()).list();
		
		return lista;
	}
	
	
	public static String getUniqueSqlHQL(String sqlHQL)  throws Exception {

		StringBuilder query = new StringBuilder();
		//Monta a query generica 
		query.append(sqlHQL);
		String retorno  = (String) sessionFactory.getCurrentSession().createQuery(query.toString()).uniqueResult().toString();
		
		return retorno;
	}
	
	
	public static List<?> getListSqlDinamico(String sql) throws Exception {

 		List<?> lista =  sessionFactory.getCurrentSession().createSQLQuery(sql).list();
 		
 		return lista;
 	}

  public static Usuarios buscaDadosUsuarioLogado () throws Exception {
		
		
		String username;
		Usuarios usuario = new Usuarios();
		
		//pegando o usuario logado no contexto do spring security
	    Object usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				
				if (usuarioLogado  instanceof UserDetails ) {
				   username= ( (UserDetails)usuarioLogado).getUsername();
				   usuario = getUsuarioByUsername(username);
				   
				} else {
				   username = usuarioLogado .toString();
				}
		
		return usuario; 
	}

  
   public static String getNumeroRegistros(String idConta, String status, String classeName) throws Exception {
		
	     StringBuilder sql = new StringBuilder();
         sql.append("select count(*) from "+ classeName + " as a where conta.id = " + idConta);
         if (status.equals("")) {
            sql.append(" and a.status = '"+ status + "'");
         }
      	

	    String retorno  = HibernateUtilHQL.getUniqueSqlHQL(sql.toString());
	
	 return retorno;
	
}  
  
    public static Usuarios getUsuarioByUsername(String username) throws Exception {
		
		List lista = getListSqlHQL("from Usuarios u where u.username='"+ username+"'");
		
		return (Usuarios) lista.get(0);
		
	}
	
}
