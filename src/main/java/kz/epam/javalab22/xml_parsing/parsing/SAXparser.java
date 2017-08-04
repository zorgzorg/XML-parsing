package kz.epam.javalab22.xml_parsing.parsing;

import kz.epam.javalab22.xml_parsing.entity.*;
import kz.epam.javalab22.xml_parsing.enumeration.Tags;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SAXparser {
    public static Salad getSalad(File xmlFile) {
        UserHandler userHandler = new UserHandler();

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser SAX_parser = factory.newSAXParser();
            SAX_parser.parse(xmlFile, userHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Salad salad = new Salad(userHandler.getNameSalad(), userHandler.getIngredients());
        return salad;

    }
}
class UserHandler extends DefaultHandler {
    private static String SALAD = Tags.SALAD.toString().toLowerCase();
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

    private static String nameSalad;
    private static List<Ingredient> ingredients = new ArrayList<Ingredient>();

    private Boolean bName = false;
    private Boolean bCaloricValue = false;
    private Boolean bWeight = false;
    private Boolean bUnit = false;
    private Boolean bPreliminaryProcessing = false;
    private Boolean bLenten = false;
    private Boolean bHotLevel = false;

    private String name;
    private int caloricValue;
    private int weight;
    private String unit;
    private Boolean preliminaryProcessing = false;
    private Boolean lenten = false;
    private int hotLevel;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(SALAD.equalsIgnoreCase(qName)){
            for (int i = 0; i < attributes.getLength(); i++) {
                if(NAME.equalsIgnoreCase(attributes.getQName(i))){
                    nameSalad = attributes.getValue(i);
                }
            }
        } else  if(NAME.equalsIgnoreCase(qName)){
            bName = true;
        } else  if(CALORICVALUE.equalsIgnoreCase(qName)){
            bCaloricValue = true;
        } else if(WEIGHT.equalsIgnoreCase(qName)){
            bWeight = true;
        } else if(UNIT.equalsIgnoreCase(qName)){
            bUnit = true;
        } else if(PRELIMINARYPROCESSING.equalsIgnoreCase(qName)){
            bPreliminaryProcessing = true;
        } else if(LENTEN.equalsIgnoreCase(qName)){
            bLenten = true;
        } else if(HOTLEVEL.equalsIgnoreCase(qName)){
            bHotLevel = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(VEGETABLE.equalsIgnoreCase(qName)) {
            ingredients.add(new Vegetable(
                    name,
                    caloricValue,
                    weight,
                    unit,
                    preliminaryProcessing
            ));
        } else  if(DRESSING.equalsIgnoreCase(qName)) {
            ingredients.add(new Dressing(
                    name,
                    caloricValue,
                    weight,
                    unit,
                    lenten
            ));
        } else  if(SEASONING.equalsIgnoreCase(qName)) {
                ingredients.add(new Seasoning(
                        name,
                        caloricValue,
                        weight,
                        unit,
                        hotLevel
                ));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(bName){
            name = new String(ch, start, length);
            bName = false;
        } else if(bCaloricValue){
            caloricValue = new Integer(new String(ch, start, length));
            bCaloricValue = false;
        } else if(bWeight){
            weight = new Integer(new String(ch, start, length));
            bWeight = false;
        } else if(bUnit){
            unit = new String(ch, start, length);
            bUnit = false;
        } else if(bPreliminaryProcessing){
            preliminaryProcessing = new Boolean(new String(ch, start, length));
            bPreliminaryProcessing = false;
        } else if(bLenten){
            lenten = new Boolean(new String(ch, start, length));
            bLenten = false;
        } else if(bHotLevel){
            hotLevel = new Integer(new String(ch, start, length));
            bHotLevel = false;
        }
    }

    public static String getNameSalad() {
        return nameSalad;
    }

    public static List<Ingredient> getIngredients() {
        return ingredients;
    }
}



