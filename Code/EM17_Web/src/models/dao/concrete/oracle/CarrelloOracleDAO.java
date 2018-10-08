package models.dao.concrete.oracle;

import java.sql.SQLException;
import java.util.ArrayList;
import models.Evento;
import models.dao.interfaces.CarrelloDAO;

public class CarrelloOracleDAO implements CarrelloDAO {

	
	public boolean createNewCarrello(int id_utente,int id_evento,int quantita) {
		ArrayList<Object> params = new ArrayList<>();
	    String query = "INSERT INTO CARRELLO (ID_CARRELLO,ID_EVENTO,ID_UTENTE,QUANTITA) VALUES(id_carrello_sequence.nextval,?,"
	    		+ "?,?)";
	    
	    params.add(id_evento);
	    params.add(id_utente);
	    params.add(quantita);
	    
	    try {
	        Database.getInstance().execQuery(query, params);
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	        return false;
	    }
	    
	    return true;
	}
	
	
	public boolean deleteCarrello(int utente){
	    ArrayList<Object> params = new ArrayList<>();
	    String query = "DELETE FROM CARRELLO WHERE ID_UTENTE=?";
	    params.add(utente);
	    
	    try {
	        Database.getInstance().execQuery(query, params);
	    } catch (SQLException ex) {
	        System.err.println(ex.getMessage());
	        return false;
	    }
	    return true;
	}
}
