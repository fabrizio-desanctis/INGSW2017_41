package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Evento;
import models.dao.concrete.oracle.CarrelloOracleDAO;
import models.dao.concrete.oracle.EventoOracleDAO;
import models.dao.interfaces.CarrelloDAO;
import models.dao.interfaces.EventoDAO;

/**
 * Servlet implementation class CarrelloController
 */
@WebServlet("/MyCarrello")
public class CarrelloController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static double prezzoEvento;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarrelloController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void setPrezzoEvento(double p) {
    	prezzoEvento=p;
    }
    
    public double getPrezzoEvento() {
    	return prezzoEvento;
    }
    
    public boolean statusCart (int id_utente) {
    	EventoDAO evento = new EventoOracleDAO();
		Evento myevento = new Evento();
		try {
			myevento = evento.getEventoCarrello(Integer.toString(id_utente));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(myevento==null)
			return false;
		
		return true;
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_utente=request.getParameter("param");
		EventoDAO evento = new EventoOracleDAO();
		Evento myevento = new Evento();
		RequestDispatcher view = null;
		CarrelloDAO car = new CarrelloOracleDAO();
		Date today = new Date();
		try {
			myevento = evento.getEventoCarrello(id_utente);
			if(myevento != null)
			if((!today.before(myevento.getData())) ||  evento.getRestanti(myevento.getId())==0 ) {
				myevento=null;
				car.deleteCarrello(Integer.parseInt(id_utente));
				}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("carrello", myevento);
		view = request.getRequestDispatcher("shopping-cart.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
