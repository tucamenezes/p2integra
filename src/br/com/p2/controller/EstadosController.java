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
import br.com.p2.model.Estados;



@Controller
@RequestMapping(value="/estados")
public class EstadosController extends DaoInterfaceImplements<Estados> implements DaoInterface<Estados> {

	public EstadosController(Class<Estados> persistenceClass) {
		
		super(persistenceClass);
		
	}
	
	@RequestMapping(value="listar", method=RequestMethod.GET,headers="Accept=application/json")
	@ResponseBody
	public String listarApp() throws Exception {
     
		return new Gson().toJson(super.listar());
			
	}
	


}
