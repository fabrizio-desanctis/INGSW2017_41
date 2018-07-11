package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Calendar;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import controllers.InserisciEventoController;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.TextArea;
import javax.swing.JButton;
import java.awt.Color;


/**
*
* @author Fabrizio De Sanctis
*/

public class InserisciEventoWindow {

	private JFrame frame;
	private JTextField nomeEventoField;
	private JTextField luogoField;
	private JTextField eurField;
	private JTextField nrBigliettiField;
	private JTextField centField;


	/**
	 * Create the application.
	 */
	public InserisciEventoWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 573, 469);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("EM'17 - Inserisci nuovo evento");
		
		JLabel label = new JLabel("EM'17");
		label.setBounds(10, 11, 172, 71);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setFont(new Font("Arial Black", Font.PLAIN, 50));
		frame.getContentPane().add(label);
		
		JDateChooser dateChooser = new JDateChooser();
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.DAY_OF_MONTH, 1);
		dateChooser.setDate(currentDate.getTime());
        dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(135, 158, 113, 27);
		dateChooser.setSelectableDateRange(currentDate.getTime(), null);
		frame.getContentPane().add(dateChooser);
		
		JLabel tipologiaLabel = new JLabel("Tipologia:");
		tipologiaLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		tipologiaLabel.setBounds(20, 96, 75, 14);
		frame.getContentPane().add(tipologiaLabel);
		
		JComboBox tipologiaComboBox = new JComboBox();
		tipologiaComboBox.setModel(new DefaultComboBoxModel(new String[] {"Concerti", "Sport", "Museo", "Teatro", "Eventi", "Cinema"}));
		tipologiaComboBox.setFont(new Font("Arial", Font.BOLD, 11));
		tipologiaComboBox.setBounds(135, 94, 113, 20);
		frame.getContentPane().add(tipologiaComboBox);
		
		JLabel nomeEventoLabel = new JLabel("Nome evento: ");
		nomeEventoLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		nomeEventoLabel.setBounds(20, 130, 107, 14);
		frame.getContentPane().add(nomeEventoLabel);
		
		nomeEventoField = new JTextField();
		
		nomeEventoField.setBackground(Color.WHITE);
		nomeEventoField.setFont(new Font("Arial", Font.BOLD, 11));
		nomeEventoField.setBounds(135, 127, 113, 20);
		frame.getContentPane().add(nomeEventoField);
		nomeEventoField.setColumns(60);
		
		JLabel dataLabel = new JLabel("Data:");
		dataLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		dataLabel.setBounds(20, 161, 107, 14);
		frame.getContentPane().add(dataLabel);
		
		JLabel orarioLabel = new JLabel("Orario:");
		orarioLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		orarioLabel.setBounds(20, 193, 57, 14);
		frame.getContentPane().add(orarioLabel);
		
		JComboBox hourComboBox = new JComboBox();
		hourComboBox.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		hourComboBox.setFont(new Font("Arial", Font.BOLD, 11));
		hourComboBox.setBounds(135, 191, 47, 20);
		frame.getContentPane().add(hourComboBox);
		
		JComboBox minuteComboBox = new JComboBox();
		minuteComboBox.setModel(new DefaultComboBoxModel(new String[] {"00", "15", "30", "45"}));
		minuteComboBox.setFont(new Font("Arial", Font.BOLD, 11));
		minuteComboBox.setBounds(201, 191, 47, 20);
		frame.getContentPane().add(minuteComboBox);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 121, 238, 20);
		frame.getContentPane().add(separator_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 152, 238, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 218, 306, 2);
		frame.getContentPane().add(separator_2);
		
		JLabel luogoLabel = new JLabel("Luogo:");
		luogoLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		luogoLabel.setBounds(316, 96, 75, 14);
		frame.getContentPane().add(luogoLabel);
		
		JLabel localit‡Label = new JLabel("Localit\u00E0");
		localit‡Label.setFont(new Font("Arial Black", Font.BOLD, 12));
		localit‡Label.setBounds(316, 130, 107, 14);
		frame.getContentPane().add(localit‡Label);
		
		JLabel prezzoLabel = new JLabel("Prezzo:");
		prezzoLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		prezzoLabel.setBounds(316, 161, 69, 14);
		frame.getContentPane().add(prezzoLabel);
		
		JLabel nrBigliettiLabel = new JLabel("Nr. biglietti:");
		nrBigliettiLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		nrBigliettiLabel.setBounds(316, 193, 91, 14);
		frame.getContentPane().add(nrBigliettiLabel);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(306, 121, 238, 20);
		frame.getContentPane().add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(306, 152, 238, 2);
		frame.getContentPane().add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(306, 218, 238, 2);
		frame.getContentPane().add(separator_5);
		
		luogoField = new JTextField();
		luogoField.setFont(new Font("Arial", Font.BOLD, 11));
		luogoField.setColumns(30);
		luogoField.setBounds(431, 94, 113, 20);
		frame.getContentPane().add(luogoField);
		
		JComboBox localitaComboBox = new JComboBox();
		localitaComboBox.setFont(new Font("Arial", Font.BOLD, 11));
		localitaComboBox.setBounds(431, 128, 113, 20);
		frame.getContentPane().add(localitaComboBox);
		
		eurField = new JTextField();
		eurField.setFont(new Font("Arial", Font.BOLD, 11));
		eurField.setColumns(10);
		eurField.setBounds(431, 158, 47, 20);
		frame.getContentPane().add(eurField);
		
		nrBigliettiField = new JTextField();
		nrBigliettiField.setFont(new Font("Arial", Font.BOLD, 11));
		nrBigliettiField.setColumns(10);
		nrBigliettiField.setBounds(431, 187, 113, 20);
		frame.getContentPane().add(nrBigliettiField);
		
		JLabel descrizioneLabel = new JLabel("Descrizione*:");
		descrizioneLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		descrizioneLabel.setBounds(20, 231, 107, 14);
		frame.getContentPane().add(descrizioneLabel);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(10, 340, 306, 2);
		frame.getContentPane().add(separator_6);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(306, 340, 238, 2);
		frame.getContentPane().add(separator_7);
		
		TextArea descrizioneTextArea = new TextArea();
		descrizioneTextArea.setFont(new Font("Arial", Font.BOLD, 11));
		descrizioneTextArea.setBounds(135, 232, 409, 95);
		frame.getContentPane().add(descrizioneTextArea);
		
		JButton indietroButton = new JButton("ANNULLA");
		indietroButton.setFont(new Font("Arial Black", Font.BOLD, 12));
		indietroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		indietroButton.setBounds(10, 365, 162, 41);
		frame.getContentPane().add(indietroButton);
		
		JButton avantiButton = new JButton("CONFERMA");
		avantiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		avantiButton.setFont(new Font("Arial Black", Font.BOLD, 12));
		avantiButton.setBounds(382, 365, 162, 41);
		frame.getContentPane().add(avantiButton);
		
		JLabel InfoLabel = new JLabel("Aggiungi nuovo evento");
		InfoLabel.setVerticalAlignment(SwingConstants.TOP);
		InfoLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		InfoLabel.setBounds(184, 46, 276, 71);
		frame.getContentPane().add(InfoLabel);
		
		JLabel lblFacoltativo = new JLabel("(*) Facoltativo.");
		lblFacoltativo.setBounds(20, 315, 107, 14);
		frame.getContentPane().add(lblFacoltativo);
		
		centField = new JTextField();
	    centField.setText("00");
	    centField.setFont(new Font("Arial", Font.BOLD, 11));
	    centField.setColumns(10);
	    centField.setBounds(488, 158, 34, 20);
	    frame.getContentPane().add(centField);
	    
	    JLabel virgolaLabel = new JLabel(",");
	    virgolaLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
	    virgolaLabel.setBounds(479, 161, 13, 14);
	    frame.getContentPane().add(virgolaLabel);
	    
	    JLabel eurLabel = new JLabel("\u20AC");
	    eurLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
	    eurLabel.setBounds(525, 158, 19, 20);
	    frame.getContentPane().add(eurLabel);
	    
	    
	    InserisciEventoController controller = new InserisciEventoController(); //Istanzia controller
		InserisciEventoController.AnnullaListener myAnnullaListener = controller.new AnnullaListener();
		indietroButton.addActionListener(myAnnullaListener);
		InserisciEventoController.JTextFieldFilter myFilterEur = controller.new JTextFieldFilter(InserisciEventoController.JTextFieldFilter.NUMERIC);
	    myFilterEur.setNegativeAccepted(false);
	    eurField.setDocument(myFilterEur);
	    InserisciEventoController.JTextFieldFilter myFilterCent = controller.new JTextFieldFilter(InserisciEventoController.JTextFieldFilter.NUMERIC);
	    myFilterCent.setNegativeAccepted(false);
	    centField.setDocument(myFilterCent);
	    InserisciEventoController.JTextFieldFilter myFilterBiglietti = controller.new JTextFieldFilter(InserisciEventoController.JTextFieldFilter.NUMERIC);
	    myFilterBiglietti.setNegativeAccepted(false);
	    nrBigliettiField.setDocument(myFilterBiglietti);
	    InserisciEventoController.addItemToComboBox(localitaComboBox);
	    InserisciEventoController.ConfermaListener myConferma = controller.new ConfermaListener(avantiButton,nomeEventoField,luogoField,eurField,centField,
				nrBigliettiField,tipologiaComboBox,hourComboBox,minuteComboBox,localitaComboBox,dateChooser,descrizioneTextArea);
	    avantiButton.addActionListener(myConferma);
	    
	}
	
	public JFrame getJFrame() {
		return frame;
	}
}
