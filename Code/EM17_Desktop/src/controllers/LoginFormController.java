package controllers;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import models.Amministratore;
import models.dao.concrete.MySQL.AmministratoreMySQLDAO;
import models.dao.interfaces.AmministratoreDAO;
import views.LoginForm;


/**
*
* @author Fabrizio De Sanctis
*/

public class LoginFormController {
	private static JFrame myFrame;
	
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
					window.getJFrame().setVisible(true);
					myFrame=window.getJFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void setInvisible () {
		myFrame.setVisible(false);
		
	}
	
	public static void setVisible () {
		myFrame.setLocationRelativeTo(null);
		myFrame.setVisible(true);
	}
	
	/*Questa classe interna gestisce il modo in cui il tasto Accedi si abilita/disabilita*/
	public class AccediCaretListener implements CaretListener {
		private JButton accediButton;
		private JPasswordField passwordField;
		private JTextField usernameField;
		
		public AccediCaretListener (JButton accediButton,JPasswordField passwordField,JTextField usernameField) {
			this.accediButton=accediButton;
			this.passwordField=passwordField;
			this.usernameField=usernameField;
		}
		
		public void caretUpdate(CaretEvent e) {
			if(passwordField.getPassword().length >0 && usernameField.getText().length() >0) 
				accediButton.setEnabled(true); 
			else accediButton.setEnabled(false);
			
			
		}
	}
	/*Questa classe interna gestisce il funzionamento del tasto Accedi*/
	public class EffettuaLoginListener implements ActionListener {
		private JPasswordField passwordField;
		private JTextField usernameField;
		private AmministratoreDAO a;
		
		public EffettuaLoginListener (JPasswordField passwordField,JTextField usernameField) {
			this.passwordField=passwordField;
			this.usernameField=usernameField;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			boolean matching=false;
			a = new AmministratoreMySQLDAO(); //Da cambiare se si cambia tipo Database.
			for(Amministratore x: a.getAmministratoriList()) {
				String md5Password= Amministratore.convertPasswd(String.valueOf(passwordField.getPassword()));
				if(md5Password.equals(x.getPassword()) && usernameField.getText().equals(x.getUsername())) {
					matching=true;
					break;
				}
				
			}	
			
			if(matching) {
				MainMenuController.start();
				setInvisible();
			}
			else {
				JOptionPane.showMessageDialog(myFrame, "Non è possibile effettuare l'acceso\n"+"Username e/o Password errata!", "Errore", 0);
			}
				
			
		}
		
	}
	
}
