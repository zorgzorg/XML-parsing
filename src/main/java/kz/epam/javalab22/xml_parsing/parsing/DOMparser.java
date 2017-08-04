package kz.epam.javalab22.xml_parsing.parsing;

import kz.epam.javalab22.xml_parsing.entity.*;
import kz.epam.javalab22.xml_parsing.enumeration.Tags;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DOMparser {
    private static List<Ingredient> ingredients = new ArrayList<Ingredient>();
    private static String nameSalad="";

    private static String NAME = Tags.NAME.toString().toLowerCase();
    private static String CALORICVALUE = Tags.CALORICVALUE.toString().toLowerCase();
    private static String WEIGHT = Tags.WEIGHT.toString().toLowerCase();
    private static String UNIT = Tags.UNIT.toString().toLowerCase();
    private static String PRELIMINARYPROCESSING = Tags.PRELIMINARYPROCESSING.toString().toLowerCase();
    private static String LENTEN = Tags.LENTEN.toString().toLowerCase();
    private static String HOTLEVEL = Tags.HOTLEVEL.toString().toLowerCase();
    private static String VEGETABLE = Tags.VEGETABLE.toString().toLowerCase();
    private static String DRESSING = Tags.DRESSING.toString().toLowerCase();
    private static String SEASONING = Tags.SEASONING.toString().toLowerCase();

    public static Salad getSalad(File xmlFile) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();
            nameSalad = doc.getDocumentElement().getAttribute(NAME);

            getIngredients(doc.getElementsByTagName(VEGETABLE), VEGETABLE);
            getIngredients(doc.getElementsByTagName(DRESSING), DRESSING);
            getIngredients(doc.getElementsByTagName(SEASONING), SEASONING);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Salad salad = new Salad(nameSalad, ingredients);
        return salad;
    }

    private static List<Ingredient> getIngredients(NodeList nodeList, String type){

        for (int temp = 0; temp < nodeList.getLength(); temp++) {
            Node nNode = nodeList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                    if(VEGETABLE.equalsIgnoreCase(type)){
                        ingredients.add(new Vegetable(
                                eElement.getElementsByTagName(NAME).item(0).getTextContent(),
                                Integer.parseInt(eElement.getElementsByTagName(CALORICVALUE).item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName(WEIGHT).item(0).getTextContent()),
                                eElement.getElementsByTagName(UNIT).item(0).getTextContent(),
                                Boolean.parseBoolean(eElement.getElementsByTagName(PRELIMINARYPROCESSING).item(0).getTextContent())
                        ));
                    } else if (DRESSING.equalsIgnoreCase(type)) {
                        ingredients.add(new Dressing(
                                eElement.getElementsByTagName(NAME).item(0).getTextContent(),
                                Integer.parseInt(eElement.getElementsByTagName(CALORICVALUE).item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName(WEIGHT).item(0).getTextContent()),
                                eElement.getElementsByTagName(UNIT).item(0).getTextContent(),
                                Boolean.parseBoolean(eElement.getElementsByTagName(LENTEN).item(0).getTextContent())
                        ));
                    } else if(SEASONING.equalsIgnoreCase(type)) {
                        ingredients.add(new Seasoning(
                                eElement.getElementsByTagName(NAME).item(0).getTextContent(),
                                Integer.parseInt(eElement.getElementsByTagName(CALORICVALUE).item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName(WEIGHT).item(0).getTextContent()),
                                eElement.getElementsByTagName(UNIT).item(0).getTextContent(),
                                Integer.parseInt(eElement.getElementsByTagName(HOTLEVEL).item(0).getTextContent())
                        ));
                    }
            }
        }

        return ingredients;
    }
}
