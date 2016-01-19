package com.co.hsanchez.zimbraclient.io;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.co.hsanchez.zimbraclient.Util;


public class FileManager {
	private Properties prop;
	private InfoDB bean;
	private final String FILE = "properties/config.properties";
	
	public FileManager(){
		this.bean = new InfoDB();
		prop = new Properties();
		try {
			prop.load(new FileInputStream(Util.getFile(FILE)));
		} catch (Exception e) {
//			new LogException("No Existe el Archivo "+FILE,LogType.Error,this.getClass());
					}
		
	}
	/*
	 * Metodo para cargar los datos de los correos 
	 * establecidos en el properties al bean
	 */
	public InfoDB chargeProps(){
		bean.setHost(prop.getProperty("host"));
		bean.setUser(prop.getProperty("user"));
		bean.setPassw(prop.getProperty("passw"));
		bean.setPort(prop.getProperty("port"));
		bean.setDb(prop.getProperty("db"));
		bean.setAdds(prop.getProperty("adds"));
		return bean;
	}
	
	
	

}
