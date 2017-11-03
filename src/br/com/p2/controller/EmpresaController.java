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
import br.com.p2.model.Empresas;
import br.com.p2.model.Usuarios;



@Controller
@RequestMapping(value="/empresas")
public class EmpresaController extends DaoInterfaceImplements<Empresas> implements DaoInterface<Empresas> {

	public EmpresaController(Class<Empresas> persistenceClass) {
		
		super(persistenceClass);
		
	}
	
	
	
	@RequestMapping(value="listar/{status}", method=RequestMethod.GET,headers="Accept=application/json")
	@ResponseBody
	public String listarEmpresas(@PathVariable("status") String status) throws Exception {
	
        if (status=="") {
           status="A";
        }
        
        Usuarios usuario = HibernateUtilHQL.buscaDadosUsuarioLogado();
        
        return new Gson().toJson(HibernateUtilHQL.getListSqlHQL("from Empresas as a where a.conta.id = "+ usuario.getConta().getId() + " and a.status='" + status + "'"));
			
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="salvar", method=RequestMethod.POST,headers="Accept=application/json")
	@ResponseBody
	public ResponseEntity salvarApp(@RequestBody String jSonApp) throws Exception {
		
		Empresas empresa = new Gson().fromJson(jSonApp, Empresas.class);
	    super.salvarAtualizar(empresa);
	    
     
		return new ResponseEntity<>(HttpStatus.CREATED);
			
	}
	

	@RequestMapping(value="deletar/{idEmpresa}", method=RequestMethod.DELETE)
	public @ResponseBody String deletarApp(@PathVariable("idEmpresa") String idEmpresa) throws Exception {
		
		
		Empresas objeto = super.loadObjeto(Long.parseLong(idEmpresa));	
		objeto.setStatus("I");
		super.salvarAtualizar(objeto);
		
		return "";
		
	}
	
	
	@RequestMapping(value="buscarempresa/{idEmpresa}", method=RequestMethod.GET)
	public @ResponseBody String buscarApp(@PathVariable("idEmpresa") String idEmpresa) throws Exception {
		
		String vReturn;
		
		Empresas objeto = super.loadObjeto(Long.parseLong(idEmpresa));	
		if (objeto==null) {
			vReturn = "";
		}
		
		vReturn = new Gson().toJson(objeto);
		
		return vReturn;
		
		
	}	

}
