package model;

public class SerFile extends AbstractFile{

	public SerFile(String path, String name, boolean directory) {
		super(path, name, directory);
	}
	
	/**
	 * Ne brisati prazan konstruktor, potreban za citanje json-a
	 * @author Novakovic
	 */
	public SerFile(){
		super("default", "default", false);
	}

	
}
