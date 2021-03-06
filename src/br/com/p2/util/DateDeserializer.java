package br.com.p2.util;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.sun.el.parser.ParseException;

public class DateDeserializer implements JsonDeserializer<Date>{

	
		  @Override
		  public Date deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		      String date = element.getAsString();

		      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		      //format.setTimeZone(TimeZone.getTimeZone("GMT"));

		      try {
		          return format.parse(date);
		      } catch (java.text.ParseException exp) {
		          System.out.println("Failed to parse Date:" + exp.getMessage());
		          return null;
		      }
		   }
	
	
}
