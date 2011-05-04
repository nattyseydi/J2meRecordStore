
// importation des apis
import javax.microedition.lcdui.*; //API UI
import javax.microedition.midlet.*;// API Midlet



public class PersistenceMidlet extends MIDlet {
    
    static FormAdd fa = new FormAdd();
    public static PersistenceMidlet pm;
    public PersistenceMidlet()
    {
     pm =this;
    }
    public void startApp()
     {
        Display.getDisplay(this).setCurrent(fa);     
     }
     public void pauseApp()
     {
     
     }
     public void destroyApp(boolean unconditional)
     {
     }
}     
