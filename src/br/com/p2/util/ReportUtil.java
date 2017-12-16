package br.com.p2.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;

import br.com.p2.hibernate.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;


@Component
public class ReportUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private static final String FOLDER_RELATORIOS = "/relatorios";
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR"; 
	
	private String SEPARATOR = File.separator;
	private String caminhoArquivoRelatorio = null;
	private JRExporter tipoArquivoExportado = null;
	private String caminhoSubreport_Dir = "";
	private File arquivoGerado = null;
	
	@SuppressWarnings({"rawtypes, unchecked"})
	public String gerarRelatorio (List<?> listDataBeanColletionReport, HashMap parametrosRelatorios, 
			              String nomeRelatorioJasper, String nomeRelatorioSaida, ServletContext servletContext) throws Exception {
							
		//criar lista de colecoes de beans que carregem dados para o relatorio
		//comentei pois no momento o rel tem o sql interno.
		//JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listDataBeanColletionReport);
		
		//fornece o caminho fisico até a pasta 	que contem os relatorios. Compilador .jasper.
		
		String caminhoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);
		File file = new File(caminhoRelatorio+SEPARATOR+nomeRelatorioJasper+".jasper");
		
		if (caminhoRelatorio==null || (caminhoRelatorio==null&&caminhoRelatorio.isEmpty()) || !file.exists()) {
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
		}
		
		//caminho para as imagens
		parametrosRelatorios.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);
		
		//caminho completo ate o relatorio indicado
		String caminhoArquivoJasper = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper";
		
		//faz o carregamento do relatorio
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivoJasper);
		
		//Seta o parametro SUBREPORT_DIR com o caminho fisico para os SUBREPORTS
		caminhoSubreport_Dir = 	caminhoRelatorio + SEPARATOR;
		parametrosRelatorios.put("SUBREPORT_DIR", caminhoSubreport_Dir);
		
		//carrega o arquivo compilado para memoria
	    //JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorios, jrbcds);
	    JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorios, HibernateUtil.getConnection());
	    
	    tipoArquivoExportado = new JRPdfExporter();
	    
	    //caminho relatorio exportado 
	    caminhoArquivoRelatorio = caminhoRelatorio + SEPARATOR + nomeRelatorioSaida+".pdf";
	    
	    //cria o novo arquivo exportado
	    arquivoGerado = new File(caminhoArquivoRelatorio);
	    
	    //prepara a impresso
	    tipoArquivoExportado .setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
	    
	    tipoArquivoExportado .setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
	    
	    tipoArquivoExportado .exportReport();
	    
	    //remove o arquivo do servidor apos download
	    arquivoGerado.deleteOnExit();
	    
		
		return caminhoArquivoRelatorio;
		
	}
	
	

	
	
}
