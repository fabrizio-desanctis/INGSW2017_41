package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import controllers.VisualizzaStatisticheController;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JButton;



/**
*
* @author Fabrizio De Sanctis
*/

public class VisualizzaStatisticheWindow {

	private JFrame frame;


	/**
	 * Create the application.
	 */
	public VisualizzaStatisticheWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 573, 484);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("EM'17 - Inserisci nuovo evento");
		
		JLabel label = new JLabel("EM'17");
		label.setBounds(10, 11, 172, 71);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setFont(new Font("Arial Black", Font.PLAIN, 50));
		frame.getContentPane().add(label);
		
		
		JLabel tipologiaLabel = new JLabel("Biglietti (Venduti/Totale):");
		tipologiaLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		tipologiaLabel.setBounds(20, 96, 191, 14);
		frame.getContentPane().add(tipologiaLabel);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 121, 391, 8);
		frame.getContentPane().add(separator_1);
		//Limite caratteri per il campo descrizione
		
		
		JButton indietroButton = new JButton("ANNULLA");
		indietroButton.setFont(new Font("Arial Black", Font.BOLD, 12));
		indietroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		indietroButton.setBounds(10, 391, 162, 41);
		frame.getContentPane().add(indietroButton);
		
		JLabel InfoLabel = new JLabel("Statistiche");
		InfoLabel.setVerticalAlignment(SwingConstants.TOP);
		InfoLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		InfoLabel.setBounds(184, 46, 136, 41);
		frame.getContentPane().add(InfoLabel);
		
		JLabel vendutiLabel = new JLabel("New label");
		vendutiLabel.setFont(new Font("Arial", Font.BOLD, 11));
		vendutiLabel.setBounds(221, 97, 73, 14);
		frame.getContentPane().add(vendutiLabel);
		
		JLabel lblOrdiniTotali = new JLabel("Ordini totali:");
		lblOrdiniTotali.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblOrdiniTotali.setBounds(20, 126, 191, 14);
		frame.getContentPane().add(lblOrdiniTotali);
		
		JLabel ordiniTotaliLabel = new JLabel("New label");
		ordiniTotaliLabel.setFont(new Font("Arial", Font.BOLD, 11));
		ordiniTotaliLabel.setBounds(221, 127, 143, 14);
		frame.getContentPane().add(ordiniTotaliLabel);
	    
	    
	    
	    VisualizzaStatisticheController controller = new VisualizzaStatisticheController(); //Istanzia controller
	  //Listener per il tasto Annulla
		VisualizzaStatisticheController.AnnullaListener myAnnullaListener = controller.new AnnullaListener();
		indietroButton.addActionListener(myAnnullaListener);
	    frame.addWindowListener(controller.new MyWindowListener());
	    controller.caricaInfoEvento(vendutiLabel, ordiniTotaliLabel);
	    
	   
	    
	}
	
	public JFrame getJFrame() {
		return frame;
	}
}
