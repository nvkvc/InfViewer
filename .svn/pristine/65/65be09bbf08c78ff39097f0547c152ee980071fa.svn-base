package controller.jsonEditorController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.codehaus.jackson.map.ObjectMapper;

import helpClasses.JSONFile;
import model.Skladiste;
import view.MainFrame;
import view.MyTabbedPane;

public class SetAsMetaSchemeAction implements ActionListener{

	private MyTabbedPane tp;
	
	public SetAsMetaSchemeAction(MyTabbedPane mtp){
		tp = mtp;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JPanel panel = (JPanel)tp.getSelectedComponent();
		JTextArea ta = (JTextArea)(((JScrollPane)panel.getComponent(0)).getViewport().getView());
		String content = ta.getText();
		if(panel != null && JSONFile.checkIfValidJSON(content)){
			ObjectMapper om = new ObjectMapper();
			Skladiste s = null;
			try {
				s = om.readValue(content, Skladiste.class);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			MainFrame.getMainFrame().initializeTree(s);
		}else if(!JSONFile.checkIfValidJSON(content)){
			JOptionPane.showMessageDialog(null, "JSON is not valid!");
		}
		
	}

}
