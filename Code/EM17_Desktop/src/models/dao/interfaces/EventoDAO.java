package models.dao.interfaces;

import models.Evento;
import java.util.LinkedList;

/**
*
* @author Fabrizio De Sanctis
*/

public interface EventoDAO {

	public LinkedList<Evento> getListaEventi ();
	
}
