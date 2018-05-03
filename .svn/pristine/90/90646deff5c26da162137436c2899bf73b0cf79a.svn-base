package controller.jsonEditorController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import helpClasses.JSONFile;
import view.MyTabbedPane;

public class JSONValidationAction implements ActionListener{

	private MyTabbedPane tp;
	
	public JSONValidationAction(MyTabbedPane tp) {
		this.tp = tp;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JPanel panel = (JPanel)tp.getSelectedComponent();
		if(panel!=null){
			JTextArea ta = (JTextArea)(((JScrollPane)panel.getComponent(0)).getViewport().getView());
			boolean isValid = JSONFile.checkIfValidJSON(ta.getText());
			if(isValid){
				new JOptionPane().showMessageDialog(null, "JSON file is valid!", "JSONValidation Confirm", JOptionPane.INFORMATION_MESSAGE);
			}else{
				new JOptionPane().showMessageDialog(null, "JSON file is invalid!", "JSONValidation Error", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

}
