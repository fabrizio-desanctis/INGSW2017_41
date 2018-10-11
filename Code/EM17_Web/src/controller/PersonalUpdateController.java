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

/**
 * Servlet implementation class PersonalUpdateController
 */
@WebServlet("/ModifyUser")
public class PersonalUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
    	String cognome = request.getParameter("cognome");
    	String numero = request.getParameter("telefono");
    	String sessoM = request.getParameter("sessoM");
    	String sessoF = request.getParameter("sessoF");
    	String indirizzo = request.getParameter("indirizzo");
    
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
    	String sesso=null;
    		if(sessoF!=null) {
    			if(sessoF.equals("M"))
    				sesso=new String("M");
    			else sesso=new String("F");
    		}
    		
    		else if(sessoM !=null) {
    			if(sessoM.equals("M"))
    				sesso=new String("M");
    			else sesso=new String("F");
    		}
    		//sesso=sessoM;
    	//if(sessoM==null)
    		//sesso=sessoF;
    		
    		
    	LoginController log = new LoginController();
		User myUser = new User(log.getIdUtente(),nome,cognome,numero,citta,provincia,cap,null,null,date,sesso,indirizzo);
    
    			
		request.setAttribute("utente", myUser);
		view = request.getRequestDispatcher("personal.jsp");
		view.forward(request, response);
	}

}
