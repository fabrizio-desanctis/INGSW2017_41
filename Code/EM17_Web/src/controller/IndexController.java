package controller;


import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Evento;
import models.dao.concrete.oracle.EventoMySQLDAO;
import models.dao.interfaces.EventoDAO;

/**
 * Servlet implementation class IndexController
 */
@WebServlet(urlPatterns = "/SearchEvent2")
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
    	EventoDAO e = new EventoMySQLDAO();
    	TreeSet<String> list = new TreeSet<String>();
    	list= e.getListaLocalita();
    	return list;
    }
    
    
    public LinkedList<Evento> getEventi () throws ParseException {
    	EventoDAO e = new EventoMySQLDAO();
    	List<Evento> list = new LinkedList<Evento>();
    	LinkedList<Evento> returned = new LinkedList<Evento>();
    	list = e.getPopolari();
    	int i=0;
    	for (Evento x: list) {
    		if(i<5) {
    			returned.add(x);
    			
    			
    		}
    			
    		i++;
    	}
    	return returned;
    }
    
    public static LinkedList<Evento> getSport () throws ParseException {
    	EventoDAO e = new EventoMySQLDAO();
    	List<Evento> list = new LinkedList<Evento>();
    	LinkedList<Evento> returned = new LinkedList<Evento>();
    	list = e.getEventiSport();
    	
    	for (Evento x: list) 
    		returned.add(x);
    			
    	return returned;
    }
    
    public static LinkedList<Evento> getSpettacolo () throws ParseException {
    	EventoDAO e = new EventoMySQLDAO();
    	List<Evento> list = new LinkedList<Evento>();
    	LinkedList<Evento> returned = new LinkedList<Evento>();
    	list = e.getEventiSpettacolo();
    	
    	for (Evento x: list) 
    			returned.add(x);
    			
      	return returned;
    }
    
    public static LinkedList<Evento> getCultura () throws ParseException {
    	EventoDAO e = new EventoMySQLDAO();
    	List<Evento> list = new LinkedList<Evento>();
    	LinkedList<Evento> returned = new LinkedList<Evento>();
    	list = e.getEventiCultura();
    	
    	for (Evento x: list) 
    			returned.add(x);
    	
    	return returned;
    }
	
    public static LinkedList<Evento> getConcerti () throws ParseException {
    	EventoDAO e = new EventoMySQLDAO();
    	List<Evento> list = new LinkedList<Evento>();
    	LinkedList<Evento> returned = new LinkedList<Evento>();
    	list = e.getEventiConcerti();
    
    	for (Evento x: list) 
    			returned.add(x);
    
    	return returned;
    }
    
    public static LinkedList<Evento> getEventoByID (String search) throws ParseException {
    	EventoDAO e = new EventoMySQLDAO();
    	List<Evento> list = new LinkedList<Evento>();
    	LinkedList<Evento> returned = new LinkedList<Evento>();
    	list = e.getEventibyID(search);
    
    	for (Evento x: list) 
    			returned.add(x);
    
    	return returned;
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    		throws ServletException, IOException  {
    		List<Evento> list = new LinkedList<Evento>();
    		RequestDispatcher view;
    		boolean productPage = false;
    		if(req.getParameter("param").toString().equals("Sport")) {
    			try {
    				list = IndexController.getSport();
				} catch (ParseException e1) {
					}
    		}
    		
    		else if(req.getParameter("param").toString().equals("Spettacolo")) {
    			try {
    				list = IndexController.getSpettacolo();
				} catch (ParseException e1) {
					}
    		}
    		
    		else if(req.getParameter("param").toString().equals("Cultura")) {
    			try {
    				list = IndexController.getCultura();
				} catch (ParseException e1) {
					}
    		}
    		
    		else if(req.getParameter("param").toString().equals("Concerti")) {
    			try {
    				list = IndexController.getConcerti();
				} catch (ParseException e1) {
					}
    		}
    		
    		else {
    			try {
    				productPage=true;
    				list = IndexController.getEventoByID(req.getParameter("param"));
    			} catch (ParseException e1) {
    				
    			}
    		}
    		
    		req.setAttribute("eventi", list);	
    		if(productPage == true)
    			view = req.getRequestDispatcher("product-page.jsp");
    		else if(list.size()==0) {
				view = req.getRequestDispatcher("search-failed.jsp");  // ..e lo inviamo alla JSP
			}
			else {
				view = req.getRequestDispatcher("search.jsp");  // ..e lo inviamo alla JSP
			}

    		view.forward(req, res);    	
    		
    }
    
    
    public void doPost(HttpServletRequest req, HttpServletResponse res)
    		throws ServletException, IOException  {
    	EventoDAO e = new EventoMySQLDAO();
    	List<Evento> list = new LinkedList<Evento>();
		RequestDispatcher view;
			try {
				list = e.getEventiFromSearch(req.getParameter("cerca").toString());
			} catch (ParseException e1) {
			}
		
		req.setAttribute("eventi", list);	
		if(list.size()==0) {
			view = req.getRequestDispatcher("search-failed.jsp");  // ..e lo inviamo alla JSP
		}
		else {
			view = req.getRequestDispatcher("search.jsp");  // ..e lo inviamo alla JSP
		}
	view.forward(req, res);
		
    }
    
}
