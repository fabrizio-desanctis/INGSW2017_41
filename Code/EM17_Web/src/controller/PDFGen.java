package controller;


import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfWriter;
import models.Biglietto;
import models.Carrello;
import models.Ordine;
import models.dao.concrete.oracle.BigliettoMySQLDAO;
import models.dao.concrete.oracle.CarrelloMySQLDAO;
import models.dao.concrete.oracle.OrdineMySQLDAO;
import models.dao.interfaces.BigliettoDAO;
import models.dao.interfaces.CarrelloDAO;
import models.dao.interfaces.OrdineDAO;


 

@WebServlet("/pdfgen")
public class PDFGen extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* Font and color constants */
    private static final Font FONT1 = new Font(Font.FontFamily.COURIER, 30, Font.NORMAL, new BaseColor(90,131,219));
    private static final Font FONT2 = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, new BaseColor(120,120,120));
    /**
     * A simple Hello World Servlet.
     * @see HttpServlet#doGet(
     *      HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    			String id = request.getParameter("param");
    			OrdineDAO ord = new OrdineMySQLDAO();
    			CarrelloDAO car = new CarrelloMySQLDAO();
    			BigliettoDAO bigl = new BigliettoMySQLDAO();
    			RequestDispatcher view = null;
    			Ordine myOrdine = null;
    
    	        Carrello c = (Carrello) request.getAttribute("carrello");
    	        ServletContext context = request.getServletContext();
    	        String path = context.getRealPath("/");
    	        System.out.println(path);
    	        
    	        
    	        /* Creates document */
    	        Document document = new Document(PageSize.A4);
    	        document.addTitle("Ticket_ " + id);
    	        document.addCreationDate();
    	        String myPath= "pdf/"+id+".pdf";
    	        
    	        try {
    	        	
    	        	ord.createNewOrdine(Integer.toString(c.getIdCarrello()), c.getIdEvento(), c.getIdUtente(), c.getQuantità(),c.getQuantità(),myPath);
    				myOrdine = ord.getOrdineInfo(Integer.toString(c.getIdCarrello()));
    				Biglietto b = new Biglietto(0,c.getIdEvento(),"NO",Integer.toString(myOrdine.getId_ordine()));
    				for(int i=0;i<c.getQuantità();i++) {
    					bigl.createNewTicket(b);
					}
    				
    	            
    	            PdfWriter.getInstance(document,new FileOutputStream(path + "pdf\\"+id+".pdf"));
    	            document.open();
    	            document.add( new Paragraph("EM '17", FONT1) );
    	            document.add(Chunk.NEWLINE);
    	            document.add( new Paragraph("Posti acquistati: "+c.getQuantità(), FONT2) );
    	            document.add(Chunk.NEWLINE);
    	            document.add( new Paragraph("Grazie per aver acquistato! Ti ricordiamo che tutto ciè che ti occorre per accedere al tuo evento è il QR-Code.", FONT2) );
    	            document.add(Chunk.NEWLINE);
    	            int index=1;
    	            for(Integer i: bigl.getListaBiglietti(Integer.toString(myOrdine.getId_ordine()))) {
    	            BarcodeQRCode barcodeQRCode = new BarcodeQRCode(i.toString(), 1000, 1000, null);
					Image codeQrImage = barcodeQRCode.getImage();
					codeQrImage.scaleAbsolute(100, 100);
					document.add( new Paragraph("QR CODE "+index, FONT1) );
					index++;
					document.add(codeQrImage); 
					}
    	            
    	            document.close();
    	            car.deleteCarrello(c.getIdUtente());
    	            request.setAttribute("ordine", myOrdine);
    				view = request.getRequestDispatcher("payment-success.jsp");
    				view.forward(request, response);


    	        } catch (DocumentException ex) {
    	        } catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	
    }
    }
 
   
