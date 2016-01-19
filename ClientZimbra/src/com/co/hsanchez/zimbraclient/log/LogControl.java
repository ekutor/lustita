package com.co.hsanchez.zimbraclient.log;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.MissingResourceException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.xml.DOMConfigurator;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.co.hsanchez.zimbraclient.Util;



public class LogControl {
	private Logger logError,logDebug,logWarning,logTrace;
	private final String log4jFile = "properties/log4j.xml";
	private static LogControl lgc;
	
	private LogControl(){
		//BasicConfigurator.configure();
		try {			
			cargarConfigLog4j();
			logError =Logger.getLogger("LoggerError");
			logDebug =Logger.getLogger("LoggerDebug");
			logWarning =Logger.getLogger("LoggerWarning");
			logTrace =Logger.getLogger("LoggerTrace");
		} catch (MissingResourceException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("No se encuentra el archivo de configuracion log4j");
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public static LogControl getLog(){
		
		if(lgc == null){
			lgc = new LogControl();
		}		
		return lgc;
	}
	
	public void addTrace(String mensaje){
		logTrace.trace(mensaje);
	}
	
	public void addDebug(String mensaje){
		logDebug.debug(mensaje);
	}
	
	public void addInfo(String mensaje){
//		log.info(mensaje);
	}
	
	public void addWarn(String mensaje){
		logWarning.warn(mensaje);
	}
	
	public void addError(String mensaje){
		logError.error(mensaje);
	}
	
	public void addFatal(String mensaje){
//		log.fatal(mensaje);
	}
		
	/**
	 * Método para cargar la configuración del Log
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws URISyntaxException 
	 * @Warning el método requiere una carga inicial de los parámetros de la aplicación
	 * @throws Exception
	 */
	public void cargarConfigLog4j() throws /*Exception*/ MissingResourceException,
	SAXException, IOException, ParserConfigurationException, URISyntaxException{
		
		File jarDir = Util.getParentDirPathClass();
		
		if (jarDir != null && jarDir.isDirectory()) {
			File log4j = Util.getFile(log4jFile);

			if(log4j != null){ 
				LoggerRepository hierarchy = LogManager.getLoggerRepository();
				
				InputStream log4JConfig = new FileInputStream(log4j);
				
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(log4JConfig);
				DOMConfigurator domConf = new DOMConfigurator();
				domConf.doConfigure(doc.getDocumentElement(), hierarchy);
			}
		}
	}
}
