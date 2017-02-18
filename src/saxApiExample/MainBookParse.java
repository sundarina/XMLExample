package saxApiExample;

import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;

public class MainBookParse {
    public static void main(String args[]) {
        String fileName = "xml" + File.separator + "books.xml";
        // Создаем экземпляр обработчика SAX
        DefaultHandler bookHandler = new ParseBooksSAX();
        // Создаем экземпляр класса SAXParserFactory
        SAXParserFactory saxParserFactory =
                SAXParserFactory.newInstance();
        try {
            // Создаем экземпляр класса SAXParser
            SAXParser Sax_Parser =
                    saxParserFactory.newSAXParser();
            // Запуск парсера XML файла
            Sax_Parser.parse(new File(fileName), bookHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

