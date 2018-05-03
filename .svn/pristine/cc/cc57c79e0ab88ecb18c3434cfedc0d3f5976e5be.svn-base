package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.Position;

import controller.infViewerController.CancelListener;
import controller.infViewerController.SearchListener;


public class SearchFrame extends JDialog {
	
 private JButton buttonse = new JButton("Search");
 private JButton buttonc = new JButton("Cancel search");
 private FileView addtable;
 private JTextArea ta ;
 
 
 public SearchFrame(FileView addtable) {
	 setSize(500, 200);
	 
  this.addtable = addtable;
  JPanel panel = new JPanel();
//ovde ide slika
  panel.setLayout(new BorderLayout());

  JLabel search = new JLabel("Search", JLabel.CENTER);
  search.setFont(new Font(null, Font.BOLD, 20));
  
  

  panel.add(search, BorderLayout.NORTH);
  panel.add(forma(), BorderLayout.CENTER);

  panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
  getContentPane().add(panel);

  setTitle("Search");
 
  setLocationRelativeTo(null);
  setVisible(true);
  setModal(true);

 }

 public JPanel forma() {
	 ta = new JTextArea();
	 ta.setSize(50, 50);
  JPanel mainpanel = new JPanel();
  mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));

  

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
    Box vbox = Box.createVerticalBox();
    
    panel.add(vbox);
    panel.add(Box.createRigidArea(new Dimension(10, 0)));
 
   mainpanel.add(Box.createRigidArea(new Dimension(0, 20)));
    mainpanel.add(panel);

    mainpanel.add(Box.createRigidArea(new Dimension(0, 25)));

    JPanel buttons = new JPanel();
    buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
    buttons.add(ta);
    buttons.add(buttonse);
    buttons.add(buttonc);
    

    mainpanel.add(buttons);
    buttonse.addActionListener(new SearchListener(this));
   buttonc.addActionListener(new CancelListener(this)); 
    
    return mainpanel;
   }
  
 
 public JButton getButtons() {
  return buttonse;
 }



 public FileView getAddtable() {
  return addtable;
 }
 
}