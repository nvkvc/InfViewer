package controller.infViewerController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import helpClasses.JSONFile;
import helpClasses.MyJSONFilter;
import model.Skladiste;
import view.MainFrame;

public class ImportJSONAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new MyJSONFilter());
		fileChooser.setDialogTitle("Open JSON File");
		
		 int ret = fileChooser.showDialog(null, "Open file");

		 if (ret == JFileChooser.APPROVE_OPTION) {
			 File file = fileChooser.getSelectedFile();
			 String str = "";
			 try {
				Scanner sc = new Scanner(file);
				while(sc.hasNextLine()){
					str+=sc.nextLine();
				}
				sc.close();
			 } catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			 }
			 if(JSONFile.checkIfValidJSON(str)){
				 if(file!=null){
			    	 Skladiste s=JSONFile.readJSON(file);
			    	 if(s!=null){
			    		MainFrame.getMainFrame().initializeTree(s);
			    	 }
			     }
			 }else{
				 JOptionPane.showMessageDialog(null, "JSON is invalid!");
			 }
		 }
	}

}
