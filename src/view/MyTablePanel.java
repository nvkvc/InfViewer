package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Entitet;

public class MyTablePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private Entitet entitet;
	private String[] columnNames; 
	
	public MyTablePanel(Entitet entitet){
		this.entitet = entitet;
		table = null;
		if(entitet!=null && entitet.getAtributi().size()!=0){
			columnNames = new String[entitet.getAtributi().size()];
			for(int i = 0; i<entitet.getAtributi().size();i++){
				columnNames[i] = entitet.getAtributi().get(i).getNaziv();
			}
			
			Object data[][] = new Object[50][columnNames.length];
			table = new JTable(data, columnNames);
			setLayout(new BorderLayout());
			
			this.add(table.getTableHeader(), BorderLayout.NORTH);
		    JScrollPane scroll = new JScrollPane(table);
	        this.add(scroll, BorderLayout.CENTER);
		}
		
	}
}
