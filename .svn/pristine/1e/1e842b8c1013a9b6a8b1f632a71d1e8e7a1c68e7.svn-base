package controller.infViewerController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import JSONEditor.EditorMetaSeme;
import model.Korisnik;
import view.Login;
import view.MainFrame;

public class ProveraLogIn implements ActionListener {

	private ArrayList<Korisnik> korisnici;
	private Login prozor;

	public ProveraLogIn(Login prozor, ArrayList<Korisnik> korisnici){
		this.korisnici = korisnici;
		this.prozor = prozor;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(Korisnik k: korisnici){
			String user = prozor.getUserText().getText();
			String pass = prozor.getPassField().getText();
			if(k.getPassword().equals(pass) && k.getUsername().equals(user)){
				MainFrame.getMainFrame(k);
				prozor.dispose();
				return;
			}
		}
		prozor.getPassField().setText("");
		prozor.getUserText().setText("");
	}

}
