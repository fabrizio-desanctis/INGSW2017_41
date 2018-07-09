package dao.concrete.oracle;

import dao.interfaces.EventoDAO;
import models.Evento;
import java.util.LinkedList;

public class EventoOracleDAO implements EventoDAO{
	
@Override
public LinkedList<Evento> getListaEventi () {
	return new LinkedList<Evento> ();
}
}
