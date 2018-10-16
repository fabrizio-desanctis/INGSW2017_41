package models;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 

/**
*
* @author Alessandro Barra
*/

public class Amministratore {
 
    private String username;
    private String password;
    
    public Amministratore() {
    	
    }
   
    public Amministratore(String User,String pw){
        this.username=User;
        this.password=pw;
    }
 
    public String getUsername() {
        return username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
   
    public static String convertPasswd(String pass) {
    String password = null;
    MessageDigest mdEnc;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
            mdEnc.update(pass.getBytes(), 0, pass.length());
            pass = new BigInteger(1, mdEnc.digest()).toString(16);
                while (pass.length() < 32) {
                    pass = "0" + pass;
                }
            password = pass;
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
    return password;
    }
   
}