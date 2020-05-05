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

public class WriteXMLFile {
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
	
	public void saveXML(String savePath) {
		try {
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
	
    public void addRecord(String inputName, String inputCourse, String inputGroup, String inputTasks, String inputCompletedTasks, String inputLanguage) {
        Element student = doc.createElement("student");
        rootElement.appendChild(student);
        student.setAttribute("name", inputName);
        student.setAttribute("course", inputCourse);
        student.setAttribute("group", inputGroup);
        student.setAttribute("tasks", inputTasks);
        student.setAttribute("completedTasks", inputCompletedTasks);
        student.setAttribute("language", inputLanguage);
    }
}