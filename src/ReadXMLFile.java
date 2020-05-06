import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ReadXMLFile extends Ppvis2 {
	
	//чтение и запись XML файла в studentList
	public void readXML(String filePath){
		try {
			studentList = new ArrayList<>();
			SAXParserFactory factory = SAXParserFactory.newInstance();
	        SAXParser parser;
			parser = factory.newSAXParser();
			XMLHandler handler = new XMLHandler();
			parser.parse(new File(filePath), handler);	
			updateTable();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	//класс XMLHandler с переопределенным методом startElement из DefaultHandler
	private static class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("student")) {
            	Student student = new Student();
                student.setName(attributes.getValue("name"));
                student.setCourse(Integer.parseInt(attributes.getValue("course")));
                student.setGroup(Integer.parseInt(attributes.getValue("group")));
                student.setTasks(Integer.parseInt(attributes.getValue("tasks")));
                student.setCompletedTasks(Integer.parseInt(attributes.getValue("completedTasks")));
                student.setLanguage(attributes.getValue("language"));
                studentList.add(student);
            }
        }
    }
}