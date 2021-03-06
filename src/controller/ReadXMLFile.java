package controller;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import model.Model;
import model.Student;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ReadXMLFile {
	static Model model = new Model();
	
	//чтение и запись XML файла в studentList
	public void readXML(String filePath){
		try {
			model.newStudentList();
			SAXParserFactory factory = SAXParserFactory.newInstance();
	        SAXParser parser;
			parser = factory.newSAXParser();
			XMLHandler handler = new XMLHandler();
			parser.parse(new File(filePath), handler);
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
                model.getStudentList().add(student);
            }
        }
    }
}