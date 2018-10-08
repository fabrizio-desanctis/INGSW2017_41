package models.dao.interfaces;

public interface CarrelloDAO {
	public boolean createNewCarrello(int id_utente,int id_evento,int quantita);
	public boolean deleteCarrello(int utente);
}
