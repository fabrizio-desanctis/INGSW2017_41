package controllers;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
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
	
	/*Questa classe interna gestisce il funzionamento del tasto Annulla*/
	public class AnnullaListener implements ActionListener {
		
		public AnnullaListener () {
		}
		
		public void actionPerformed(ActionEvent arg0) {
			setInvisible();
			MainMenuController.setVisible();
		}
	}
	
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
	
	
}
