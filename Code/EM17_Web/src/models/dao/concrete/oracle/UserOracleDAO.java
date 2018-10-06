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
		String query = "select id_utente,email,password from utente where email like '" + user + "'" ;
		ArrayList<Object> params = null;
		User u=null;
		
		try {
			ResultSet rs = Database.getInstance().execQuery(query, params);
			if(rs!= null){
				while(rs.next()){
					u= new User(rs.getInt("ID_UTENTE"),rs.getString("EMAIL"),rs.getString("PASSWORD"));
					
					
				}
			}
		} catch (SQLException ex) { 
		}
		return u;
	}
	
	
	@Override
	public User getAllInfoUser (String id) throws ParseException {
		String query = "select * from utente where id_utente = " + id ;
		ArrayList<Object> params = null;
		User u=null;
		
		try {
			ResultSet rs = Database.getInstance().execQuery(query, params);
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
			       
					u= new User(rs.getInt("id_utente"),rs.getString("nome"),rs.getString("cognome"),rs.getString("telefono"),rs.getString("citta"),rs.getString("provincia"),rs.getString("cap"),rs.getString("email"),rs.getString("password"),date,rs.getString("sesso"));
					
					
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
	
	
	@Override
	public boolean updateUser(User u){
	    ArrayList<Object> params = new ArrayList<>();
	    String query = "UPDATE USER SET  NOME = ?,COGNOME = ?,TELEFONO = ?,SESSO = ?, DATA_NASCITA = ?,CITTA = ?,PROVINCIA = ?,CAP = ? WHERE ID = ?";
	    params.add(u.getNome());
	    params.add(u.getCognome());
	    params.add(u.getTelefono());
	    params.add(u.getSesso());
	    params.add(u.getDataNascita().toString());
	    params.add(u.getCittà());
	    params.add(u.getProvincia());
	    params.add(u.getCap());
	    params.add(u.getId());
	    try {
	        Database.getInstance().execQuery(query, params);
	    } catch (SQLException ex) {
	        System.err.println(ex.getMessage());
	        return false;
	    }
	    return true;
	}
}
