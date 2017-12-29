package br.com.p2.util;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.p2.controller.UsuarioController;
import br.com.p2.hibernate.HibernateUtilHQL;
import br.com.p2.model.Contas;
import br.com.p2.model.ContasApps;
import br.com.p2.model.Usuarios;

public abstract class DadosUsuarioLogadoUtil {       
	 


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
   
	
	
	public static Usuarios getUsuarioByUsername(String username) throws Exception {
		

		List lista = HibernateUtilHQL.getListSqlHQL("from Usuarios u where u.username='"+ username+"'");
		
		return (Usuarios) lista.get(0);
		
	}  
	
	
     
	
	
    public static boolean contaAppValida(String idConta) throws Exception {
		
		String retorno;
		
		List<?> lista = HibernateUtilHQL.getListSqlHQL("from Contas as c where c.conta.id="+ idConta);
		if (lista.isEmpty()) {
			retorno ="N";
		} else {
			retorno ="S";
		}
			
		return retorno =="S";
			
	}
    
   public static Contas buscarContaHash(String hashId) throws Exception {
		
		Contas retorno;
		
		List<?> lista = HibernateUtilHQL.getListSqlHQL("from Contas as c where c.conta.hashValidation="+ hashId);
		if (lista.isEmpty()) {
			retorno = null;
		} else {
			retorno = (Contas) lista.get(0);
		}
			
		return retorno;
			
	}
    

}