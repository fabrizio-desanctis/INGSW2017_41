package models;

public class Carrello {
	private int idCarrello;
	private int idUtente;
	private int idEvento;
	private int quantit�;
	
public Carrello() {
		
	}
	
	public Carrello(int carr,int idu,int ide,int quant) {
		this.idCarrello=carr;
		this.idEvento=ide;
		this.idUtente=idu;
		this.quantit�=quant;
	}
	
	public int getIdCarrello() {
		return idCarrello;
	}

	public void setIdCarrello(int idCarrello) {
		this.idCarrello = idCarrello;
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public int getQuantit�() {
		return quantit�;
	}

	public void setQuantit�(int quantit�) {
		this.quantit� = quantit�;
	}

	
	
	
}
