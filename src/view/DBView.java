package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;

import model.DBFile;

@SuppressWarnings("serial")
public class DBView extends FileView{
	
	private FileView fv;
	public DBView(DBFile file) throws JSONException
	{
		super(file);
		file.fetchBlock();
		
		fv = this;
		//init();
		
		getTable().setModel(new DefaultTableModel(file.getData(), file.getFields().toArray()));
		//JButton add = new JButton("Add");
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					AddDialog ad = new AddDialog(file, table, file.getFields());
					ad.setModal(true);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		sort.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DBSortDialog db = new DBSortDialog(file);
				db.setModal(true);
				
			}
		});
		getTb().add(add);
		
		
		JButton filter = new JButton("Filter");
		filter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					DBFilterDialog fd = new DBFilterDialog(file);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		getTb().add(filter);
	}
}
