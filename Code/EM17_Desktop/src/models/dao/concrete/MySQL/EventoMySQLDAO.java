package models.dao.concrete.MySQL;

import models.dao.interfaces.EventoDAO;
import models.Evento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.TreeSet;

public class EventoMySQLDAO implements EventoDAO{
	
@Override
public LinkedList<Evento> getListaEventi () throws ParseException {
	String query = "select * from Evento where (select count(*) from Biglietto where id_evento = id) = 0  order by id " ;
	ArrayList<Object> params = null;
	LinkedList<Evento> list = new LinkedList<Evento>();
	Date today = new Date(System.currentTimeMillis());
	
	try {
		ResultSet rs = Database.getInstance().execQuery(query.toUpperCase(), params);
		if(rs!= null){
			while(rs.next()){
				try {
					SimpleDateFormat sdf=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy",Locale.US);
			        Date bbDate;
			        bbDate = sdf.parse(rs.getString("DATA"));
			        Evento e= new Evento(rs.getInt("ID"),rs.getString("NOME"),rs.getString("TIPOLOGIA"),bbDate,rs.getString("LOCALITA"),rs.getString("LUOGO"),rs.getFloat("PREZZO"),rs.getInt("NRBIGLIETTI"),rs.getString("DESCRIZIONE"),rs.getString("LINKIMMAGINE"));
						if(today.before(e.getData()))
							list.add(e);
				}catch (ParseException e) {  System.err.println(e.getMessage());}
				
				
			}
		}
	} catch (SQLException ex) { list=null;
	}
	return list;
}


@Override
public LinkedList<Evento> getAllEventi () throws ParseException {
	String query = "select * from Evento" ;
	ArrayList<Object> params = null;
	LinkedList<Evento> list = new LinkedList<Evento>();

	
	try {
		ResultSet rs = Database.getInstance().execQuery(query.toUpperCase(), params);
		if(rs!= null){
			while(rs.next()){
				try {
					SimpleDateFormat sdf=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy",Locale.US);
			        Date bbDate;
			        bbDate = sdf.parse(rs.getString("DATA"));
			        Evento e= new Evento(rs.getInt("ID"),rs.getString("NOME"),rs.getString("TIPOLOGIA"),bbDate,rs.getString("LOCALITA"),rs.getString("LUOGO"),rs.getFloat("PREZZO"),rs.getInt("NRBIGLIETTI"),rs.getString("DESCRIZIONE"),rs.getString("LINKIMMAGINE"));
					list.add(e);
				}catch (ParseException e) {  System.err.println(e.getMessage());}
				
				
			}
		}
	} catch (SQLException ex) { list=null;
	}
	return list;
}


@Override
public ArrayList<Object> getInfoEventi (Evento e) {
	String query = "SELECT  EVENTO.NRBIGLIETTI, COUNT(ORDINE.QUANTITA) AS VENDUTI, COUNT(*) AS ORDINI FROM EVENTO INNER JOIN ORDINE ON EVENTO.ID=ORDINE.ID_EVENTO " + 
			"WHERE EVENTO.ID=?";
	ArrayList<Object> params = new ArrayList<>();
	params.add(e.getId());
	ArrayList<Object> list = new ArrayList<>();

	
	try {
		ResultSet rs = Database.getInstance().execQuery(query.toUpperCase(), params);
		if(rs!= null){
			while(rs.next()){
				
					list.add(rs.getInt("venduti"));
					list.add(rs.getInt("nrbiglietti"));
					list.add(rs.getInt("ordini"));
				
				
				
			}
		}
	} catch (SQLException ex) { list=null;
	}
	return list;
}

@Override
public TreeSet<String> getListaLocalita () {
	
	String query = "select nome from localita " ;
	ArrayList<Object> params = null;
	TreeSet<String> list = new TreeSet<String>();

	try {
		ResultSet rs = Database.getInstance().execQuery(query.toUpperCase(), params);
		if(rs!= null){
			while(rs.next()){
				String s = new String(rs.getString("NOME"));
				list.add(s);
				
			}
		}
	} catch (SQLException ex) {}
	return list;
}


public boolean createNewEvento(Evento e) {
	ArrayList<Object> params = new ArrayList<>();
    String query = "INSERT INTO EVENTO (NOME,DATA,LUOGO,LOCALITA,PREZZO,NRBIGLIETTI,DESCRIZIONE,LINKIMMAGINE,TIPOLOGIA) VALUES(?,"
    		+ "?,?,?,?,?,?,?,?)";
    
    params.add(e.getNome());
    params.add(e.getData().toString());
    params.add(e.getLuogo());
    params.add(e.getLocalitÓ());
    params.add(e.getPrezzo());
    params.add(e.getNumeroBiglietti());
    if(e.getDescrizione()==null)
    	params.add("");
    else {
    	params.add(e.getDescrizione());
    }
    params.add(e.getLinkImmagine());
    params.add(e.getTipologia());
    try {
        Database.getInstance().execUpdate(query.toUpperCase(), params);
    } catch (SQLException ex) {
    	System.err.println(ex.getMessage());
        return false;
    }
    
    return true;
}


@Override
public boolean deleteEvento(Evento e){
    ArrayList<Object> params = new ArrayList<>();
    String query = "DELETE FROM EVENTO WHERE NOME = ? AND DATA = ?";
    params.add(e.getNome());
    params.add(e.getData().toString());
    try {
        Database.getInstance().execUpdate(query.toUpperCase(), params);
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
        return false;
    }
    return true;
}


@Override
public boolean updateEvento(Evento e){
    ArrayList<Object> params = new ArrayList<>();
    String query = "UPDATE EVENTO SET  NOME = ?,DATA = ?,LUOGO = ?,LOCALITA = ?, PREZZO = ?,NRBIGLIETTI = ?,DESCRIZIONE = ?,LINKIMMAGINE = ?,TIPOLOGIA = ? WHERE ID = ?";
    params.add(e.getNome());
    params.add(e.getData().toString());
    params.add(e.getLuogo());
    params.add(e.getLocalitÓ());
    params.add(e.getPrezzo());
    params.add(e.getNumeroBiglietti());
    
    if(e.getDescrizione()==null)
    	params.add("");
    else {
    	params.add(e.getDescrizione());
    }
    params.add(e.getLinkImmagine());
    params.add(e.getTipologia());
    params.add(e.getId());
    try {
        Database.getInstance().execUpdate(query.toUpperCase(), params);
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
        return false;
    }
    return true;
}


}


