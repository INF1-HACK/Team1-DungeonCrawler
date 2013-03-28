import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;


public class PlayerReader {
	public static void main(String argv[]) {
		
	    try {
	 
		File fXmlFile = new File("/afs/inf.ed.ac.uk/user/s12/s1210107/player.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		
		doc.getDocumentElement().normalize();
		
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		
		// this part of the code is for further copy paste into the finished xml parser
		//this is the player part for it
		NodeList playerList = doc.getElementsByTagName("player"); //NodeList containing all of the children of player and player
		
		System.out.println("----------------------------");
		System.out.println(playerList.getLength()); //printing the sub tress length in this case players length
		 
		
		
		
		for (int temp = 0; temp < playerList.getLength(); temp++) {
	 
			Node playerNode = playerList.item(temp);
	 
			System.out.println("\nCurrent Element :" + playerNode.getNodeName());
	 
			if (playerNode.getNodeType() == Node.ELEMENT_NODE) {
	 
				Element eElement = (Element) playerNode;
				
				eElement.getElementsByTagName("hitpointsp").item(0).setTextContent("70");
				
				System.out.println("Hitpoints of player : " + eElement.getElementsByTagName("hitpointsp").item(0).getTextContent());
				System.out.println("Stats : " + eElement.getElementsByTagName("stats").item(0).getTextContent());
				System.out.println("Items : " + eElement.getElementsByTagName("items").item(0).getTextContent());
				System.out.println("Position of player : " + eElement.getElementsByTagName("positionp").item(0).getTextContent());
				//this is the output part ^^
				
				
			}
		}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	
}
