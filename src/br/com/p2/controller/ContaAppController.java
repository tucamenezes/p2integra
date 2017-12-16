package br.com.p2.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import br.com.p2.dao.DaoInterface;
import br.com.p2.dao.DaoInterfaceImplements;
import br.com.p2.hibernate.HibernateUtilHQL;
import br.com.p2.model.Contas;
import br.com.p2.model.ContasApps;
import br.com.p2.model.Usuarios;


@Controller
@RequestMapping(value="/appconta")
public class ContaAppController extends DaoInterfaceImplements<ContasApps> implements DaoInterface<ContasApps> {

	public ContaAppController(Class<ContasApps> persistenceClass) {
		
		super(persistenceClass);
		
	}
	
	@RequestMapping(value="listar", method=RequestMethod.GET,headers="Accept=application/json")
	@ResponseBody
	public String listarApp() throws Exception {
     
		return new Gson().toJson(super.listar());
			
	}
	
	
	@RequestMapping(value="salvar", method=RequestMethod.POST,headers="Accept=application/json")
	@ResponseBody
	public String salvarApp(@RequestBody String jSonApp) throws Exception {
		
		
		boolean appContaJaExiste;  
		ContasApps contaApp = new Gson().fromJson(jSonApp, ContasApps.class);
		  
		
		
		appContaJaExiste = appContaExiste(String.valueOf(contaApp.getApp().getId()), String.valueOf(contaApp.getConta().getId()));
		
		  
		  if (!appContaJaExiste) {
		     super.salvarAtualizar(contaApp);
	    	     return "0";
		  } else {
			  return "1";
		  }
			
	}
	

	@RequestMapping(value="deletar/{idContaApp}", method=RequestMethod.DELETE)
	public @ResponseBody String deletarApp(@PathVariable("idContaApp") String idContaApp) throws Exception {
		
		ContasApps objeto = new ContasApps();
		
		objeto.setId(Long.parseLong(idContaApp));
		super.deletar(objeto);
		
		return "";
		
	}
	
	
	@RequestMapping(value="buscaruserlogado", method=RequestMethod.GET)
	public @ResponseBody String buscarDadosUsuario() throws Exception {
		
		Usuarios usuario = HibernateUtilHQL.buscaDadosUsuarioLogado();
		
		return new Gson().toJson(usuario);		
		
	}
	
	
	@RequestMapping(value="buscarapps", method=RequestMethod.GET)
	public @ResponseBody String buscarAppsbyUserName() throws Exception {
		
		Usuarios usuario = HibernateUtilHQL.buscaDadosUsuarioLogado();
		List<?> lista = HibernateUtilHQL.getListSqlHQL("select c.app from ContasApps as c, Usuarios u where c.conta = u.conta and u.username='"+ usuario.getUsername() +"'");
		
		return new Gson().toJson(lista);		
		
	}	
	
	
	@RequestMapping(value="validarlicenca", method=RequestMethod.GET)
	@ResponseBody
	public String listar(@RequestParam("idConta") String idConta, @RequestParam("idApp") String idApp, @RequestParam("hashcode") String hashCode) throws Exception {
		
		String retorno = "";
		
      	List resultado =  new ArrayList();
      	
      	resultado = HibernateUtilHQL.getListSqlHQL("from Contas as a where a.id = " + idConta + 
      											  " and a.hashValidation = '"+ hashCode + "'" +
      											  "  and a.status ='A'");
      	
      	if (resultado.isEmpty()) {
      		retorno = "Empresa não encontrada ou não ativa!";
      	} else if (resultado.size() >0) {
      		//empresa OK, validando o modulo
      		if (appContaExiste(idApp, idConta)) {
          		retorno = "P2OK";
          	} else {
          		retorno = "Empresa válida mas módulo não ativo para o cadastro, entre em contato com o suporte!";
          	}
      	}
      	
      	
      		
      	
        
		//return retorno;
      	return new Gson().toJson(retorno);		
		
	}
	
	
	
	
	private boolean appContaExiste(String idApp, String idConta) throws Exception {
		
		String retorno;
		
		List<?> lista = HibernateUtilHQL.getListSqlHQL("from ContasApps as c where c.app.id=" + idApp + " and c.conta.id="+ idConta);
		if (lista.isEmpty()) {
			retorno ="N";
		} else {
			retorno ="S";
		}
			
		return retorno =="S";
			
	}

}
