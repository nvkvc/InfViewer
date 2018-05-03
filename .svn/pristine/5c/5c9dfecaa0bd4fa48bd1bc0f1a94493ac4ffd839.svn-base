package controller.jsonEditorController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import JSONEditor.FileTypeFilter;
import helpClasses.JSONFile;
import view.MyTabbedPane;

public class SaveEditorAction implements ActionListener {
	private MyTabbedPane tabbedPane;

	public SaveEditorAction(MyTabbedPane tp) {
		tabbedPane = tp;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JPanel panel = (JPanel) tabbedPane.getSelectedComponent();
		if (panel != null) {
			JFileChooser fs = new JFileChooser(new File("c:\\"));
			fs.setDialogTitle("Save a File");
			fs.setSelectedFile(new File(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())));
			fs.setFileFilter(new FileTypeFilter(".json", "JSON File"));
			int result = fs.showSaveDialog(null);

			String content = "";
			JScrollPane sp = (JScrollPane) panel.getComponent(0);
			JTextArea ta = (JTextArea) sp.getViewport().getView();
			content = ta.getText();
			File fi = fs.getSelectedFile();
			try {
				FileWriter fw = new FileWriter(fi);
				fw.write(content);
				fw.flush();
				fw.close();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}
		}
	}
}
