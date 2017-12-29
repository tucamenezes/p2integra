package br.com.p2.controller;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import br.com.p2.model.veiculos.PropostaVeiculo;
import br.com.p2.util.DateDeserializer;





@Controller
@RequestMapping(value="/propostas")
public class PropostaController extends DaoInterfaceImplements<PropostaVeiculo> implements DaoInterface<PropostaVeiculo> {

	public PropostaController(Class<PropostaVeiculo> persistenceClass) {
		
		super(persistenceClass);
		
	}
	
	@RequestMapping(value="listar", method=RequestMethod.GET,headers="Accept=application/json")
	@ResponseBody
	public String listar(@RequestParam("status") String status,  
			             @RequestParam("dataInicial") String dataInicial, @RequestParam("dataFinal") String dataFinal) throws Exception {
		
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
        
        StringBuilder sql = new StringBuilder();
       
        sql.append("from PropostaVeiculo as a where conta.id = " + usuarioLogado.getConta().getId());
        
		if (status.equals("")){
			sql.append(" and status = ('A')");
        } else {
           	sql.append(" and status =('"+ status + "')");
        }
		
		if (!dataInicial.equals("")){
			sql.append("and dataProposta >= '"+ dataInicial + "' and dataProposta<= '"+ dataFinal + "'");
        }
		
	
        
		return new Gson().toJson(HibernateUtilHQL.getListSqlHQL(sql.toString()));
		
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="salvar", method=RequestMethod.POST,headers="Accept=application/json")
	@ResponseBody
	public ResponseEntity salvar(@RequestBody String jSonApp) throws Exception {
		
		
		//validacoes anteriores
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
		
		Gson gson = gsonBuilder.create();
	
		PropostaVeiculo proposta = gson.fromJson(jSonApp, PropostaVeiculo.class);
		
		
		if (proposta.getId() == null || proposta.getId()==0) {
		   //grava a conta do usuario do sistema.
			
		   proposta.setStatus("A");
		  // avaliacao.setUsuario(HibernateUtilHQL.buscaDadosUsuarioLogado());
		  // avaliacao.setConta(HibernateUtilHQL.buscaDadosUsuarioLogado().getConta());	
		   
		}

	    super.salvarAtualizar(proposta);
	    
     
		return new ResponseEntity<>(HttpStatus.CREATED);
			
	}
	


	
	@RequestMapping(value="liberar/{idProposta}", method=RequestMethod.POST)
	public @ResponseBody String liberarProposta(@PathVariable("idProposta") String idProposta) throws Exception {
		
		String retorno;
		
		PropostaVeiculo objeto = super.loadObjeto(Long.parseLong(idProposta));
		
		Usuarios usuarioLogado = HibernateUtilHQL.buscaDadosUsuarioLogado();
		
		
		objeto.setId(Long.parseLong(idProposta));
		
		   //atualiza o usuario que liberou
		   objeto.setLiberador(usuarioLogado);
		   //atualiza a data de liberacao
		   objeto.setDataLiberacao(Calendar.getInstance().getTime());
		   // atualiza o status
	       objeto.setStatus("L");
		
	       //so salva se o usuario logado for da mesma conta 
	    if (objeto.getConta().getId() == usuarioLogado.getConta().getId()) { 
		    super.salvarAtualizar(objeto);
		    retorno = "";
	    } else {
	    	  retorno = "Erro : Sem permissao na conta";
	    }
		
		
		return retorno;
		 
	}
	
	
	@RequestMapping(value="qtdePendente", method=RequestMethod.GET)
	public @ResponseBody String qtde() throws Exception {
		
		String retorno="";
		
		Usuarios usuarioLogado = HibernateUtilHQL.buscaDadosUsuarioLogado();
		
		retorno = HibernateUtilHQL.getNumeroRegistros(usuarioLogado.getConta().getId().toString(), "A", PropostaVeiculo.class.getName());
		
		if (retorno.equals("")) {
			retorno = "0";
		}
		
		return retorno;
		
	}
	
	
	
}
