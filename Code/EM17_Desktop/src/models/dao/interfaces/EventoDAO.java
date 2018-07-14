package models.dao.interfaces;

import models.Evento;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

/**
*
* @author Fabrizio De Sanctis
*/

public interface EventoDAO {

	public LinkedList<Evento> getListaEventi () throws ParseException;
	public LinkedList<Evento> getAllEventi () throws ParseException;
	public ArrayList<Object> getInfoEventi (Evento e);
	public TreeSet<String> getListaLocalita();
	public boolean createNewEvento(Evento e);
	public boolean deleteEvento(Evento e);
	public boolean updateEvento(Evento e);
	
}
