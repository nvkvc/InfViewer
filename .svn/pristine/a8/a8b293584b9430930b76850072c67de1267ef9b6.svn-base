package controller.jsonEditorController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import view.MyTabbedPane;

public class NewEditorAction implements ActionListener{

	private MyTabbedPane tabbedPane;
	
	public NewEditorAction(MyTabbedPane tabbedPane){
		this.tabbedPane = tabbedPane;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		tabbedPane.addPanel(new JTextArea());
		
	}

}
