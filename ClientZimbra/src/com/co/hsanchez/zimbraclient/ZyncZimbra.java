package com.co.hsanchez.zimbraclient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import javax.xml.ws.BindingProvider;

import zimbra.AccountBy;
import zimbra.AccountSelector;
import zimbraaccount.AuthRequest;
import zimbramail.CalTZInfo;
import zimbramail.CancelAppointmentRequest;
import zimbramail.CancelAppointmentResponse;
import zimbramail.CreateAppointmentRequest;
import zimbramail.CreateAppointmentResponse;
import zimbramail.GetMsgRequest;
import zimbramail.GetMsgResponse;
import zimbramail.ModifyAppointmentRequest;
import zimbramail.ModifyAppointmentResponse;
import zimbramail.MsgSpec;
import zimbramail.SearchRequest;
import zimbramail.SearchResponse;

import com.co.hsanchez.zimbraclient.db.conect.DBManagerDAO;
import com.co.hsanchez.zimbraclient.db.dto.NotaZimbra;
import com.co.hsanchez.zimbraclient.db.dto.User;
import com.co.hsanchez.zimbraclient.handlerSOAP.HeaderHandler;
import com.co.hsanchez.zimbraclient.handlerSOAP.HeaderHandlerResolver;
import com.co.hsanchez.zimbraclient.log.LogInfo;
import com.zimbra.wsdl.zimbraservice.ZcsPortType;
import com.zimbra.wsdl.zimbraservice.ZcsService;

public class ZyncZimbra {

	private ZcsPortType port;
	private String token;
	private String urlZimbra = "https://laumail.laumayer.com/service/wsdl/ZimbraService.wsdl";
	private DBManagerDAO db;

	public ZyncZimbra() {
		db = new DBManagerDAO();//
	}

	public void createConex(String urlString) {
		try {
			URL url;
			if(token != null && token.length() > 10){
				HeaderHandler.TOKEN = token;
			}
			if (urlString != null) {
				url = new URL(urlString);
			} else {
				url = new URL(urlZimbra);
			}
			ZcsService service = new ZcsService(url);
			HeaderHandlerResolver handlerResolver = new HeaderHandlerResolver();

			service.setHandlerResolver(handlerResolver);

			port = service.getZcsServicePort();
			((BindingProvider) port).getRequestContext().put(
					"javax.xml.ws.client.connectionTimeout", 90000);
			((BindingProvider) port).getRequestContext().put(
					"javax.xml.ws.client.receiveTimeout", 90000);
			
			((BindingProvider) port).getRequestContext().put(
					"javax.xml.ws.client.receiveTimeout", 90000);
		} catch (MalformedURLException ex) {
			LogInfo.E("Excepcion:: ");
			LogInfo.E(Util.errorToString(ex));
		}
	}
		

	public String getAuth(User u ) {
		
		AuthRequest ar = new AuthRequest();
		LogInfo.T("getAuth:: ");
		AccountSelector as = new AccountSelector();
		as.setBy(AccountBy.NAME);
		as.setValue(u.getUser());
		ar.setAccount(as);
		ar.setPassword(u.getPassword());
		createConex(null);
		try{
			
			LogInfo.T("Intento :: passsw:"+ar.getPassword());
			zimbraaccount.AuthResponse resp = port.authRequest(ar);
			token = resp.getAuthToken();
			LogInfo.T("Token obtenido:: "+token);
			u.setToken(token);
			db.saveToken(u);
		}catch(Exception se){
			LogInfo.E("Excepcion get AUth:: ");
			LogInfo.E(Util.errorToString(se));
			
			String p = db.getPassW(u); 
			p = Util.convertChars(p);
			u.setPassword(p);
			LogInfo.T("passw:: "+p);
			//cont++;
			getAuth(u);
			
		}
		return token;
	}
	/**
	 * Metodo que sincroniza el calendario de Zimbra con Sugar
	 * @param u
	 * @return
	 */
	public String syncCalendar(User u, int time) {
		
		LogInfo.T("syncCalendar:: ");
		SearchResponse sr = this.getCalendarfromActualUser(time);
		String resp = "NO Sincronizado";
		try {
			List<NotaZimbra> meetsZimbra = db.saveCalendar(sr, u, time);
			if(meetsZimbra != null && meetsZimbra.size() >0){
				for(NotaZimbra meet :meetsZimbra ){
					GetMsgResponse r = getMeetDetailFromZimbra(meet.getIdZimbra());
					db.saveMeet(r , u, meet.getId());
				}
				resp = "Sincronizado";
			}
		} catch (Exception e) {
			LogInfo.E("Excepcion:: ");
			LogInfo.E(Util.errorToString(e));
		}
		LogInfo.T("syncCalendar:: "+ resp);
		return resp;
	}
	private String createMeeting(User user, String idMeet) {
			createConex(null);
			CreateAppointmentRequest meet = db.getMeet(idMeet, user);
			CreateAppointmentResponse resp = saveZimbraMeet(meet);
			//db.deleteTempMeet(idMeet);
			LogInfo.T("zimbra id : " +resp.getApptId() + " "+ resp.getCalItemId() + " "+ resp.getInvId());
			db.updateMeet(resp ,  DBManagerDAO.actualMeet);
		return null;
	}
	
	private String modifyMeeting(User user, String idMeet) {
		createConex(null);
		ModifyAppointmentRequest meet = db.getModifiedMeet(idMeet, user);
		if(meet != null){
			ModifyAppointmentResponse resp = saveZimbraMeet(meet);
			LogInfo.T("modificada zimbra id : " +resp.getApptId() + " "+ resp.getCalItemId() + " "+ resp.getInvId());
		}
	return null;
	}
	
	private String deleteMeeting(User user, String idMeet) {
		createConex(null);
		CancelAppointmentRequest meet = db.getDeletedMeet(idMeet, user);
		if(meet != null){
			CancelAppointmentResponse resp = saveZimbraMeet(meet);
			LogInfo.T("Eliminada : " );
		}
	return null;
	}

	public GetMsgResponse getMeetDetailFromZimbra(String idMeet) {
		
		GetMsgRequest mgr= new GetMsgRequest();
		
		MsgSpec message = new MsgSpec();
		message.setId(idMeet);
		message.setNeedExp(true);
		
		mgr.setM(message);
		createConex(null);
		GetMsgResponse resp = port.getMsgRequest(mgr);
		return resp;

	}
	
	public CancelAppointmentResponse saveZimbraMeet(CancelAppointmentRequest ar) {
		LogInfo.T("Enviando Reunion de ELIMINACION  a Zimbra:: ");
		CancelAppointmentResponse resp = port.cancelAppointmentRequest(ar);
		LogInfo.T("Respuesta Zimbra :: ");
		return resp;
	}
	
	public ModifyAppointmentResponse saveZimbraMeet(ModifyAppointmentRequest ar) {
		LogInfo.T("Enviando Reunion de modificacion  a Zimbra:: ");
		ModifyAppointmentResponse resp = port.modifyAppointmentRequest(ar);
		LogInfo.T("Respuesta Zimbra :: "+ resp.getApptId() );
		return resp;
	}
	
	public CreateAppointmentResponse saveZimbraMeet(CreateAppointmentRequest ar) {
		LogInfo.T("Enviando Reunion a Zimbra:: ");
		CreateAppointmentResponse resp = port.createAppointmentRequest(ar);
		LogInfo.T("Respuesta Zimbra :: "+ resp.getApptId() );
		return resp;

	}
	
	public String getToken(User u) {
		
		LogInfo.T("obteniendo Token:: ");
		try {
			return db.getToken(u);
		} catch (Exception e) {
			LogInfo.E("Excepcion:: ");
			LogInfo.E(Util.errorToString(e));
		}
		LogInfo.T("Token NO Obtenido OK:: ");
		return "";
	}

	private String print(CreateAppointmentRequest ar) {
		StringBuilder sb = new StringBuilder();
		
		return null;
	}
	
	/**
	 * Obtiene el calendario de zimbra desde una fecha dada hasta  un mes para un usuario
	 * @return
	 */
	public SearchResponse getCalendarfromActualUser(int time) {
		LogInfo.T("getCalendar:: ");
		Calendar dateIni = Util.getInitDate(time);
		Calendar dateFin = Util.getEndDate(time);
		SearchRequest sr = new SearchRequest();

		sr.setTypes("appointment");
		sr.setSortBy("none");
		sr.setLimit(500);
		sr.setCalExpandInstStart(dateIni.getTimeInMillis());
		sr.setCalExpandInstEnd(dateFin.getTimeInMillis());
		
		LogInfo.T("Fecha Inicial: "+sr.getCalExpandInstStart());
		sr.setOffset(0);
		LogInfo.T("Fecha Final: "+sr.getCalExpandInstEnd());

		CalTZInfo ct = new CalTZInfo();
		ct.setId("America/Bogota");
		ct.setStdoff(-300);
		sr.setTz(ct);

		sr.setLocale("es");
		sr.setQuery("inid:10");

		createConex(null);
		LogInfo.T("llamando al servicio");
		SearchResponse resp = port.searchRequest(sr);
		LogInfo.T("llamando finalizado");
		return resp;

	}

	public static void main(String[] args) {
		User user = new User();
		String resp = "";
		String meetID = "";
		LogInfo.T("Iniciando Proceso ZyncZimbra");
		try{
			if (args != null && args.length > 0) {
				String arRec= "";
				for(String a : args){
					arRec += a+" ";
				}
				LogInfo.T("Args Recibidos:: "+ arRec);
				int operacion = Integer.parseInt(args[0]);
				ZyncZimbra zz = new ZyncZimbra();
	
				user.setIdSugar(args[1]);
				user.setUserName(args[2]);
				LogInfo.T("Operacion Recibida "+operacion);
				switch (operacion) {
				case 1: // SINCRONIZAR
					user.setUser(args[3]);
					user.setPassword(args[4]);
					LogInfo.T("User:: "+user);
					zz.getAuth(user);
					resp = zz.syncCalendar(user,0);
					break;
				case 2: // sincronizar por token
					user.setToken(args[3]);
					LogInfo.T("User:: "+user);
					if(user.getToken() == null || user.getToken().length() < 40){
						String token = zz.getToken(user);
						HeaderHandler.TOKEN = token;
						user.setToken(token);
					}
					HeaderHandler.TOKEN = user.getToken();
					resp = zz.syncCalendar(user,1);
					break;
				case 3: //Crear Reunion en Zimbra
					user.setToken(args[3]);
					meetID = args[4];
					LogInfo.T("User:: "+user);
					HeaderHandler.TOKEN = user.getToken();
					resp = zz.createMeeting(user, meetID);
					break;
				case 4: // AUTENTICAR
					user.setUser(args[3]);
					user.setPassword(args[4]);
					LogInfo.T("User:: "+user);
					resp = zz.getAuth(user);
					break;
				case 5: //Modificar Reunion en Zimbra
					user.setToken(args[3]);
					meetID = args[4];
					LogInfo.T("User:: "+user);
					HeaderHandler.TOKEN = user.getToken();
					resp = zz.modifyMeeting(user, meetID);
					break;
				case 6: //Eliminar Reunion en Zimbra
					user.setToken(args[3]);
					meetID = args[4];
					LogInfo.T("User:: "+user);
					HeaderHandler.TOKEN = user.getToken();
					resp = zz.deleteMeeting(user, meetID);
					break;
				}
				
				
			}else{
				user.setIdSugar("c96d5b39-5390-a195-d3c0-54ca97040fec");			
				user.setUserName("Luz");
				user.setToken("0_7a0cc4c0d5c09b2b9a484218a54b0cad8052d95e_69643d33363a33666261396162632d323263622d346463392d383865332d3934643564383061363935383b6578703d31333a313435303139363138353739363b747970653d363a7a696d6272613b");
				//meetID = args[4];
				LogInfo.T("User:: "+user);
				ZyncZimbra zz = new ZyncZimbra();
				HeaderHandler.TOKEN = user.getToken();
				resp = zz.syncCalendar(user, 0);
			}
			
		} catch (Exception e) {
			LogInfo.E("Excepcion:: ");
			LogInfo.E(Util.errorToString(e));
		}
		LogInfo.T("Proceso Terminado");
	}

	
}
