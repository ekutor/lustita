package com.co.hsanchez.zimbraclient.log;



public class LogInfo extends Throwable {
	
	private LogControl lc;
	private static final long serialVersionUID = 102603153538L;
	
	public LogInfo(String mensaje, LogType tipo) {
        super(mensaje);	
        lc = LogControl.getLog();
        mensaje = makeFormat(mensaje);
        
        switch(tipo){
        	case Trace:{
        		lc.addTrace(mensaje);
        		break;
        	}
        	case Debug:{
        		lc.addDebug(mensaje);
        		break;
        	}
        	case Warning:{
        		lc.addWarn(mensaje);
        		break;
        	}
        	case Error:{
        		lc.addError(mensaje);
        		break;
        	}
        	case Fatal:{
        		lc.addFatal(mensaje);
        		break;
        	}
        }
        
        
    }
	
	/*
	 * Genera una excepcion propia para el almacenamiento de la misma en los logs
	 */
	public LogInfo(String mensaje, LogType tipo, Class clase) {    
        this(clase+"->"+mensaje, tipo);
    }
	
	private String makeFormat(String mensaje) {
		String finalMessage = " ";
		//finalMessage = formatter(mensaje.length());
		finalMessage += mensaje;
		return finalMessage;
	}
	
	@SuppressWarnings("unused")
	private String formatter(int length){
		String format = "*";
		String finalMessage = "";
		for(int i=0; i<5;i++){
			finalMessage = finalMessage + format;
		}
		return finalMessage;
	}
	
	
	public static void T(String mensaje){
		new LogInfo( mensaje, LogType.Trace);
	}
	
	public static void E(String mensaje){
		new LogInfo( mensaje, LogType.Error);
	}
}
