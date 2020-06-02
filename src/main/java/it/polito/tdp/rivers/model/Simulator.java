package it.polito.tdp.rivers.model;

import java.util.LinkedList;
import java.util.PriorityQueue;

import it.polito.tdp.rivers.db.RiversDAO;



public class Simulator {
	
	RiversDAO dao=new RiversDAO();
	
	//proprietà del mondo
	private float Q; // capacità massima
	private float C; // capacità giorno per giorno
	private float fin; //flusso in ingresso
	
	double ermin;
	
	//valori da calcolare
	LinkedList<Float> ccc= new LinkedList<>();
	float Cmed;
	int giorniNO;
	
	
	
	
	//coda eventi
	
	private PriorityQueue<Event> queue= new PriorityQueue<>();
	
	
	
	//gestione eventi
	public void run(int k, float fmed, River river) {
		// inizializzazione
		this.queue.clear();
		this.dao.dammiEventi(queue, river);
		this.setQ(86400*fmed*30*k);	
		System.out.println(this.getQ());
		this.setC(this.getQ()/2);
		ccc.add(this.getC());
		
		//erogazione minima-->
		ermin=0.8* fmed;
		
		while(!this.queue.isEmpty()){
			
			Event e=this.queue.poll();
			this.processEvent(e, fmed);

		}
		
	}

	
	
	
	
	
	private void processEvent(Event e, float fmed) {
		
		double caso=Math.random();
		double caso2=Math.random();
		
		if(caso<=0.1 && caso2<0.5) { 
			if(this.getC()+(e.getFlusso()-0.8*fmed)<= this.getQ()) {
				this.setC((float) (this.getC()+(e.getFlusso()-0.8*fmed*10)));
			    	}
			else this.setC(this.getQ());
		}
		
		else {
			if(this.getC()+(e.getFlusso()-0.8*fmed)<= this.getQ()) {
				this.setC((float) (this.getC()+(e.getFlusso()-0.8*fmed)));
			    	}
			else this.setC(this.getQ());
		}
		
		if(e.getFlusso()< ermin)
		{
			giorniNO++;
		}
		
		
		this.ccc.add(this.getC());
	}






	public LinkedList<Float> getCcc() {
		return ccc;
	}






	public void setCcc(LinkedList<Float> ccc) {
		this.ccc = ccc;
	}






	public int getGiorniNO() {
		return giorniNO;
	}






	public void setGiorniNO(int giorniNO) {
		this.giorniNO = giorniNO;
	}






	public float getQ() {
		return Q;
	}

	public void setQ(float q) {
		Q = q;
	}

	public float getC() {
		return C;
	}

	public void setC(float c) {
		C = c;
	}

	public float getFin() {
		return fin;
	}

	public void setFin(float fin) {
		this.fin = fin;
	}
	
}
