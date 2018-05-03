package model;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.swing.event.EventListenerList;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;
import org.json.JSONException;

import event.UpdateBlockEvent;
import event.UpdateBlockListener;

@JsonTypeInfo(use = Id.NAME,
include = JsonTypeInfo.As.PROPERTY,
property = "type")
@JsonSubTypes({
@Type(value = SerFile.class),
@Type(value = SekFile.class),
@Type(value = IndSekFile.class),
@Type(value = DBFile.class),
})


@JsonIgnoreProperties(ignoreUnknown = true) 
public abstract class AbstractFile extends Entitet{
	
	private String name;
	private String path;
	private boolean directory;
	private boolean changed = false;
	protected  String[][] data = null;
	
	protected long BLOCK_FACTOR=20;
	protected int BUFFER_SIZE=0;
	protected int RECORD_SIZE=0;
	protected int BLOCK_NUM=0;
	protected long RECORD_NUM=0;
	protected long FILE_POINTER=0;
	protected long FILE_SIZE=0;
	private int countFetches = 0;
	
	EventListenerList listenerBlockList = new EventListenerList();
	UpdateBlockEvent updateBlockEvent = null;
	
	protected ArrayList<FileField> fields = new ArrayList<>();
	protected String filePath;
	
	public AbstractFile(String path, String name, boolean directory)
	{
		this.path = path;
		this.name = name;
		this.directory = directory;
	}
	
	public void delete(String path)
	{
		File file = new File(path);
		
		if (!file.isDirectory() || (file.isDirectory() && file.listFiles().length == 0))
			file.delete();
		
		else 
		{
			for (File f : file.listFiles())
				delete(f.getAbsolutePath());
			delete(path);
		}	
	}
	
	public void save() {
		
	}

	public int binInBlock(String key, int left, int right)
	{
		if (left > right) return -1;
		int mid = (left + right) / 2;
		
		String s = "";
		for (String st : data[mid]) s += st;
		if (key.compareTo(extractKey(s, fields)) < 0)
			return binInBlock(key, left, mid);
		else if (key.compareTo(extractKey(s, fields)) > 0)
			return binInBlock(key, mid + 1, right);
		return mid;
	}
	
	protected void fireUpdateBlockPerformed() {
	     Object[] listeners = listenerBlockList.getListenerList();
	     for (int i = listeners.length-2; i>=0; i-=2) {
	         if (listeners[i]==UpdateBlockListener.class) {
	             if (updateBlockEvent == null)
	            	 updateBlockEvent = new UpdateBlockEvent(this);
	             ((UpdateBlockListener)listeners[i+1]).updateBlockPerformed(updateBlockEvent);
	         }
	     }
	}
	
	public int[] search(String key)
	{
		return new int[]{-1};
	}
	public void addRecord(ArrayList<String> record)
	{
		
	}
	
	public void readHeader() throws IOException, JSONException {
		
		ArrayList<String> lines = new ArrayList<>();
		RandomAccessFile raf = new RandomAccessFile(new File(getAbsolutePath()), "r");
		while(raf.getFilePointer() < raf.length())
			lines.add(raf.readLine());
		
		for (String line : lines)
		{		
			StringTokenizer st = new StringTokenizer(line, "\\/");
			String s = st.nextToken();
			if (s.equals("path"))
				filePath = st.nextToken();
			else if (s.equals("field"))
			{
				String name = st.nextToken();
				String type = st.nextToken();
				int length = Integer.parseInt(st.nextToken());
				boolean primaryKey = Boolean.parseBoolean(st.nextToken());
				fields.add(new FileField(name, type, length, primaryKey));
				RECORD_SIZE += length;
			}
			
		}
		
		RECORD_SIZE += 2;
		raf.close();
	}
	
	public void fetchBlock() throws IOException
	{	
		  RandomAccessFile raf = new RandomAccessFile(getPath()+File.separator+filePath,"r");
		  FILE_SIZE = raf.length();
		  RECORD_NUM=(long) Math.ceil((FILE_SIZE*1.0000)/(RECORD_SIZE*1.0000));
		  BLOCK_NUM=(int) (RECORD_NUM/BLOCK_FACTOR)+1;
		  
		  if (FILE_POINTER/RECORD_SIZE+BLOCK_FACTOR>RECORD_NUM) 
			   BUFFER_SIZE=(int) (RECORD_NUM-FILE_POINTER/RECORD_SIZE)*RECORD_SIZE;
		  else 
			   BUFFER_SIZE=(int)(RECORD_SIZE*BLOCK_FACTOR);
		  
		 raf.close();
		  
		 
	    	
	    FILE_POINTER = loadData();

		
	}
	
	public long loadData() throws IOException
	{
	
		 byte []buffer=new byte[BUFFER_SIZE];
		  data = new String[(int) BUFFER_SIZE/RECORD_SIZE][];
		RandomAccessFile raf = new RandomAccessFile(getPath()+File.separator+filePath,"r");
		 raf.seek(FILE_POINTER);
		  raf.read(buffer);
	      String s = new String(buffer);
	      if (s.length() < buffer.length)
	      {
	    	  for (int i = s.length(); i < buffer.length; i++)
	    		  s = s + " ";
	      }
	
	      int c = 0;
	      for (int i=0; i < BUFFER_SIZE/RECORD_SIZE; i++){
	    	  
	    	 String line = s.substring(i * RECORD_SIZE,i * RECORD_SIZE + RECORD_SIZE);
			 data[i - c] = new String[fields.size()]; 
			 int k = 0;
			 for (int j = 0; j < fields.size(); j++)
			 {
				String field=null;
			   	field =  line.substring(k,k + fields.get(j).getLength());
				data[i - c][j] = field;
				k = k+fields.get(j).getLength();
			 }		
	      }
	      long l = raf.getFilePointer();
	      raf.close();
	      return l;
	}
	
	public String extractKey(String s, ArrayList<FileField> fields)	{
		int c = 0;
		String tmp = "";
		for (int k = 0; k < fields.size(); k++) {
			if (!fields.get(k).isPrimaryKey()) {
				c += fields.get(k).getLength();
				continue;
			}
			tmp += s.substring(c, c + fields.get(k).getLength());
			c += fields.get(k).getLength();
		}
		return tmp;
	}
	public void externalSort() throws IOException {
		File folder = new File(getPath() + File.separator + "folder_dat");
		File files[] = folder.listFiles();
		
		int bufferSize = (int)BLOCK_NUM / (int)BLOCK_FACTOR ;
		if (bufferSize == 0) bufferSize = 1;
		String buffers[][] = new String[files.length][bufferSize];
		
		
		
		RandomAccessFile sortedRaf = null;
		//File sorted = new File(getPath() + File.separator + getName().substring(0, getName().length() - 4) +  "_sorted.txt");
		File sorted = new File(getPath() + File.separator + filePath);
		sorted.delete();
		sorted.createNewFile();
		
		sortedRaf = new RandomAccessFile(sorted, "rw");
		sortedRaf.seek(0);
	
		
		RandomAccessFile rafs[] = new RandomAccessFile[buffers.length];
		for (int i = 0; i < buffers.length; i++)
			try {
				RandomAccessFile raf = new RandomAccessFile(folder.getAbsolutePath() + File.separator + "dat" + i + ".txt", "rw");
				raf.seek(0);
				rafs[i] = raf;
			} catch (IOException e) {
				 
				e.printStackTrace();
			}
		
		int bufferIndexes[] = new int[buffers.length];
		Arrays.fill(bufferIndexes, 0);
		
		for (int i = 0; i < buffers.length; i++)
		{
			RandomAccessFile raf = rafs[i];
			for (int j = 0; j < bufferSize ; j++)
			{
				byte b[] = new byte[RECORD_SIZE];
				raf.read(b);
				buffers[i][j] = new String(b);
			}
			
		}
		int counter = 0;
		while (counter != RECORD_NUM)
		{
			String[] strings = new String[buffers.length];
			for (int i = 0; i < buffers.length; i++)
			{
				int j = bufferIndexes[i];
				if (j == -1) continue;
				String s = buffers[i][j];
				int c = 0;
				String tmp = extractKey(s, fields);
				strings[i] = tmp;
				
			}
			
			int min = -1;
			for (int i = 0; i < strings.length; i++)
			{
				if (strings[i] == null || strings[i].trim().length() == 0) continue;
				
				if (min == -1) min = i;
				if (strings[i].compareTo(strings[min]) < 0)
				{
					min = i;
				}
			}
			if (min == -1) 
			{
				counter++;
				continue;
			}
			String record = buffers[min][bufferIndexes[min]];
			bufferIndexes[min]++;
			if (bufferIndexes[min] >= bufferSize)
			{
				bufferIndexes[min] = 0;	
				RandomAccessFile raf = rafs[min];
				if (raf.getFilePointer() >= BLOCK_FACTOR * RECORD_SIZE)
					bufferIndexes[min] = -1;
				else
				{
					for (int j = 0; j < bufferSize ; j++)
					{
						byte b[] = new byte[RECORD_SIZE];
						raf.read(b);
						buffers[min][j] = new String(b);
					}
				}
			}	
			
			
			sortedRaf.writeBytes(record);
			sortedRaf.setLength(sortedRaf.getFilePointer());
			counter++;
		
		}
		for (RandomAccessFile raf : rafs)
			raf.close();
		
		sortedRaf.close();
		//filePath = sorted.getName();
//		new File(filePath).delete();
//		sorted.renameTo(new File(filePath));
		setChanged();
		notifyObservers(true);

	
		
	}
	
	public int getCountFetches() {
		return countFetches;
	}
	
	public void setCountFetches(int countFetches) {
		this.countFetches = countFetches;
	}
	
	public void sort()	{
		try {
			externalSort();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void modifyRecord(ArrayList<String> record, String[] old)
	{
		
	}
	public void preSort()	{
		FILE_POINTER = 0;
		File folder = new File(getPath() + File.separator + "folder_dat");
		if (folder.exists()) {
			for (File f : folder.listFiles())
				f.delete();
		}
		folder.mkdir();
		for (int c = 0; c < BLOCK_NUM; c++) {
			try {
				fetchBlock();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String tmp[] = new String[(int)BLOCK_FACTOR];
			int k = 0;
			for (int i = 0; i < data.length; i++) {
				String s = "";
				for (int j = 0; j < fields.size(); j++) {
					if (fields.get(j).isPrimaryKey()) s+= data[i][j];
				}
				tmp[k++] = s;
			}
			
			int indexes[] = new int[tmp.length];
			for (int i = 0; i < indexes.length; i++) indexes[i] = i;
			TreeMap<String,Integer> map = new TreeMap<String,Integer>();
			for( int i : indexes ) {
				if (tmp[i] != null)
					map.put( tmp[i], i );
			}
			
			
			
			File f = new File(folder.getAbsolutePath() + File.separator + "dat" + c  + ".txt");
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			RandomAccessFile raf;
			try {
				raf = new RandomAccessFile(f, "rw");
				raf.seek(0);
				for (int i : map.values()) {
					for (int j = 0; j < fields.size(); j++) {
						String s = data[i][j];
						while (s.length() < fields.get(j).getLength()) s += " ";
						raf.writeBytes(s);
					}
					raf.writeBytes("\r\n");
					
				}
				raf.setLength(raf.length());
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getPath() {
		return path;
	}
	
	public boolean isDirectory()
	{
		return directory;
	}
	
	public String getAbsolutePath()
	{
		return path+"/"+name;
	}

	public boolean getChanged()
	{
		return changed;
		
	}
	
	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	
	public String[][] getData() {
		return data;
	}
	

	public void setFILE_POINTER(long fILE_POINTER) {
		FILE_POINTER = fILE_POINTER;
	}
	
	public ArrayList<FileField> getFields() {
		return fields;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getFILE_POINTER() {
		return FILE_POINTER;
	}
	
	public long getFILE_SIZE() {
		return FILE_SIZE;
	}
	
	public long getBLOCK_FACTOR() {
		return BLOCK_FACTOR;
	}
	
	public int getBLOCK_NUM() {
		return BLOCK_NUM;
	}
	
	public long getRECORD_NUM() {
		return RECORD_NUM;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public int getRECORD_SIZE() {
		return RECORD_SIZE;
	}
	
	public void setBLOCK_FACTOR(long bLOCK_FACTOR) {
		BLOCK_FACTOR = bLOCK_FACTOR;
	}
	
	public void setRECORD_SIZE(int rECORD_SIZE) {
		RECORD_SIZE = rECORD_SIZE;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}

