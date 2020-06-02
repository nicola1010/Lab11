package it.polito.tdp.rivers.model;

import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {

	RiversDAO dao = new RiversDAO();
	
	public List<River> getAllRivers() {
		return dao.getAllRivers();
	}

	public String numMis(River value) {
		
		return dao.numMis(value);
	}
	
	
}
