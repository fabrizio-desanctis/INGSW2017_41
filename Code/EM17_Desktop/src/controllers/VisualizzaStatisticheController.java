package controllers;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import views.VisualizzaStatisticheWindow;

/**
*
* @author Fabrizio De Sanctis
*/

public class VisualizzaStatisticheController {
private static JFrame myFrame;
private static ArrayList<Object> myParam;
	
	public static void start(ArrayList<Object> x) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myParam=x;
					VisualizzaStatisticheWindow window = new VisualizzaStatisticheWindow();
					myFrame=window.getJFrame();
					myFrame.setLocationRelativeTo(null);
					window.getJFrame().setVisible(true);
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
	
	
	public class MyWindowListener implements WindowListener {

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			setInvisible();
			MainMenuController.setVisible();
		
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
	}
	
	/*Questa classe interna gestisce il funzionamento del tasto Annulla*/
	public class AnnullaListener implements ActionListener {
		
		public AnnullaListener () {
		}
		
		public void actionPerformed(ActionEvent arg0) {
			
			setInvisible();
			MainMenuController.setVisible();
			
	}
	}
	
	
	public void caricaInfoEvento (JLabel venduti, JLabel nrOrdini) {
			if(myParam != null && myParam.size()!= 0) {
			venduti.setText(myParam.get(0).toString() + "/" + myParam.get(1).toString());
			nrOrdini.setText(myParam.get(2).toString());
			}
		
		
	}
	
}
