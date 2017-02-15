import org.xml.sax.SAXException;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ProgramOfWorldmap {
    public static void main(String[] args) throws IOException, SAXException {
        Worldmap wm =new Worldmap();
        wm.loadFromFile("xml" + File.separator + "map.xml");
       System.out.println(wm.countCountries());
        try {
            wm.saveToFile("xml" + File.separator + "eat.xml");
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}