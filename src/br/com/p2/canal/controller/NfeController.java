package br.com.p2.canal.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.p2.canal.model.Fornecedor;
import br.com.p2.canal.model.NFE;
import br.com.p2.canal.vo.VONFE;
import br.com.p2.dao.DaoInterface;
import br.com.p2.dao.DaoInterfaceImplements;
import br.com.p2.hibernate.HibernateUtilHQL;
import br.com.p2.model.Empresas;
import br.com.p2.model.Usuarios;
import br.com.p2.model.avaliacao.Avaliacao;
import br.com.p2.model.avaliacao.AvaliacaoImagens;
import br.com.p2.util.DateDeserializer;
import br.com.p2.util.XMLUtils;



@Controller
@RequestMapping(value="/nfe")
public class NfeController extends DaoInterfaceImplements<NFE> implements DaoInterface<NFE>{
	
	
    private static final int BUFFER_SIZE = 4096;
	
	private String filePath = "";
	
	public NfeController(Class<NFE> persistenceClass) {
		
		super(persistenceClass);
	}
	
	
  private String formatarData (Date data, String formato) {
	 
	  SimpleDateFormat df = new SimpleDateFormat(formato); 
	  return df.format(data);
	  
  }
   
   
   @RequestMapping(value="listar", method=RequestMethod.GET,headers="Accept=application/json")
   @ResponseBody
   public String listar(@RequestParam("idFornecedor") String idFornecedor, @RequestParam("idEmpresa") String idEmpresa,
		                @RequestParam("dataInicial") String dataInicial, @RequestParam("dataFinal") String dataFinal) throws Exception {
		
		SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" ); 
		Date dtAux = new Date();
     	Calendar cadAux = Calendar.getInstance();
     	String vSql=""; 
     	Usuarios usuarioLogado = HibernateUtilHQL.buscaDadosUsuarioLogado();
     	List<NFE> listRetorno =  new ArrayList<>();
		
		if (dataInicial.equals("")) {
			dtAux = Calendar.getInstance().getTime();
			cadAux.add(Calendar.DATE,-30);
			dataInicial = formatarData(cadAux.getTime(), "MM/dd/yyyy");
		} else {
			dtAux = df.parse(dataInicial);
			dataInicial = formatarData(dtAux, "MM/dd/yyyy");
		}
		
		if (dataFinal.equals("")) {
			dtAux = Calendar.getInstance().getTime();
			dataFinal= formatarData(dtAux, "MM/dd/yyyy");
		} else {
			dtAux = df.parse(dataFinal);
			dataFinal = formatarData(dtAux, "MM/dd/yyyy");	
		}
		
		
		String vSqlEmpresa ="";
		
		if (idFornecedor.equals("0")){
			vSql= "select a.id, id_nfe_externo, a.id_transacao, a.serie_nota, a.nome_empresa, data from canal_nfe_xml a where a.id_conta = " + usuarioLogado.getConta().getId() + 
                    " and a.data>= '"+ dataInicial + "' and a.data<= '"+ dataFinal  + "' ";
	        } else {
	          	vSql= "select a.id, id_nfe_externo, a.id_transacao, a.serie_nota, a.nome_empresa, data from canal_nfe_xml a, canal_fornecedores_nfe b where a.id = b.id_nfe and b.id_fornecedor = " + idFornecedor  + " and a.id_conta = " + usuarioLogado.getConta().getId() + 
	                    " and a.data>= '"+ dataInicial + "' and a.data<= '"+ dataFinal  + "' ";
	        }
		
		if (idEmpresa.equals("0")){
			vSqlEmpresa= "";
	        } else {
	          	vSqlEmpresa= " and a.id_empresa = " + idEmpresa;
	        }

		vSql = vSql + vSqlEmpresa + " order by a.data, a.id_transacao";
		
		System.out.println(vSql);
		
		List<?> listAux =  HibernateUtilHQL.getListSqlDinamico(vSql);
		SimpleDateFormat formato = new SimpleDateFormat( "yyyy-MM-dd" ); 
		
		for (int i=0; i< listAux.size(); i++ ) {
			NFE nfe = new NFE();
			Object[] obj = (Object[]) listAux.get(i);
			nfe.setId(Long.parseLong(verificaIntegerNulo(obj[0])));
			nfe.setTransacaoVenda(Integer.parseInt(verificaIntegerNulo(obj[1])));
			nfe.setNumeroNota(Integer.parseInt(verificaIntegerNulo(obj[2])));
			nfe.setSerieNota(verificaStringNulo(obj[3]));
			nfe.setNomeEmpresa(verificaStringNulo(obj[4]));
			nfe.setDataSaida(formato.parse(verificaStringNulo(obj[5])));
			listRetorno.add(nfe);
			
		}
		
      	return new Gson().toJson(listRetorno);
		
	}
   
   
   private String verificaStringNulo(Object valor ) {
	   return valor == null ? "" : valor.toString();
   }
   
   private String verificaIntegerNulo(Object valor ) {
	   return valor == null ? "0" : valor.toString();
   }
   
   
   private boolean isStringEmpty(String valor ) {
	   return valor == null || valor.equals("") ? true : false;
   }
   
   
   @RequestMapping(value="listarFornecedores", method=RequestMethod.GET,headers="Accept=application/json")
   @ResponseBody
   public String listarFornecedores() throws Exception {
		
		
       Usuarios usuarioLogado = HibernateUtilHQL.buscaDadosUsuarioLogado();
		return new Gson().toJson(HibernateUtilHQL.getListSqlHQL("from Fornecedor as a where conta.id = " + usuarioLogado.getConta().getId()));
		
	}
   
   @RequestMapping(value="listarempresas", method=RequestMethod.GET,headers="Accept=application/json")
   @ResponseBody
   public String listarEmpresas() throws Exception {
		
	   List<Empresas> listRetorno =  new ArrayList<>();	
       Usuarios usuarioLogado = HibernateUtilHQL.buscaDadosUsuarioLogado();
       String vSql= "select distinct a.id_empresa, a.nome_empresa from canal_nfe_xml a where a.id_conta = " + usuarioLogado.getConta().getId();
       
       List<?> listAux =  HibernateUtilHQL.getListSqlDinamico(vSql);
       
		for (int i=0; i< listAux.size(); i++ ) {
			Empresas empresa = new Empresas();
			Object[] obj = (Object[]) listAux.get(i);
			empresa.setId(Long.parseLong(verificaIntegerNulo(obj[0])));
			empresa.setNome(verificaStringNulo(obj[1]).toUpperCase());
			listRetorno.add(empresa);	
		}
		
     	return new Gson().toJson(listRetorno);
		
	}
   
   
    @SuppressWarnings("rawtypes")
	@RequestMapping(value="salvar", method=RequestMethod.POST)
	public @ResponseBody String salvarApp(@RequestBody  String jSonApp) throws Exception {
	
      //System.out.println(jSonApp);	
    	  String vRetorno = "0";
	//try {
			//Definir no Gson o novo deseserializar para que possa trabalhat com as datas
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
			Gson gson = gsonBuilder.create();
			
			NFE nfe = gson.fromJson(jSonApp, NFE.class);	
		    super.salvarAtualizar(nfe);
		    
		//} catch (Exception e) {
	//		vRetorno = "-99 - " + e.getMessage() ;
	//	}
		

		return vRetorno;
			
	}
    
    
    @RequestMapping(value="downloadxml", method=RequestMethod.GET)
	public void downloadXML(HttpServletRequest request, HttpServletResponse response, @RequestParam("idNfe") String idNfe) 
			                            throws Exception {
		
	  //obtendo o caminho real da aplicacao
		ServletContext context = request.getServletContext();
		
		
		List<NFE> listXml = (List<NFE>) HibernateUtilHQL.getListSqlHQL("from NFE as a where a.id in (" + idNfe + ")");
		//List<String> listXml = (List<String>) HibernateUtilHQL.getListSqlHQL("select a.xmlNFE from NFE as a where a.id in (1,3)");
		
		String xml ="";
		String nomeArquivo ="";
		String nomeArquivoZip ="NFEXml.zip";
		String nomeArquivoXML ="NFEXml.zip";
		String chaveNFE = "";
		List<File> files = new ArrayList<>();
		
		
		for (int i=0; i < listXml.size(); i++) {
			xml = listXml.get(i).getXmlNFE();
			chaveNFE = listXml.get(i).getChaveNFE();
			
			if (!isStringEmpty(chaveNFE)) {
				nomeArquivoXML = chaveNFE;
			} else {
				nomeArquivoXML = "NFECanal" + i;
			}
			nomeArquivo = XMLUtils.gerarArquivoXML(xml, nomeArquivoXML + ".xml");
			files.add(new File(nomeArquivo));
		}

		File arquivoZip = XMLUtils.zipFiles(files,"NFECanal.zip" );
		
	    String filePath = arquivoZip.getName();
		      
	   //constrindo o caminho completo do arquivo 
		File downloadFile = new File(filePath);
		FileInputStream inputStream = new FileInputStream(downloadFile);
		
		//obter o tipo MIME do arquivo
		String mimeType = context.getMimeType(filePath);
		if (mimeType==null) {
			//definindo como tipo binario de mapeamento MIME não encontrado
			mimeType = "application/octet-stream";
		}
		
		//definir atributos de conteudos para a resposta
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());
		
		//definir cabeclho para resposta
		String headKey = "Content-Disposition";
		String headValue = String.format("attachment;filename=\"%s\"", downloadFile.getName());
		response.setHeader(headKey,headValue);
		
		//obter fluxo de saida da resposta
		OutputStream outStream = response.getOutputStream();
		
		byte[] buffer = new byte [BUFFER_SIZE];
		int bytesRead = -1;
		
		//escrever os bytes lidos a partir do fluxo de entrada para o fluxo de saida
		while ((bytesRead = inputStream.read(buffer))!=-1) {
			outStream.write(buffer, 0 , bytesRead);
		}
		
		inputStream.close();
		outStream.close();
		
	}
	
	

}
