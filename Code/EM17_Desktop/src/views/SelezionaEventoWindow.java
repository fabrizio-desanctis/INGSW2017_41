package views;



import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import controllers.SelezionaEventoController;
import java.text.ParseException;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;


/**
*
* @author Fabrizio De Sanctis
*/

public class SelezionaEventoWindow {

	private JFrame frmEmSeleziona;
	private JTable table;

	

	/**
	 * Create the application.
	 * @throws ParseException 
	 */
	public SelezionaEventoWindow() throws ParseException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ParseException 
	 */
	private void initialize() throws ParseException {
		frmEmSeleziona = new JFrame();
		frmEmSeleziona.setTitle("EM' 17 - Seleziona evento");
		frmEmSeleziona.setBounds(100, 100, 573, 484);
		frmEmSeleziona.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmEmSeleziona.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("EM'17");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setFont(new Font("Arial Black", Font.PLAIN, 50));
		label.setBounds(10, 11, 172, 71);
		frmEmSeleziona.getContentPane().add(label);
		
		JLabel selezionaEventoLabel = new JLabel("Seleziona evento");
		selezionaEventoLabel.setVerticalAlignment(SwingConstants.TOP);
		selezionaEventoLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		selezionaEventoLabel.setBounds(188, 46, 276, 71);
		frmEmSeleziona.getContentPane().add(selezionaEventoLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 93, 534, 264);
		frmEmSeleziona.getContentPane().add(scrollPane);
		
		SelezionaEventoController controller = new SelezionaEventoController();
		table = new JTable();
		table.setFont(new Font("Arial", Font.BOLD, 11));
		table.setShowVerticalLines(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		controller.getTable(table);
		table.getSelectionModel().setSelectionInterval(0,0);
		scrollPane.setViewportView(table);
		
		JButton indietroButton = new JButton("ANNULLA");
		indietroButton.setFont(new Font("Arial Black", Font.BOLD, 12));
		indietroButton.setBounds(10, 393, 162, 41);
		frmEmSeleziona.getContentPane().add(indietroButton);
		
		JButton avantiButton = new JButton("CONFERMA");
		avantiButton.setFont(new Font("Arial Black", Font.BOLD, 12));
		avantiButton.setBounds(382, 393, 162, 41);
		frmEmSeleziona.getContentPane().add(avantiButton);
		
		frmEmSeleziona.addWindowListener(controller.new MyWindowListener());
		//Listener per il tasto Annulla
		SelezionaEventoController.AnnullaListener myAnnullaListener = controller.new AnnullaListener();
		indietroButton.addActionListener(myAnnullaListener);
		
	}
	
	public JFrame getJFrame() {
		return frmEmSeleziona;
	}
}
