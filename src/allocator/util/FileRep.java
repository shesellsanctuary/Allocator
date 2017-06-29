/**
 * 
 */
package allocator.util;

import java.io.File;

import allocator.INFOperationService;
import allocator.data.Database;
import allocator.util.FileRep;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import allocator.data.Database;
import allocator.domain;
import allocator.impl;


/**
 * @author felipebertoldo, emilycalvet, marcellotomazi
 *
 */
public class FileRep {

	private String pathName;
	private Database database;
	private static final int EMPTY_NUMBER = -1;
	private static final String EMPTY_STR = "";
	
	/**
	 * Class constructor specifying the file path
	 * @param filePath  string containing the path of the file
	 */
	public FileRep(String pathName) {
		
		this.pathName = pathName;
		this.database = new Database();
	}
	
	/**
	 * 
	 * @return
	 */
	public Database getDatabase() {
		return this.database;
	}
	
	
	/**
	 * Discover the file extension, then parses the file and put its information on the Database.
	 */
   public void read() {
	   
	   int extensionIndex = pathName.lastIndexOf('.');
	   String extension = pathName.substring(extensionIndex + 1);
	   
	   if (extension == "xml") {
		   
		   try {
			   	
				File newXML = new File(pathName);
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(newXML);
				
				readCoursesStructure(doc);
				readFeaturesStructure(doc);
				readBuildingsStructure(doc);
			}
			
			catch(Exception e) {
			
				System.out.println(e);
			}	
	   }
	   else {
		   
		   System.out.println("This file is not supported!");
		   // TODO: TOCA EXCEPTION!
	   }

   }
   
   /**
    * TODO
    * @param doc
    */
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
   
   
   /**
    * TODO 
    * @param groupList
    * @param course
    */
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
   
   /**
    * TODO
    * @param sessionList
    * @param group
    */
   	private void readSessions(NodeList sessionList, Group group) {
   		
   		for (int session = 0; session < sessionList.getLength(); session++) {

			org.w3c.dom.Node sessionNode = sessionList.item(session);
			if (sessionNode.getNodeType() == Node.ELEMENT_NODE) {

				Element sessionElement = (Element) sessionNode;
				createSession(sessionElement, group);
			}
		}
   	}
 
   	/**
   	 * TODO
   	 * @param doc
   	 */
   	private void readFeaturesStructure(Document doc) {
   		
   		doc.getDocumentElement().normalize();
		org.w3c.dom.Node featuresNode = doc.getElementsByTagName("features").item(0);

		if (featuresNode.getNodeType() == Node.ELEMENT_NODE) {

			Element featuresElement = (Element) featuresNode;
			NodeList featureList = featuresElement.getChildNodes();
			for (int feature  = 0; feature < featureList.getLength(); feature++) {

				org.w3c.dom.Node featureNode = featureList.item(feature);
				if (featureNode.getNodeType() == Node.ELEMENT_NODE) {
					
					//System.out.print("[FEATURE]");
					@SuppressWarnings("unused")
					Element featureElement = (Element) featureNode;
					//System.out.print(" Name: " + featureElement.getAttribute("name"));
					//System.out.println(" ID: " + featureElement.getAttribute("id"));		
				}
			}
		}
   	}
   	
   	
   	/**
   	 * TODO
   	 * @param doc
   	 */
   	private void readBuildingsStructure(Document doc) {
   	
   		doc.getDocumentElement().normalize();
		org.w3c.dom.Node buildingsNode = doc.getElementsByTagName("buildings").item(0);

		if (buildingsNode.getNodeType() == Node.ELEMENT_NODE) {

			Element buildingsElement = (Element) buildingsNode;
			NodeList buildingList = buildingsElement.getChildNodes();
			
			for (int building  = 0; building < buildingList.getLength(); building++) {

				org.w3c.dom.Node buildingNode = buildingList.item(building);
				if (buildingNode.getNodeType() == Node.ELEMENT_NODE) {

					Element buildingElement = (Element) buildingNode;
					Building relatedBuilding = createBuilding(buildingElement);
					NodeList roomList = buildingElement.getChildNodes();
					readRooms(roomList, relatedBuilding);			
				}
			}
		}
   	}
   	
   	/**
   	 * 
   	 * @param roomList
   	 */
  	private void readRooms(NodeList roomList, Building building) {
  		
  		for (int room  = 0; room < roomList.getLength(); room++) {

			org.w3c.dom.Node roomNode = roomList.item(room);
			if (roomNode.getNodeType() == Node.ELEMENT_NODE) {

				Element roomElement = (Element) roomNode;
				createRoom(roomElement, building);
			}
		}
  	}
   
  	/**
  	 * 
  	 * @param courseElement
  	 * @return
  	 */
  	private Course createCourse(Element courseElement) {
  		
  		Course newCourse = new Course(courseElement.getAttribute("name"), courseElement.getAttribute("id"));
  		database.insert(newCourse);
  		
  		return newCourse;
  	}
  	
  	/**
  	 * 
  	 * @param groupElement
  	 * @return
  	 */
  	private List<Professor> createProfessor(Element groupElement) {
  		
  		List<Professor> profList = new ArrayList<Professor>();
		String professors[] = createListProfessors(groupElement.getAttribute("teacher"));

		for (int professor = 0; professor < professors.length; professor++) {
			
			Professor newProf = new Professor(professors[professor]);
			database.insert(newProf);
			profList.add(newProf);
			//TODO: INSERT into Database
		}
		return profList;
  	}
  	 	
  	/**
  	 * 
  	 * @param groupElement
  	 * @param profList
  	 * @param course
  	 * @return
  	 */
  	private Group createGroup(Element groupElement, List<Professor> profList, Course course) {
  		
  		int numberOfStudents = Integer.parseInt(groupElement.getAttribute("number_of_students"));
		Group newGroup = new Group(numberOfStudents, profList, groupElement.getAttribute("id"), course);
		database.insert(newGroup);
		course.addGroup(newGroup);
  		
		return newGroup;
  	}
  	
  	/**
  	 * TODO:
  	 * @param sessionElement
  	 * @param group
  	 */
  	private void createSession(Element sessionElement, Group group) {
  		
  		int weekday = strToInt(sessionElement.getAttribute("weekday"));
  		int duration = strToInt(sessionElement.getAttribute("duration"));
		List<Integer> feature_ids = new ArrayList<Integer>();
  		String featIdStr = sessionElement.getAttribute("feature_ids");

  		if(featIdStr != EMPTY_STR) {
  			
  			String feat_ids[] = featIdStr.split(Pattern.quote(","));
	  		for (int feature = 0; feature < feat_ids.length; feature++) 
	  		{
	  			if(feat_ids[feature] != null) {
	  				feature_ids.add(Integer.parseInt(feat_ids[feature]));
	  			}
	  		}
  		}
		Session newSession = new Session(weekday, duration, sessionElement.getAttribute("start_time"), feature_ids, group);
		group.addSession(newSession);
		database.insert(newSession);
  	}
  	
  	/**
  	 * TODO:
  	 * @param buildingElement
  	 */
  	private Building createBuilding(Element buildingElement) {
  		
		Building newBuilding = new Building(buildingElement.getAttribute("id"));
		database.insert(newBuilding);
		
		return newBuilding;
  	}
  	
  	/**
  	 * TODO:
  	 * @param roomElement
  	 * @param building
  	 * @return
  	 */
  	private void createRoom(Element roomElement, Building building) {
		
  		boolean availableRoom = setAvailableAllocationToBool(roomElement.getAttribute("available_for_allocation"));
  		int numberPlaces = strToInt(roomElement.getAttribute("number_of_places"));
  		List<Integer> feature_ids = new ArrayList<Integer>();
  		String featIdStr = roomElement.getAttribute("feature_ids");

  		if(featIdStr != EMPTY_STR) {
  			
  			String feat_ids[] = featIdStr.split(Pattern.quote(","));
	  		for (int feature = 0; feature < feat_ids.length; feature++) 
	  		{
	  			if(feat_ids[feature] != null) {
	  				feature_ids.add(Integer.parseInt(feat_ids[feature]));
	  			}
	  		}
  		}
  		Room newRoom = new Room(roomElement.getAttribute("id"), feature_ids, numberPlaces, availableRoom, building, roomElement.getAttribute("note"));
  		building.addRoom(newRoom);
  		database.insert(newRoom);
  	}
  	
  	/**
  	 * 
  	 * @param data
  	 * @return
  	 */
  	private String[] createListProfessors(String data) {
  		
  		String professors[] = data.split(Pattern.quote(","));
  		return professors;
  	}
  	
  	/**
  	 * Parses a String to an Integer. If the string is empty, returns -1. 
  	 * It works for our XML File, once it is only used with >= 0 values
  	 * @param str 
  	 * @return the number on the string
  	 */
  	private int strToInt(String str) {
  		
  		int number;
  		if(str != EMPTY_STR) {
  			
  			number = Integer.parseInt(str);
  		}
  		else {
  			
  			number = EMPTY_NUMBER;
  		}
  		
  		return number;
  	}
  	
  	/**
  	 * 
  	 * @param str
  	 * @return
  	 */
  	private boolean setAvailableAllocationToBool(String str) {
  		
  		boolean booleanValue = true;
  		if (str != EMPTY_STR) {
  			
  			if (str == "true") {
  				booleanValue = true;
  			}
  			else if (str == "false") {
  				booleanValue = false;
  			}
  		}
  		else {
  			booleanValue = true;
  		}
	
  		return booleanValue;
  	}


	public void createFile() 
	{
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			// allocation elements
			Document doc = docBuilder.newDocument();
			Element allocationElement = doc.createElement("Allocation");
			doc.appendChild(allocationElement);
			
			// courses elements
			Element coursesElement = doc.createElement("Courses");
			doc.appendChild(coursesElement);
			
			createCourses(doc);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult file = new StreamResult(new File("C:\\Allocations.xml"));
	
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
	
			transformer.transform(source, file);
	
			System.out.println("File saved!");
			
		} catch (Exception e) {
			
			System.out.println(e);
		}
	}
	
	private void createCourses(Document doc)
	{
		for(int i = 0; i < courses; i++) //TODO
		{
			// course elements
			Element courseElement = doc.createElement("Course");
			doc.appendChild(courseElement);
			
			// set name attribute to course element
			Attr courseName = doc.createAttribute("name");
			courseAttributeName.setValue(""); //TODO
			courseElement.setAttributeNode(courseName);
			
			// set id attribute to course element
			Attr courseId = doc.createAttribute("id");
			courseAttributeId.setValue(""); //TODO
			courseElement.setAttributeNode(courseId);
			
			createGroups(doc);
		}
	}
	
	private void createGroups(Document doc)
	{
		for(int i = 0; i < groups; i++) //TODO
		{
			// group elements
			Element groupElement = doc.createElement("group");
			groupElement.appendChild(group);
			
			// set attribute to group element TODO
			Attr groupNOS = doc.createAttribute("number_of_students");
			groupElementNOS.setValue("1");
			groupElement.setAttributeNode(groupNOS);
			
			// set attribute to group element
			Attr groupTeacher = doc.createAttribute("teacher");
			groupElementTeacher.setValue("1");
			groupElement.setAttributeNode(groupTeacher);
			
			// set attribute to group element
			Attr groupId = doc.createAttribute("id");
			groupElementId.setValue("1");
			groupElement.setAttributeNode(groupId);
			
			createSessions(doc); //TODO
		}
	}
	
	private void createSessions(Document doc)
	{
		for(int i = 0; i < sessions ; i++) // TODO
		{
			// session elements
			Element sessionElement = doc.createElement("session");
			sessionElement.appendChild(session);
			
			// set attribute to staff element TODO
			Attr sessionDuration = doc.createAttribute("duration");
			sessionDuration.setValue("1");
			sessionElement.setAttributeNode(sessionDuration);
			
			Attr sessionRBId = doc.createAttribute("requires_building_id");
			sessionRBId.setValue("1");
			sessionElement.setAttributeNode(sessionRBId);
			
			Attr sessionRRId = doc.createAttribute("requires_room_id");
			sessionRRId.setValue("1");
			sessionElement.setAttributeNode(sessionRRId);
			
			Attr sessionWeekday = doc.createAttribute("weekday");
			sessionWeekday.setValue("1");
			sessionElement.setAttributeNode(sessionWeekday);
			
			Attr sessionStartTime = doc.createAttribute("start_time");
			sessionStartTime.setValue("1");
			sessionElement.setAttributeNode(sessionStartTime);
		}
	}
}

