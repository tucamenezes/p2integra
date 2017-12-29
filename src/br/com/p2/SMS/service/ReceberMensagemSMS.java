package br.com.p2.SMS.service;




import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.client.RestTemplate;

import br.com.p2.util.SimpleClientHttpRequestWithGetBodyFactory;




public abstract class ReceberMensagemSMS {
	  
	
	  public static String receberMensagemOnline() {
		  
		  String uri = "https://api-rest.zenvia360.com.br/services/received/search/2017-12-01T00:00:00/2017-12-30T23:59:59";
			
		  RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestWithGetBodyFactory());
		  
		  HttpHeaders headers = createHeaders("p2tecnologia","p2tec00");
		  headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		  
		  HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		  
		  ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
		  
		  System.out.println(result);
		  
		  //ReceivedMessage mensagemSMS = new Gson().fromJson(result.toString(), ReceivedMessage.class);
		  
		  return result.toString();
	  }
	  
	  
	  
	  private static HttpHeaders createHeaders(String username, String password) {
		   
		  return new HttpHeaders() {{
		         String auth = username + ":" + password;
		         byte[] encodedAuth = Base64.encodeBase64( 
		            auth.getBytes(Charset.forName("US-ASCII")) );
		         String authHeader = "Basic " + new String( encodedAuth );
		         set( "Authorization", authHeader );
		      }};
		}

}
