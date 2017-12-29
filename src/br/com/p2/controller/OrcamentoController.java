package br.com.p2.controller;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import br.com.p2.dao.DaoInterface;
import br.com.p2.dao.DaoInterfaceImplements;
import br.com.p2.hibernate.HibernateUtilHQL;
import br.com.p2.model.Usuarios;
import br.com.p2.model.orcamento.Orcamento;




@Controller
@RequestMapping(value="/orcamentos")
public class OrcamentoController extends DaoInterfaceImplements<Orcamento> implements DaoInterface<Orcamento> {

	public OrcamentoController(Class<Orcamento> persistenceClass) {
		
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
		

        
		return new Gson().toJson(HibernateUtilHQL.getListSqlHQL("from Orcamento as a where conta.id = " + usuarioLogado.getConta().getId() + 
				                                                " and dataCompetencia>= '"+ dataInicial + "' and dataCompetencia<= '"+ dataFinal 
				                                                + "'  and a.status in " + status));
		
	}
	

	
	
	

	
	@RequestMapping(value="liberar/{idOrcamento}", method=RequestMethod.POST)
	public @ResponseBody String liberarAvaliacao(@PathVariable("idOrcamento") String idOrcamento) throws Exception {
		
		
		String retorno;
		
		Orcamento objeto = super.loadObjeto(Long.parseLong(idOrcamento));
		
		Usuarios usuarioLogado = HibernateUtilHQL.buscaDadosUsuarioLogado();
		
		objeto.setId(Long.parseLong(idOrcamento));
		
		//atualiza o usuario que liberou
		objeto.setUsuarioLiberou(usuarioLogado);
		//atualiza a data de liberacao
		objeto.setDataLiberacao(Calendar.getInstance().getTime());
		//libera o orcamento
		objeto.setLiberado("S");
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
		
		retorno = HibernateUtilHQL.getNumeroRegistros(usuarioLogado.getConta().getId().toString(), "A", Orcamento.class.getName());
		
		if (retorno.equals("")) {
			retorno = "0";
		}
		
		return retorno;
		
	}
	
	
	
	
}
