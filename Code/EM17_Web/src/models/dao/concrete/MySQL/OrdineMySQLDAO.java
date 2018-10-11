package models.dao.concrete.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import models.Ordine;
import models.dao.interfaces.OrdineDAO;

public class OrdineMySQLDAO implements OrdineDAO {
	
	public boolean getOrdinebyId (String id) throws ParseException {
		String query = "select id_ordine from ordine where id_ordine="+id;
		ArrayList<Object> params = null;
		boolean value=false;

		
		try {
			ResultSet rs = Database.getInstance().execQuery(query.toUpperCase(), params);
			if(rs!= null){
				while(rs.next()){
						value=true;
						
				}
			}
		} catch (SQLException ex) { 
		}
		return value;
	}
	
	
	public Ordine getOrdineInfo (String id) throws ParseException {
		String query = "select id_ordine,totale,evento.nome,evento.prezzo,quantita,pdflink,evento.linkimmagine from ordine inner join evento on ordine.id_evento=evento.id " + 
				"where id_ordine="+id;
		ArrayList<Object> params = null;
		
		Ordine o = null;
		
		try {
			ResultSet rs = Database.getInstance().execQuery(query.toUpperCase(), params);
			if(rs!= null){
				while(rs.next()){
						o = new Ordine(Integer.parseInt(rs.getString("id_ordine")),0, 0,rs.getInt("quantita"),null,rs.getDouble("totale"),rs.getString("pdflink"),rs.getDouble("prezzo"),rs.getString("nome"),rs.getString("linkimmagine"));
						
				}
			}
		} catch (SQLException ex) { 
		}
		return o;
	}
	
	
	public LinkedList<Ordine> getOrdineInfoList (String id_utente) throws ParseException {
		String query = "select id_ordine,ordine.data,totale,evento.nome,evento.prezzo,quantita,pdflink,evento.linkimmagine from ordine inner join evento on ordine.id_evento=evento.id where id_utente= " + id_utente;
		ArrayList<Object> params = null;
		LinkedList<Ordine> list = new LinkedList<Ordine>();
		Ordine o = null;
		
		try {
			ResultSet rs = Database.getInstance().execQuery(query.toUpperCase(), params);
			if(rs!= null){
				while(rs.next()){
						SimpleDateFormat sdf=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy",Locale.US);
						Date bbDate;
						bbDate = sdf.parse(rs.getString("data"));
						o = new Ordine(Integer.parseInt(rs.getString("id_ordine")),0, 0,rs.getInt("quantita"),bbDate,rs.getDouble("totale"),rs.getString("pdflink"),rs.getDouble("prezzo"),rs.getString("nome"),rs.getString("linkimmagine"));
						list.add(o);
				}
			}
		} catch (SQLException ex) { list=null;
		}
		return list;
	}
	
	
	
	
	public boolean createNewOrdine(String id,int id_evento,int id_utente,int quantita,int totale,String pdf) {
		ArrayList<Object> params = new ArrayList<>();
	    String query = "INSERT INTO ORDINE (ID_ORDINE,DATA,ID_EVENTO,ID_UTENTE,QUANTITA,TOTALE,PDFLINK) VALUES(?,?,"
	    		+ "?,?,?,?*(select evento.prezzo from evento where evento.id=?),?)";
	    Date today=new Date();
	    params.add(id);
	    params.add(today.toString());
	    params.add(id_evento);
	    params.add(id_utente);
	    params.add(quantita);
	    params.add(quantita);
	    params.add(id_evento);
	    params.add(pdf);
	    
	    try {
	        Database.getInstance().execUpdate(query.toUpperCase(), params);
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	        return false;
	    }
	    
	    return true;
	}
	
}
