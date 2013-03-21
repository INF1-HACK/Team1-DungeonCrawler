import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class MapReader {
	
	 public static void main(String argv[]) {
	
    try {
 
	File fXmlFile = new File("/afs/inf.ed.ac.uk/user/s12/s1210107/map.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
	
	doc.getDocumentElement().normalize();
	
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	
	// this part of the code is for further copy paste into the finished xml parser
	//this is the map part for it
	NodeList mapList = doc.getElementsByTagName("tiles");
	
	System.out.println("----------------------------");
	System.out.println(mapList.getLength());
	 
	for (int temp = 0; temp < mapList.getLength(); temp++) {
 
		Node mapNode = mapList.item(temp);
 
		System.out.println("\nCurrent Element :" + mapNode.getNodeName());
 
		if (mapNode.getNodeType() == Node.ELEMENT_NODE) {
 
			Element eElement = (Element) mapNode;
			
			
			//getFirstElementChild(eElement.getElementsByTagName("type")).setTextContent("70");
			
			System.out.println("Tiles : " + eElement.getElementsByTagName("type").item(0).getTextContent());
			System.out.println("Positiont : " + eElement.getElementsByTagName("positiont").item(0).getTextContent());

		}
	}
    } catch (Exception e) {
    	e.printStackTrace();
    }
}
}