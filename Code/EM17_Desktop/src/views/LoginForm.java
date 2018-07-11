package views;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Window.Type;
import controllers.LoginFormController;



/**
*
* @author Fabrizio De Sanctis
*/

public class LoginForm {

	private JFrame frmEmLogin;
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		LoginFormController.start();
	}

	/**
	 * Create the application.
	 */
	public LoginForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	
	private void initialize() {
		frmEmLogin = new JFrame("Home");
		frmEmLogin.setTitle("EM'17 - Login");
		frmEmLogin.setType(Type.UTILITY);
		frmEmLogin.setBounds(100, 100, 450, 300);
		frmEmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEmLogin.getContentPane().setLayout(null);
		frmEmLogin.setLocationRelativeTo(null);
		
		JLabel em17Label = new JLabel("EM'17");
		em17Label.setBounds(134, 11, 168, 71);
		em17Label.setVerticalAlignment(SwingConstants.TOP);
		em17Label.setFont(new Font("Arial Black", Font.PLAIN, 50));
		frmEmLogin.getContentPane().add(em17Label);
		
		JLabel usernameLabel = new JLabel("USERNAME");
		usernameLabel.setFont(new Font("Arial Black", Font.PLAIN, 13));
		usernameLabel.setBounds(88, 109, 91, 19);
		frmEmLogin.getContentPane().add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("PASSWORD");
		passwordLabel.setFont(new Font("Arial Black", Font.PLAIN, 13));
		passwordLabel.setBounds(88, 150, 91, 19);
		frmEmLogin.getContentPane().add(passwordLabel);
		
		
		
		JButton accediButton = new JButton("ACCEDI");
		accediButton.setFont(new Font("Arial Black", Font.BOLD, 12));
		
		accediButton.setBounds(134, 194, 168, 33);
		frmEmLogin.getContentPane().add(accediButton);
		accediButton.setEnabled(false);
		
		
		usernameField = new JTextField();
		usernameField.setBounds(189, 109, 150, 20);
		frmEmLogin.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(191, 151, 148, 20);
		frmEmLogin.getContentPane().add(passwordField);
		
		
		LoginFormController controller = new LoginFormController(); //Istanzia controller.
		//Istanzia nuovo CaretListener per la gestione delle caselle di testo.
		LoginFormController.AccediCaretListener myCaretListener = controller.new AccediCaretListener(accediButton,passwordField,usernameField);
		//Collega il CaretListener alle due caselle di testo.
		passwordField.addCaretListener(myCaretListener);
		usernameField.addCaretListener(myCaretListener);
		//Istanzia nuovo Listener per il tasto Accedi.
		LoginFormController.EffettuaLoginListener myEffettuaLogin = controller.new EffettuaLoginListener(passwordField,usernameField);
		//Collega il Listener al tasto Accedi.
		accediButton.addActionListener(myEffettuaLogin);
		
	}
	
	public JFrame getJFrame() {
		return frmEmLogin;
	}
	
}



 