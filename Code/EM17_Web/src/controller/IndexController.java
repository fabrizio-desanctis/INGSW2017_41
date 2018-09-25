package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Evento;
import models.dao.concrete.oracle.EventoOracleDAO;
import models.dao.interfaces.EventoDAO;

/**
 * Servlet implementation class IndexController
 */
@WebServlet(urlPatterns = "/SearchEvent.do")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    public TreeSet<String> ListaLocalita () {
    	EventoDAO e = new EventoOracleDAO();
    	TreeSet<String> list = new TreeSet<String>();
    	list= e.getListaLocalita();
    	return list;
    }
    
    
    public ArrayList<Evento> getEventiSport () throws ParseException {
    	EventoDAO e = new EventoOracleDAO();
    	ArrayList<Evento> list = new ArrayList<Evento>();
    	ArrayList<Evento> returned = new ArrayList<Evento>();
    	list = e.getEventiSport();
    	int i=0;
    	for (Evento x: list) {
    		if(i<2)
    			returned.add(x);
    		i++;
    	}
    	return returned;
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("useradd.jsp");
		request.getParameter("tipologia");
		System.out.println(request.getParameter("tipologia"));
		System.out.println(request.getParameter("località"));
		System.out.println(request.getParameter("cerca"));
		view.forward(request, response);
	}

}
