package view;

import javax.swing.JOptionPane;

public class ErrorPane extends JOptionPane{

	public ErrorPane(String message){
		this.showMessageDialog(null, message, "SQL Error Code", JOptionPane.ERROR_MESSAGE);
	}
}
