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
		// TODO Auto-generated constructor stub
		super(persistenceClass);
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
		
		if (dataInicial=="") {
			dtAux = Calendar.getInstance().getTime();
			cadAux.add(Calendar.DATE,-30);
			dataInicial = df.format(cadAux.getTime());
		}
		
		if (dataFinal=="") {
			dtAux = Calendar.getInstance().getTime();
			dataFinal= df.format(dtAux);
		}
		
		
		String vSqlEmpresa ="";
		
		if (idFornecedor.equals("0")){
			vSql= "select a.id, a.id_nfe_externo, e.nome from canal_nfe_xml a, empresas e where a.id_empresa = e.id and a.id_conta = " + usuarioLogado.getConta().getId() + 
                    " and a.data>= '"+ dataInicial + "' and a.data<= '"+ dataFinal  + "' ";
	        } else {
	          	vSql= "select a.id, a.id_nfe_externo, e.nome from canal_nfe_xml a, canal_fornecedores_nfe b, empresas e where a.id = b.id_nfe and a.id_empresa = e.id and b.id_fornecedor = " + idFornecedor  + " and a.id_conta = " + usuarioLogado.getConta().getId() + 
	                    " and a.data>= '"+ dataInicial + "' and a.data<= '"+ dataFinal  + "' ";
	        }
		
		if (idEmpresa.equals("0")){
			vSqlEmpresa= "";
	        } else {
	          	vSqlEmpresa= " and a.id_empresa = " + idEmpresa;
	        }
		
		
		vSql = vSql + vSqlEmpresa;
		
		System.out.println(vSql);
		
		List<?> listAux =  HibernateUtilHQL.getListSqlDinamico(vSql);
		
		for (int i=0; i< listAux.size(); i++ ) {
			NFE nfe = new NFE();
			Object[] obj = (Object[]) listAux.get(i);
			nfe.setId(Long.parseLong(obj[0].toString()));
			nfe.setIdNfeExterno(Integer.parseInt(obj[1].toString()));
			nfe.setNomeEmpresa(obj[2].toString());
			listRetorno.add(nfe);
			
		}
		
      	return new Gson().toJson(listRetorno);
		
	}
   
   
   
   
   @RequestMapping(value="listarFornecedores", method=RequestMethod.GET,headers="Accept=application/json")
   @ResponseBody
   public String listarFornecedores() throws Exception {
		
		
       Usuarios usuarioLogado = HibernateUtilHQL.buscaDadosUsuarioLogado();

       
		return new Gson().toJson(HibernateUtilHQL.getListSqlHQL("from Fornecedor as a where conta.id = " + usuarioLogado.getConta().getId()));
		
	}
   
   
    @SuppressWarnings("rawtypes")
	@RequestMapping(value="salvar", method=RequestMethod.POST)
	public @ResponseBody String salvarApp(@RequestBody  String jSonApp) throws Exception {
	
      //System.out.println(jSonApp);	
    	  String vRetorno = "0";
	try {
			//Definir no Gson o novo deseserializar para que possa trabalhat com as datas
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.create();

			NFE nfe = gson.fromJson(jSonApp, NFE.class);
			
			System.out.println("Fornecedores : " + nfe.getFornecedores().get(0).getNome());
			System.out.println("Fornecedores : " + nfe.getDataSaida());
			
			
		    super.salvarAtualizar(nfe);
		    
		} catch (Exception e) {
			vRetorno = "-99 - " + e.getMessage() ;
		}
		

		return vRetorno;
			
	}
    
    
    private boolean isFornecedorExiste (Fornecedor fornecedor) throws Exception {
    	
		Fornecedor retorno =  (Fornecedor) HibernateUtilHQL.getListSqlHQL("from Fornecedor f where f.id= " + fornecedor.getId()).get(0);
    	
    	return retorno!=null;
    	
    }
    
    @RequestMapping(value="downloadxml", method=RequestMethod.GET)
	public void downloadXML(HttpServletRequest request, HttpServletResponse response, @RequestParam("idNfe") String idNfe) 
			                            throws Exception {
		
	  //obtendo o caminho real da aplicacao
		ServletContext context = request.getServletContext();
		
		
		//List<String> listXml = (List<String>) HibernateUtilHQL.getListSqlHQL("select a.nfeXml from NFE as a where a.id in ('" + idNfe + "')");
		List<String> listXml = (List<String>) HibernateUtilHQL.getListSqlHQL("select a.nfeXml from NFE as a where a.id in (1,3)");
		
		String xml ="";
		String nomeArquivo ="";
		String nomeArquivoZip ="NFEXml.zip";
		List<File> files = new ArrayList<>();
		
		
		for (int i=0; i < listXml.size(); i++) {
			xml = listXml.get(i);
			nomeArquivo = XMLUtils.gerarArquivoXML(xml, "NFECanal"+i+".xml");
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
