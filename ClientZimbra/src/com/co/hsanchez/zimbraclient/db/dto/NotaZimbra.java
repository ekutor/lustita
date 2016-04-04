package com.co.hsanchez.zimbraclient.db.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.co.hsanchez.zimbraclient.Util;
import com.co.hsanchez.zimbraclient.log.LogInfo;

import zimbramail.AppointmentHitInfo;
import zimbramail.InstanceDataInfo;


public class NotaZimbra 
{		
	private String id;
	private String idZimbra;
	private String name;
	private String desc;
	private String deleted;
	private String location;
	private String creator;
	private String duration_minutes;
	private List<FechasReunion> fechas;
	private FechasReunion fechaActual;
	private String status;
	private String type;
	private boolean periodica;
	private String assigned_user_id;
	private String modified_user_id;
	private String date_entered;
	private String status_met_det;
	private int random;
	private String idZimbraIndividual;
	private String repeatType = "",repeatInterval= "",repeatCount = "",repeatParentId = "",repeatUntil = "";
	
	
	public static final String TYPE = "Zimbra";

    public NotaZimbra() {
    	fechas = new ArrayList<FechasReunion>();
    }


	public boolean isPeriodica() {
		return periodica;
	}


	public void setPeriodica(boolean periodica) {
		this.periodica = periodica;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getIdZimbra() {
		return idZimbra;
	}


	public void setIdZimbra(String idZimbra) {
		this.idZimbra = idZimbra;
	}
	

	public String getIdZimbraIndividual() {
		return idZimbraIndividual;
	}


	public void setIdZimbraIndividual(String idZimbraIndividual) {
		this.idZimbraIndividual = idZimbraIndividual;
	}


	public String getName() {
		return name;
	}


	public FechasReunion getFechaActual() {
		return fechaActual;
	}


	public void setFechaActual(FechasReunion fechaActual) {
		this.fechaActual = fechaActual;
	}


	public void setName(String name) {
		if(name != null && name.length() > 80){
			this.name = name.substring(name.length() -80);
		}else{
			this.name = "";
		}
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public String getDeleted() {
		return deleted;
	}


	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getCreator() {
		return creator;
	}


	public void setCreator(String creator) {
		this.assigned_user_id = creator;
		this.creator = creator;
	}


	public String getDuration_minutes() {
		return duration_minutes;
	}


	public void setDuration_minutes(String duration_minutes) {
		this.duration_minutes = duration_minutes;
	}


	public List<FechasReunion> getFechas() {
		return fechas;
	}


	public void setFechas(List<FechasReunion> fechas) {
		this.fechas = fechas;
	}

	public void addFecha(FechasReunion fecha) {
		this.fechas.add(fecha);
	}

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getAssigned_user_id() {
		return assigned_user_id;
	}


	public void setAssigned_user_id(String assigned_user_id) {
		this.assigned_user_id = assigned_user_id;
	}


	public String getModified_user_id() {
		return modified_user_id;
	}


	public void setModified_user_id(String modified_user_id) {
		this.modified_user_id = modified_user_id;
	}


	public String getDate_entered() {
		return date_entered;
	}


	public void setDate_entered(String date_entered) {
		this.date_entered = date_entered;
	}
	


	public int getRandom() {
		return random;
	}


	public void setRandom(int random) {
		this.random = random;
	}


	public static NotaZimbra conversor(AppointmentHitInfo ap, User u){
		NotaZimbra n = new NotaZimbra();
		n.deleted = "0";
		
		n.modified_user_id = u.getIdSugar();

		n.type = TYPE;
		n.status = getStatusZimbra(ap.getPtst());
		n.status_met_det = getStatusZimbraMeetDet(ap.getPtst());
		
		
		UUID uuidRel  = UUID.randomUUID();
		n.id = ""+uuidRel;
		if(ap.getUid().length() >= 32){
			n.idZimbra = ap.getUid().substring(ap.getUid().length()-32);//el id es de 112 digitos
		}else{
			n.idZimbra = ap.getUid();
		}
		
		n.idZimbraIndividual = ap.getInvId();
		n.name = ap.getName();
		n.desc = ap.getFr();
		n.location = ap.getLoc();
		try{
			n.creator = ap.getOr().getA();
		}catch(java.lang.NullPointerException ne){
			LogInfo.T("Nota Sin creador::" + n.name);
		}
		long duration= ap.getDur() / 60000;
		
		n.duration_minutes =  String.valueOf(duration);
		
		for(InstanceDataInfo fechaZimbra : ap.getInst()){
			String ds = Util.calculateHour(fechaZimbra.getS());
			String de = Util.calculateHour(fechaZimbra.getS() + ap.getDur());
			FechasReunion fr = new FechasReunion(ds,de);
			n.fechas.add(fr);
		}
		n.periodica = (n.fechas.size() > 1);
		LogInfo.T("Nota Duracion::" + n.duration_minutes );
//		LogInfo.T("Nota Fecha Fin::" + n.date_end );
		
		return n;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("id:" + id );
		sb.append(" assigned_user_id "+assigned_user_id );
		sb.append(" modified_user_id "+modified_user_id );
	
		sb.append(" type "+type );
		sb.append(" status "+status);
		sb.append(" status_met_det "+status_met_det);
		sb.append(" id "+id);
		sb.append("idZimbra "+idZimbra);
		sb.append("name "+name);
		sb.append("desc "+desc);
		sb.append("location "+location);
		sb.append("creator "+creator);
		sb.append("duration_minutes "+duration_minutes);
		for( FechasReunion f : fechas){
			sb.append("date_start "+f.getDate_start());
			sb.append("date_end "+f.getDate_end());
		}

		sb.append("deleted:" + deleted);
	
		return sb.toString();
	}


	private static String getStatusZimbra(String statusZimbra) {
		String s = "none";
		try{
			if(statusZimbra.equalsIgnoreCase("AC")){
				s = "accept";
			}
		}catch(java.lang.NullPointerException ne){
			
		}
		return s;
	}
	
	public static String getStatusZimbraMeetDet(String statusZimbra) {
		String s = "Not Held";
		try{
			if(statusZimbra.equalsIgnoreCase("AC")){
				s = "Held";
			}else if(statusZimbra.equalsIgnoreCase("NE")){
				s = "Planned";
			}
		}catch(java.lang.NullPointerException ne){
			
		}
		
		return s;
	}


	public String getStatus_met_det() {
		return status_met_det;
	}


	public void setStatus_met_det(String status_met_det) {
		this.status_met_det = status_met_det;
	}


	public String getRepeatType() {
		return repeatType;
	}


	public void setRepeatType(String repeatType) {
		this.repeatType = repeatType;
	}


	public String getRepeatInterval() {
		return repeatInterval;
	}


	public void setRepeatInterval(String repeatInterval) {
		this.repeatInterval = repeatInterval;
	}


	public String getRepeatCount() {
		return repeatCount;
	}


	public void setRepeatCount(String repeatCount) {
		this.repeatCount = repeatCount;
	}


	public String getRepeatParentId() {
		return repeatParentId;
	}


	public void setRepeatParentId(String repeatParentId) {
		this.repeatParentId = repeatParentId;
	}


	public String getRepeatUntil() {
		return repeatUntil;
	}


	public void setRepeatUntil(String repeatUntil) {
		this.repeatUntil = repeatUntil;
	}

	
 }