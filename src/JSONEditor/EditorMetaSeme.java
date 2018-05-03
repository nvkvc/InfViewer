package JSONEditor;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import controller.jsonEditorController.EditorDeleteTabAction;
import controller.jsonEditorController.JSONValidationAction;
import controller.jsonEditorController.NewEditorAction;
import controller.jsonEditorController.OpenEditorAction;
import controller.jsonEditorController.SaveEditorAction;
import controller.jsonEditorController.SetAsMetaSchemeAction;
import controller.jsonEditorController.WindowAction;
import view.MyTabbedPane;

public class EditorMetaSeme extends JFrame {
	
	private JMenuBar menu;
	private JToolBar toolbar;
	private JTextArea ta;
	private JPanel panel;
	private JMenu fileMenu;	
	private JButton novi;
	private JButton otvori;
	private JButton obrisi;
	private JButton sacuvaj;
	private JButton validacija;
	private JScrollPane scrollPane;
	private JMenuItem fileNovi;
	private JMenuItem fileOtvori;
	private JMenuItem fileSacuvaj;
	private JButton setMetaScheme;

	private MyTabbedPane tabbedPane;
	
	public EditorMetaSeme()
	{
		ta = new JTextArea();
		toolbar  =new JToolBar();
		panel = new JPanel();
		menu = new JMenuBar();
		novi = new JButton(new ImageIcon("resources/new.png"));
		novi.setToolTipText("Start new JSON file");
		otvori = new JButton(new ImageIcon("resources/open.png"));
		otvori.setToolTipText("Open existing JSON file");
		fileMenu = new JMenu("File");
		fileNovi = new JMenuItem("New");
		fileOtvori = new JMenuItem("Open");
		tabbedPane = new MyTabbedPane();
		scrollPane = new JScrollPane(tabbedPane);////////////////////////////////ne znam koji je ispravan
		obrisi = new JButton(new ImageIcon("resources/exit.png"));
		obrisi.setToolTipText("Remove tab");
		fileSacuvaj = new JMenuItem("Save");
		sacuvaj = new JButton(new ImageIcon("resources/save.png"));
		sacuvaj.setToolTipText("Save JSON file");
		validacija = new JButton("Validate JSON");
		setMetaScheme = new JButton("Set As MS");
		setMetaScheme.setToolTipText("Set As Meta-Scheme");
		
		menu.add(fileMenu);
		fileMenu.add(fileNovi);
		fileMenu.add(fileOtvori);
		fileMenu.add(fileSacuvaj);
		toolbar.add(novi);
		toolbar.add(otvori);
		toolbar.add(obrisi);
		toolbar.add(sacuvaj);
		toolbar.add(validacija);
		toolbar.add(setMetaScheme);
		
		otvori.addActionListener(new OpenEditorAction(tabbedPane));
		fileOtvori.addActionListener(new OpenEditorAction(tabbedPane));
		novi.addActionListener(new NewEditorAction(tabbedPane));
		fileNovi.addActionListener(new NewEditorAction(tabbedPane));
		obrisi.addActionListener(new EditorDeleteTabAction(tabbedPane));
		fileSacuvaj.addActionListener(new SaveEditorAction(tabbedPane));
		validacija.addActionListener(new JSONValidationAction(tabbedPane));
		sacuvaj.addActionListener(new SaveEditorAction(tabbedPane));
		setMetaScheme.addActionListener(new SetAsMetaSchemeAction(tabbedPane));
		this.addWindowListener(new WindowAction(tabbedPane));
		
		setTitle("Editor Meta Seme");
		panel.setLayout(new BorderLayout());
		setJMenuBar(menu);		
		add(toolbar, BorderLayout.PAGE_START);
		add(tabbedPane,BorderLayout.CENTER);
		this.setVisible(true);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
