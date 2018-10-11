package models.dao.concrete.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import models.Biglietto;
import models.dao.interfaces.BigliettoDAO;

public class BigliettoMySQLDAO implements BigliettoDAO{
	
	
	public boolean createNewTicket(Biglietto b) {
		ArrayList<Object> params = new ArrayList<>();
	    String query = "INSERT INTO BIGLIETTO (ID_EVENTO,USATI,ID_ORDINE) VALUES(?,"
	    		+ "?,?)";
	  
	    params.add(b.getIdEvento());
	    params.add("NO");
	    params.add(b.getIdOrdine());
	   
	    try {
	        Database.getInstance().execUpdate(query, params);
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	        return false;
	    }
	    
	    return true;
	}
	
	@Override
	public LinkedList<Integer> getListaBiglietti (String id_ordine) throws ParseException {
		String query = "select id_ticket from Biglietto where id_ordine="+id_ordine ;
		ArrayList<Object> params = null;
		LinkedList<Integer> list = new LinkedList<Integer>();
		
		
		try {
			ResultSet rs = Database.getInstance().execQuery(query.toUpperCase(), params);
			if(rs!= null){
				while(rs.next()){
					Integer i = new Integer(rs.getInt("id_ticket"));
					list.add(i);
					
					
				}
			}
		} catch (SQLException ex) { list=null;
		}
		return list;
	}

}
