
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.w3c.dom.Document;
import org.w3c.dom.*;

import org.xml.sax.SAXException;

import java.io.*;
import java.util.ArrayList;

public class Worldmap {
    // Массив стран
    private ArrayList<Country> countries;
    // Массив городов
    private ArrayList<City> cities;

    public Worldmap() throws IOException, SAXException {
        countries = new ArrayList<>();
        cities = new ArrayList<>();
    }

    // Записать данные в файл XML
    public void saveToFile(String filename) throws TransformerException {

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
        Element root = doc.createElement("eat");
        doc.appendChild(root);

        Element country1 = null;
        Element city1 = null;

        for (int i = 0; i < countries.size(); i++) {
            // Создаем объект "страна"
            country1 = doc.createElement(countries.getClass().getName());
            country1.setAttribute("code", String.valueOf(countries.get(i).getCode()));
            country1.setAttribute("name", countries.get(i).getName());
            root.appendChild(country1);

            for (int j = 0; j < countries.size(); j++) {
                // Создаем объекты "города"
                city1 = doc.createElement(cities.getClass().getName());
                city1.setAttribute("id", String.valueOf(cities.get(j).getCode()));
                city1.setAttribute("name", cities.get(j).getName());
                city1.setAttribute("count", String.valueOf(cities.get(j).getCount()));
                city1.setAttribute("country", String.valueOf(cities.get(j).getCountry()));
                if (cities.get(j).isCapital()) {
                    city1.setAttribute("iscap", "1");
                } else {
                    city1.setAttribute("iscap", "0");
                }

                country1.appendChild(city1);
            }
        }


        /**Чтобы сохранить полученное дерево в файл,
         Transformation API для XML (пакет javax.xml.transform)*/


        Source domSource = new DOMSource(doc);
        Result fileResult = new StreamResult(new File(filename));
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "WINDOWS-1251");
        transformer.transform(domSource, fileResult);

    }

    // Прочитать данные из файла XML
    public void loadFromFile(String filename) {
        DocumentBuilderFactory dbf = null;
        DocumentBuilder db = null;
        Document doc = null;

        try {
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            doc = db.parse(new File("xml" + File.separator + "map.xml"));
        } catch (ParserConfigurationException e1) { //конфигурации
            e1.printStackTrace();
        } catch (SAXException e) { //схемы
            e.printStackTrace();
        } catch (IOException e) { //чтение записть файла
            e.printStackTrace();
        }

        // Получаем корневой элемент
        Element root = doc.getDocumentElement();
        if (root.getTagName().equals("map")) {
            // Получаем коллекцию стран
            NodeList listCountries = root.getElementsByTagName("country"); //Три записи типа кантри
            // Проходим по странам
            for (int i = 0; i < listCountries.getLength(); i++) {
                // Получаем текущую страну
                Element country = (Element) listCountries.item(i); //одна нода 1 страна
                int countryCode = Integer.parseInt(country.getAttribute("id"));
                String countryName = country.getAttribute("name");

                countries.add(new Country(countryCode, countryName));
                System.out.println(countryCode + " " + countryName + ":");


                // Получаем коллекцию городов для страны
                NodeList listCities = country.getElementsByTagName("city");
                // Проходим по городам
                for (int j = 0; j < listCities.getLength(); j++) {
                    // Получаем текущий город
                    Element city = (Element) listCities.item(j);
                    String cityName = city.getAttribute("name");
                    System.out.println("  " + cityName);
                    int cityCode = Integer.parseInt(city.getAttribute("id"));
                    int cap = Integer.parseInt(city.getAttribute("iscap"));
                    boolean isCap = false;
                    if (cap == 1) {
                        isCap = true;
                    }
                    int count = Integer.parseInt(city.getAttribute("count"));
                    cities.add(new City(cityName, isCap, count, cityCode, countries.get(i)));
                }

            }

            for (int i = 0; i < countries.size(); i++) {
                for (int j = 0; j < cities.size(); j++) {
                    System.out.println(cities.get(j));
                }
               System.out.println(countries.get(i));
            }


        }


    }

    // Добавить новую страну
    public void addCountry(int code, String name) {

// если страны с заданным кодом в массиве countries еще нет -
//добавляем новую страну в массив
// в противном случае генерируем исключение

    }

    // Получить страну c заданным кодом

//    public Country getCountry(int code) {
//
//        // возвращаем страну с заданным кодом
//        // если страны с заданным кодом в массиве countries нет -
//        //генерируем исключение
//
//
//    }

//    // Получить страну c заданным номером
//    public Country getCountryInd(int index) {
//        // возвращаем страну с заданным порядковым номером
//        // если номер выходит за границы индексов массива -
//        //генерируем исключение
//    }

    // Получить количество стран
    public int countCountries() {
        return countries.size();
        // возвращаем количество стран
    }

    // Удалить страну

    public void deleteCountry(int code) {
        // Удаляем страну с заданным кодом, а также все города,
        // ссылающиеся на данну страну
        // Если страны с заданным кодом в массиве countries нет -
        // генерируем исключение
    }

    // Добавить новый город для заданной страны
    public void addCity(int code, String name, boolean isCapital,
                        int count, int countryCode) {
        // если город с заданным кодом code уже есть
        //генерируем исключение
        // если страны с заданным кодом countryCode нет
        //генерируем исключение
        // в противном случае, добавляем новый город
    }


}