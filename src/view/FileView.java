package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;

import model.AbstractFile;
import model.IndSekFile;
import model.SekFile;

public class FileView extends JPanel implements Observer {
	
	private AbstractFile file;
	private JButton save;
	private JToolBar tb;
	protected JButton sort;
	protected JTable table;
	private JPanel panel = new JPanel();
	//private JLabel fetchCountLabel;
	private String[] columnNames;
	private AddFrame addframe;
	private JButton search;
	protected JButton add;
	private JTree indexTree;
	
	public AddFrame getAddFrame() {
		return addframe;
	}


	public void setaddframe(AddFrame addframe) {
		this.addframe = addframe;
	}


	public FileView(AbstractFile file) throws JSONException {
		this.file = file;
		file.addObserver(this);
		setLayout(new BorderLayout());
		tb = new JToolBar();
		save = new JButton("Save");
		add(tb, BorderLayout.NORTH);
		try {
			if(file.getFields()==null || file.getFields().size()==0){
				try {
					file.readHeader();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		init();
		
	}

	
	public void loadPanelInfo(JPanel panel) {
		panel.removeAll();
		JButton bf = new JButton("Block factor");
		bf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = JOptionPane.showInputDialog("Enter new block factor");
				if (s == null || s.trim().length() == 0) return;
				getFile().setBLOCK_FACTOR(Integer.parseInt(s));
				loadPanelInfo(panel);
			}
		});
		panel.add(bf);
		panel.add(new JLabel(String.valueOf(getFile().getBLOCK_FACTOR())));
		panel.add(new JLabel("   Record size: " + getFile().getRECORD_SIZE()));
		panel.add(new JLabel("   Record num: " + getFile().getRECORD_NUM()));
		panel.add(new JLabel("   File size " + getFile().getFILE_SIZE()));
		panel.add(new JLabel("   Block num: " + getFile().getBLOCK_NUM()));
		SwingUtilities.updateComponentTreeUI(MainFrame.getMainFrame());
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof Boolean) {
			if ((Boolean)arg1) {
				System.out.println("!");
				file.setFILE_POINTER(0);
				try {
					file.fetchBlock();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			table.setModel(new DefaultTableModel(file.getData(), file.getFields().toArray()));
		}
		
		if (arg1 instanceof Integer)
		{
			int pos = (int)arg1;
			table.getSelectionModel().setSelectionInterval(pos, pos);
			Rectangle rect = table.getCellRect(pos, 0, true);
			//Point pt = ((JViewport)table.getParent()).getViewPosition();
			//rect.setLocation(rect.x - pt.x, rect.y - pt.y);
			//table.scrollRectToVisible(rect);
		}
		
	}
	
	public void init() {
		//fetchCountLabel = new JLabel("Broj pristupa: "+file.getCountFetches());
		loadPanelInfo(panel);
		add(panel, BorderLayout.SOUTH);
		//getTb().add(fetchCountLabel);
		getTb().addSeparator();
		getTb().addSeparator();
		table = new JTable();
		table.setModel(new DefaultTableModel(file.getData(), file.getFields().toArray()));
		add(new JScrollPane(table), BorderLayout.CENTER);
		
		JButton fetch = new JButton("Fetch");
		fetch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					file.fetchBlock();
					file.setCountFetches(file.getCountFetches()+1);
					//fetchCountLabel.setText("Broj pristupa: "+file.getCountFetches());
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				table.setModel(new DefaultTableModel(file.getData(), file.getFields().toArray()));
			}
		});
		
		
		JButton loadFirst = new JButton("Load first block");
		loadFirst.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				file.setFILE_POINTER(0);
				try {
					file.fetchBlock();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				table.setModel(new DefaultTableModel(file.getData(), file.getFields().toArray()));
			}
		});
		
		sort = new JButton("Sort");
		sort.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				file.preSort();
				file.sort();
			}
		});
		
		
		getTb().add(sort);
		getTb().add(fetch);
		getTb().add(loadFirst);
		search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SearchFrame(null);
				
			}
		});
		add = new JButton("Add");
		getTb().add(add);
		getTb().add(search);
		
		//u slucaju indeks-sekvencijalne datoteke, ovaj taster pravi novi tree
		
		if(file instanceof IndSekFile){
			
		}else{
			add(new JScrollPane(table), BorderLayout.CENTER);
		}
		/*
		btnMakeIND.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					if(file instanceof SekFile)
						((SekFile)file).makeTree();
					//SekFile.makeTree();
				} catch (Exception e) {
					
					e.printStackTrace();
				}finally{
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				
			}
			
		});
*/		
		

	}
	public AbstractFile getFile() {
		return file;
	}
	
	public JButton getSave() {
		return save;
	}
	
	public JToolBar getTb() {
		return tb;
	}
	
	/*
	public JLabel getFetchCountLabel() {
		return fetchCountLabel;
	}
	*/
	
	public JTable getTable() {
		return table;
	}
	
	public JButton getSort() {
		return sort;
	}


	public Object getSearch() {
		// TODO Auto-generated method stub
		return null;
	}


	public void setSearch(SearchFrame searchFrame) {
		// TODO Auto-generated method stub
		
	}


}
