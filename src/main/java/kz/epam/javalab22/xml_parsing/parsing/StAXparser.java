package kz.epam.javalab22.xml_parsing.parsing;

import kz.epam.javalab22.xml_parsing.entity.*;
import kz.epam.javalab22.xml_parsing.enumeration.Tags;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.*;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StAXparser {
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

    public static Salad getSalad(File xmlFile) {
        String nameSalad="";
        List<Ingredient> ingredients = new ArrayList<Ingredient>();

        Boolean bName = false;
        Boolean bCaloricValue = false;
        Boolean bWeight = false;
        Boolean bUnit = false;
        Boolean bPreliminaryProcessing = false;
        Boolean bLenten = false;
        Boolean bHotLevel = false;

        String name = "";
        int caloricValue = 0;
        int weight = 0;
        String unit = "";
        Boolean preliminaryProcessing = false;
        Boolean lenten = false;
        int hotLevel = 0;

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(xmlFile));

            while(eventReader.hasNext()){
                XMLEvent event = eventReader.nextEvent();
                String qName = "";
                switch(event.getEventType()){
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        qName = startElement.getName().getLocalPart();
                        if(SALAD.equalsIgnoreCase(qName)){
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            nameSalad = attributes.next().getValue();
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
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if(bName){
                            name = new String(characters.getData());
                            bName = false;
                        } else if(bCaloricValue){
                            caloricValue = new Integer(new String(characters.getData()));
                            bCaloricValue = false;
                        } else if(bWeight){
                            weight = new Integer(new String(characters.getData()));
                            bWeight = false;
                        } else if(bUnit){
                            unit = new String(characters.getData());
                            bUnit = false;
                        } else if(bPreliminaryProcessing){
                            preliminaryProcessing = new Boolean(new String(characters.getData()));
                            bPreliminaryProcessing = false;
                        } else if(bLenten){
                            lenten = new Boolean(new String(characters.getData()));
                            bLenten = false;
                        } else if(bHotLevel){
                            hotLevel = new Integer(new String(characters.getData()));
                            bHotLevel = false;
                        }
                        break;
                    case  XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        String type = endElement.getName().getLocalPart();
                        if(VEGETABLE.equalsIgnoreCase(type)) {
                            ingredients.add(new Vegetable(
                                    name,
                                    caloricValue,
                                    weight,
                                    unit,
                                    preliminaryProcessing
                            ));
                        } else  if(DRESSING.equalsIgnoreCase(type)) {
                            ingredients.add(new Dressing(
                                    name,
                                    caloricValue,
                                    weight,
                                    unit,
                                    lenten
                            ));
                        } else  if(SEASONING.equalsIgnoreCase(type)) {
                            ingredients.add(new Seasoning(
                                    name,
                                    caloricValue,
                                    weight,
                                    unit,
                                    hotLevel
                            ));
                        }
                        break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        Salad salad = new Salad(nameSalad, ingredients);
        return salad;

    }
}

