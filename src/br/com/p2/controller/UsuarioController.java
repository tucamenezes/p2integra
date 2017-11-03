package br.com.p2.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;


import br.com.p2.dao.DaoInterface;
import br.com.p2.dao.DaoInterfaceImplements;
import br.com.p2.hibernate.HibernateUtilHQL;
import br.com.p2.model.Usuarios;
import br.com.p2.util.GenerateHashPasswordUtil;



@Controller
@RequestMapping(value="/usuarios")
public class UsuarioController extends DaoInterfaceImplements<Usuarios> implements DaoInterface<Usuarios> {

	public UsuarioController(Class<Usuarios> persistenceClass) {
		
		super(persistenceClass);
		
	}
	
	@RequestMapping(value="listar/{status}", method=RequestMethod.GET,headers="Accept=application/json")
	@ResponseBody
	public String listar(@PathVariable("status") String statusUsuario) throws Exception {
		
        if (statusUsuario=="") {
        	   statusUsuario="A";
        }
		return new Gson().toJson(HibernateUtilHQL.getListSqlHQL("from Usuarios as u where u.status='" + statusUsuario + "'"));
			
	}
	
	@RequestMapping(value="consultar/{username}", method=RequestMethod.GET,headers="Accept=application/json")
	@ResponseBody
	public String consultarUsername(@PathVariable("username") String username) throws Exception {
		
		String retorno;
		
		List lista = HibernateUtilHQL.getListSqlHQL("from Usuarios as u where u.username='" + username + "'");
		if (lista.isEmpty()) {
			retorno ="N";
		} else {
			retorno ="S";
		}
			
		return retorno;
			
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="salvar", method=RequestMethod.POST,headers="Accept=application/json")
	@ResponseBody
	public String salvar(@RequestBody String jSonApp) throws Exception {

		boolean usernameJaExiste;  
		Usuarios usuario = new Gson().fromJson(jSonApp, Usuarios.class);
		
		//forca encriptar a senha
		usuario.setPassword(GenerateHashPasswordUtil.generateHash(usuario.getPassword()));
		  
		  if (usuario.getId() == null) {
			  usernameJaExiste = usernameExiste(usuario.getUsername());
		  } else {
			  usernameJaExiste=false;
		  }
		  
		  if (!usernameJaExiste) {
		     super.salvarAtualizar(usuario);
	    	     return "0";
		  } else {
			  return "1";
		  }
	    	  
			
	}
	

	@RequestMapping(value="deletar/{idUsuario}", method=RequestMethod.DELETE)
	public @ResponseBody String deletar(@PathVariable("idUsuario") String idUsuario) throws Exception {
		
		
		Usuarios objeto = super.loadObjeto(Long.parseLong(idUsuario));	
		objeto.setStatus("I");
		super.salvarAtualizar(objeto);
		
		return "";
		
	}
	
	
	@RequestMapping(value="buscarusuario/{idUsuario}", method=RequestMethod.GET)
	public @ResponseBody String buscar(@PathVariable("idUsuario") String idUsuario) throws Exception {
		
		String vReturn;
		
		Usuarios objeto = super.loadObjeto(Long.parseLong(idUsuario));	
		if (objeto==null) {
			vReturn = "";
		}
		
		vReturn = new Gson().toJson(objeto);
		
		return vReturn;
		
		
	}	
	
	private boolean usernameExiste(String username) throws Exception {
		
		String retorno;
		
		List lista = HibernateUtilHQL.getListSqlHQL("from Usuarios as u where u.username='" + username + "'");
		if (lista.isEmpty()) {
			retorno ="N";
		} else {
			retorno ="S";
		}
			
		return retorno =="S";
			
	}

}
