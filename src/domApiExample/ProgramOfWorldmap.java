package domApiExample;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class ProgramOfWorldmap {
    public static void main(String[] args) throws IOException, SAXException {
        Worldmap wm = new Worldmap();
        wm.loadFromFile("xml" + File.separator + "map.xml");

        wm.saveToFile("xml" + File.separator + "eat.xml");

        try {
            wm.addCountry(6, "Бразилия");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            wm.getCountry(6);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            wm.deleteCountry(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            wm.addCity("Полтава",false, 28000, 1, 6);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(wm.countCountries());
    }
}