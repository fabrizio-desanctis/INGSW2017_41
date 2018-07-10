package models.dao.concrete.oracle;

import models.Amministratore;
import models.dao.interfaces.AmministratoreDAO;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
*
* @author Fabrizio De Sanctis
*/

public class AmministratoreOracleDAO implements AmministratoreDAO {
	@Override
	public List<Amministratore> getAmministratoriList () {
		
		String query = "select * from Amministratore " ;
		ArrayList<Object> params = null;
		List<Amministratore> list = new ArrayList<>();
    
		try {
			ResultSet rs = Database.getInstance().execQuery(query, params);
			if(rs!= null){
				while(rs.next()){
					Amministratore a = new Amministratore(rs.getString("USERNAME"),rs.getString("MD5_PASSWORD"));
					list.add(a);
					
				}
			}
		} catch (SQLException ex) {}
    return list;
}

	}

