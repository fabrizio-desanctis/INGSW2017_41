package controllers;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class LoginFormController {

	
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
}
