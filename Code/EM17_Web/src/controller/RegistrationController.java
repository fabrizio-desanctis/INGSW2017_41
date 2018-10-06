package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.dao.concrete.oracle.UserOracleDAO;
import models.dao.interfaces.UserDAO;

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
    	String citta = request.getParameter("citta");
    	String provincia = request.getParameter("provincia");
    	String cap = request.getParameter("cap");
    	String email  = request.getParameter("email");
		String password = User.convertPasswd(request.getParameter("password")).toString();

    	System.out.println(nome);
    	System.out.println(cognome);
    	System.out.println(numero);
    	System.out.println(sesso);
    	System.out.println(nome);
    	System.out.println(data);
    	System.out.println(citta);
    	System.out.println(provincia);
    	System.out.println(cap);
    	System.out.println(email);
    	System.out.println(password);







		RequestDispatcher view=null;
		UserDAO usr = new UserOracleDAO();
		User user = null;
		ArrayList<Integer> error = new ArrayList<Integer>(2);
		error.add(0,0);
		error.add(1,0);
		try {
			user = usr.getUser(email);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(user != null) {
			if(user.getPassword().equals(password)) {
				
				view = request.getRequestDispatcher("index.jsp");
			}
			else {
				error.set(0, 1);
				view = request.getRequestDispatcher("login.jsp");
			}
		}
		
		else {
			error.set(1, 1);
			view = request.getRequestDispatcher("login.jsp");
		}
		request.setAttribute("errore", error);
		view.forward(request, response);
	}

}
