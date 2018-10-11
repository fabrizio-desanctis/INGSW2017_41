package models.dao.concrete.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import models.User;
import models.dao.interfaces.UserDAO;

public class UserMySQLDAO implements UserDAO {

public LinkedList<User> getAllInfoUser () throws ParseException {
	String query = "select sum(ORDINE.totale) as totale,ORDINE.id_utente,UTENTE.nome,UTENTE.cognome,UTENTE.telefono,UTENTE.sesso,UTENTE.data_nascita,UTENTE.citta,UTENTE.provincia,UTENTE.cap,UTENTE.email,UTENTE.indirizzo from ORDINE " + 
			"right outer join UTENTE  on ORDINE.id_utente=UTENTE.id_utente group by UTENTE.id_utente";
	ArrayList<Object> params = null;
	LinkedList<User> list = new LinkedList<User> ();
	User u=null;
	
	try {
		ResultSet rs = Database.getInstance().execQuery(query.toUpperCase(), params);
		if(rs!= null){
			while(rs.next()){
				SimpleDateFormat in = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.US);
		    	String parameter = rs.getString("data_nascita");
		    	Date date=null;
		    	try {
					date = in.parse(parameter);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		       
				u= new User(rs.getInt("id_utente"),rs.getString("nome"),rs.getString("cognome"),rs.getString("telefono"),rs.getString("citta"),rs.getString("provincia"),rs.getString("cap"),rs.getString("email"),null,date,rs.getString("sesso"),rs.getString("indirizzo"),rs.getDouble("totale"));
				list.add(u);
				
			}
		}
	} catch (SQLException ex) { 
	}
	return list;
}


@Override
public boolean deleteUser(String email){
    ArrayList<Object> params = new ArrayList<>();
    String query = "DELETE FROM UTENTE WHERE EMAIL = ?";
    params.add(email);
   
    try {
        Database.getInstance().execUpdate(query.toUpperCase(), params);
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
        return false;
    }
    return true;
}


}
