package br.com.p2.controller;



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
import br.com.p2.model.App;


@Controller
@RequestMapping(value="/app")
public class AppController extends DaoInterfaceImplements<App> implements DaoInterface<App> {

	public AppController(Class<App> persistenceClass) {
		
		super(persistenceClass);
		
	}
	
	@RequestMapping(value="listar/{status}", method=RequestMethod.GET,headers="Accept=application/json")
	@ResponseBody
	public String listar(@PathVariable("status") String status) throws Exception {
		
        if (status=="") {
           status="A";
        }
        
		return new Gson().toJson(HibernateUtilHQL.getListSqlHQL("from App as a where a.status='" + status + "'"));
			
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="salvar", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity salvarApp(@RequestBody String jSonApp) throws Exception {
		
	    App app = new Gson().fromJson(jSonApp, App.class);
	    super.salvarAtualizar(app);
	    
     
		return new ResponseEntity<>(HttpStatus.CREATED);
			
	}
	

	@RequestMapping(value="deletar/{idApp}", method=RequestMethod.DELETE)
	public @ResponseBody String deletarApp(@PathVariable("idApp") String idApp) throws Exception {
		
		App objeto = new App();
		
		objeto.setId(Long.parseLong(idApp));
		super.deletar(objeto);
		
		return "";
		
	}
	
	
	@RequestMapping(value="buscarapp/{idApp}", method=RequestMethod.GET)
	public @ResponseBody String buscarApp(@PathVariable("idApp") String idApp) throws Exception {
		
		String vReturn;
		
		App objeto = super.loadObjeto(Long.parseLong(idApp));	
		if (objeto==null) {
			vReturn = "";
		}
		
		vReturn = new Gson().toJson(objeto);
		
		return vReturn;
		
		
	}	

}
