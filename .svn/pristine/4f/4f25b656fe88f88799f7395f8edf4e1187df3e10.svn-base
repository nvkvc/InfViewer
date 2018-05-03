package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.DBFile;
import model.FileField;

public class DBSortDialog extends JDialog{
	public DBSortDialog(DBFile file)
	{
		super();
		
		JPanel content = new JPanel();
		setContentPane(content);
		content.setLayout(new GridLayout(file.getFields().size() + 1, 1));
		ArrayList<ButtonGroup> groups = new ArrayList<>();
		ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
		
		for (FileField f : file.getFields())
		{
			ButtonGroup group = new ButtonGroup();
			JRadioButton b1 = new JRadioButton("ascending");
			JRadioButton b2 = new JRadioButton("descending");
			JCheckBox cb = new JCheckBox(f.getName());
			group.add(b1);
			group.add(b2);
			b1.setSelected(true);
			groups.add(group);
			checkBoxes.add(cb);
			JPanel p = new JPanel();
			p.add(cb);
			p.add(b1);
			p.add(b2);
			content.add(p);
			
		}
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					file.sortMDI(groups, checkBoxes);
					setVisible(false);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		JPanel pa = new JPanel();
		pa.add(ok);
		add(pa);
		
		setLocationRelativeTo(null);
		setSize(600, 500);
		setVisible(true);
	}
}
