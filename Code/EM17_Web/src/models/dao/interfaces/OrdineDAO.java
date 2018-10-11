package models.dao.interfaces;

import java.text.ParseException;
import java.util.LinkedList;

import models.Ordine;

public interface OrdineDAO {
	
	public boolean getOrdinebyId (String id) throws ParseException;
	public boolean createNewOrdine(String id,int id_evento,int id_utente,int quantita,int totale,String pdf);
	public Ordine getOrdineInfo (String id) throws ParseException;
	public LinkedList<Ordine> getOrdineInfoList (String id_utente) throws ParseException;
}
