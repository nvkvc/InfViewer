package controller.infViewerController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import view.MyTabbedPane;

public class DeleteTabAction implements ActionListener{

	private MyTabbedPane tp;
	
	public DeleteTabAction(MyTabbedPane tp) {
		this.tp = tp;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JPanel panel = (JPanel)tp.getSelectedComponent();
		
		if(panel!=null){
			tp.remove(panel);
			
		}
		
	}

	
}
