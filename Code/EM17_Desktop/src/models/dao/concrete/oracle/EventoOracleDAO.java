package models.dao.concrete.oracle;

import models.dao.interfaces.EventoDAO;
import models.Evento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

public class EventoOracleDAO implements EventoDAO{
	
@Override
public LinkedList<Evento> getListaEventi () {
	return new LinkedList<Evento> ();
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
    String query = "INSERT INTO EVENTO (ID,NOME,DATA,LUOGO,LOCALITA,PREZZO,NRBIGLIETTI,DESCRIZIONE,LINKIMMAGINE) VALUES(id_event_sequence.nextval,?,"
    		+ "?,?,?,?,?,?,?)";
    
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
    try {
        Database.getInstance().execQuery(query, params);
    } catch (SQLException ex) {
        return false;
    }
    
    return true;
}

}


