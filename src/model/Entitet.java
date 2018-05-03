package model;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME,
include = JsonTypeInfo.As.PROPERTY,
property = "type")
@JsonSubTypes({
@Type(value = AbstractFile.class),
})
public abstract class Entitet extends AbstractSChild{

	private ArrayList<Relacija> relacije;
	private ArrayList<Atribut> atributi;
	
	public Entitet(){
		relacije = new ArrayList();
		atributi = new ArrayList();
	}
	
	public void setRelacije(ArrayList<Relacija> relacije) {
		this.relacije = relacije;
	}
	
	public void setAtributi(ArrayList<Atribut> atributi) {
		this.atributi = atributi;
	}
	
	public ArrayList<Relacija> getRelacije() {
		return relacije;
	}
	
	public ArrayList<Atribut> getAtributi() {
		return atributi;
	}

}
