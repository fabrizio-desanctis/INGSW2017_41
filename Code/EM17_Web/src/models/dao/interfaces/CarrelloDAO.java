package models.dao.interfaces;

import java.text.ParseException;

import models.Carrello;

public interface CarrelloDAO {
	public boolean createNewCarrello(int id_utente,int id_evento,int quantita);
	public boolean deleteCarrello(int utente);
	public Carrello getCarrello (String utente) throws ParseException;
}
