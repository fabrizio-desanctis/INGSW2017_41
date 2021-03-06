package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import controllers.MainMenuController;
import javax.swing.JButton;
import javax.swing.ImageIcon;




/**
*
* @author Fabrizio De Sanctis
*/

public class MainMenuWindows {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public MainMenuWindows() {
		initialize();
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 384);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("EM'17 - Men�");
		
		JLabel em17Label = new JLabel("EM'17");
		em17Label.setVerticalAlignment(SwingConstants.TOP);
		em17Label.setFont(new Font("Arial Black", Font.PLAIN, 50));
		em17Label.setBounds(131, 11, 168, 71);
		frame.getContentPane().add(em17Label);
		frame.setLocationRelativeTo(null);
		
		JLabel LogoutLabel = new JLabel("Logout");
		LogoutLabel.setFont(new Font("Arial Black", Font.PLAIN, 12));
		LogoutLabel.setBounds(10, 43, 46, 14);
		frame.getContentPane().add(LogoutLabel);
		
		JButton logoutButton = new JButton("");
		logoutButton.setIcon(new ImageIcon(MainMenuWindows.class.getResource("/logout.png")));
		logoutButton.setBounds(23, 22, 22, 22);
		frame.getContentPane().add(logoutButton);
		
		JButton inserireNuovoEventoButton = new JButton("INSERIRE NUOVO EVENTO");
		inserireNuovoEventoButton.setFont(new Font("Arial Black", Font.BOLD, 8));
		inserireNuovoEventoButton.setBounds(115, 93, 199, 34);
		frame.getContentPane().add(inserireNuovoEventoButton);
		
		
		JButton modificaEventoButton = new JButton("MODIFICA EVENTO ESISTENTE");
		modificaEventoButton.setFont(new Font("Arial Black", Font.BOLD, 8));
		modificaEventoButton.setBounds(115, 138, 199, 34);
		frame.getContentPane().add(modificaEventoButton);
		
		JButton eliminaEventoButton = new JButton("ELIMINA EVENTO ESISTENTE");
		eliminaEventoButton.setFont(new Font("Arial Black", Font.BOLD, 8));
		eliminaEventoButton.setBounds(115, 183, 199, 34);
		frame.getContentPane().add(eliminaEventoButton);
		
		JButton visualizzaStatisticheButton = new JButton("VISUALIZZA STATISTICHE");
		visualizzaStatisticheButton.setFont(new Font("Arial Black", Font.BOLD, 8));
		visualizzaStatisticheButton.setBounds(115, 228, 199, 34);
		frame.getContentPane().add(visualizzaStatisticheButton);
		
		JButton utenteButton = new JButton("VISUALIZZA/ELIMINA UTENTE");
		utenteButton.setFont(new Font("Arial Black", Font.BOLD, 8));
		utenteButton.setBounds(115, 273, 199, 34);
		frame.getContentPane().add(utenteButton);
		
		MainMenuController controller = new MainMenuController(); //Istanzia controller
		MainMenuController.LogoutListener myLogoutListener = controller.new LogoutListener();
		logoutButton.addActionListener(myLogoutListener);
		MainMenuController.InserisciEventoListener myInserisciListener = controller.new InserisciEventoListener();
		inserireNuovoEventoButton.addActionListener(myInserisciListener);
		MainMenuController.ModificaEventoListener myModificaListener = controller.new ModificaEventoListener();
		modificaEventoButton.addActionListener(myModificaListener);
		MainMenuController.EliminaEventoListener myEliminaListener = controller.new EliminaEventoListener();
		eliminaEventoButton.addActionListener(myEliminaListener);
		MainMenuController.StatisticheListener myStatListener = controller.new StatisticheListener();
		visualizzaStatisticheButton.addActionListener(myStatListener);
		MainMenuController.UtenteListener myUtenteListener = controller.new UtenteListener();
		utenteButton.addActionListener(myUtenteListener);
	}
	
	public JFrame getJFrame() {
		return frame;
	}
}
