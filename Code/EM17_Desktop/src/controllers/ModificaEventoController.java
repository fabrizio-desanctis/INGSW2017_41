package controllers;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;
import models.Evento;
import models.dao.concrete.oracle.EventoOracleDAO;
import models.dao.interfaces.EventoDAO;
import views.ModificaEventoWindow;

/**
*
* @author Fabrizio De Sanctis
*/

public class ModificaEventoController {
private static JFrame myFrame;
private static Evento myEvento;
	
	public static void start(Evento e) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myEvento=e;
					ModificaEventoWindow window = new ModificaEventoWindow();
					myFrame=window.getJFrame();
					myFrame.setLocationRelativeTo(null);
					
					window.getJFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public void caricaInfoEvento (JTextField nomeEvento,JTextField luogo,JTextField eur,JTextField cent,
				JTextField nrBiglietti,JComboBox tipologia,JComboBox hour,JComboBox minute,JComboBox localita,JDateChooser date,
				JTextArea descrizione,JTextField linkImmagine) {
		nomeEvento.setText(myEvento.getNome());
		luogo.setText(myEvento.getLuogo());
		nrBiglietti.setText(String.valueOf(myEvento.getNumeroBiglietti()));
		tipologia.setSelectedItem(myEvento.getTipologia());
		localita.setSelectedItem(myEvento.getLocalità());
		descrizione.setText(myEvento.getDescrizione());
		linkImmagine.setText(myEvento.getLinkImmagine());
		float prezzo = myEvento.getPrezzo();
		String prezzoString = String.valueOf(prezzo);
		char[] prezzoCharacter = new char[10];
			for(int j=0;j<10;j++)
				prezzoCharacter[j]='n';
		
		for(int y=0;y<prezzoString.length();y++)
			prezzoCharacter[y] = prezzoString.charAt(y);
		
		int i=0;
			for(i=0;i<prezzoCharacter.length;i++)
				if(prezzoCharacter[i]=='.')
					break;
		String centesimi=null;
			if(prezzoCharacter[i+1]!='n') {
				centesimi = Character.toString(prezzoCharacter[i+1]);
				if(prezzoCharacter[i+2] != 'n')
					centesimi= centesimi + Character.toString(prezzoCharacter[i+2]);
			}
		int prezzoInt = (int)prezzo;
		eur.setText(String.valueOf(prezzoInt));
		if(centesimi != null)
			cent.setText(centesimi);
		else {
			cent.setText("00");
		}
		int ora = myEvento.getData().getHours();
		hour.setSelectedItem(String.valueOf(ora));
		int min = myEvento.getData().getMinutes();
		minute.setSelectedItem(String.valueOf(min));
		date.setDate(myEvento.getData());
	}
	
	public static void setInvisible () {
		myFrame.setVisible(false);
	}
	
	public static void setVisible () {
		myFrame.setLocationRelativeTo(null);
		myFrame.setVisible(true);
	}
	

	public static void addItemToComboBox (JComboBox<String> localitaComboBox) {
		EventoDAO e = new EventoOracleDAO ();
		for(String s: e.getListaLocalita()) {
			localitaComboBox.addItem(s);
			
		}
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
	
	
	
	/*Questa classe interna gestisce il funzionamento dei campi relativi a prezzo e numero biglietti*/
	public class JTextFieldFilter extends PlainDocument {
		  /**
		 * Version 1.0
		 */
		  private static final long serialVersionUID = 9089954016981164854L;
		  public static final String ALPHA = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		  public static final String NUMERIC = "0123456789";
		  public static final String ALPHA_NUMERIC = ALPHA + NUMERIC;
		  private int limit;
		  

		  protected String acceptedChars = null;

		  protected boolean negativeAccepted = false;


		  public JTextFieldFilter(String acceptedchars,int limit) {
		    acceptedChars = acceptedchars;
		    this.limit=limit;
		  }

		  public void setNegativeAccepted(boolean negativeaccepted) {
		    if (acceptedChars.equals(ALPHA_NUMERIC)) {
		      negativeAccepted = negativeaccepted;
		      acceptedChars += "-";
		    }
		  }

		  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		    if (str == null)
		      return;
		    for (int i = 0; i < str.length(); i++) {
		      if (acceptedChars.indexOf(String.valueOf(str.charAt(i))) == -1)
		        return;
		    }

		    if (negativeAccepted) {
		      if (str.indexOf(".") != -1 ) {
		        if (getText(0, getLength()).indexOf(".") != -1) {
		          return;
		        }
		      }
		    }

		    if (negativeAccepted && str.indexOf("-") != -1) {
		      if (str.indexOf("-") != 0 || offset != 0) {
		        return;
		      }
		    }
		    if ((getLength() + str.length()) <= limit) {
		    super.insertString(offset, str, attr);
		    }
		  }
		}
	
	/*Questa classe interna si occupa di impostare un limite di caratteri per un campo di testo*/
	public class JTextFieldLimit extends PlainDocument {
		  /**
		 * 
		 */
		private static final long serialVersionUID = -4475067315478169054L;
		private int limit;
		public JTextFieldLimit(int limit) {
		    super();
		    this.limit = limit;
		  }

		  public JTextFieldLimit(int limit, boolean upper) {
		    super();
		    this.limit = limit;
		  }

		  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		    if (str == null)
		      return;

		    if ((getLength() + str.length()) <= limit) {
		      super.insertString(offset, str, attr);
		    }
		  }
		}
	
	
	public class ConfermaListener implements ActionListener {
		private JTextField nomeEventoField;
		private JTextField luogoField;
		private JTextField eurField;
		private JTextField nrBigliettiField;
		private JTextField centField;
		private JTextField linkImmagineField;
		@SuppressWarnings("rawtypes")
		JComboBox tipologiaComboBox;
		@SuppressWarnings("rawtypes")
		JComboBox hourComboBox;
		@SuppressWarnings("rawtypes")
		JComboBox minuteComboBox;
		@SuppressWarnings("rawtypes")
		JComboBox localitaComboBox;
		JDateChooser dateChooser;
		JTextArea descrizioneTextArea;
		
		
		@SuppressWarnings("rawtypes")
		public ConfermaListener (JTextField nomeEvento,JTextField luogo,JTextField eur,JTextField cent,
				JTextField nrBiglietti,JComboBox tipologia,JComboBox hour,JComboBox minute,JComboBox localita,JDateChooser date,
				JTextArea descrizione,JTextField linkImmagine) {
			this.nomeEventoField=nomeEvento;
			this.luogoField=luogo;
			this.eurField=eur;
			this.centField=cent;
			this.nrBigliettiField=nrBiglietti;
			this.tipologiaComboBox=tipologia;
			this.hourComboBox=hour;
			this.minuteComboBox=minute;
			this.localitaComboBox=localita;
			this.dateChooser=date;
			this.descrizioneTextArea=descrizione;
			this.linkImmagineField=linkImmagine;
			
		}

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			//Evento myEvento = new Evento();
			String reportError=new String("Sono stati riportati i seguenti errori:\n");
			boolean errori=false;
			if(nomeEventoField.getText().length()==0) {
				reportError+="Nome evento non inserito.\n";
				errori=true;
			}
			
			if(eurField.getText().length()==0 || centField.getText().length()==0) {
				reportError+="Prezzo evento non inserito.\n";
				errori=true;
			}
			
			if(luogoField.getText().length()==0) {
				reportError+="Luogo evento non inserito.\n";
				errori=true;
			}
			
			if(nrBigliettiField.getText().length()==0) {
				reportError+="Numero biglietti disponibili non inserito.\n";
				errori=true;
			}
			
			if(linkImmagineField.getText().length()==0) {
				reportError+="Link immagine non inserito.\n";
				errori=true;
			}
			
			Date date=dateChooser.getDate();
			date.setHours(hourComboBox.getSelectedIndex());
			date.setMinutes(Integer.valueOf((String)minuteComboBox.getSelectedItem()));
			
			
			
			
			if(errori)
				JOptionPane.showMessageDialog(myFrame,reportError , "Errore/i", 0);
			else {
				myEvento.setNome(nomeEventoField.getText());
				myEvento.setData(date);
				if(descrizioneTextArea.getText().length()>0)
				myEvento.setDescrizione(descrizioneTextArea.getText());
				myEvento.setLinkImmagine(linkImmagineField.getText());
				myEvento.setLocalità((String)localitaComboBox.getSelectedItem());
				myEvento.setLuogo(luogoField.getText());
				myEvento.setNumeroBiglietti(Integer.parseInt(nrBigliettiField.getText()));
				String prezzo=eurField.getText()+"."+centField.getText();
				myEvento.setPrezzo(Float.parseFloat(prezzo));
				myEvento.setTipologia((String)tipologiaComboBox.getSelectedItem());
				EventoDAO ed = new EventoOracleDAO();
				String myMessage = new String();
				myMessage= "TIPOLOGIA: "+ myEvento.getTipologia()+"\n";;
				myMessage+= "NOME EVENTO: "+myEvento.getNome()+"\n";
				SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				String dateFormat = dataFormat.format(myEvento.getData());
				myMessage+= "DATA:" + dateFormat + "\n";
				myMessage+= "LUOGO: " + myEvento.getLuogo() + "\n";
				myMessage+= "LOCALITA': " + myEvento.getLocalità() + "\n";
				myMessage+= "PREZZO: " + myEvento.getPrezzo() + "€\n";
				myMessage+= "LINK: " + myEvento.getLinkImmagine()+"\n";
				myMessage+= "DESCRIZIONE: "+ myEvento.getDescrizione()+"\n";
				myMessage+= "\nConfermi?\n";
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(myFrame, myMessage, "Conferma modifica", dialogButton);
				if(dialogResult == 0) {
					if(ed.updateEvento(myEvento)) {
						JOptionPane.showMessageDialog(myFrame,"Evento modificato correttamente.","Evento modificato", 1);
						setInvisible();
						MainMenuController.setVisible();
					}
					else {
					JOptionPane.showMessageDialog(myFrame,"Impossibile modificare evento.","Evento non modificato", 0);	
					} 
					
				}
			}
			
		}
	}
	
	
}
