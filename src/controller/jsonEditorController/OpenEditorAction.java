package controller.jsonEditorController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

import helpClasses.JSONFile;
import helpClasses.MyJSONFilter;
import view.MyTabbedPane;

public class OpenEditorAction implements ActionListener{

	private MyTabbedPane tabbedPane;
	
	public OpenEditorAction(MyTabbedPane tp){
		tabbedPane = tp;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new MyJSONFilter());
		fileChooser.setDialogTitle("Open JSON File");
		
		 int ret = fileChooser.showDialog(null, "Open file");

		 if (ret == JFileChooser.APPROVE_OPTION) {
			 File file = fileChooser.getSelectedFile();
		     if(file!=null){
		    	 String str = JSONFile.readFromJSON(file);
		    	 if(str!=null){
		    		tabbedPane.addPanel(file.getName(), str, new JTextArea());
		    	 }
		     }
		 }
	}

}
