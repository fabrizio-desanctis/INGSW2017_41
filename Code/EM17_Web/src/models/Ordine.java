package models;

import java.util.Date;

public class Ordine {
	private int id_utente,id_evento,quantita;
	private int id_ordine;
	private Date data;
	private String pdf;
	private double totale,prezzo;
	private String nome_evento;
	private String linkImmagine;
	
	
	public String getLinkImmagine() {
		return linkImmagine;
	}

	public void setLinkImmagine(String linkImmagine) {
		this.linkImmagine = linkImmagine;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public String getNome_evento() {
		return nome_evento;
	}

	public void setNome_evento(String nome_evento) {
		this.nome_evento = nome_evento;
	}

	public void setId_ordine(int id_ordine) {
		this.id_ordine = id_ordine;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	
	
	public Ordine () {
		
	}
	
	public Ordine(int ido,int idu, int ide,int quantita,Date data,double totale,String pdf,double prezzo,String nome,String linki) {
		this.id_ordine=ido;
		this.id_utente=idu;
		this.id_evento=ide;
		this.data=data;
		this.quantita=quantita;
		this.totale=totale;
		this.pdf=pdf;
		this.prezzo=prezzo;
		this.nome_evento=nome;
		this.linkImmagine=linki;
		
	}
	
	public String getPdf() {
		return pdf;
	}
	
	public double getTotale() {
		return totale;
	}

	public int getId_ordine() {
		return id_ordine;
	}

	

	public int getId_utente() {
		return id_utente;
	}

	public void setId_utente(int id_utente) {
		this.id_utente = id_utente;
	}

	public int getId_evento() {
		return id_evento;
	}

	public void setId_evento(int id_evento) {
		this.id_evento = id_evento;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
	
	
}
