package model;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Atribut> atributi;
	private String[] columnNames;
	
	public MyTableModel(ArrayList<Atribut> atributi){
		this.atributi = atributi;
		columnNames = new String[this.atributi.size()];
		for(int i = 0; i<atributi.size();i++){
			columnNames[i] = atributi.get(i).getNaziv();
		}
	}
	
	  @Override
	    public int getColumnCount() {
	         return columnNames.length;
	    }


	    @Override
	    public String getColumnName(int index) {
	        return columnNames[index];
	    }
}
