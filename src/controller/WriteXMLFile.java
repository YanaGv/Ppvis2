package controller;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import model.Model;
import model.Student;

public class WriteXMLFile extends Model {
	private static DocumentBuilderFactory docFactory;
	private static DocumentBuilder docBuilder;
	private static Document doc;
	private Element rootElement;
	
	public WriteXMLFile(){
		try {
			docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();
			
			rootElement = doc.createElement("info");
			doc.appendChild(rootElement);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}
	}
	
	//сохранение studentList в XML файл 
	public void saveXML(String savePath) {
		try {
			for(Student student: studentList)
				addRecord(student);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer;
			transformer = transformerFactory.newTransformer();
		
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(savePath));
			transformer.transform(source, result);

			System.out.println("File saved!");
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
	
	//добавление записи в XML
    private void addRecord(Student inputStudent) {
        Element student = doc.createElement("student");
        rootElement.appendChild(student);
        student.setAttribute("name", inputStudent.getName());
        student.setAttribute("course", ""+inputStudent.getCourse());
        student.setAttribute("group", ""+inputStudent.getGroup());
        student.setAttribute("tasks", ""+inputStudent.getTasks());
        student.setAttribute("completedTasks", ""+inputStudent.getCompletedTasks());
        student.setAttribute("language", inputStudent.getLanguage());
    }
}