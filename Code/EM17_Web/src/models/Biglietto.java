package models;

public class Biglietto {
	private int idBiglietto,idEvento;
	private String usato,idOrdine;
	
	public Biglietto() {
		
	}
	
	public Biglietto(int idb,int ide,String usato,String ido) {
		this.idBiglietto=idb;
		this.idEvento=ide;
		this.idOrdine=ido;
		this.usato=usato;
	}

	public int getIdBiglietto() {
		return idBiglietto;
	}

	public void setIdBiglietto(int idBiglietto) {
		this.idBiglietto = idBiglietto;
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public String getUsato() {
		return usato;
	}

	public void setUsato(String usato) {
		this.usato = usato;
	}

	public String getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(String idOrdine) {
		this.idOrdine = idOrdine;
	}

}
