package view;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
 
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
 
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
 
import model.AbstractFile;
import model.DBFile;
import model.FileField;
import model.SerFile;
 
public class AddDialog extends JDialog {
 
    private ArrayList<JTextField> textFields = new ArrayList<>();
    private ArrayList<String> data = new ArrayList<String>();
    private AbstractFile file;
    private JTable table;
    private JPanel content;
    private JButton ok;
   
    public AddDialog(AbstractFile file, JTable table, ArrayList<FileField> fields) throws JSONException
    {
        this.file = file;
        this.table = table;
        content = new JPanel();
        setContentPane(content);
        setSize(600, 500);
        JSONObject object = null;
        /*
        if (file instanceof DBFile)
        {  
            try {
                 object = new JSONObject(new JSONTokener(new BufferedReader(new FileReader("json" + File.separator + "meta-scheme.json"))));
                 if (object.has(file.getName() + "_KOLONE"))
                     object = object.getJSONObject(file.getName() + "_KOLONE");
                 else object = null;
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        */
        for (FileField f : fields)
        {
            JPanel p = new JPanel();
            p.setSize(content.getWidth(), 50);
            if (object != null)
            	p.add(new JLabel(object.getString(f.getName())));
            else 
            	p.add(new JLabel(f.getName()));
            int n = f.getLength();
            if (n > 40) 
            	n = 40;
            JTextField tField = new JTextField(n);
            textFields.add(tField);
            p.add(tField);
            content.add(p);
        }
       
        ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < fields.size(); i++)    
                {
                    String s = textFields.get(i).getText().trim();
                    if (!(file instanceof DBFile))
                        for (int j = 0; j < fields.get(i).getLength() - textFields.get(i).getText().length(); j++)
                            s += " ";
                    data.add(s);
                }
                setVisible(false);         
                file.addRecord(data);                      
            }
        });
        add(ok);
        setVisible(true);
        setLocationRelativeTo(null);
    }
   
    public JPanel getContent() {
        return content;
    }
   
    public JButton getOk() {
        return ok;
    }
   
    public ArrayList<JTextField> getTextFields() {
        return textFields;
    }
   
    public ArrayList<String> getData() {
        return data;
    }
   
    public AbstractFile getFile() {
        return file;
    }
   
    public JTable getTable() {
        return table;
    }
}