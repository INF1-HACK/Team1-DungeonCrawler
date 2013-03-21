import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class restaurantreader {
	
	 public static void main(String argv[]) {
	
    try {
 
	File fXmlFile = new File("/afs/inf.ed.ac.uk/user/s12/s1210107/restaurants.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
	
	doc.getDocumentElement().normalize();
	
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	 
	NodeList nList = doc.getElementsByTagName("restaurant");
	
	System.out.println("----------------------------");
	 
	for (int temp = 0; temp < nList.getLength(); temp++) {
 
		Node nNode = nList.item(temp);
 
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
 
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			Element eElement = (Element) nNode;
			
			System.out.println("Name : " + eElement.getAttribute("name"));
			System.out.println("Address : " + eElement.getElementsByTagName("address").item(0).getTextContent());
			System.out.println("Cuisine : " + eElement.getElementsByTagName("cuisine").item(0).getTextContent());
			System.out.println("Phoneno : " + eElement.getElementsByTagName("phoneno").item(0).getTextContent());
			 
		}
	}
    } catch (Exception e) {
    	e.printStackTrace();
    }
}
}
