package br.com.p2.SMS.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import br.com.p2.SMS.model.MensagemSMS;
import br.com.p2.dao.DaoInterface;
import br.com.p2.dao.DaoInterfaceImplements;
import br.com.p2.hibernate.HibernateUtilHQL;
import br.com.p2.model.Contas;
import br.com.p2.model.Usuarios;
import br.com.p2.util.DadosUsuarioLogadoUtil;


@Controller
@RequestMapping(value="/sms")
public class SMSController extends DaoInterfaceImplements<MensagemSMS> implements DaoInterface<MensagemSMS> {

	public SMSController(Class<MensagemSMS> persistenceClass) {
		
		super(persistenceClass);
		
	}
	
	
	
	//TODO pegar a conta pelo Hash e nao pelo numero da conta.
	@RequestMapping(value="obterRespostas", method=RequestMethod.GET,headers="Accept=application/json")
	@ResponseBody
	public String listarApp(@RequestParam("dataInicial") String dataInicial, @RequestParam("dataFinal") String dataFinal, 
			                @RequestParam("status") String status) throws Exception {
        
		
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
        
        
        if (status.equals("X")) {
        	   return getRespostasBase(usuarioLogado.getConta().getId(), dataInicial, dataFinal);
        } else {
        	 return getRespostasBase(usuarioLogado.getConta().getId(), dataInicial, dataFinal, status);
        }
        	 
			
	}
	
	

		@RequestMapping(value="obterRespostasJob", method=RequestMethod.GET,headers="Accept=application/json")
		@ResponseBody
		public String listarApp(@RequestParam("idConta") String idConta, @RequestParam("dataInicial") String dataInicial, @RequestParam("dataFinal") String dataFinal, 
				                @RequestParam("status") String status) throws Exception {
	        
			
			Contas contaCliente = new Contas();
			
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
			
            //busca a conta pelo id
	        contaCliente = DadosUsuarioLogadoUtil.buscarContaHash(idConta);
	        
	        
	        if (status.equals("X")) {
	        	   return getRespostasBase(contaCliente.getId(), dataInicial, dataFinal);
	        } else {
	        	 return getRespostasBase(contaCliente.getId(), dataInicial, dataFinal, status);
	        }
	        	 
				
		}
	
	//executa a consulta idenpendente se pelo status da resposta
	private String getRespostasBase (Long idConta, String dataInicial, String dataFinal, String statusIntegracao) throws Exception {
		
		return new Gson().toJson(HibernateUtilHQL.getListSqlHQL("from MensagemSMS as a where conta.id = " + idConta + 
                " and dataRecebimento >= '"+ dataInicial + "' and dataRecebimento <= '"+ dataFinal 
                + "'  and a.statusIntegracao in " + statusIntegracao));
	}
	
    //executa a consulta idenpendente se a resposta ja foi ou não integrada.
	private String getRespostasBase (Long idConta, String dataInicial, String dataFinal) throws Exception {
		
		return new Gson().toJson(HibernateUtilHQL.getListSqlHQL("from MensagemSMS as a where conta.id = " + idConta + 
                " and dataRecebimento >= '"+ dataInicial + "' and dataRecebimento <= '"+ dataFinal));
	}
	
   
   
   


	
	

	
	

}
