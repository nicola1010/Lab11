package it.polito.tdp.rivers.model;


import java.util.Date;


public class Event implements Comparable<Event> {
	
	
	
	private Date data;
	
	private float flusso;

	
	
	public Event(Date data,float flusso) {
		super();
		this.data = data;
		this.flusso = flusso;
	}


	public Date getData() {
		return data;
	}


	@Override
	public int compareTo(Event o) {
		return this.data.compareTo(o.data);
	}


	public float getFlusso() {
		return flusso;
	}


	public void setFlusso(float flusso) {
		this.flusso = flusso;
	}


	
	

}
