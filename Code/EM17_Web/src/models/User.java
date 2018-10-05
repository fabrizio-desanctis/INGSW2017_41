package models;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class User {
	private int id;
	private String nome, cognome, telefono,città, provincia,cap,email,password;
	private Date dataNascita;
	private char sesso;
	
	public User(int i,String name,String cognom, String tel, String city, String prov, String cap, String mail, String pw, Date dt, char sex) {
		this.id=i;
		this.nome=name;
		this.cognome=cognom;
		this.telefono=tel;
		this.città=city;
		this.provincia=prov;
		this.cap=cap;
		this.email=mail;
		this.password=pw;
		this.dataNascita=dt;
		this.sesso=sex;
	}
	
	public User() {
		
	}
	
	public User(String email,String password) {
		this.email=email;
		this.password=password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCittà() {
		return città;
	}

	public void setCittà(String città) {
		this.città = città;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public char getSesso() {
		return sesso;
	}

	public void setSesso(char sesso) {
		this.sesso = sesso;
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
