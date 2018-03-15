package br.com.p2.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.hssf.record.formula.functions.Replace;

public class XMLUtils implements Serializable{

	private static final long serialVersionUID = 1L;
	 static final int TAMANHO_BUFFER = 4096; // 4kb
	
	public static String gerarArquivoXML(String textoXml, String nomeArquivo) throws IOException {
		
		FileWriter arquivo;

			arquivo = new FileWriter(new File(nomeArquivo));
			arquivo.write(textoXml);
			arquivo.close();
			
		 

		return nomeArquivo;
	}
	
	 //método para compactar arquivo
	 public static String compactarArquivo(String arqSaida,String arqEntrada) throws IOException{
	       int cont;
	       byte[] dados = new byte[TAMANHO_BUFFER];
	                   
	       BufferedInputStream origem = null;
	       FileInputStream streamDeEntrada = null;
	       FileOutputStream destino = null;
	       ZipOutputStream saida = null;
	       ZipEntry entry = null;
	              
	            destino = new FileOutputStream(new File(arqSaida));
	            saida = new ZipOutputStream(new BufferedOutputStream(destino));

	            
	            File file = new File(arqEntrada);
	            streamDeEntrada = new FileInputStream(file);
	            origem = new BufferedInputStream(streamDeEntrada, TAMANHO_BUFFER);
	            entry = new ZipEntry(file.getName());
	            saida.putNextEntry(entry);
	                       
	                while((cont = origem.read(dados, 0, TAMANHO_BUFFER)) != -1) {
	                  saida.write(dados, 0, cont);
	                }
	            origem.close();
	            saida.close();
	 
	            return arqSaida;
	        
	   }
	 
	 
	 //método para compactar arquivo
	 public static File zipFiles (List<File> files, String filename) throws IOException{
		 File zipfile = new File(filename);
		    // Create a buffer for reading the files
		    byte[] buf = new byte[1024];

		        // create the ZIP file
		        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
		        // compress the files
		        for(int i=0; i<files.size(); i++) {
		            FileInputStream in = new FileInputStream(files.get(i).getCanonicalFile());
		            // add ZIP entry to output stream
		            out.putNextEntry(new ZipEntry(files.get(i).getName()));
		            // transfer bytes from the file to the ZIP file
		            int len;
		            while((len = in.read(buf)) > 0) {
		                out.write(buf, 0, len);
		            }
		            // complete the entry
		            out.closeEntry();
		            in.close();
		        }
		        // complete the ZIP file
		        out.close();
		       
		        return zipfile;
		}
}
