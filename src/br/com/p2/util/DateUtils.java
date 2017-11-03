package br.com.p2.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static String getDateAtualString(String formato) {
		
		DateFormat df = new SimpleDateFormat(formato);
		
		return df.format(Calendar.getInstance().getTime());
		
		
	}
	
    public static String getDateString(Date data, String formato) {
		
		DateFormat df = new SimpleDateFormat(formato);
		
		return df.format(data);
		
		
	}

}
