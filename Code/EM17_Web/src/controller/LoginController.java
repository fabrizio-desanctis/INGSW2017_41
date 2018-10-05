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
 * Servlet implementation class LoginController
 */
@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static boolean autenticato=false;;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();

        // TODO Auto-generated constructor stub
    }
    
    public boolean getAutenticato() {
    	return autenticato;
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email  = request.getParameter("user").toString();
		String password = User.convertPasswd(request.getParameter("password")).toString();
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
				autenticato=true;
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
