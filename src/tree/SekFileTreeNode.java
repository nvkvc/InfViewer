package tree;

import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import model.SekFile;

public class SekFileTreeNode extends AbstractSChildTreeNode{

	private SekFile sekFile;
	
	public SekFileTreeNode(SekFile s){
		this.sekFile = s;
	}
	
	public SekFile getSekFile() {
		return sekFile;
	}
	
	public void setSekFile(SekFile sekFile) {
		this.sekFile = sekFile;
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(MutableTreeNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUserObject(Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFromParent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChildCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIndex(TreeNode node) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Enumeration children() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return sekFile.toString();
	}
}
