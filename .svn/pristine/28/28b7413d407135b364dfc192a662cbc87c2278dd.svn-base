package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.DBFile;
import model.FileField;

public class DBFilterDialog extends JDialog{
	
	public DBFilterDialog(DBFile file) throws SQLException
	{
		super();
		JPanel content = new JPanel();
		setContentPane(content);
		
		ArrayList<JTextField> textFields = new ArrayList<JTextField>();
		
		ArrayList<FileField> fields = file.getFields();
		for (FileField f : fields)
		{
			JPanel p = new JPanel();
			p.setSize(content.getWidth(), 50);
		
		     p.add(new JLabel(f.getName()));
			int n = f.getLength();
			if (n > 40) n = 40;
			JTextField tField = new JTextField(n);
			textFields.add(tField);
			p.add(tField);
			content.add(p);
		}
		
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<String> data = new ArrayList<String>();
				for (int i = 0; i < fields.size(); i++)		
				{
					String s = textFields.get(i).getText();
					for (int j = 0; j < fields.get(i).getLength() - textFields.get(i).getText().length(); j++)
						s += " ";
					data.add(s);
				}
				
				try {
					file.findFilterRecord(data);
				} catch (SQLException e1) {
					String message = e1.getMessage();
					new ErrorPane(message);
				}
				setVisible(false);
			}
		});
		add(ok);
		setSize(600, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
