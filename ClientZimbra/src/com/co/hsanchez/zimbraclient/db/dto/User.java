package com.co.hsanchez.zimbraclient.db.dto;

import com.co.hsanchez.zimbraclient.Util;



public class User 
{		
	private String idSugar;
	private String idZimbra;
	private String userName;
	private String password;
	private String user;
	private String token;
	private String mail;

    public User() {
    
    }
    
    
   
    public User(String idSugar, String userName, String password, String user) {
		super();
		this.idSugar = idSugar;
		this.userName = userName;
		this.password = password;
		this.user = user;
	}



	public String getMail() {
		return mail;
	}



	public void setMail(String mail) {
		this.mail = mail;
	}



	public String getToken() {
		return token;
	}



	public String getIdSugar() {
		return idSugar;
	}



	public void setIdSugar(String idSugar) {
		this.idSugar = idSugar;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getUser() {
		return user;
	}



	public void setUser(String user) {
		this.user = user;
	}



	public String getIdZimbra() {
		return idZimbra;
	}



	public void setIdZimbra(String idZimbra) {
		this.idZimbra = idZimbra;
	}



	public void setToken(String token) {
		this.token = token ;
		
	}
    
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(" User{");
		sb.append("idSugar:");sb.append(idSugar);sb.append(",");
		sb.append("idZimbra:");sb.append(idZimbra);sb.append(",");
		sb.append("userName:");sb.append(userName);sb.append(",");
		//sb.append("password:");sb.append(password);sb.append(",");
		sb.append("user:");sb.append(user);sb.append(",");
		sb.append("mail:");sb.append(mail);sb.append(",");
		sb.append("token:");sb.append(token);sb.append("}");

		return sb.toString();
	}
    
 }