package tree;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import model.AbstractSChild;
import model.DBFile;
import model.Entitet;
import model.IndSekFile;
import model.Paket;
import model.SekFile;
import model.SerFile;

public class PaketTreeNode extends AbstractSChildTreeNode{


	private Paket paket;
	private ArrayList<AbstractSChildTreeNode> deca;
	
	public PaketTreeNode(Paket paket){
		this.paket = paket;
		deca = new ArrayList<>();
		for(AbstractSChild dete:paket.getDeca()){
			if(dete instanceof Paket){
				deca.add(new PaketTreeNode((Paket) dete));
			}else if(dete instanceof Entitet){
				if(dete instanceof SerFile){
					deca.add(new SerFileTreeNode((SerFile)dete));
				}
				else if(dete instanceof SekFile){
					deca.add(new SekFileTreeNode((SekFile)dete));
				}
				else if(dete instanceof IndSekFile){
					deca.add(new IndSekTreeNode((IndSekFile)dete));
				}
				else if(dete instanceof DBFile){
					deca.add(new DBTreeNode((DBFile)dete));
				}
			}
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return paket.getNaziv();
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
		return deca.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return deca.size();
	}

	@Override
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIndex(TreeNode node) {
		return deca.indexOf(node);
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public boolean isLeaf() {
		if(deca.size()==0){
			return true;
		}
		return false;
	}

	@Override
	public Enumeration children() {
		// TODO Auto-generated method stub
		return null;
	}


}
