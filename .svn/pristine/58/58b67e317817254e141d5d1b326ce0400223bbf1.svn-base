package controller.infViewerController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.tree.MutableTreeNode;

import org.json.JSONException;

import model.AbstractFile;
import tree.DBTreeNode;
import tree.IndSekTreeNode;
import tree.PaketTreeNode;
import tree.SekFileTreeNode;
import tree.SerFileTreeNode;
import tree.SkladisteTreeNode;
import view.MainFrame;

public class EntityClickedMouseAdapter extends MouseAdapter{

	public void mouseClicked(MouseEvent e){
		if(e.getClickCount()==2){
			MutableTreeNode node = (MutableTreeNode) MainFrame.getMainFrame().getTree().getLastSelectedPathComponent();
			
			if(node!=null && !(node instanceof SkladisteTreeNode || node instanceof PaketTreeNode)){
				 AbstractFile file = null;
				/*
				 if(node instanceof IndSekTreeNode){
					 file = ((IndSekTreeNode)node).getIndSekFile();
				 }
				 else if(node instanceof SerFileTreeNode){
					 file = ((SerFileTreeNode)node).getSerFile();
				 }
				 else if(node instanceof SekFileTreeNode){
					 file = ((SekFileTreeNode)node).getSekFile();
				 }
				 else
				 */
				 
				if(node instanceof DBTreeNode){
					file = ((DBTreeNode)node).getDBFile(); 
				 }
				
				try {
					MainFrame.getMainFrame().getTp1().addDBViewPanel(file);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}
