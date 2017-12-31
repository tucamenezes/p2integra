package br.com.p2.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.p2.hibernate.HibernateUtil;
import br.com.p2.hibernate.HibernateUtilHQL;
import br.com.p2.model.avaliacao.Avaliacao;
import br.com.p2.util.DadosUsuarioLogadoUtil;
import br.com.p2.util.ReportUtil;
import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping(value="/imprimirAuditoria")
public class FileDownloaderController {
	
	@Autowired
	private ReportUtil reportUtil;
	
	@Autowired
	private AvaliacaoController avaliacaoController;
	
	
	private static final int BUFFER_SIZE = 4096;
	
	private String filePath = "";
	
	
	@RequestMapping(value="avalia", method=RequestMethod.GET)
	public void downloadAuditoriaAvalia(HttpServletRequest request, HttpServletResponse response, @RequestParam("status") String status,
			                            @RequestParam("dataInicial") String dataInicial, @RequestParam("dataFinal") String dataFinal) 
			                            throws Exception {
		
	  //obtendo o caminho real da aplicacao
		ServletContext context = request.getServletContext();
		
		
		ArrayList<Avaliacao> avaliacoes = (ArrayList<Avaliacao>)  avaliacaoController.listarAvaliacoes(dataInicial, dataFinal);
		HashMap parameters = new HashMap();
		parameters.put("data_inicial",dataInicial);
		parameters.put("data_final",dataFinal);
	    parameters.put("status",status);
	
		
		//pega o caminho do arquivo e monta o relatorio
		filePath = reportUtil.gerarRelatorio (avaliacoes, parameters, "auditoriaAvaliacoes", "auditoriaAvaliacoes", context);
		
      
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
	
	
	@RequestMapping(value="orcamento", method=RequestMethod.GET)
	public void downloadAuditoriaOrcamento(HttpServletRequest request, HttpServletResponse response, @RequestParam("status") String status,
			                              @RequestParam("dataInicial") String dataInicial, @RequestParam("dataFinal") String dataFinal) 
			                              throws Exception {
		
	  //obtendo o caminho real da aplicacao
		ServletContext context = request.getServletContext();
		
		HashMap parameters = new HashMap();
		parameters.put("data_inicial",dataInicial);
		parameters.put("data_final",dataFinal);
	    parameters.put("status",status);
	    parameters.put("idConta",DadosUsuarioLogadoUtil.buscaDadosUsuarioLogado().getConta().getId());
	
		
		//pega o caminho do arquivo e monta o relatorio
		filePath = reportUtil.gerarRelatorio (null, parameters, "auditoriaOrcamentos", "auditoriaOrcamentos", context);
		
      
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
	
	@RequestMapping(value="desconto", method=RequestMethod.GET)
	public void downloadAuditoriaDesconto(HttpServletRequest request, HttpServletResponse response, @RequestParam("status") String status,
			                              @RequestParam("dataInicial") String dataInicial, @RequestParam("dataFinal") String dataFinal) 
			                              throws Exception {
		
	  //obtendo o caminho real da aplicacao
		ServletContext context = request.getServletContext();
		
		HashMap parameters = new HashMap();
		parameters.put("data_inicial",dataInicial);
		parameters.put("data_final",dataFinal);
	    parameters.put("status",status);
	    parameters.put("conta",DadosUsuarioLogadoUtil.buscaDadosUsuarioLogado().getConta().getId().toString());
	
		
		//pega o caminho do arquivo e monta o relatorio
		filePath = reportUtil.gerarRelatorio (null, parameters, "auditoriaDescontos", "auditoriaDescontos", context);
		
      
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
