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
import br.com.p2.model.Contas;
import br.com.p2.util.GenerateHashPasswordUtil;


@Controller
@RequestMapping(value="/contas")
public class ContaController extends DaoInterfaceImplements<Contas> implements DaoInterface<Contas> {

	public ContaController(Class<Contas> persistenceClass) {
		
		super(persistenceClass);
		
	}
	
	@RequestMapping(value="listar/{status}", method=RequestMethod.GET,headers="Accept=application/json")
	@ResponseBody
	public String listar(@PathVariable("status") String status) throws Exception {
		
        if (status=="") {
           status="A";
        }
        
		return new Gson().toJson(HibernateUtilHQL.getListSqlHQL("from Contas as c where c		.status='" + status + "'"));
			
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="salvar", method=RequestMethod.POST,headers="Accept=application/json")
	@ResponseBody
	public ResponseEntity salvarApp(@RequestBody String jSonApp) throws Exception {
		
		Integer random = 9999 + (int) (Math.random() * 100000);
		
	    Contas conta = new Gson().fromJson(jSonApp, Contas.class);
	    //so atualiza se estiver em branco
	    if (conta.getHashValidation() == null || conta.getHashValidation().isEmpty()) {
	    	    	        conta.setHashValidation(GenerateHashPasswordUtil.generateHash(random.toString()));
	    	    	 
	    }
	    
	    super.salvarAtualizar(conta);
	    
     
		return new ResponseEntity<>(HttpStatus.CREATED);
			
	}
	

	@RequestMapping(value="deletar/{idConta}", method=RequestMethod.DELETE)
	public @ResponseBody String deletarApp(@PathVariable("idConta") String idConta) throws Exception {
		
		//Contas objeto = new Contas();
		Contas objeto = super.loadObjeto(Long.parseLong(idConta));	
		objeto.setStatus("I");
		super.salvarAtualizar(objeto);
		//objeto.setId(Long.parseLong(idConta));
		//super.deletar(objeto);
		
		return "";
		
	}
	
	
	@RequestMapping(value="buscarconta/{idConta}", method=RequestMethod.GET)
	public @ResponseBody String buscarApp(@PathVariable("idConta") String idConta) throws Exception {
		
		String vReturn;
		
		Contas objeto = super.loadObjeto(Long.parseLong(idConta));	
		if (objeto==null) {
			vReturn = "";
		}
		
		vReturn = new Gson().toJson(objeto);
		
		return vReturn;
		
		
	}	

}
