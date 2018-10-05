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
}
