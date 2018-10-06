package models.dao.concrete.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import models.Evento;
import models.User;
import models.dao.interfaces.UserDAO;

public class UserOracleDAO implements UserDAO {

	@Override
	public User getUser (String user) throws ParseException {
		String query = "select email,password from utente where email like '" + user + "'" ;
		ArrayList<Object> params = null;
		User u=null;
		
		try {
			ResultSet rs = Database.getInstance().execQuery(query, params);
			if(rs!= null){
				while(rs.next()){
					u= new User(rs.getString("EMAIL"),rs.getString("PASSWORD"));
					
					
				}
			}
		} catch (SQLException ex) { 
		}
		return u;
	}
	
	@Override
	public boolean createNewUser(User u) {
		ArrayList<Object> params = new ArrayList<>();
	    String query = "INSERT INTO UTENTE (ID_UTENTE,NOME,COGNOME,TELEFONO,SESSO,DATA_NASCITA,CITTA,PROVINCIA,CAP,EMAIL,PASSWORD) VALUES(id_user_sequence.nextval,?,"
	    		+ "?,?,?,?,?,?,?,?,?)";
	    
	    params.add(u.getNome());
	    params.add(u.getCognome());
	    params.add(u.getTelefono());
	    params.add(u.getSesso());
	    params.add(u.getDataNascita().toString());
	    params.add(u.getCittà());
	    params.add(u.getProvincia());
	    params.add(u.getCap());
	    params.add(u.getEmail());
	    params.add(u.getPassword());
	 
	    try {
	        Database.getInstance().execQuery(query, params);
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	        return false;
	    }
	    
	    return true;
	}
}
