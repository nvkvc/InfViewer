package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import tree.KeyElement;
import tree.Node;
import tree.NodeElement;
import tree.Tree;
import view.FileView;
import view.MainFrame;

public class SekFile extends AbstractFile{
	
	private Tree tree;
	
	public Tree getTree() {
		return tree;
	}
	
	public SekFile(String path, String name, boolean directory) {
		super(path, name, directory);
			
	}
	
	/**
	 * Ne brisati prazan konstruktor, potreban za citanje json-a
	 * @author Novakovic
	 */
	public SekFile(){
		super("default", "default", false);
	}
	
	
	public void applyChanges() throws JSONException	{
		File cd = new File(getPath() + File.separator + getName() + "_cd.txt");
		if (!cd.exists()) return;
		
		SekFile sf = new SekFile(getPath(), getName(), false);
		try {
			sf.getFields().add(new FileField("type", "char", 1, false));
			try {
				sf.readHeader();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sf.setRECORD_SIZE(RECORD_SIZE + 1);
			sf.setFilePath(getName() + "_cd.txt");
			sf.fetchBlock();
			sf.preSort();
			sf.externalSort();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		
		File f = new File(getPath() + File.separator + filePath);
		File newFile = new File(getPath() + File.separator + getName() + "_new.txt");
		
		File errors = new File(getPath() + File.separator + getName() + "_errors.txt");
		
		try {
			RandomAccessFile oldRaf = new RandomAccessFile(f, "r");
			RandomAccessFile cdRaf = new RandomAccessFile(cd, "r");
			RandomAccessFile newRaf = new RandomAccessFile(newFile, "rw");
			RandomAccessFile errorsRaf = new RandomAccessFile(errors, "rw");
			
			String s1 = oldRaf.readLine();
			String key1 = extractKey(s1, fields);
			String s2 = cdRaf.readLine();
			String key2 = extractKey(s2.substring(1, s2.length()), fields);
			
			boolean end = false;
		
			String lastInsertedKey = "";
			
			while(oldRaf.getFilePointer() < oldRaf.length()) {	
				boolean deleted = false;
				if (cdRaf.getFilePointer() >= cd.length() && s2 == null) {
					newRaf.writeBytes(s1);
					newRaf.writeBytes("\r\n");
					s1 = oldRaf.readLine();
					continue;
				}
				
				if (key1.compareTo(key2) > 0) {
					if (s2.charAt(0) == 'I') {
						if (!key2.equals(lastInsertedKey)) {
							lastInsertedKey = key2;
							newRaf.writeBytes(s2.substring(1, s2.length()));
							s2 = cdRaf.readLine();
							if (s2 != null)
								key2 = extractKey(s2.substring(1, s2.length()), fields);
						}
						else {
							errorsRaf.writeBytes("Insert: ");
							errorsRaf.writeBytes(s2.substring(1, s2.length()));
							s2 = cdRaf.readLine();
							if (s2 != null)
								key2 = extractKey(s2.substring(1, s2.length()), fields);
							continue;
						}
					}
				}
				
				else if (key1.compareTo(key2) < 0) {
					newRaf.writeBytes(s1);
					s1 = oldRaf.readLine();
					key1 = extractKey(s1, fields);
				
				}
				
				else if (key1.equals(key2)) {		
					if (s2.charAt(0) == 'I') {
						errorsRaf.writeBytes("Insert: ");
						errorsRaf.writeBytes(s2.substring(1, s2.length()));
						s2 = cdRaf.readLine();
						if (s2 != null)
							key2 = extractKey(s2.substring(1, s2.length()), fields);
						continue;
					}
					
					else if (s2.charAt(0) == 'M') {
						newRaf.writeBytes(s2.substring(1, s2.length()));
					}
					else deleted = true;
					
					s1 = oldRaf.readLine();
					key1 = extractKey(s1, fields);
					s2 = cdRaf.readLine();
					if (s2 != null)
						key2 = extractKey(s2.substring(1, s2.length()), fields);
				}
				if (!deleted)
					newRaf.writeBytes("\r\n");
				
			}
			
			oldRaf.close();
			cdRaf.close();
			newRaf.setLength(newRaf.getFilePointer());
			newRaf.close();
			errorsRaf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		f.delete();
		newFile.renameTo(new File(getPath() + File.separator + filePath));
		cd.delete();
		setChanged();
		notifyObservers(true);	
	}	
	
	@Override
	public int[] search(String key) {
		return binSearch(key, 0, (int)BLOCK_NUM);
		
	}
	
	public int [] binSearch(String key, int left, int right) {

		if (left > right || key.trim().equals("")) return new int[]{-1, -1};
		int mid = (left + right) / 2;
		FILE_POINTER = (mid - 1) * BLOCK_FACTOR * RECORD_SIZE;
		long tmp = 0;
		try {
			tmp = FILE_POINTER;
			fetchBlock();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (FILE_POINTER == FILE_SIZE)
		{
			return new int[]{mid, binInBlock(key, 0, (int)((FILE_SIZE - tmp) / RECORD_SIZE))};
		}
		String first = "";
		for (String st : data[0]) 
			first += st;
		
		String last = "";
		for (String st : data[(int)BLOCK_FACTOR - 1]) last += st;
		
		if (key.compareTo(extractKey(first, fields)) < 0)
			return binSearch(key, left, mid);
		
		else if (key.compareTo(extractKey(last, fields)) > 0)
			return binSearch(key, mid + 1, right);
		
		int k = (int)BLOCK_FACTOR - 1;
		return new int[]{mid, binInBlock(key, 0, k)};
	
	}
	public void writeToCd(String c, ArrayList<String> record) {
		String s = "";
		for (String st : record) s += st;
		File f = new File(getPath() + File.separator + getName() + "_cd.txt");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
			}
		}
		try {
			RandomAccessFile raf = new RandomAccessFile(f, "rw");
			raf.seek(raf.length());
			raf.writeBytes(c);
			raf.writeBytes(s);
			raf.writeBytes("\r\n");
			raf.setLength(raf.getFilePointer());
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	
	public void addRecord(ArrayList<String> record) {
		writeToCd("I", record);
	
	}
	
	public void deleteRecord(ArrayList<String> record) {
		writeToCd("D", record);
	}
	
	public void modifyRecord(ArrayList<String> record) {
		writeToCd("M", record);
	}
					//////////////////sluzi za pravljenje tree-ja u slucaju da ne postoji zapamcen//////////
	
	
	
/*	public void makeINDFile()throws IOException{
		 makeTree();
		 String headerINDName=makeINDHeader();
		 
		AppCore.getInstance().getLista().setModel(new ListaModel(path));
		//indeks - sekvencijalna datoteka je kreirana, vrsimo njen prikaz:
		UIINDFile uifile=new UIINDFile(path,headerINDName,false);
		uifile.FILE_POINTER=0;
		FileView fileView=new FileView(uifile);
	    AppCore.getInstance().setFileView(fileView);
	    AppCore.getInstance().getFramework().setSelectedIndex(AppCore.getInstance().getFramework().getSelectedIndex()+1);
	}
	
	
	public String makeINDHeader()throws IOException{
		
		
	    String headerINDName=headerName.replaceAll(".sek",".ind");
	    File indHeader=new File(path+File.separator+headerINDName);
	    if (!indHeader.exists()){
	    	indHeader.createNewFile();
	    }	 
	   
	   
		//otvaramo header file indeks-sekvencijalne datoteke
		RandomAccessFile afile=new RandomAccessFile(path+File.separator+headerName,"r");
		byte [] temp_buffer=new byte[(int) afile.length()];
		//promenicemo putanju do fajla sa podacima
		afile.read(temp_buffer);
		afile.close();

		
		//u header indeks-sekvencijalne datoteke upisujemo novu putanju i isti opis
		afile=new RandomAccessFile(indHeader.getAbsoluteFile(),"rw");
		afile.seek(0);
		//dodajemo jos i putanju do stable
		afile.writeBytes("tree/"+headerName.replaceAll(".sek",".tree")+"\r\n");
		afile.writeBytes("overZone/"+headerName.replaceAll(".sek",".over")+"\r\n");
		afile.write(temp_buffer);
		afile.setLength(afile.length());
		afile.close();
		return headerINDName;
	}
*/				
	/*
	 * Metoda sluzi za pravljenje drveta u koje cemo pamtiti podatke po kojima pretrazujemo
	 */
	public void makeTree() throws Exception
    {
		FILE_POINTER=0;
		List<Node> listNodes=new ArrayList<Node>();
		
		tree=null;
		long tFilePointer=0;

		//citanje bloka po bloka i formiranje za svaki blok po jedan NodeElement
		//sva NodeElementa cine jedan Node
	    while (FILE_POINTER<FILE_SIZE ){
	    	tFilePointer=FILE_POINTER;
			fetchBlock();
			List <KeyElement> listKeyElements=new ArrayList<KeyElement>();
			
			List <NodeElement> listNodeElements=new ArrayList<NodeElement>();
		    for (int i=0;i<fields.size();i++){
		    	if (fields.get(i).isPrimaryKey()){
		    		KeyElement keyElement=new KeyElement(fields.get(i).getType(),data[0][i]);
		    		listKeyElements.add(keyElement);
		    	}
			
	    	}
		    //posle ovoga moze se kreirati jedan NodeElement
		    NodeElement nodeElement=new NodeElement((int) (tFilePointer/RECORD_SIZE),listKeyElements);

		    
		    listNodeElements.add(nodeElement);
		    Node node=new Node(listNodeElements);
		    tFilePointer=FILE_POINTER;
			fetchBlock();
		    listKeyElements=new ArrayList<KeyElement>();
			
		    for (int i=0;i<fields.size();i++){
		    	if (fields.get(i).isPrimaryKey()){
		    		KeyElement keyElement=new KeyElement(fields.get(i).getType(),data[0][i]);
		    		listKeyElements.add(keyElement);
		    	}
			
	    	}
		    //posle ovoga moze se kreirati jod jedan NodeElement
		    nodeElement=new NodeElement((int) (tFilePointer/RECORD_SIZE),listKeyElements);

		    
		    listNodeElements.add(nodeElement);
		    //dva NodeElement-a cine jedan Node
		    node=new Node(listNodeElements);
		    listNodes.add(node);		
		}
	    
		 //posle ovoga u listNodes imamo za svaka 2  bloka po jedan Node
	    groupNodes(listNodes);
	    
	    
	    //ovaj Node treba da bude koren stabla, odnosno poslednji Node koji ćete kreirati
	    Node root=new Node();
	    
	    if(listNodes.size()>0)
	    {
	    	Node node1 = listNodes.get(0);
	    	Node node2 = listNodes.get(1);
	    	
	    	NodeElement element1 = node1.data.get(0).clone();
	    	element1.setBlockAddress(0);
	    	NodeElement element2 = node2.data.get(0).clone();
	    	element2.setBlockAddress(1);
	    	
	    	root.children.add(node1);
	    	root.children.add(node2);
	    	
	    	root.data.add(element1);
	    	root.data.add(element2);
	    }
	    else{
	    	root.children.add(listNodes.get(0));
	    }
	    tree=new Tree();
	    tree.setRootElement(root);

		 FILE_POINTER=0;
		//imamo stablo potrebno je serijalizovati ga
		ObjectOutputStream os;
		String treeFileName=getName().replaceAll(".sek",".tree");
		try{
			os = new ObjectOutputStream(new FileOutputStream(getPath()+File.separator+treeFileName));
			os.writeObject(tree);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
			
	}
	/*
	 * prolazak kroz nodove prvog nivoa i formiranje ostalih nivoa. Od 2 susedna noda iz liste listNodes uzimaju
	 * se podaci prvog nodeElementa i pravi novi Node na visem nivou. Njegovi nodeElementi imaju poziciju u listi dece neposrednog 
	 * NodeElementa
	 */
	private List<Node> groupNodes(List<Node> listNodes)
	{
		while(listNodes.size()>2)
		{
			List<Node> toBeAdded = new ArrayList<>();
			for(int i =0;i<listNodes.size();i++){
				if(i+1<listNodes.size())
				{
					Node node1 = listNodes.get(i);
					Node node2 = listNodes.get(i+1);
					
					NodeElement element1 = node1.data.get(0).clone();
					element1.setBlockAddress(0);
					NodeElement element2 = node2.data.get(0).clone();
					element2.setBlockAddress(1);
					
					Node parentNode = new Node();
					parentNode.children.add(node1);
					parentNode.children.add(node2);
					
					parentNode.data.add(element1);
					parentNode.data.add(element2);
					
					toBeAdded.add(parentNode);
					i++;
				}
			}
			if(listNodes.size()%2==1)
				toBeAdded.add(listNodes.get(listNodes.size()-1));
			listNodes.clear();
			listNodes.addAll(toBeAdded);
		}
		return listNodes;
	}
}
