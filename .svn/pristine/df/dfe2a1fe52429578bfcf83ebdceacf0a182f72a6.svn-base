package controller.infViewerController;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.SearchFrame;
import view.FileView;

public class SearchListener implements ActionListener {
 private FileView addtable;

 public SearchListener(FileView addtable) {
  this.addtable = addtable;
 }

 public SearchListener(SearchFrame searchFrame) {
	// TODO Auto-generated constructor stub
}

@Override
 public void actionPerformed(ActionEvent e) {
  if (addtable.getSearch()!= null) {
	  ((Dialog) addtable.getSearch()).setVisible(true);
   return;
  }
  addtable.setSearch(new SearchFrame(addtable));
 }

}