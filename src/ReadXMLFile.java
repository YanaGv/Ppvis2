import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ReadXMLFile {
	public void readXML(String filePath){
		try {
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
	private static class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("student")) {
                String name = attributes.getValue("name");
                String group = attributes.getValue("group");
            }
        }
    }
}