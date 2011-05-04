
import javax.microedition.lcdui.*;
import javax.microedition.rms.*;
public class FormAdd extends Form implements CommandListener{
    // déclaration des composants Items
    TextField txtNom, txtPrenom, txtAge;
    Command cmdSave, cmdShow, cmdOk;
    static RecordStore sauve; // pour l'unité de sauvegarde
    StringItem resultat;
    public FormAdd()
    {
        super("Inscription");
        try
        {
         sauve = RecordStore.openRecordStore("Sauve", true, RecordStore.AUTHMODE_PRIVATE,true);
        }catch (RecordStoreException re){System.out.println("Erreur Creation de sauvegarde");}
        txtNom = new TextField ("Nom","", 20,TextField.ANY);
        txtPrenom = new TextField ("Prenom","", 20, TextField.ANY);
        txtAge = new TextField ("Age","", 5, TextField.NUMERIC);
        cmdSave = new Command ("Save", Command.SCREEN, 0);
        cmdShow = new Command ("View", Command.SCREEN, 1);
        append(txtNom); append(txtPrenom); append(txtAge);
        addCommand(cmdSave); addCommand(cmdShow);
        setCommandListener(this);    
        }
    public void commandAction (Command c, Displayable d)
     {
        if (c == cmdSave)
        {
            String n = txtNom.getString();
            String p = txtPrenom.getString();
            String a = txtAge.getString();
            String chaine = n+" "+p+" "+a;
            byte [] donnees = chaine.getBytes();
            try
            {
                int i = sauve.addRecord(donnees, 0,donnees.length);
                System.out.println("Donnees enregistrees : ID = "+i);
                txtNom.setString("");
                txtPrenom.setString("");
                txtAge.setString("");
            }catch (RecordStoreException re){System.out.println(" Erreur Insertion");}
        }
        
        if (c == cmdShow)
        {
            this.deleteAll();
            this.removeCommand(cmdSave);
            cmdOk = new Command ("Find", Command.SCREEN, 0);
            txtNom.setLabel("Enter ID Record");
            txtNom.setString("");
            append(txtNom); addCommand(cmdOk);
        }
       
       if (c == cmdOk)
        {
            byte [] donnees = null;
            try {
                int id = Integer.parseInt(txtNom.getString());
                donnees = sauve.getRecord(id);
            }catch(RecordStoreException re){}
            this.deleteAll();
            resultat = new StringItem ("","", TextField.ANY);
            resultat.setText(new String(donnees));  
            append(resultat);    
        }
     }
     
        
}    
