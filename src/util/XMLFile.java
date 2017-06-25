/**
 * 
 */
package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import allocator.domain.*;


/**
 * @author felipebertoldo, emilycalvet, marcellotomazi
 *
 */
public class XMLFile {

	private String filePath;
	
	/**
	 * Class constructor specifying the file path
	 * @param filePath  string containing the path of the file
	 */
	public XMLFile(String filePath) {
		this.filePath = filePath;
	}
	
	
	/**
	 * Parses the XML File and put its information on the Database.
	 */
   public void readXML() {
	   
	   try {
			
			File newXML = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(newXML);
			
			readCoursesStructure(doc);
			readFeaturesStructure(doc);
			readBuildingsStructure(doc);
		}
		
		catch(Exception e) {
			
			System.out.println(e);
		}	
   }
   
   private void readCoursesStructure(Document doc) {
	   	
	   	doc.getDocumentElement().normalize();

	   	NodeList courseList = doc.getElementsByTagName("course");
		
		for (int course = 0; course < courseList.getLength(); course++) {
		
			org.w3c.dom.Node courseNode = courseList.item(course);
		
			if (courseNode.getNodeType() == Node.ELEMENT_NODE) {
		
				Element courseElement = (Element) courseNode;			
				Course relatedCourse = createCourse(courseElement);
				NodeList groupList = courseElement.getChildNodes();				
				readGroups(groupList, relatedCourse);				
			}
		}   
   }
   
   private void readGroups(NodeList groupList, Course course) {
	   
	   for (int group = 0; group < groupList.getLength(); group++) {
			
			org.w3c.dom.Node groupNode = groupList.item(group);
			if (groupNode.getNodeType() == Node.ELEMENT_NODE) {

				Element groupElement = (Element) groupNode;
				List<Professor> profList = createProfessor(groupElement);
				Group relatedGroup = createGroup(groupElement, profList, course);
				
				NodeList sessionList = groupElement.getChildNodes();				
				readSessions(sessionList, relatedGroup);				
			}
		}
	}
   
   	private void readSessions(NodeList sessionList, Group group) {
   		
   		for (int session = 0; session < sessionList.getLength(); session++) {

			org.w3c.dom.Node sessionNode = sessionList.item(session);
			if (sessionNode.getNodeType() == Node.ELEMENT_NODE) {

				Element sessionElement = (Element) sessionNode;
				
				createSession(sessionElement, group);
			}
		}
   	}
 
   	private void readFeaturesStructure(Document doc) {
   		
   		doc.getDocumentElement().normalize();
   		
		System.out.println("____________________________________________________________________________________________");
		org.w3c.dom.Node featuresNode = doc.getElementsByTagName("features").item(0);

		if (featuresNode.getNodeType() == Node.ELEMENT_NODE) {

			Element featuresElement = (Element) featuresNode;
			NodeList featureList = featuresElement.getChildNodes();
			for (int feature  = 0; feature < featureList.getLength(); feature++) {

				org.w3c.dom.Node featureNode = featureList.item(feature);
				if (featureNode.getNodeType() == Node.ELEMENT_NODE) {

					System.out.print("[FEATUREN]");
					Element featureElement = (Element) featureNode;

					System.out.print(" Name: " + featureElement.getAttribute("name"));
					System.out.println(" ID: " + featureElement.getAttribute("id"));		
				}
			}
		}
   	}
   	
   	private void readBuildingsStructure(Document doc) {
   	
   		doc.getDocumentElement().normalize();
   		
   		System.out.println("____________________________________________________________________________________________");
		org.w3c.dom.Node buildingsNode = doc.getElementsByTagName("buildings").item(0);

		if (buildingsNode.getNodeType() == Node.ELEMENT_NODE) {

			Element buildingsElement = (Element) buildingsNode;
			NodeList buildingList = buildingsElement.getChildNodes();
			for (int building  = 0; building < buildingList.getLength(); building++) {

				org.w3c.dom.Node buildingNode = buildingList.item(building);
				if (buildingNode.getNodeType() == Node.ELEMENT_NODE) {

					System.out.println("[BUILDINGS]");
					Element buildingElement = (Element) buildingNode;

					System.out.println(" ID: " + buildingElement.getAttribute("id"));
						
					NodeList roomList = buildingElement.getChildNodes();
					
					readRooms(roomList);
					
				}
			}
		}
   	}
   	
  	private void readRooms(NodeList roomList) {
  		
  		for (int room  = 0; room < roomList.getLength(); room++) {

			org.w3c.dom.Node roomNode = roomList.item(room);
			if (roomNode.getNodeType() == Node.ELEMENT_NODE) {

				System.out.print("\t[ROOM]");
				Element roomElement = (Element) roomNode;

				System.out.print(" ID: " + roomElement.getAttribute("id"));
				System.out.print(" Feature IDs: " + roomElement.getAttribute("feature_ids"));
				System.out.print(" Seats: " + roomElement.getAttribute("number_of_places"));
				System.out.println(" Info: " + roomElement.getAttribute("note"));
			}
		}
  	}
   
  	private Course createCourse(Element courseElement) {
  		Course currentCourse = new Course(courseElement.getAttribute("name"), courseElement.getAttribute("id"));
  		System.out.println(currentCourse.getName());
  		// toca no banco
  		
  		return currentCourse;
  	}
  	
  	private List<Professor> createProfessor(Element groupElement) {
  		
  		List<Professor> profList = new ArrayList<Professor>();
		String professors[] = createListProfessors(groupElement.getAttribute("teacher"));

		for(int professor = 0; professor < professors.length; professor++) {
			Professor newProf = new Professor(professors[professor]);
			profList.add(newProf);
			//toca no banco
		}
		return profList;
  	}
  	
  	private String[] createListProfessors(String data)
  	{
  		String professors[] = data.split(Pattern.quote(","));
  		return professors;
  	}
  	
  	private Group createGroup(Element groupElement, List<Professor> profList, Course course) {
  		
  		int numberOfStudents = Integer.parseInt(groupElement.getAttribute("number_of_students"));
		Group newGroup = new Group(numberOfStudents, profList, groupElement.getAttribute("id"), course);
  		System.out.println(newGroup.getId());
  		// toca no banco	
  		return newGroup;
  	}
  	
  	private void createSession(Element sessionElement, Group group) {
  		int weekday = Integer.parseInt(sessionElement.getAttribute("weekday"));
  		int duration = Integer.parseInt(sessionElement.getAttribute("duration"));
		List<Integer> feature_ids = new ArrayList<Integer>();
  		String fids = sessionElement.getAttribute("feature_ids");

  		if(fids != "") {
  			String feat_ids[] = fids.split(Pattern.quote(","));
	  		
	  		for(int feature = 0; feature < feat_ids.length; feature++)
	  		{
	  			if(feat_ids[feature] != null) {
	  				feature_ids.add(Integer.parseInt(feat_ids[feature]));
	  			}
	  		}
  		}
		Session newSession = new Session(weekday, duration, sessionElement.getAttribute("start_time"), feature_ids, group);
  	}
}
