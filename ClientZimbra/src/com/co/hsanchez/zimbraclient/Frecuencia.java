package com.co.hsanchez.zimbraclient;

public enum Frecuencia {
	DIARIO("DAI"),SEMANAL("WEE"),MENSUAL("MON");
	private String freq;
	
	Frecuencia(String f){
		this.freq = f;
	}
	
	public String getFrecuencia(){
		return freq;
	}
}
