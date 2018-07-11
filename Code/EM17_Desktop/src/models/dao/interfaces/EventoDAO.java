package models.dao.interfaces;

import models.Evento;
import java.util.LinkedList;
import java.util.TreeSet;

/**
*
* @author Fabrizio De Sanctis
*/

public interface EventoDAO {

	public LinkedList<Evento> getListaEventi ();
	public TreeSet<String> getListaLocalita();
	public boolean createNewEvento(Evento e);
	
}
