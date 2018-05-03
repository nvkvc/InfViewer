package model;

import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class Relacija extends InformacioniResurs {

	private Entitet referisaniEntitet;
	
	public void setReferisaniEntitet(Entitet referisaniEntitet) {
		this.referisaniEntitet = referisaniEntitet;
	}
	
	public Entitet getReferisaniEntitet() {
		return referisaniEntitet;
	}
}
