package models.dao.concrete.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import models.Carrello;
import models.dao.interfaces.CarrelloDAO;

public class CarrelloMySQLDAO implements CarrelloDAO {

	
	public boolean createNewCarrello(int id_utente,int id_evento,int quantita) {
		ArrayList<Object> params = new ArrayList<>();
	    String query = "INSERT INTO CARRELLO (ID_EVENTO,ID_UTENTE,QUANTITA) VALUES(?,"
	    		+ "?,?)";
	    
	    params.add(id_evento);
	    params.add(id_utente);
	    params.add(quantita);
	    
	    try {
	        Database.getInstance().execUpdate(query, params);
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	        return false;
	    }
	    
	    return true;
	}
	
	
	public Carrello getCarrello (String utente) throws ParseException {
		String query = "Select * from Carrello where id_utente="+utente;
		ArrayList<Object> params = null;
		Carrello c = null;

		
		try {
			ResultSet rs = Database.getInstance().execQuery(query.toUpperCase(), params);
			if(rs!= null){
				while(rs.next()){
						c= new Carrello(rs.getInt("id_carrello"),rs.getInt("id_utente"),rs.getInt("id_evento"),rs.getInt("quantita"));
						
				}
			}
		} catch (SQLException ex) { 
		}
		return c;
	}
	
	
	public boolean deleteCarrello(int utente){
	    ArrayList<Object> params = new ArrayList<>();
	    String query = "DELETE FROM CARRELLO WHERE ID_UTENTE=?";
	    params.add(utente);
	    
	    try {
	        Database.getInstance().execUpdate(query, params);
	    } catch (SQLException ex) {
	        System.err.println(ex.getMessage());
	        return false;
	    }
	    return true;
	}
}
