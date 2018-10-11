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
import models.User;
import models.dao.concrete.MySQL.UserMySQLDAO;
import models.dao.interfaces.UserDAO;
import views.SelezionaUtenteWindow;


/**
*
* @author Fabrizio De Sanctis
*/

public class SelezionaUtenteController {
	private static JFrame myFrame;
	private static int scelta;
	
	public static void start(int scelt) {
		scelta=scelt;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelezionaUtenteWindow window = new SelezionaUtenteWindow();
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
			if(scelta==2) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(myFrame, "Le modifiche verranno annullate.\n Confermi?\n", "Annulla", dialogButton);
				if(dialogResult == 0) {
				setInvisible();
				MainMenuController.setVisible();
				}
			}
				else {
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
		String[] colName = { "ID UTENTE","SPESA","NOME","COGNOME","TELEFONO","SESSO","DATA NASCITA","INDIRIZZO","CITTA","PROVINCIA","CAP","EMAIL"};
		LinkedList<User> list= null;
		UserDAO e = new UserMySQLDAO();
		if(scelta == 3) {
		list = e.getAllInfoUser();
		}
		
		Object[][] object = null;
		
		
        if (list!= null && list.size() != 0) {
        	object = new Object[list.size()][13];
            int i = 0;
            for (User x : list) {
            	
            	object[i][0] = x.getTotale();
                SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				String datFormat = dataFormat.format(x.getDataNascita());
				object[i][1] = x.getNome().toUpperCase();
                object[i][2] = x.getCognome().toUpperCase();
                object[i][3] =x.getTelefono();
                object[i][4]= x.getSesso().toUpperCase();
                object[i][5] = datFormat;
                object[i][6] = x.getIndirizzo().toUpperCase();
                object[i][7] = x.getCittà().toUpperCase();
                object[i][8] = x.getCittà().toUpperCase();
                object[i][9] = x.getProvincia().toUpperCase();
                object[i][10] = x.getCap().toUpperCase();
                object[i][11] = x.getEmail();
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
			if(scelta==2) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(myFrame, "Le modifiche verranno annullate.\n Confermi?\n", "Annulla", dialogButton);
				if(dialogResult == 0) {
				setInvisible();
				MainMenuController.setVisible();
				}
			}
				else {
					setInvisible();
					MainMenuController.setVisible();
				}
			}
		}
		
	
	
	public class ConfermaListener implements ActionListener {
		private JTable table;
		
		public ConfermaListener(JTable table) {
			this.table=table;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			if(scelta==3) {
				EliminaUtente();
			}
			
		}
		
		
		public void EliminaUtente() {
			int row = table.getSelectedRow();
			String valueOne=null;
		
			UserDAO usr = new UserMySQLDAO();
			if(table.getRowCount() != 0) {
				valueOne = table.getModel().getValueAt(row,11).toString();
			}
					
				if(usr.deleteUser(valueOne)) {
					JOptionPane.showMessageDialog(myFrame,"L'utente selezionato è stato correttamente eliminato.\n" , "Evento eliminato",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(myFrame,"L'utente selezionato non è stato eliminato.\n" , "Errore", JOptionPane.ERROR_MESSAGE);
				}
			setInvisible();
			MainMenuController.setVisible();
			}
		}
		
		
		
	}

