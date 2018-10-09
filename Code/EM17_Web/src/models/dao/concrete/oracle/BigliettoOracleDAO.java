package models.dao.concrete.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import models.Biglietto;
import models.Evento;
import models.dao.interfaces.BigliettoDAO;

public class BigliettoOracleDAO implements BigliettoDAO{
	
	
	public boolean createNewTicket(Biglietto b) {
		ArrayList<Object> params = new ArrayList<>();
	    String query = "INSERT INTO BIGLIETTO (ID_TICKET,ID_EVENTO,USATI,ID_ORDINE) VALUES(id_biglietto_sequence.nextval,?,"
	    		+ "?,?)";
	    Date today=new Date();
	    params.add(b.getIdEvento());
	    params.add("NO");
	    params.add(b.getIdOrdine());
	   
	    try {
	        Database.getInstance().execQuery(query, params);
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
			ResultSet rs = Database.getInstance().execQuery(query, params);
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
