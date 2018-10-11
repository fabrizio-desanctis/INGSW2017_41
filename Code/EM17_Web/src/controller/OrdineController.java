package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Ordine;
import models.dao.concrete.MySQL.OrdineMySQLDAO;
import models.dao.interfaces.OrdineDAO;


/**
 * Servlet implementation class OrdineController
 */
@WebServlet("/Ordini")
public class OrdineController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdineController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("param");
		OrdineDAO ord = new OrdineMySQLDAO();
		LinkedList<Ordine> myOrd= new LinkedList<Ordine>();
		RequestDispatcher view=null;
		try {
			myOrd = ord.getOrdineInfoList(id);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		request.setAttribute("ordini", myOrd);
		view = request.getRequestDispatcher("orders.jsp");
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
