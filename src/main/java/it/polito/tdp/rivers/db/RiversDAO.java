package it.polito.tdp.rivers.db;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.rivers.model.Event;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}

	public String numMis(River value) {
		
		int misurazioni=-1;
		
		final String sql = "SELECT COUNT(*) as numero_misurazioni FROM flow WHERE river= ? ";

	//<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, value.getId());

			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				misurazioni= res.getInt("numero_misurazioni");
				
			}
			
			conn.close();
			

			
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
		return Integer.toString(misurazioni);

	}

	public String primaMis(River value) {

		Date data;
		
		final String sql = " SELECT day FROM flow WHERE river= ? ORDER BY day ";

	//<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, value.getId());

			ResultSet res = st.executeQuery();
			
		res.next();
		data= res.getDate("day");
			conn.close();
			

			
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
		return data.toString();

		
	}

	public String ultimaMis(River value) {
		

		Date data;
		
		final String sql = " SELECT day FROM flow WHERE river= ? ORDER BY day desc";

	//<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, value.getId());

			ResultSet res = st.executeQuery();
			
		res.next();
		data= res.getDate("day");
			conn.close();
			

			
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
		return data.toString();

		
	}

	public String fmed(River value) {
		
		float media;
		
		final String sql = " SELECT AVG(flow) as media FROM flow WHERE river= ? ORDER BY flow ";

	//<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, value.getId());

			ResultSet res = st.executeQuery();
			
		res.next();
		media= res.getFloat("media");
			conn.close();
			
			
			
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
		return Float.toString(media);
	}

	public void dammiEventi(PriorityQueue<Event> queue, River river) {
		
		final String sql = " SELECT * FROM flow WHERE river= ? ORDER BY day ";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, river.getId());

			ResultSet res = st.executeQuery();
			
		while(res.next()) {
			Event e=new Event(res.getDate("day"), res.getFloat("flow"));
			queue.add(e);
		}
		
			conn.close();
			
			
			
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
		
	}
}
