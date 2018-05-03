package helpClasses;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import model.DBFile;
import model.Korisnik;
import model.Paket;
import model.Skladiste;
import model.TipKorisnika;

public class JSONFile {

	/**
	 * Funkcija za ucitavanje skldista u jtree
	 * @param f
	 * @return
	 */
	public static Skladiste readJSON(File f){
		ObjectMapper om = new ObjectMapper();
		String str = "";
		try {
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()){
				str+=sc.nextLine();
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Skladiste s = null;
		try {
			s = om.readValue(str, Skladiste.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return s;
	}
	
	public static boolean checkIfValidJSON(String str){
		 try {
		       final ObjectMapper mapper = new ObjectMapper();
		       mapper.readTree(str);
		       return true;
		    } catch (IOException e) {
		       return false;
		    }
	}
	
	public static String readFromJSON(File f){
		String str = "";

		try {
			FileInputStream in = new FileInputStream(f);
			while(in.available() > 0){
				//System.out.print((char)in.read() + "");
				str+=(char)in.read() + "";
			}
			in.close();
			} catch (FileNotFoundException e) {
			System.out.println("Error: Cannot open file for reading.");
			} catch (EOFException e) {
				System.out.println("Error: EOF encountered, file may be corrupted.");
			} catch (IOException e) {
				System.out.println("Error: Cannot read from file.");
			}
		return str;
	}

	public static void saveJSON(String content, File f){
		
		ObjectMapper om = new ObjectMapper();
		
		String str = null;
		if(f.exists()){
			try {
				str = om.writeValueAsString(content);
				PrintWriter pw = new PrintWriter(f);
				pw.write(str);
				pw.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	

	public static ArrayList<Korisnik> ucitajKorisnike(){
		File f = new File("resources/korisnici.json");
		ArrayList<Korisnik> korisnici = new ArrayList();
		String str =  "";
		try {
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()){
				str+=sc.nextLine();
			}
			sc.close();
			ObjectMapper om = new ObjectMapper();
			try {
				korisnici = om.readValue(str, new TypeReference<List<Korisnik>>() {});
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return korisnici;
	}
	
	public static void sacuvajKorisnike(ArrayList<Korisnik> korisnici){
		if(korisnici == null){
			korisnici = new ArrayList<Korisnik>();
			korisnici.add(new Korisnik("admin", "admin", TipKorisnika.ADMIN));
		}
		else if(korisnici.size()==0){
			korisnici.add(new Korisnik("admin", "admin", TipKorisnika.ADMIN));
		}
		
		ObjectMapper om = new ObjectMapper();
		File f = new File("resources/korisnici.json");
		String str = null;
		try {
			str = om.writeValueAsString(korisnici);
			PrintWriter pw = new PrintWriter(f);
			pw.write(str);
			pw.close();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void createMetaScheme(){
		Skladiste s = new Skladiste("147.91.175.155", "ui-2016-tim203.4", "ui-2016-tim203.4", "ui-2016-tim203.4.SuK990");
		
		Paket p = new Paket();
		p.setNaziv("ui-2016-tim203.4");
		
		DBFile db1 = new DBFile("STUDENTI");
		DBFile db2 = new DBFile("TOK_STUDIJA");
		
		p.getDeca().add(db1);
		p.getDeca().add(db2);
		s.getDeca().add(p);
		
		ObjectMapper om = new ObjectMapper();
		File f = new File("C:/Users/Novakovic/Desktop/ms4.json");
		String str = null;
		try {
			str = om.writeValueAsString(s);
			PrintWriter pw = new PrintWriter(f);
			pw.write(str);
			pw.close();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
