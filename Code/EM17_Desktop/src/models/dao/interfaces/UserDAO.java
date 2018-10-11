package models.dao.interfaces;

import java.text.ParseException;
import java.util.LinkedList;
import models.User;

public interface UserDAO {
	public LinkedList<User> getAllInfoUser () throws ParseException;
	public boolean deleteUser(String email);
	
}
