package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.dao.concrete.oracle.UserMySQLDAO;
import models.dao.interfaces.UserDAO;

/**
 * Servlet implementation class PersonalController
 */
@WebServlet("/MyInfo")
public class PersonalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUtente = request.getParameter("param");
		UserDAO usr = new UserMySQLDAO();
		User myUser=null;
		RequestDispatcher view=null;
		try {
			myUser = usr.getAllInfoUser(idUtente);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("utente", myUser);
		view = request.getRequestDispatcher("personal.jsp");
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
    	String cognome = request.getParameter("cognome");
    	String numero = request.getParameter("telefono");
    	String sesso = request.getParameter("sesso");
    	String data = request.getParameter("data");
    	SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
    	String parameter = data;
    	Date date=null;
    	RequestDispatcher view=null;
    	try {
			date = in.parse(parameter);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	String citta = request.getParameter("citta");
    	String provincia = request.getParameter("provincia");
    	String cap = request.getParameter("cap");
    	
		User myUser = new User(0,nome,cognome,numero,citta,provincia,cap,null,null,date,sesso);
		request.setAttribute("utente", myUser);
		view = request.getRequestDispatcher("personal-update.jsp");
		view.forward(request, response);
		

	}

}
