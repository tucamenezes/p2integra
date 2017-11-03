package br.com.p2.util;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.p2.controller.UsuarioController;
import br.com.p2.hibernate.HibernateUtilHQL;
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

}