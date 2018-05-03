package model;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import view.ErrorPane;
import view.MainFrame;

public class DBFile extends AbstractFile{
	
	
	public DBFile(String name)
	{
		super("", name, false);
	} 
	
	public DBFile(){
		super("default", "default", false);
	}
	public void findFilterRecord(ArrayList<String> record) throws SQLException
	{
		String s = "SELECT * FROM " + getName() + " WHERE ";
		for (int i = 0; i < record.size(); i++)
		{
			String rec = record.get(i);
			if (rec.trim().equals("")) continue;
			
			s += "LTRIM(RTRIM(" + getFields().get(i).getName() + "))";
			
			String type = getFields().get(i).getType();
			if (type.equals("numeric") || type.equals("int") || type.equals("smallint"))
			{
				char c = rec.charAt(0);
				if (c != '=' && c != '>' && c != '<') s += "=?";
				else s += String.valueOf(c) + "?";
			}
			else s += " like ?";
			s +=  " AND ";
		}
		s = s.substring(0, s.length() - 4);
		
		PreparedStatement pStatement = MainFrame.getMainFrame().getConnection().prepareStatement(s);
		int k = 1;
		for (int i = 0; i < record.size(); i++)
		{
			String rec = record.get(i);
			if (rec.trim().equals("")) continue;
			char c = rec.charAt(0);
			if (c == '=' || c =='>' || c == '<') rec = rec.substring(1, rec.length());
			String type = getFields().get(i).getType();
			
			if (type.equals("numeric")) pStatement.setObject(k++,rec.trim() , java.sql.Types.NUMERIC);
			else if (type.equals("smallint")) pStatement.setInt(k++, Integer.parseInt(rec));
			else pStatement.setString(k++, rec.trim());
		}
		
		ResultSet rs = pStatement.executeQuery();
		int i = 0;
		
		data = new String[(int)RECORD_NUM][fields.size()];
		while(rs.next())
		{
			data[i++] = new String[fields.size()];
			for (int j = 0; j < fields.size(); j++)
				data[i - 1][j] = rs.getString(j + 1);
		}
		
		setChanged();
		notifyObservers(false);
		
		setChanged();
		notifyObservers(0);
	}
	
	/**
	 * author Petar Eric
	 * @param groups
	 * @param boxes
	 * @throws SQLException
	 */
	
	public void sortMDI(ArrayList<ButtonGroup> groups, ArrayList<JCheckBox> boxes) throws SQLException
    {
        String s = "SELECT * FROM " + getName() + " ORDER BY ";
       
        for (int i = 0; i < getFields().size(); i++)
        {
        	//ako nije selektovan check box, skoci ponovo na for petlju
            if (!boxes.get(i).isSelected()) continue;
            //ako jeste, uzmi ime iz check boxa 
            s += getFields().get(i).getName() + " ";
           //iz option grupe uzimamo enume i smestamo ih u e
            ButtonGroup group = groups.get(i);
            Enumeration<AbstractButton> e = group.getElements();
            //prolazimo kroz sve radio buttone iz e i trazimo selektovane
            //ako jeste, i citaj tekst pored radio buttona i taj tekst u skracenom obliku stavi na kraj s-a
            while (e.hasMoreElements())
            {
                JRadioButton o = (JRadioButton) e.nextElement();
                if (o.isSelected())
                {
                    if (o.getText().equals("ascending"))
                    	s += "ASC, ";
                    else if (o.getText().equals("descending")) 
                    	s += "DESC, ";
                }
            }
        }
        //ako se zavrsava sa ORDER BY, to znaci da nema polja po kojima se sortira
        if (s.endsWith("ORDER BY "))
        {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "No selection");
            return;
        }
        //iz izraza odsecamo ", "
        s = s.substring(0, s.length() - 2);
        
        //kreiramo objekat SQL izraza kojim pretrazujemo bazu na koju smo se zakacili
        //sa konekcijom koju poseduje glavni ekran
        Statement statement = MainFrame.getMainFrame().getConnection().createStatement();
        //izvrsavamo izraz koji ima zadati upit s i rezultat pakujem u rs
        ResultSet rs = statement.executeQuery(s);
        int i = 0;
        //rezultat rs upisujem u matricu data
        //broj vrsta u matrici odredjuje i a to je broj zapisa
        //broj kolona 
        while(rs.next())
        {
            data[i++] = new String[fields.size()];
            for (int j = 0; j < fields.size(); j++)
                data[i - 1][j] = rs.getString(j + 1);
           
        }
        setChanged();
        notifyObservers(false);
       
    }
	
	public void readHeader()
	{
		try {
			DatabaseMetaData metaData = MainFrame.getMainFrame().getConnection().getMetaData();
			ResultSet columns = metaData.getColumns(null, null, getName(), null);
			
			
			while(columns.next())
			{
				ResultSet keys = metaData.getPrimaryKeys(null, null, getName());
				String name = columns.getString("COLUMN_NAME");
				boolean pk = false;
				while(keys.next())
					if (name.equals(keys.getString("COLUMN_NAME"))) pk = true;
				
				FileField field = new FileField(name, columns.getString("TYPE_NAME"), columns.getInt("COLUMN_SIZE"), pk);
				getFields().add(field);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void fetchBlock()
	{
		try {
			Statement statement = MainFrame.getMainFrame().getConnection().createStatement();
			ResultSet count = statement.executeQuery("SELECT COUNT(*) FROM " + getName());
			
			if (count.next()) RECORD_NUM = count.getInt(1);
			statement.close();
			count.close();
			
			String s = "";
			if (fields.size() > 0) s = fields.get(0).getName();
			for (int i = 1; i < fields.size(); i++) s += "," + fields.get(i).getName();
				
			statement = MainFrame.getMainFrame().getConnection().createStatement();
			count = statement.executeQuery("SELECT " + s + " FROM " + getName());
			
			data = new String[(int)RECORD_NUM][];
			int i = 0;
			while(count.next())
			{
				data[i++] = new String[fields.size()];
				for (int j = 0; j < fields.size(); j++)
					data[i - 1][j] = count.getString(j + 1);
			}
			count.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	


@Override
    public void addRecord(ArrayList<String> record) {
       
        try {
            String fields = "";
            if (getFields().size() > 0) fields = getFields().get(0).getName();
            for (int i = 1; i < getFields().size(); i++) fields += ", " + getFields().get(i).getName();
           
            String s = "INSERT INTO " + getName() + " (" + fields;
            s += ") VALUES (?";
            for (int i = 0; i < getFields().size() - 1; i++) s += ",?";
            s+= ")";
            PreparedStatement pStatement = MainFrame.getMainFrame().getConnection().prepareStatement(s);
           
            for (int i = 0; i < record.size(); i++)
            {
                String type = getFields().get(i).getType();
                if (type.equals("numeric")) pStatement.setObject(i + 1, record.get(i).trim() , java.sql.Types.NUMERIC);
                else if (type.equals("int") || type.equals("smallint")) pStatement.setInt(i + 1, Integer.parseInt(record.get(i).trim()));
                else pStatement.setString(i + 1, record.get(i));
            }
            pStatement.executeUpdate();
           
            setChanged();
            notifyObservers(true);
           
            for (int i = 0; i < data.length; i++)
            {
                boolean found = true;
                for (int j = 0; j < data[i].length; j++)
                {
                    if (data[i][j] == null)
                    {
//                      if (record.get(j).trim().equals("") || record.get(j) == null) continue;
                        continue;
//                      else found = false;
                    }
                    else if (!record.get(j).trim().equals(data[i][j].trim()) && !getFields().get(j).getType().equals("datetime"))
                            found = false;
                }
                if (found)
                {
                    setChanged();
                    notifyObservers(i);
                    break;
                }
            }
   
        } catch (SQLException e) {
        	new ErrorPane(e.getMessage());
        }
       
       

        }


	

}

