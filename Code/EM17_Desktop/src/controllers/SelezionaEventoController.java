package controllers;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Evento;
import models.dao.concrete.oracle.EventoOracleDAO;
import models.dao.interfaces.EventoDAO;
import views.SelezionaEventoWindow;

public class SelezionaEventoController {
	private static JFrame myFrame;
	private static int scelta;
	
	public static void start(int scelt) {
		scelta=scelt;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelezionaEventoWindow window = new SelezionaEventoWindow();
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
	
	/*Questa classe si occupa di gestire il meccanismo di chiusura della finestra*/
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
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(myFrame, "Le modifiche verranno annullate.\n Confermi?\n", "Annulla", dialogButton);
			if(dialogResult == 0) {
			setInvisible();
			MainMenuController.setVisible();
			}
			
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
	/*Questa procedura carica tutte le righe nella tabella*/
	public void getTable (JTable table) throws ParseException {
		String[] colName = { "NOME EVENTO", "DATA"};

		EventoDAO e = new EventoOracleDAO();
		LinkedList<Evento> list = e.getListaEventi();
		Object[][] object = null;
		
		
        if (list!= null && list.size() != 0) {
        	object = new Object[list.size()][2];
            int i = 0;
            for (Evento x : list) {
            	object[i][0] = x.getNome();
                SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				String dateFormat = dataFormat.format(x.getData());
                object[i][1] = dateFormat;
                i++;
            } 
        }
        table.setModel(new DefaultTableModel(object,colName) {
        	/**
			 * 
			 */
			private static final long serialVersionUID = -103041836869835135L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        
        table.setRowSelectionAllowed(true);
      
		
	}
	
	
	/*Questa classe interna gestisce il funzionamento del tasto Annulla*/
	public class AnnullaListener implements ActionListener {
		
		public AnnullaListener () {
		}
		
		public void actionPerformed(ActionEvent arg0) {
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(myFrame, "Le modifiche verranno annullate.\n Confermi?\n", "Annulla", dialogButton);
			if(dialogResult == 0) {
			setInvisible();
			MainMenuController.setVisible();
			}
	}
	}
}
	


