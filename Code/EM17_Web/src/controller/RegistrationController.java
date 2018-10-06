package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.dao.concrete.oracle.UserOracleDAO;
import models.dao.interfaces.UserDAO;
import oracle.sql.DATE;

/**
 * Servlet implementation class RegistrationController
 */
@WebServlet("/AddUser")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationController() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	String nome = request.getParameter("nome");
    	String cognome = request.getParameter("cognome");
    	String numero = request.getParameter("telefono");
    	String sesso = request.getParameter("sesso");
    	String data = request.getParameter("data");
    	SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
    	String parameter = data;
    	Date date=null;
    	try {
			date = in.parse(parameter);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	String citta = request.getParameter("citta");
    	String provincia = request.getParameter("provincia");
    	String cap = request.getParameter("cap");
    	String email  = request.getParameter("email");
		String password = User.convertPasswd(request.getParameter("password")).toString();

    	

    	RequestDispatcher view=null;
		UserDAO usr = new UserOracleDAO();
		User user = null;

		try {
			user = usr.getUser(email);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(user == null) {
				User newUser = new User(0,nome,cognome,numero,citta,provincia,cap,email,password,date,sesso);
				usr.createNewUser(newUser);
				view = request.getRequestDispatcher("registration-success.html");
		}
		
			else {
				
				view = request.getRequestDispatcher("registration-failed.html");
			}
		
		view.forward(request, response);
	}

}
