package controllers;

import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.toedter.calendar.JDateChooser;

import models.Evento;
import models.dao.concrete.oracle.EventoOracleDAO;
import models.dao.interfaces.EventoDAO;
import views.InserisciEventoWindow;

/**
*
* @author Fabrizio De Sanctis
*/

public class InserisciEventoController {
private static JFrame myFrame;
	
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserisciEventoWindow window = new InserisciEventoWindow();
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
	

	public static void addItemToComboBox (JComboBox<String> localitaComboBox) {
		EventoDAO e = new EventoOracleDAO ();
		for(String s: e.getListaLocalita()) {
			localitaComboBox.addItem(s);
			
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
	
	/*Questa classe interna gestisce il funzionamento dei campi relativi a prezzo e numero biglietti*/
	public class JTextFieldFilter extends PlainDocument {
		  /**
		 * Version 1.0
		 */
		  private static final long serialVersionUID = 9089954016981164854L;
		  public static final String ALPHA = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		  public static final String NUMERIC = "0123456789";
		  public static final String ALPHA_NUMERIC = ALPHA + NUMERIC;

		  protected String acceptedChars = null;

		  protected boolean negativeAccepted = false;

		  public JTextFieldFilter() {
		    this(ALPHA_NUMERIC);
		  }

		  public JTextFieldFilter(String acceptedchars) {
		    acceptedChars = acceptedchars;
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
		      if (str.indexOf(".") != -1) {
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

		    super.insertString(offset, str, attr);
		  }
		}
	
	public class ConfermaListener implements ActionListener {
		private JTextField nomeEventoField;
		private JTextField luogoField;
		private JTextField eurField;
		private JTextField nrBigliettiField;
		private JTextField centField;
		private JButton confermaButton;
		@SuppressWarnings("rawtypes")
		JComboBox tipologiaComboBox;
		@SuppressWarnings("rawtypes")
		JComboBox hourComboBox;
		@SuppressWarnings("rawtypes")
		JComboBox minuteComboBox;
		@SuppressWarnings("rawtypes")
		JComboBox localitaComboBox;
		JDateChooser dateChooser;
		TextArea descrizioneTextArea;
		
		
		@SuppressWarnings("rawtypes")
		public ConfermaListener (JButton confermaButton,JTextField nomeEvento,JTextField luogo,JTextField eur,JTextField cent,
				JTextField nrBiglietti,JComboBox tipologia,JComboBox hour,JComboBox minute,JComboBox localita,JDateChooser date,
				TextArea descrizione) {
			this.confermaButton=confermaButton;
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
			
		}

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			Evento myEvento = new Evento();
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
			
			Date date=dateChooser.getDate();
			date.setHours((int)hourComboBox.getSelectedIndex());
			date.setMinutes((int)minuteComboBox.getSelectedIndex());
			
			
			
			if(errori)
				JOptionPane.showMessageDialog(myFrame,reportError , "Errore/i", 0);
			else {
				myEvento.setNome(nomeEventoField.getText());
				myEvento.setData(date);
				if(descrizioneTextArea.getText().length()>0)
				myEvento.setDescrizione(descrizioneTextArea.getText());
				myEvento.setId(1);
				myEvento.setLinkImmagine("http//");
				myEvento.setLocalità((String)localitaComboBox.getSelectedItem());
				myEvento.setLuogo(luogoField.getText());
				myEvento.setNumeroBiglietti(Integer.parseInt(nrBigliettiField.getText()));
				String prezzo=eurField.getText()+"."+centField.getText();
				myEvento.setPrezzo(Float.parseFloat(prezzo));
				myEvento.setTipologia((String)tipologiaComboBox.getSelectedItem());
				EventoDAO ed = new EventoOracleDAO();
				
				if(ed.createNewEvento(myEvento))
					JOptionPane.showMessageDialog(myFrame,"Evento inserito correttamente.","Evento inserito", 1);
				else {
					JOptionPane.showMessageDialog(myFrame,"Impossibile aggiungere evento.","Evento non inserito", 0);	
				}
			}
			
		}
	}
	
	
}
