package kz.epam.javalab22.xml_parsing.runner;

import kz.epam.javalab22.xml_parsing.entity.Salad;
import kz.epam.javalab22.xml_parsing.operation.XMLValidation;
import kz.epam.javalab22.xml_parsing.parsing.DOMparser;
import kz.epam.javalab22.xml_parsing.parsing.SAXparser;
import kz.epam.javalab22.xml_parsing.parsing.StAXparser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;


public class Runner {
    private static final String XML_FILENAME = "salad.xml";
    private static final String XSD_FILENAME = "salad.xsd";
    private static final String FILE_PATH = System.getProperty("user.dir")+"/xml_parsing/src/main/resources";
    private static final String VALIDATION_PASSED = " is valid against ";
    private static final String VALIDATION_NOT_PASSED = " is not valid against ";
    private static final String SEPARATOR = "\n";
    private static final String EQUALS = "Objects are equal!";
    private static final String NOT_EQUALS = "Objects are NOT equal!";
    private static final String HASHCODE = "Hashcode ";
    private static final String SAX = "SAX";
    private static final String STAX = "StAX";
    private static final String DOM = "DOM";
    private static final String PARSED_OBJECT = " parsed object: ";

    public static void main(String[] args) throws ParserConfigurationException, SAXException {
        Salad saladSAX;
        Salad saladStAX;
        Salad saladDOM;
        String validationResult;

        File xmlFile = new File(FILE_PATH + File.separator + XML_FILENAME);
        File xsdFile = new File(FILE_PATH+ File.separator + XSD_FILENAME);

        boolean isValid = XMLValidation.validateXML(xmlFile, xsdFile);

        if(isValid){
            validationResult = VALIDATION_PASSED;
        } else {
            validationResult = VALIDATION_NOT_PASSED;
        }

        System.out.println(XML_FILENAME + validationResult + XSD_FILENAME);

        saladSAX = SAXparser.getSalad(xmlFile);
        saladStAX = StAXparser.getSalad(xmlFile);
        saladDOM = DOMparser.getSalad(xmlFile);

        System.out.println(SAX + SEPARATOR + saladSAX);
        System.out.println(STAX + SEPARATOR + saladStAX);
        System.out.println(DOM + SEPARATOR + saladDOM);

        if(saladSAX.equals(saladStAX)&&saladSAX.equals(saladDOM)){
            System.out.println(EQUALS);
        } else {
            System.out.println(NOT_EQUALS);
        }

        System.out.println(HASHCODE + SAX + PARSED_OBJECT + saladSAX.hashCode());
        System.out.println(HASHCODE + STAX + PARSED_OBJECT + saladSAX.hashCode());
        System.out.println(HASHCODE + DOM + PARSED_OBJECT + saladSAX.hashCode());
    }


}
