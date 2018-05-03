package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import controller.infViewerController.CloseAction;
import controller.infViewerController.ProveraLogIn;
import helpClasses.JSONFile;
import model.Korisnik;

public class Login extends JFrame{
	
	private final JTextField userText;
	private JPasswordField passField;
	private JPanel login;
	private ArrayList<Korisnik> korisnici = null;
	
	public Login(){
		try{
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		korisnici = JSONFile.ucitajKorisnike();
		setTitle("Login 203.4");
		setSize(600,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JPanel panelImg = new JPanel();
		panelImg.setLayout(new BorderLayout());
		panelImg.setPreferredSize(new Dimension(512, 552));
		ImageIcon img = new ImageIcon("resources/db.gif");
		
		JLabel labelImg = new JLabel(img);
		panelImg.add(labelImg);
		
		login = new JPanel();
		login.setLayout(null);
		login.setPreferredSize(new Dimension(350, 120));
		
		JLabel userLabel = new JLabel("Username:");
		userLabel.setBounds(10, 10, 80, 25);			
		login.add(userLabel);

	    userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		login.add(userText);
		userText.setToolTipText("Please enter your username.");

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(10, 40, 80, 25);
		login.add(passwordLabel);
		

		passField = new JPasswordField(20);
		passField.setBounds(100, 40, 160, 25);
		login.add(passField);
		passField.setToolTipText("Please enter your password.");

		JButton loginButton = new JButton("Login");
		loginButton.setBounds(10, 80, 80, 25);
		login.add(loginButton);
		
		
		String pass = passField.getText();
		String user = userText.getText();
		
		loginButton.addActionListener(new ProveraLogIn(this, korisnici));
		
		panelImg.setVisible(true);
		login.setVisible(true);
		getContentPane().add(panelImg,BorderLayout.NORTH);
		getContentPane().add(login, BorderLayout.SOUTH);
		setVisible(true);
		setResizable(false);

	}
	
	
	
	public JTextField getUserText() {
		return userText;
	}


	public JPasswordField getPassField() {
		return passField;
	}
	
}
