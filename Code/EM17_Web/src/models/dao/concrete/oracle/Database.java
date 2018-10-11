package models.dao.concrete.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.jdbc.Driver;

/**
*
* @author Fabrizio De Sanctis
*/

public class Database {
    private static Database instance;
    private final LinkedBlockingQueue<Connection> freeConnections;
    private static final int MAXCONNECTIONS = 5;
  
    
    String dbName = "EM17";
    String userName = "fabrizio";
    String password = "23041995";
    String hostname = "em17-mobile.clxk8xciqyvy.us-east-1.rds.amazonaws.com";
    String port = "3306";
    String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
    
    private Connection conn;
    private final int connections; //total number of connections.
    
    private Database() {
        int n=0;
        freeConnections = new LinkedBlockingQueue<>(MAXCONNECTIONS);
        for(int i = 0;i < MAXCONNECTIONS;i++){
            try {
                conn = createConnection();
                freeConnections.put(conn);
                n++;
            } catch (SQLException | InterruptedException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println(ex.getMessage());
            } 
        }
        connections = n;
    }

   
    
    
    private Connection createConnection() throws SQLException{
   
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Connection newConnection = DriverManager.getConnection(jdbcUrl);
        return newConnection;
    }
    
    public void commit() throws SQLException{
        Connection c = this.getConnection();
        if(c!=null)
            c.commit();
    }
    
    public void rollback() throws SQLException{
        Connection c = this.getConnection();
        if(c!=null)
            c.rollback();
    }
    
    private Connection getConnection(){
        Connection c = null;
        if(connections != 0){
            try {
                c = freeConnections.take();
            } catch (InterruptedException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return c;
    }
    
    public static synchronized Database getInstance(){
        if(instance==null){
            instance = new Database();
        }
        return instance;
    }
    
    
    public ResultSet execUpdate(String query, List<Object> params) throws SQLException{
        PreparedStatement st;
        ResultSet rs = null;
        Connection c = getConnection();
        int k = 1;
        st = c.prepareStatement(query);
        if (params != null){
            for (Object p : params ){
                if( p instanceof java.sql.Date)
                    st.setDate(k,(Date) p);
                else if (p instanceof Float)
                    st.setFloat(k,(Float)p);
                else if( p instanceof Integer)
                    st.setInt(k, (Integer)p);
                else if( p instanceof Number)
                    st.setDouble(k, (Double)p);
                else if( p instanceof String)
                    st.setString(k, (String)p);
                else
                    System.err.println(p.getClass().toString());
                k++;
            }
        }
        try{
            st.execute();
            freeConnections.put(c);
        }catch(SQLException e){
            System.err.println("statement = " + st.toString());
            System.err.println("query = " + query);
            System.err.println("params = " + params);
            throw e;
        } catch (InterruptedException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet execQuery(String query, List<Object> params) throws SQLException{
        PreparedStatement st;
        ResultSet rs = null;
        Connection c = getConnection();
        int k = 1;
        st = c.prepareStatement(query);
        if (params != null){
            for (Object p : params ){
                if( p instanceof java.sql.Date)
                    st.setDate(k,(Date) p);
                else if (p instanceof Float)
                    st.setFloat(k,(Float)p);
                else if( p instanceof Integer)
                    st.setInt(k, (Integer)p);
                else if( p instanceof Number)
                    st.setDouble(k, (Double)p);
                else if( p instanceof String)
                    st.setString(k, (String)p);
                else
                    System.err.println(p.getClass().toString());
                k++;
            }
        }
        try{
            rs = st.executeQuery();
            freeConnections.put(c);
        }catch(SQLException e){
            System.err.println("statement = " + st.toString());
            System.err.println("query = " + query);
            System.err.println("params = " + params);
            throw e;
        } catch (InterruptedException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    
    
}