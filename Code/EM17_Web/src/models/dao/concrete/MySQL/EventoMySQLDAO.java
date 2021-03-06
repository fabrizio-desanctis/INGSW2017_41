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


public LinkedList<Evento> getPopolari () throws ParseException {
	String query = "select id,nome,data,luogo,localita,nrbiglietti,descrizione,linkimmagine,tipologia,prezzo,(select count(*) from Biglietto where id_evento = id)as venduti from Evento order by venduti desc" ;
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


public int getRestanti (int id_evento) throws ParseException {
	String query = "select nrbiglietti - (select COALESCE(sum(quantita),0) from ordine where id_evento=?) as restanti from Evento where id=?" ;
	ArrayList<Object> params = new ArrayList<>();
	params.add(id_evento);
	params.add(id_evento);
	
	int ritorno=0;
	
	try {
		ResultSet rs = Database.getInstance().execQuery(query.toUpperCase(), params);
		if(rs!= null){
			while(rs.next()){
				ritorno= rs.getInt("restanti");
				
				
				
			}
		}
	} catch (SQLException ex) { 
	}
	return ritorno;
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
		ResultSet rs = Database.getInstance().execQuery(query.toUpperCase(), params);
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
	Date today = new Date();
	
	try {
		ResultSet rs = Database.getInstance().execQuery(query.toUpperCase(), params);
		if(rs!= null){
			while(rs.next()){
					SimpleDateFormat sdf=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy",Locale.US);
					Date bbDate;
					bbDate = sdf.parse(rs.getString("DATA"));
					Evento x= new Evento(rs.getInt("ID"),rs.getString("NOME"),rs.getString("TIPOLOGIA"),bbDate,rs.getString("LOCALITA"),rs.getString("LUOGO"),rs.getFloat("PREZZO"),rs.getInt("NRBIGLIETTI"),rs.getString("DESCRIZIONE"),rs.getString("LINKIMMAGINE"));
					if(today.before(x.getData()))
						list.add(x);	
			}
		}
	} catch (SQLException ex) { list=null;
	}
	return list;
}


@Override
public Evento getEventoCarrello (String utente) throws ParseException {
	String query = "Select evento.nome as nome_evento,evento.data as data, carrello.quantita as quantita,evento.prezzo as prezzo_evento,evento.linkimmagine as link_evento,evento.id as id_evento " + 
			"from (carrello  inner join evento on carrello.id_evento=evento.id) inner join utente on utente.id_utente=carrello.id_utente " + 
			"where utente.id_utente=" + utente;
	ArrayList<Object> params = null;
	Evento e = null;
	
	
	try {
		ResultSet rs = Database.getInstance().execQuery(query.toUpperCase(), params);
		if(rs!= null){
			while(rs.next()){
				    SimpleDateFormat sdf=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy",Locale.US);
					Date bbDate;
					bbDate = sdf.parse(rs.getString("data"));
					e= new Evento(rs.getInt("id_evento"),rs.getString("nome_evento"),rs.getFloat("prezzo_evento"),rs.getString("link_evento"),rs.getInt("quantita"),bbDate);
					//if(!today.before(e.getData()))
						//e=null;

			}
		}
	} catch (SQLException ex) { 
	}
	
	return e;
}


@Override
public ArrayList<Evento> getEventibyID (String search ) throws ParseException {
	String query = "select * from Evento where id="+search;
	ArrayList<Object> params = null;
	ArrayList<Evento> list = new ArrayList<>();
	
	
	try {
		ResultSet rs = Database.getInstance().execQuery(query.toUpperCase(), params);
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
	Date today = new Date();
	
	try {
		ResultSet rs = Database.getInstance().execQuery(query.toUpperCase(), params);
		if(rs!= null){
			while(rs.next()){
					SimpleDateFormat sdf=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy",Locale.US);
					Date bbDate;
					bbDate = sdf.parse(rs.getString("DATA"));
					Evento x= new Evento(rs.getInt("ID"),rs.getString("NOME"),rs.getString("TIPOLOGIA"),bbDate,rs.getString("LOCALITA"),rs.getString("LUOGO"),rs.getFloat("PREZZO"),rs.getInt("NRBIGLIETTI"),rs.getString("DESCRIZIONE"),rs.getString("LINKIMMAGINE"));
					if(today.before(x.getData()))
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
	Date today = new Date();
	
	try {
		ResultSet rs = Database.getInstance().execQuery(query.toUpperCase(), params);
		if(rs!= null){
			while(rs.next()){
					SimpleDateFormat sdf=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy",Locale.US);
					Date bbDate;
					bbDate = sdf.parse(rs.getString("DATA"));
					Evento x= new Evento(rs.getInt("ID"),rs.getString("NOME"),rs.getString("TIPOLOGIA"),bbDate,rs.getString("LOCALITA"),rs.getString("LUOGO"),rs.getFloat("PREZZO"),rs.getInt("NRBIGLIETTI"),rs.getString("DESCRIZIONE"),rs.getString("LINKIMMAGINE"));
					if(today.before(x.getData()))
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
	Date today = new Date();
	
	try {
		ResultSet rs = Database.getInstance().execQuery(query.toUpperCase(), params);
		if(rs!= null){
			while(rs.next()){
					SimpleDateFormat sdf=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy",Locale.US);
					Date bbDate;
					bbDate = sdf.parse(rs.getString("DATA"));
					Evento x= new Evento(rs.getInt("ID"),rs.getString("NOME"),rs.getString("TIPOLOGIA"),bbDate,rs.getString("LOCALITA"),rs.getString("LUOGO"),rs.getFloat("PREZZO"),rs.getInt("NRBIGLIETTI"),rs.getString("DESCRIZIONE"),rs.getString("LINKIMMAGINE"));
					if(today.before(x.getData()))
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
	Date today = new Date();
	
	try {
		ResultSet rs = Database.getInstance().execQuery(query.toUpperCase(), params);
		if(rs!= null){
			while(rs.next()){
					SimpleDateFormat sdf=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy",Locale.US);
					Date bbDate;
					bbDate = sdf.parse(rs.getString("DATA"));
					Evento x= new Evento(rs.getInt("ID"),rs.getString("NOME"),rs.getString("TIPOLOGIA"),bbDate,rs.getString("LOCALITA"),rs.getString("LUOGO"),rs.getFloat("PREZZO"),rs.getInt("NRBIGLIETTI"),rs.getString("DESCRIZIONE"),rs.getString("LINKIMMAGINE"));
					if(today.before(x.getData()))

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


