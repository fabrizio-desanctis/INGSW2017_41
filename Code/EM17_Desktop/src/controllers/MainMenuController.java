package controllers;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import views.MainMenuWindows;
import controllers.SelezionaEventoController;

/**
*
* @author Fabrizio De Sanctis
*/

public class MainMenuController {
	private static JFrame myFrame;
	
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuWindows window = new MainMenuWindows();
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
	
	/*Questa classe interna gestisce il funzionamento del tasto Logout*/
	public class LogoutListener implements ActionListener {
		
		public LogoutListener () {
		}
		
		public void actionPerformed(ActionEvent arg0) {
			setInvisible();
			LoginFormController.setVisible();
		}
	}
	
	/*Questa classe interna gestisce il funzionamento del tasto Inserisci nuovo evento*/
	public class InserisciEventoListener implements ActionListener {
		
		public InserisciEventoListener () {
		}
		
		public void actionPerformed(ActionEvent arg0) {
			setInvisible();
			InserisciEventoController.start();
		}
	}
	
	/*Questa classe interna gestisce il funzionamento del tasto Inserisci nuovo evento*/
	public class ModificaEventoListener implements ActionListener {
		
		public ModificaEventoListener () {
		}
		
		public void actionPerformed(ActionEvent arg0) {
			setInvisible();
			SelezionaEventoController.start(1);
		}
	}
	
	/*Questa classe interna gestisce il funzionamento del tasto Elimina evento*/
	public class EliminaEventoListener implements ActionListener {
		
		public EliminaEventoListener () {
		}
		
		public void actionPerformed(ActionEvent arg0) {
			setInvisible();
			SelezionaEventoController.start(2);
		}
	}
	
	/*Questa classe interna gestisce il funzionamento del tasto Visualizza statistiche evento*/
	public class StatisticheListener implements ActionListener {
		
		public StatisticheListener () {
		}
		
		public void actionPerformed(ActionEvent arg0) {
			setInvisible();
			SelezionaEventoController.start(3);
		}
	}

}
