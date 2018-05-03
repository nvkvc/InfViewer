package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;

import tree.Tree;

public class IndSekFile extends AbstractFile{
	private Tree tree;
	protected byte[] buffer;
	public IndSekFile(String path, String name, boolean directory) {
		super(path, name, directory);
	}

	/**
	 * Ne brisati prazan konstruktor, potreban za citanje json-a
	 * @author Novakovic
	 */
	public IndSekFile(){
		super("default", "default", false);
	}
	public boolean fetchNextBlock() throws IOException{
	       
		  
		  RandomAccessFile afile=new RandomAccessFile(getPath()+File.separator+filePath,"r");
		  FILE_SIZE=afile.length();
		  RECORD_NUM=(long) Math.ceil((FILE_SIZE*1.0000)/(RECORD_SIZE*1.0000));
		  BLOCK_NUM=(int) (RECORD_NUM/BLOCK_FACTOR)+1;
		  
		  
		  //BUFFER_SIZE je uvek jednak broju slogova u jednom bloku * velicina jednog sloga
		  //izuzev ako poslednji blok nije pun blok
		  if (FILE_POINTER/RECORD_SIZE+BLOCK_FACTOR>RECORD_NUM) 
			   BUFFER_SIZE=(int) (RECORD_NUM-FILE_POINTER/RECORD_SIZE)*RECORD_SIZE;
		  else 
			   BUFFER_SIZE=(int)(RECORD_SIZE*BLOCK_FACTOR);
		  
		  buffer=new byte[BUFFER_SIZE];
		  data = new String[(int) BUFFER_SIZE/RECORD_SIZE][];
		  //po otvaranju pozicioniram se na prethodni kraj zahvata
		  afile.seek(FILE_POINTER);
		  afile.read(buffer);
	      String contentS=new String(buffer);
	      if (contentS.length()<buffer.length){
	    	  for (int x=contentS.length();x<buffer.length;x++)
	    		  contentS=contentS+" ";
	      }
	
	      
	      for (int i=0;i<BUFFER_SIZE/RECORD_SIZE;i++){
	    	  
	    	 String line=contentS.substring(i*RECORD_SIZE,i*RECORD_SIZE+RECORD_SIZE);
			 data[i] = new String[fields.size()]; 
			 int k=0;
			 for (int j=0;j<fields.size();j++){
				String field=null;
			   	field=   line.substring(k,k+fields.get(j).getLength());
				data[i][j]=field;
				k=k+fields.get(j).getLength();
			 }		
	    	  
	      }
		
		

	    FILE_POINTER=afile.getFilePointer();
		afile.close();
		
		//ucitavanje novog bloka treba da izazove osvezivanje podataka u tabeli
		fireUpdateBlockPerformed();


	return true;
	
}
	/*
	 * Ovo mislim da nece raditi, treba da se pogleda!!!
	 */
	public void openTree(String treeFilePath){

		ObjectInputStream os=null;
		try {
			os = new ObjectInputStream(new FileInputStream(treeFilePath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		  
		try {
			tree = (Tree) os.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
