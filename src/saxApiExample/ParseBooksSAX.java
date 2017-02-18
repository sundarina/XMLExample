package saxApiExample;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Класс является наследником обработчика SAX
 * org.xml.sax.helpers.DefaultHandler
 */


public class ParseBooksSAX extends DefaultHandler2 {
    // Строковый буфер для накопления символов
// текстовых элементов документа
    private StringBuffer stringBuffer;


    ArrayList<Book> books;

    public ParseBooksSAX() {
        books = new ArrayList<>();

    }


    /**
     * Обрабатывает событие начала документа
     */
    public void startDocument() throws SAXException {
        System.out.println(
                "<?xml version = '1.0' encoding = 'UTF-8'?>");
    }

    /**
     * Обрабатывает событие конца документа
     */
    public void endDocument() throws SAXException {
    }

    /**
     * Обрабатывает событие начала элемента <...>
     */
    public void startElement(String namespaceURI, String localName, String qName, Attributes attrs) throws SAXException {


        displayBufferText();

        if ("".equals(localName))
            localName = qName;
        System.out.print("<" + localName);
        if (attrs != null) {
            // Получаем количество атрибутов элемента
            for (int i = 0; i < attrs.getLength(); i++) {
                // Получаем локальное имя атрибута
                String aName = attrs.getLocalName(i);
                if ("".equals(aName))
                    aName = attrs.getQName(i);
                System.out.print(" ");
                // Получаем значение атрибута
                System.out.print(aName +
                        "=\"" +
                        attrs.getValue(i) +
                        "\"");
            }
        }
        System.out.print(">");
    }

    /**
     * Обрабатывает событие конца элемента </...>
     */
    public void endElement(String namespaceURI, String localName, String qName, Attributes att) throws SAXException {
        displayBufferText();
        if ("".equals(localName))
            localName = qName;
        System.out.print("</" + localName + ">");



    }

    /**
     * Обрабатывает событие символьных данных
     * текстового элемента <...>some_text</...>
     */
    public void characters(char buf[], int offset, int len) throws SAXException {
       boolean boolName = false;
        String s = new String(buf, offset, len);
// если строковый буфер равен null, создаем новый
        if (stringBuffer == null) {
            stringBuffer = new StringBuffer(s);
        } else {
// Добавляем символы в строковый буфер
            stringBuffer.append(s);
        }
    }


//     for (int i = 0; i < books.size(); i++) {
//        if(att.getValue("BookName") != null && att.getValue("BookName").equals("Thinking in Java")) {
//
//            books.get(i).setBookName(qName);
//        }
//        //  stringBuffer = null;
//    }


    /**
     * Выводит текст, собранный в строковом буфере
     */
    private void displayBufferText() {
        if (stringBuffer != null) {
            System.out.print(stringBuffer.toString());

            stringBuffer = null;
        }
    }


    public void saveToFile(String filename) /*throws TransformerException */ {

        DocumentBuilderFactory dbf = null;
        DocumentBuilder db = null;
        Document doc = null;

        dbf = DocumentBuilderFactory.newInstance();
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        // Создаем "чистый" документ XML
        doc = db.newDocument();
        // Создаем корневой элемент
        Element root = doc.createElement("BookInfo");
        doc.appendChild(root);

        Element bookElement = null;

        for (int i = 0; i < books.size(); i++) {
            // Создаем объект "страна"
            bookElement = doc.createElement("Book");
            bookElement.setAttribute("quantity", String.valueOf(books.get(i)));
            bookElement.setAttribute("BookName", books.get(i).getBookName());
            bookElement.setAttribute("BookPrice", String.valueOf(books.get(i).getBookPrice()));
            bookElement.setAttribute("BookAuthor", books.get(i).getBookAuthor());
            root.appendChild(bookElement);
        }

    }

}