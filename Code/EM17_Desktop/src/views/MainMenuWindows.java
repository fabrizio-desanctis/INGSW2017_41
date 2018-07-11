package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import controllers.MainMenuController;
import javax.swing.ImageIcon;
import javax.swing.JButton;


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
		frame.setBounds(100, 100, 450, 338);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("EM'17 - Menù");
		
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
		logoutButton.setIcon(new ImageIcon("C:\\Users\\fabri\\git\\INGSW2017_41\\Code\\EM17_Desktop\\src\\logout.png"));
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
		
		MainMenuController controller = new MainMenuController(); //Istanzia controller
		MainMenuController.LogoutListener myLogoutListener = controller.new LogoutListener();
		logoutButton.addActionListener(myLogoutListener);
		MainMenuController.InserisciEventoListener myInserisciListener = controller.new InserisciEventoListener();
		inserireNuovoEventoButton.addActionListener(myInserisciListener);
	}
	
	public JFrame getJFrame() {
		return frame;
	}
	
}
