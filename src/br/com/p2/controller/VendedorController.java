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
import br.com.p2.model.veiculos.Vendedor;



@Controller
@RequestMapping(value="/vendedores")
public class VendedorController extends DaoInterfaceImplements<Vendedor> implements DaoInterface<Vendedor> {

	public VendedorController(Class<Vendedor> persistenceClass) {
		
		super(persistenceClass);
		
	}
	
	@RequestMapping(value="listar/{status}", method=RequestMethod.GET,headers="Accept=application/json")
	@ResponseBody
	public String listarVendedores(@PathVariable("status") String status) throws Exception {
		
        if (status=="") {
           status="A";
        }
        
		return new Gson().toJson(HibernateUtilHQL.getListSqlHQL("from Vendedor as a where a.status='" + status + "'"));
			
	}
	


}
