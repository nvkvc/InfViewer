package view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;

import model.Entitet;
import model.MyTreeModel;
import model.Paket;
import model.Skladiste;
import tree.AbstractTreeNode;
import tree.EntitetTreeNode;
import tree.PaketTreeNode;
import tree.SkladisteTreeNode;

/*
 * Klasa koja sluzi za redefinisanje izgleda tree-ja, menjaju se ikonice svakog noda
 */
public class MyTreeCellRenderer extends DefaultTreeCellRenderer{
	
	private DefaultTreeCellRenderer renderer = null;
	
	public MyTreeCellRenderer(){
		renderer = new DefaultTreeCellRenderer();
	}

/*	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
			 boolean leaf, int row, boolean hasFocus){
			//TODO CLASS
		
				Component returnValue = null;
				
				if (value != null && value instanceof Skladiste ) {
					JPanel renderer = new JPanel();
					renderer.setLayout(new FlowLayout());
	                 URL imageURL = getClass().getResource("resources/new.png");
	                 Icon icon = null;
	                 if (imageURL != null) {                     
	                     icon = new ImageIcon(imageURL);
	                     JLabel iconLabel = new JLabel(icon);
	                     Skladiste s = (Skladiste)value;
	                     JLabel nodeText = new JLabel(s.getNaziv());
	                     renderer.add(iconLabel);
	                     renderer.add(nodeText);
	                 }
	             }
	         
				if(returnValue == null){
					returnValue = renderer.getTreeCellRendererComponent(tree, returnValue, sel, expanded, leaf, row, hasFocus);
				}
				return returnValue;
			}*/
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
			 boolean leaf, int row, boolean hasFocus)
	{
		super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row,hasFocus);
		Icon icon = null;
		if (value instanceof SkladisteTreeNode ) 
		{
			 icon = new ImageIcon("resources/skladiste.png");
			 setIcon(icon);

		} 
		else if (value instanceof PaketTreeNode ) 
		{
			 icon = new ImageIcon("resources/paket.png");
			 setIcon(icon);
		}
		else if (value instanceof EntitetTreeNode ) 
		{
			 icon = new ImageIcon("resources/entitet.png");
			 setIcon(icon);
		}
		/*
		else if (value instanceof AtributTreeNode ) 
		{
			 icon = new ImageIcon("resources/atribut.png");
			 setIcon(icon);
		}
		*/
			 return this;
	}
	
}
