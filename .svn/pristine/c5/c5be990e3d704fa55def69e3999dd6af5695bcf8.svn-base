package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.infViewerController.AddListener;


public class AddFrame extends JDialog {
 private JButton add = new JButton("Add file");
 private FileView addtable;
 
 public AddFrame(FileView addtable) {
  this.addtable = addtable;
  JPanel panel = new JPanel();
  panel.setLayout(new BorderLayout());

  JLabel addLabel = new JLabel("Add");
  addLabel.setFont(new Font(null, Font.BOLD, 20));

  panel.add(addLabel, BorderLayout.NORTH);
  panel.add(forma(), BorderLayout.CENTER);

  panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
  getContentPane().add(panel);

  setTitle("Add");
  
  setLocationRelativeTo(null);
  setVisible(true);
  setModal(true);

 }

 public JPanel forma() {
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
  buttons.add(add);

  mainpanel.add(buttons);
  add.addActionListener(new AddListener(this));
  return mainpanel;
   }
   
 public void add(){
  
 }

 public FileView getAddTable() {
  return addtable;
 }
 
}
