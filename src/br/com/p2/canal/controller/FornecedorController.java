package br.com.p2.canal.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.p2.canal.model.Fornecedor;
import br.com.p2.dao.DaoInterface;
import br.com.p2.dao.DaoInterfaceImplements;
import br.com.p2.hibernate.HibernateUtilHQL;
import br.com.p2.model.Usuarios;
import br.com.p2.util.DateDeserializer;

@Controller
@RequestMapping(value="/canal/fornecedor")
public class FornecedorController extends DaoInterfaceImplements<Fornecedor> implements DaoInterface<Fornecedor> {
	
   public FornecedorController(Class<Fornecedor> persistenceClass) {		
		super(persistenceClass);
	}
	
   
   @RequestMapping(value="listar", method=RequestMethod.GET,headers="Accept=application/json")
   @ResponseBody
   public String listarFornecedores() throws Exception {
       Usuarios usuarioLogado = HibernateUtilHQL.buscaDadosUsuarioLogado();
	   
       return new Gson().toJson(HibernateUtilHQL.getListSqlHQL("from Fornecedor as a where conta.id = " + usuarioLogado.getConta().getId()));
		
	}    
   
  @RequestMapping(value="salvar", method=RequestMethod.POST)
  public @ResponseBody String salvarApp(@RequestBody  String jSonApp) throws Exception {
  	
	  GsonBuilder gsonBuilder = new GsonBuilder();
	  gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
	  Gson gson = gsonBuilder.create(); 
	  
	  Fornecedor fornececor = gson.fromJson(jSonApp, Fornecedor.class);	
	  super.salvarAtualizar(fornececor);
	
  	   return "O";
 			
  	}

}
