package models.dao.concrete.oracle;

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

public class EventoOracleDAO implements EventoDAO{
	
@Override
public LinkedList<Evento> getListaEventi () throws ParseException {
	String query = "select * from Evento where (select count(*) from Biglietto where id_evento = id) = 0  order by id " ;
	ArrayList<Object> params = null;
	LinkedList<Evento> list = new LinkedList<Evento>();
	Date today = new Date(System.currentTimeMillis());
	
	try {
		ResultSet rs = Database.getInstance().execQuery(query, params);
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
		ResultSet rs = Database.getInstance().execQuery(query, params);
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
	String query = "select distinct \r\n" + 
			"(select distinct nrbiglietti from Evento where id= ?) as nrbiglietti,\r\n" + 
			"(select distinct count(*) from Biglietto where id_evento = ?)as venduti,\r\n" + 
			"(select distinct count(*) from Ordine where b.id_order = o.id and b.id_evento = ?) as ordini\r\n" + 
			"from evento e inner join biglietto b on e.id=b.id_evento inner join ordine o on o.id=b.id_order" ;
	ArrayList<Object> params = new ArrayList<>();
	params.add(e.getId());
	params.add(e.getId());
	params.add(e.getId());
	ArrayList<Object> list = new ArrayList<>();

	
	try {
		ResultSet rs = Database.getInstance().execQuery(query, params);
		if(rs!= null){
			while(rs.next()){
				
					list.add(rs.getInt("venduti"));
					System.out.println(rs.getInt("venduti"));
					System.out.println(rs.getInt("nrbiglietti"));
					System.out.println(rs.getInt("ordini"));
					list.add(rs.getInt("nrbiglietti"));
					list.add(rs.getInt("ordini"));
				
				
				
			}
		}
	} catch (SQLException ex) { list=null;
	}
	return list;
}

@Override
public ArrayList<Evento> getEventiFromSearch (String search ) throws ParseException {
	String query = "select * from Evento where lower(nome) like lower ('%" + search + "%') OR lower(descrizione) like lower ('%" + search + "%') OR lower(luogo) like lower ('%" + search + "%')";
	ArrayList<Object> params = null;
	ArrayList<Evento> list = new ArrayList<>();

	
	try {
		ResultSet rs = Database.getInstance().execQuery(query, params);
		if(rs!= null){
			while(rs.next()){
					SimpleDateFormat sdf=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy",Locale.US);
					Date bbDate;
					bbDate = sdf.parse(rs.getString("DATA"));
					Evento x= new Evento(rs.getInt("ID"),rs.getString("NOME"),rs.getString("TIPOLOGIA"),bbDate,rs.getString("LOCALITA"),rs.getString("LUOGO"),rs.getFloat("PREZZO"),rs.getInt("NRBIGLIETTI"),rs.getString("DESCRIZIONE"),rs.getString("LINKIMMAGINE"));
					list.add(x);	
			}
		}
	} catch (SQLException ex) { list=null;
	}
	return list;
}

@Override
public ArrayList<Evento> getEventiSport () throws ParseException {
	String query = "select * from Evento where tipologia like 'Sport'";
	ArrayList<Object> params = null;
	ArrayList<Evento> list = new ArrayList<>();

	
	try {
		ResultSet rs = Database.getInstance().execQuery(query, params);
		if(rs!= null){
			while(rs.next()){
					SimpleDateFormat sdf=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy",Locale.US);
					Date bbDate;
					bbDate = sdf.parse(rs.getString("DATA"));
					Evento x= new Evento(rs.getInt("ID"),rs.getString("NOME"),rs.getString("TIPOLOGIA"),bbDate,rs.getString("LOCALITA"),rs.getString("LUOGO"),rs.getFloat("PREZZO"),rs.getInt("NRBIGLIETTI"),rs.getString("DESCRIZIONE"),rs.getString("LINKIMMAGINE"));
					list.add(x);	
			}
		}
	} catch (SQLException ex) { list=null;
	}
	return list;
}


@Override
public ArrayList<Evento> getEventiSpettacolo () throws ParseException {
	String query = "select * from Evento where tipologia like 'Spettacolo'";
	ArrayList<Object> params = null;
	ArrayList<Evento> list = new ArrayList<>();

	
	try {
		ResultSet rs = Database.getInstance().execQuery(query, params);
		if(rs!= null){
			while(rs.next()){
					SimpleDateFormat sdf=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy",Locale.US);
					Date bbDate;
					bbDate = sdf.parse(rs.getString("DATA"));
					Evento x= new Evento(rs.getInt("ID"),rs.getString("NOME"),rs.getString("TIPOLOGIA"),bbDate,rs.getString("LOCALITA"),rs.getString("LUOGO"),rs.getFloat("PREZZO"),rs.getInt("NRBIGLIETTI"),rs.getString("DESCRIZIONE"),rs.getString("LINKIMMAGINE"));
					list.add(x);	
			}
		}
	} catch (SQLException ex) { list=null;
	}
	return list;
}

@Override
public ArrayList<Evento> getEventiConcerti () throws ParseException {
	String query = "select * from Evento where tipologia like 'Concerti'";
	ArrayList<Object> params = null;
	ArrayList<Evento> list = new ArrayList<>();

	
	try {
		ResultSet rs = Database.getInstance().execQuery(query, params);
		if(rs!= null){
			while(rs.next()){
					SimpleDateFormat sdf=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy",Locale.US);
					Date bbDate;
					bbDate = sdf.parse(rs.getString("DATA"));
					Evento x= new Evento(rs.getInt("ID"),rs.getString("NOME"),rs.getString("TIPOLOGIA"),bbDate,rs.getString("LOCALITA"),rs.getString("LUOGO"),rs.getFloat("PREZZO"),rs.getInt("NRBIGLIETTI"),rs.getString("DESCRIZIONE"),rs.getString("LINKIMMAGINE"));
					list.add(x);	
			}
		}
	} catch (SQLException ex) { list=null;
	}
	return list;
}


@Override
public ArrayList<Evento> getEventiCultura () throws ParseException {
	String query = "select * from Evento where tipologia like 'Cultura'";
	ArrayList<Object> params = null;
	ArrayList<Evento> list = new ArrayList<>();

	
	try {
		ResultSet rs = Database.getInstance().execQuery(query, params);
		if(rs!= null){
			while(rs.next()){
					SimpleDateFormat sdf=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy",Locale.US);
					Date bbDate;
					bbDate = sdf.parse(rs.getString("DATA"));
					Evento x= new Evento(rs.getInt("ID"),rs.getString("NOME"),rs.getString("TIPOLOGIA"),bbDate,rs.getString("LOCALITA"),rs.getString("LUOGO"),rs.getFloat("PREZZO"),rs.getInt("NRBIGLIETTI"),rs.getString("DESCRIZIONE"),rs.getString("LINKIMMAGINE"));
					list.add(x);	
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
		ResultSet rs = Database.getInstance().execQuery(query, params);
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
    String query = "INSERT INTO EVENTO (ID,NOME,DATA,LUOGO,LOCALITA,PREZZO,NRBIGLIETTI,DESCRIZIONE,LINKIMMAGINE,TIPOLOGIA) VALUES(id_event_sequence.nextval,?,"
    		+ "?,?,?,?,?,?,?,?)";
    
    params.add(e.getNome());
    params.add(e.getData().toString());
    params.add(e.getLuogo());
    params.add(e.getLocalità());
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
        Database.getInstance().execQuery(query, params);
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
        Database.getInstance().execQuery(query, params);
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
    params.add(e.getLocalità());
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
        Database.getInstance().execQuery(query, params);
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
        return false;
    }
    return true;
}


}


