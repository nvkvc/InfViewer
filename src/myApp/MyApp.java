package myApp;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import helpClasses.JSONFile;
import view.Login;

public class MyApp {

	public static void main(String[] args) {

		/**
		 * Za administratorski rezim i sifra i username su "admin"
		 * Razlika izmedju administratorskog i korisnickog rezima je jedino u tome
		 * sto administrator ima pristup editoru meta seme za razliku od korisnika.
		 * Trenutno se korisnickom rezimu pristupa jedino sa username i password "nikola"
		 * Skladiste se nalazi u folderu resources unutar projekta pod nazivom "data.json"
		 * Skladiste se unosi iz infViewer prozor iz File menija -> importJSON
		 * ili iz Editora meta seme pozivom "Set As MS"
		 */
		new Login();
		
		//JSONFile.createMetaScheme();
	}
}
