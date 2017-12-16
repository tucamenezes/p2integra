package br.com.p2.controller;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.p2.dao.DaoInterface;
import br.com.p2.dao.DaoInterfaceImplements;
import br.com.p2.hibernate.HibernateUtilHQL;
import br.com.p2.model.Usuarios;
import br.com.p2.model.avaliacao.Avaliacao;
import br.com.p2.util.DateDeserializer;
import br.com.p2.util.DateUtils;



@Controller
@RequestMapping(value="/avaliacoes")
public class AvaliacaoController extends DaoInterfaceImplements<Avaliacao> implements DaoInterface<Avaliacao> {

	public AvaliacaoController(Class<Avaliacao> persistenceClass) {
		
		super(persistenceClass);
		
	}
	
	@RequestMapping(value="listar", method=RequestMethod.GET,headers="Accept=application/json")
	@ResponseBody
	public String listar(@RequestParam("status") String status, @RequestParam("dataInicial") String dataInicial, @RequestParam("dataFinal") String dataFinal) throws Exception {
		
		SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" ); 
		Date dtAux = new Date();
      	Calendar cadAux = Calendar.getInstance();
      	
		
		if (dataInicial=="") {
			dtAux = Calendar.getInstance().getTime();
			cadAux.add(Calendar.DATE,-30);
			dataInicial = df.format(cadAux.getTime());
		}
		
		if (dataFinal=="") {
			dtAux = Calendar.getInstance().getTime();
			dataFinal= df.format(dtAux);
		}
	
		
        Usuarios usuarioLogado = HibernateUtilHQL.buscaDadosUsuarioLogado();
        
     
        
		if (status.equals("")){
           status= "('A')";
        } else {
        		status= "('"+ status + "')";
        }
		
		
        
		return new Gson().toJson(HibernateUtilHQL.getListSqlHQL("from Avaliacao as a where conta.id = " + usuarioLogado.getConta().getId() + 
				                                                " and dataAvaliacao>= '"+ dataInicial + "' and dataAvaliacao<= '"+ dataFinal 
				                                                + "'  and a.status in " + status));
		
	}
	

	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="salvar", method=RequestMethod.POST,headers="Accept=application/json")
	@ResponseBody
	public ResponseEntity salvarApp(@RequestBody String jSonApp) throws Exception {
		
		
		//validacoes anteriores
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
		
		Gson gson = gsonBuilder.create();
	
		Avaliacao avaliacao = gson.fromJson(jSonApp, Avaliacao.class);
		
		
		if (avaliacao.getId() == null || avaliacao.getId()==0) {
		   //grava a conta do usuario do sistema.
			System.out.println(avaliacao.getId());
		   avaliacao.setStatus("A");
		   avaliacao.setUsuario(HibernateUtilHQL.buscaDadosUsuarioLogado());
		   avaliacao.setConta(HibernateUtilHQL.buscaDadosUsuarioLogado().getConta());	
		   avaliacao.setDataAvaliacao(Calendar.getInstance().getTime());
		}
		
		
	    super.salvarAtualizar(avaliacao);
	    
     
		return new ResponseEntity<>(HttpStatus.CREATED);
			
	}
	

	@RequestMapping(value="deletar/{idAvaliacao}", method=RequestMethod.DELETE)
	public @ResponseBody String deletarApp(@PathVariable("idAvaliacao") String idAvaliacao) throws Exception {
		
		
		
		Avaliacao objeto = super.loadObjeto(Long.parseLong(idAvaliacao));
		
		objeto.setId(Long.parseLong(idAvaliacao));
		objeto.setStatus("I");
		super.salvarAtualizar(objeto);
		 
		
		return "";
		
	}
	
	
	@RequestMapping(value="liberar/{idAvaliacao}", method=RequestMethod.GET)
	public @ResponseBody String liberarAvaliacao(@PathVariable("idAvaliacao") String idAvaliacao) throws Exception {
		
		
		
		Avaliacao objeto = super.loadObjeto(Long.parseLong(idAvaliacao));
		
		objeto.setId(Long.parseLong(idAvaliacao));
		objeto.setLiberada("S");
		objeto.setLiberador(HibernateUtilHQL.buscaDadosUsuarioLogado());
		//atualiza a data de liberacao
		objeto.setDataLiberacao(Calendar.getInstance().getTime());
		super.salvarAtualizar(objeto);
		
		
		return "";
		
	}
	
	@RequestMapping(value="buscaravaliacao/{idAvaliacao}", method=RequestMethod.GET)
	public @ResponseBody String buscarApp(@PathVariable("idAvaliacao") String idAvaliacao) throws Exception {
		
		String vReturn;
		
		Avaliacao objeto = super.loadObjeto(Long.parseLong(idAvaliacao));	
		if (objeto==null) {
			vReturn = "";
		}
		
		vReturn = new Gson().toJson(objeto);
		
		return vReturn;
		
		
	}	
	
	@RequestMapping(value="buscarcliente/{cpf}", method=RequestMethod.GET)
	public @ResponseBody String buscarCliente(@PathVariable("cpf") String cpf) throws Exception {
	
		Long idConta = HibernateUtilHQL.buscaDadosUsuarioLogado().getConta().getId();	
		return new Gson().toJson(HibernateUtilHQL.getListSqlHQL("from ClienteVeiculo as a where a.conta.id= "+ idConta +" and a.cpf='" + cpf + "'"));
		
		
	}	
	
	
	
   public List listarAvaliacoes(String dataInicial, String dataFinal) throws Exception {
	
        Usuarios usuarioLogado = HibernateUtilHQL.buscaDadosUsuarioLogado();
        
        String liberada = "('S')";
     
     
		return HibernateUtilHQL.getListSqlHQL("from Avaliacao as a where conta.id = " + usuarioLogado.getConta().getId() + 
				                                                " and dataAvaliacao>= '"+ dataInicial + "' and dataAvaliacao<= '"+ dataFinal 
				                                                + "'  and a.status in " + liberada);
		
	}

}
