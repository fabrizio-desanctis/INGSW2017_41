package controllers;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Evento;
import models.dao.concrete.MySQL.EventoMySQLDAO;
import models.dao.interfaces.EventoDAO;
import views.SelezionaEventoWindow;


/**
*
* @author Fabrizio De Sanctis
*/

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
		String[] colName = { "NOME EVENTO", "DATA"};
		LinkedList<Evento> list= null;
		EventoDAO e = new EventoMySQLDAO();
		if(scelta != 3) {
		list = e.getListaEventi();
		}
		else {
			list = e.getAllEventi();
		}
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
			if(scelta==2) {
				EliminaEvento();
			}
			else if(scelta==1) {
				ModificaEvento();
			}
			else if(scelta==3) {
				StatisticheEvento();
			}
		}
		
		
		public void EliminaEvento() {
			int row = table.getSelectedRow();
			String valueOne=null;
			String valueTwo=null;
			if(table.getRowCount() != 0) {
				valueOne = table.getModel().getValueAt(row,0).toString();
				valueTwo = table.getModel().getValueAt(row,1).toString();
			}
			
			boolean found = false;
			SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			EventoDAO e = new EventoMySQLDAO();
			LinkedList<Evento> list=null;
			try {
				list = e.getListaEventi();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			String dateFormat=null; 
			Evento eventoScelto=null;
			if (valueOne != null && list!= null && list.size() != 0) {
				for(Evento x : list) {
					dateFormat = dataFormat.format(x.getData());
					if(valueOne.equals(x.getNome()) && valueTwo.equals(dateFormat)){
						found=true;
						eventoScelto=x;
						break;
					}
				}
					
			}
			int dialogResult=1;
			if(found) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				dialogResult = JOptionPane.showConfirmDialog(myFrame, "L'evento selezionato sarà eliminato.\n Confermi?\n", "Conferma", dialogButton);
			}
			else {
				JOptionPane.showMessageDialog(myFrame,"Nessun evento selezionato. Impossibile continuare.\n Si prega di selezionare un evento e riprovare.\n" , "Errore/i", JOptionPane.ERROR_MESSAGE);
			}
			if(dialogResult == 0) {
				if(e.deleteEvento(eventoScelto)) {
					JOptionPane.showMessageDialog(myFrame,"L'evento selezionato è stato correttamente eliminato.\n" , "Evento eliminato",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(myFrame,"L'evento selezionato non è stato eliminato.\n" , "Errore", JOptionPane.ERROR_MESSAGE);
				}
			setInvisible();
			MainMenuController.setVisible();
			}
		}
		
		
		public void ModificaEvento() {
			int row = table.getSelectedRow();
			String valueOne=null;
			String valueTwo=null;
			if(table.getRowCount() != 0) {
				valueOne = table.getModel().getValueAt(row,0).toString();
				valueTwo = table.getModel().getValueAt(row,1).toString();
			}
			
			boolean found = false;
			SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			EventoDAO e = new EventoMySQLDAO();
			LinkedList<Evento> list=null;
			try {
				list = e.getListaEventi();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			String dateFormat=null; 
			Evento eventoScelto=null;
			if (valueOne != null && list!= null && list.size() != 0) {
				for(Evento x : list) {
					dateFormat = dataFormat.format(x.getData());
					if(valueOne.equals(x.getNome()) && valueTwo.equals(dateFormat)){
						found=true;
						eventoScelto=x;
						break;
					}
				}
					
			}
			int dialogResult=1;
			if(found) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				dialogResult = JOptionPane.showConfirmDialog(myFrame, "Sei sicuro di voler modificare questo evento?\n", "Conferma", dialogButton);
			}
			else {
				JOptionPane.showMessageDialog(myFrame,"Nessun evento selezionato. Impossibile continuare.\n Si prega di selezionare un evento e riprovare.\n" , "Errore/i", JOptionPane.ERROR_MESSAGE);
			}
			if(dialogResult == 0) {
					setInvisible();
					ModificaEventoController.start(eventoScelto);
				
				
			
			}
		}
		
		
		public void StatisticheEvento() {
			int row = table.getSelectedRow();
			String valueOne=null;
			String valueTwo=null;
			if(table.getRowCount() != 0) {
				valueOne = table.getModel().getValueAt(row,0).toString();
				valueTwo = table.getModel().getValueAt(row,1).toString();
			}
			
			boolean found = false;
			SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			EventoDAO e = new EventoMySQLDAO();
			LinkedList<Evento> list=null;
			try {
				list = e.getAllEventi();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			String dateFormat=null; 
			Evento eventoScelto=null;
			if (valueOne != null && list!= null && list.size() != 0) {
				for(Evento x : list) {
					dateFormat = dataFormat.format(x.getData());
					if(valueOne.equals(x.getNome()) && valueTwo.equals(dateFormat)){
						found=true;
						eventoScelto=x;
						break;
					}
				}
					
			}
			
			
			
			int dialogResult=1;
			if(found) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				dialogResult = JOptionPane.showConfirmDialog(myFrame, "Sei sicuro di voler visualizzare le statistiche per questo evento?\n", "Conferma", dialogButton);
			}
			else {
				JOptionPane.showMessageDialog(myFrame,"Nessun evento selezionato. Impossibile continuare.\n Si prega di selezionare un evento e riprovare.\n" , "Errore/i", JOptionPane.ERROR_MESSAGE);
			}
			if(dialogResult == 0) {
					setInvisible();
					ArrayList<Object> params = null;
					params = e.getInfoEventi(eventoScelto);
					VisualizzaStatisticheController.start(params);
				
				
			
			}
		}
	}
}
	


