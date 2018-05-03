package controller.jsonEditorController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import JSONEditor.FileTypeFilter;
import view.MyTabbedPane;

public class EditorDeleteTabAction implements ActionListener{

	private MyTabbedPane tp;
	private SaveEditorAction seAction;
	
	public EditorDeleteTabAction(MyTabbedPane tp){
		this.tp = tp;
		seAction = new SaveEditorAction(tp);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JPanel panel = (JPanel)tp.getSelectedComponent();
		if(panel!=null){
			int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove tab?", "Warning", JOptionPane.YES_NO_OPTION);
			
			if(res == JOptionPane.YES_OPTION){
				
				int res2 = JOptionPane.showConfirmDialog(null, "Do you want to save your file: "+tp.getTitleAt(tp.getSelectedIndex()), "Option", JOptionPane.YES_NO_OPTION);
				
				if(res2 == JOptionPane.YES_OPTION){
					JFileChooser fs = new JFileChooser(new File("c:\\"));
					fs.setDialogTitle("Save a File");
					fs.setSelectedFile(new File(tp.getTitleAt(tp.getSelectedIndex())));
					fs.setFileFilter(new FileTypeFilter("*.json", "JSON File"));
					int result = fs.showSaveDialog(null);
					
					if(result == JFileChooser.APPROVE_OPTION && panel!=null)
					{
						String content = "";
						JScrollPane sp = (JScrollPane)panel.getComponent(0);
						JTextArea ta = (JTextArea)sp.getViewport().getView();
						content = ta.getText();

						//System.out.println("!" + content + "!");
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
				tp.remove(panel);
			}
		}
	}

	
}
