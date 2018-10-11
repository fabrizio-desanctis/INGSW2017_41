package controller;

import java.io.IOException;

import java.text.ParseException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.LoginController;
import models.Carrello;
import models.dao.concrete.oracle.CarrelloMySQLDAO;
import models.dao.concrete.oracle.OrdineMySQLDAO;
import models.dao.interfaces.CarrelloDAO;
import models.dao.interfaces.OrdineDAO;
/**
 * Servlet implementation class PagamentoController
 */
@WebServlet("/Checking")
public class PagamentoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PagamentoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("param");
		OrdineDAO ord = new OrdineMySQLDAO();
		CarrelloDAO car = new CarrelloMySQLDAO();
		LoginController control= new LoginController();
		RequestDispatcher view = null;
		
		try {
			if(ord.getOrdinebyId(id)==false) {
				Carrello c = car.getCarrello(Integer.toString(control.getIdUtente()));
				request.setAttribute("carrello", c);
				view = request.getRequestDispatcher("pdfgen?param="+id);
				view.forward(request, response);
				}
			
			else {
				request.setAttribute("ordine", null);
				view = request.getRequestDispatcher("payment-failed.html");
				view.forward(request, response);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
