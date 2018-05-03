package controller.jsonEditorController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import JSONEditor.EditorMetaSeme;

public class OpenEditorMetaSemeAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new EditorMetaSeme();
		
	}

}
