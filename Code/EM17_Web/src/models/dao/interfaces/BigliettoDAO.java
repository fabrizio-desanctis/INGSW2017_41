package models.dao.interfaces;

import java.text.ParseException;
import java.util.LinkedList;

import models.Biglietto;

public interface BigliettoDAO {
	public boolean createNewTicket(Biglietto b);
	public LinkedList<Integer> getListaBiglietti (String id_ordine) throws ParseException;
}
