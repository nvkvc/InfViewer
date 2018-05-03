package controller.infViewerController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AddFrame;
import view.FileView;

public class AddListener implements ActionListener {

 private FileView addtable;
 public AddListener(){
  this.addtable = addtable;
 }
 

 public AddListener(AddFrame addFrame) {
	// TODO Auto-generated constructor stub
}

@Override
 public void actionPerformed(ActionEvent e) {

  if (addtable.getAddFrame() != null) {
   addtable.getAddFrame().setVisible(true);
   return;
  }
  addtable.add(new AddFrame(addtable));
 }

}