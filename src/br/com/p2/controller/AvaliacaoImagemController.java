package br.com.p2.controller;




import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.p2.dao.DaoInterface;
import br.com.p2.dao.DaoInterfaceImplements;
import br.com.p2.hibernate.HibernateUtilHQL;
import br.com.p2.model.avaliacao.Avaliacao;
import br.com.p2.model.avaliacao.AvaliacaoImagens;
import br.com.p2.util.DateDeserializer;;


@Controller
@RequestMapping(value="/avaliacoesimagens")
public class AvaliacaoImagemController extends DaoInterfaceImplements<AvaliacaoImagens> implements DaoInterface<AvaliacaoImagens> {

	public AvaliacaoImagemController(Class<AvaliacaoImagens> persistenceClass) {
		
		super(persistenceClass);
		
	}
	
	@RequestMapping(value="listar/{idAvaliacao}", method=RequestMethod.GET,headers="Accept=application/json")
	@ResponseBody
	public String listar(@PathVariable("idAvaliacao") String idAvaliacao) throws Exception {
		
      
		return new Gson().toJson(HibernateUtilHQL.getListSqlHQL("from AvaliacaoImagens as a where a.avaliacao.id = '" + idAvaliacao + "'"));
			
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="salvar", method=RequestMethod.POST,headers="Accept=application/json")
	@ResponseBody
	public ResponseEntity salvarApp(@RequestBody String jSonApp) throws Exception {
		
		
		//Definir no Gson o novo deseserializar para que possa trabalhat com as datas
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
				
		Gson gson = gsonBuilder.create();

		AvaliacaoImagens avaliacaoImagem = gson.fromJson(jSonApp, AvaliacaoImagens.class);
		
		
	    super.salvarAtualizar(avaliacaoImagem);

		return new ResponseEntity<>(HttpStatus.CREATED);
			
	}
	

	@RequestMapping(value="deletar/{idAvaliacao}", method=RequestMethod.DELETE)
	public @ResponseBody String deletarApp(@PathVariable("idAvaliacao") String idAvaliacao) throws Exception {
		
		AvaliacaoImagens objeto = new AvaliacaoImagens();
		
		
		
		objeto = (AvaliacaoImagens) HibernateUtilHQL.getListSqlHQL("from AvaliacaoImagens as a where a.id='" + idAvaliacao + "'").get(0);
		
		super.deletar(objeto);
		
		return "";
		
	}
	
	
	@RequestMapping(value="buscaravaliacao/{idAvaliacaoImagem}", method=RequestMethod.GET)
	public @ResponseBody String buscarApp(@PathVariable("idAvaliacaoImagem") String idAvaliacaoImagem) throws Exception {
		
		String vReturn;
		
		AvaliacaoImagens objeto = super.loadObjeto(Long.parseLong(idAvaliacaoImagem));	
		if (objeto==null) {
			vReturn = "";
		}
		
		vReturn = new Gson().toJson(objeto);
		
		return vReturn;
		
		
	}	
	
	

}
