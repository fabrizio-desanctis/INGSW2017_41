package models.dao.interfaces;

import java.text.ParseException;
import java.util.LinkedList;

import models.Evento;
import models.User;

public interface UserDAO {
	public User getUser (String user) throws ParseException;
	public boolean createNewUser(User u);
	public User getAllInfoUser (String id) throws ParseException;
	public boolean updateUser(User u);
}
