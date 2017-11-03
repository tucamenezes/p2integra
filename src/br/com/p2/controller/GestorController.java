package br.com.p2.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import br.com.p2.dao.DaoInterface;
import br.com.p2.dao.DaoInterfaceImplements;
import br.com.p2.hibernate.HibernateUtilHQL;
import br.com.p2.model.orcamento.Gestor;



@Controller
@RequestMapping(value="/gestores")
public class GestorController extends DaoInterfaceImplements<Gestor> implements DaoInterface<Gestor> {

	public GestorController(Class<Gestor> persistenceClass) {
		
		super(persistenceClass);
		
	}
	
	@RequestMapping(value="listar/{status}", method=RequestMethod.GET,headers="Accept=application/json")
	@ResponseBody
	public String listarModelos(@PathVariable("status") String status) throws Exception {
		
        if (status.equals("")) {
           status="A";
        }
        
		return new Gson().toJson(HibernateUtilHQL.getListSqlHQL("from Gestor as a where a.status='" + status + "'"));
			
	}
	


}
