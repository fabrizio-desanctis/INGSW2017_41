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

import com.itextpdf.text.DocumentException;

import controller.LoginController;
import models.Biglietto;
import models.Carrello;
import models.Ordine;
import models.dao.concrete.oracle.BigliettoOracleDAO;
import models.dao.concrete.oracle.CarrelloOracleDAO;
import models.dao.concrete.oracle.OrdineOracleDAO;
import models.dao.interfaces.BigliettoDAO;
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
		OrdineDAO ord = new OrdineOracleDAO();
		CarrelloDAO car = new CarrelloOracleDAO();
		BigliettoDAO bigl = new BigliettoOracleDAO();
		LoginController control= new LoginController();
		RequestDispatcher view = null;
		Ordine myOrdine = null;
		
		try {
			if(ord.getOrdinebyId(id)==false) {
				Carrello c = car.getCarrello(Integer.toString(control.getIdUtente()));
				request.setAttribute("carrello", c);
				view = request.getRequestDispatcher("pdfgen?param="+id);
				view.forward(request, response);
				}
			
			else {
				request.setAttribute("ordine", null);
				view = request.getRequestDispatcher("payment-succes.html");
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
