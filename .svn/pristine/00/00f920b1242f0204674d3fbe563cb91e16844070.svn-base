package model;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class Skladiste extends InformacioniResurs{

	private ArrayList<AbstractSChild> deca;
	private String url;
	private String username;
	private String password;
	
	

	public Skladiste(){
		deca = new ArrayList();
	}
	
	public Skladiste(String url, String name, String username, String password){
		this.setNaziv(name);
		this.url = url;
		this.username = username;
		this.password = password;
		deca = new ArrayList();
	}
	
	public ArrayList<AbstractSChild> getDeca() {
		return deca;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDeca(ArrayList<AbstractSChild> deca) {
		this.deca = deca;
	}

}
