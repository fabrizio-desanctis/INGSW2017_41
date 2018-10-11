package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.dao.concrete.oracle.CarrelloMySQLDAO;
import models.dao.interfaces.CarrelloDAO;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/AddToCart")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int idEvento=0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public int getIdEvento() {
    	return idEvento;
    }
    
    public void setIdEvento(int i) {
    	idEvento=i;
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
		String quantita=request.getParameter("quantita");
		CarrelloDAO cart = new CarrelloMySQLDAO();
		RequestDispatcher view=null;
		cart.createNewCarrello(new LoginController().getIdUtente(), getIdEvento(), Integer.parseInt(quantita));
		view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
		
	}

}
