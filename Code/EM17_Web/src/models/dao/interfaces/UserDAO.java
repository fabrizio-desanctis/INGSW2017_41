package models.dao.interfaces;

import java.text.ParseException;
import java.util.LinkedList;

import models.Evento;
import models.User;

public interface UserDAO {
	public User getUser (String user) throws ParseException;
}
