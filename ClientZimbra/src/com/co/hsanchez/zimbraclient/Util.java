package com.co.hsanchez.zimbraclient;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import com.co.hsanchez.zimbraclient.log.LogInfo;

public class Util {

	public enum DateType{
		START, END;
	} 
	
	public static String fechaInicial,fechaFinal;
	
	public static void main(String[] args ){
		getMeetingDate(Calendar.getInstance());
	}
	public static String getMeetingDate(Calendar date){
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat stime= new SimpleDateFormat("HHmmss");
		String fecha = sd.format(date.getTime());
		fecha += "T"+stime.format(date.getTime());
		System.out.println("Fecha Calculada "+ fecha);
		return fecha;
	}
	
	public static Calendar convertToCal(String date){
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		
		try {
			c.setTime(sd.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public static Calendar convertToCalMeet(String date){
		System.out.println("Fecha BD "+date);
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy hh:mma");
		Calendar c = Calendar.getInstance();
		try {
			
			c.setTime(sd.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public static Calendar getInitDate(int time){
		SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
		Calendar c = Calendar.getInstance();
		
		int mes = c.get(Calendar.MONTH);
		int anho = c.get(Calendar.YEAR);
//		if(mes == 0){
//			mes = 11;
//			anho --;
//		}else{
//			mes--;
//		}
		c.set(Calendar.YEAR, anho);
		c.set(Calendar.MONTH, mes);
		if(time == 0){
			c.set(Calendar.DATE, 1);
		}else{
			c.add(Calendar.DATE, -3);
		}
		
		fechaInicial = sd.format(c.getTime());
		LogInfo.T("Fecha Inicial: "+fechaInicial);
		return c;
	}
	
	public static String getInitDateString(Calendar c){
		SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
		return sd.format(c.getTime());
	}
	
	public static Calendar getEndDate(int time){
		SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
		Calendar c = Calendar.getInstance();
		if(time == 0){
			int mes = c.get(Calendar.MONTH);
			int anho = c.get(Calendar.YEAR);
			if(mes == 11){
				mes = 0;
				anho ++;
			}else{
				mes++;
			}
			c.set(Calendar.YEAR, anho);
			c.set(Calendar.MONTH, mes);
			c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
		}else{
			c.add(Calendar.DATE, 13);
		}
		
		fechaFinal = sd.format(c.getTime());
		LogInfo.T("Fecha Final: "+fechaFinal);
		return c;
	}
	
	public static String getLargeDate(DateType type, int time){
		SimpleDateFormat sd = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		String date = null;
		switch(type){
			case START:
				Calendar c =getInitDate(time);
				date = sd.format(c.getTime());
				break;
			case END:
				Calendar c2 =getEndDate(time);
				date = sd.format(c2.getTime());
				break;
		}

		return date;
	}

	public static String calculateHour(Long s) {
		//s +=   (5 * 3600); // GMT +5
		//Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Bogota"));
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Bogota"));
		c.setTimeInMillis(s);
		SimpleDateFormat sf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		c.add(Calendar.HOUR,  5 ); // toca agregarselo porque el sugar esta quitandolas arreglar SUGAR
		return sf.format(c.getTime());
	}
	
	
	public static File getParentDirPathClass(){
		CodeSource codeSource = Util.class.getProtectionDomain().getCodeSource();
		File jarFile = null;
		File jarDir = null;
		try {
			jarFile = new File(codeSource.getLocation().toURI().getPath());
			jarDir = jarFile.getParentFile();
		} catch (URISyntaxException e) {
			System.out.println("No se puede obtener la ruta del directorio padre");
			e.printStackTrace();
		}
		
		
		return jarDir;
	}
	
	public static File getFile(String name){
	
		File dir = Util.getParentDirPathClass();
		File file = null;
		if(dir!=null && dir.isDirectory()){
			file = new File(dir.getAbsolutePath(),name);
		}
	
		return file;
	}
	
	public static String getAbsolutePath(String resource){
		CodeSource codeSource = Util.class.getProtectionDomain().getCodeSource();
		File jarFile = null;
		File file = null;

		try {
			jarFile = new File(codeSource.getLocation().toURI().getPath());	
			file = new File(jarFile.getParentFile().getAbsolutePath(),resource);
	
		} catch (URISyntaxException e) {
			System.out.println("No se puede obtener la ruta del archivo");
			e.printStackTrace();
		}
		
		return file.getAbsolutePath();
	}
	
	public static String errorToString(Exception exc) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		exc.printStackTrace(pw);
		
		return sw.getBuffer().toString();
	}
	
	public static String convertChars(String password) {
		Map<String,String> converter = new HashMap<String,String>();
		converter.put("&#039;", "'");
		converter.put("&#034;", "\"");
		converter.put("&quot;", "\"");
		converter.put("&apos;", "'");
		converter.put("&#47;", "/");
		converter.put("&#60;", "<");
		converter.put("&#61;", "=");
		converter.put("&#62;", ">");
		converter.put("&#64;", "@");
		converter.put("&#38;", "&");
		converter.put("&amp;", "&");
		
		Iterator<Entry<String, String>> it = converter.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, String> data = it.next();
			if( password.contains(data.getKey()) ){
				password = password.replace(data.getKey() , data.getValue() );
				break;
			}
		}
		
		return password;
	}
}
