import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class DungeonReader {
	
	 public static void main(String argv[]) {
	
    try {
 
	File fXmlFile = new File("/afs/inf.ed.ac.uk/user/s12/s1210107/dungeon.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
	
	doc.getDocumentElement().normalize();
	
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	 
	NodeList nList = doc.getElementsByTagName("dungeon");
	
	System.out.println("----------------------------");
	 
	for (int temp = 0; temp < nList.getLength(); temp++) {
 
		Node nNode = nList.item(temp);
 
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
 
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			Element eElement = (Element) nNode;
			
			/*System.out.println("Map : " + eElement.getElementsByTagName("map").item(0).getTextContent());
			System.out.println("Tiles : " + eElement.getElementsByTagName("tiles").item(0).getTextContent());
			System.out.println("Positiont : " + eElement.getElementsByTagName("positiont").item(0).getTextContent());
			*/System.out.println(" : " + eElement.getElementsByTagName("map").item(0).getTextContent());
			System.out.println("Map : " + eElement.getElementsByTagName("map").item(0).getTextContent());
			//System.out.println("Map : " + eElement.getElementsByTagName("map").item(0).getTextContent());
			//System.out.println("Map : " + eElement.getElementsByTagName("map").item(0)

		}
	}
    } catch (Exception e) {
    	e.printStackTrace();
    }
}
}