package controller.jsonEditorController;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import JSONEditor.FileTypeFilter;
import view.MyTabbedPane;

public class WindowAction implements WindowListener{

	private MyTabbedPane tp;
	
	public WindowAction(MyTabbedPane pane){
		tp = pane;
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		for(int i = 0; i<tp.getComponentCount();i++){
			JPanel panel = (JPanel) tp.getComponent(i);
			int res = JOptionPane.showConfirmDialog(null, "Do you want to save your file: "+tp.getTitleAt(i), "Option", JOptionPane.YES_NO_OPTION);
			if(res == JOptionPane.YES_OPTION){
				JFileChooser fs = new JFileChooser(new File("c:\\"));
				fs.setDialogTitle("Save a File");
				fs.setSelectedFile(new File(tp.getTitleAt(i)));
				fs.setFileFilter(new FileTypeFilter("*.json", "JSON File"));
				int result = fs.showSaveDialog(null);
				
				if(result == JFileChooser.APPROVE_OPTION && panel!=null)
				{
					String content = "";
					JScrollPane sp = (JScrollPane)panel.getComponent(0);
					JTextArea ta = (JTextArea)sp.getViewport().getView();
					content = ta.getText();

					File fi = fs.getSelectedFile();
					try{
						FileWriter fw = new FileWriter(fi);
						fw.write(content);
						fw.flush();
						fw.close();
					}catch(Exception e2){
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
			}
		}
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
