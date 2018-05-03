package model;

import java.io.Serializable;

public class Korisnik{

	private String username;
	private String password;
	private TipKorisnika tip;
	
	public Korisnik(){
		
	}
	
	public Korisnik(String username,String password, TipKorisnika tip){
		this.username = username;
		this.password = password;
		this.tip = tip;
	}
	
	public TipKorisnika getTip() {
		return tip;
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
	
	@Override
	public String toString() {
		return username+" "+password;
	}
}
