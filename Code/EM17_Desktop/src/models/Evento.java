package models;



public class Evento {
	private int id;
	private String nome;
	private String tipologia;
	private String luogo;
	private String località;
	private float prezzo;
	private int numeroBiglietti;
	private String descrizione;
	private String linkImmagine;
	
	//Costruttore di Evento
	public Evento () {
		
	}
	
	//ID setter e getter
	public void setId (int id) {
		this.id=id;
	}
	
	public int getId () {
		return this.id;
	}
	
	//Nome setter e getter
	public void setNome (String nome) {
		this.nome=nome;
	}
	
	public String getNome () {
		return this.nome;
	}
	
	//Tipologia setter e getter
	public void setTipologia (String tipologia) {
		this.tipologia=tipologia;
	}
	
	public String getTipologia () {
		return this.tipologia;
	}
	
	//Luogo setter e getter
	public void setLuogo (String luogo) {
		this.luogo=luogo;
	}
	
	public String getLuogo () {
		return this.luogo;
	}
	
	//Località setter e getter
	public void setLocalità (String località) {
		this.località=località;
	}
	
	public String getLocalità () {
		return this.località;
	}
	
	//Prezzo setter e getter
	public void setPrezzo (float prezzo) {
		this.prezzo=prezzo;
	}
	
	public float getPrezzo () {
		return this.prezzo;
	}
	
	//Numero biglietti setter e getter
	public void setNumeroBiglietti (int numeroBiglietti) {
		this.numeroBiglietti=numeroBiglietti;
	}
	
	public int getNumeroBiglietti () {
		return this.numeroBiglietti;
	}
	
	//Descrizione setter e getter
	public void setDescrizione (String descrizione) {
		this.descrizione=descrizione;
	}
	
	public String getDescrizione () {
		return this.descrizione;
	}
	
	//Link setter e getter
	public void setLinkImmagine (String linkImmagine) {
		this.linkImmagine=linkImmagine;
	}
	
	public String getLinkImmagine () {
		return this.linkImmagine;
	}
}
