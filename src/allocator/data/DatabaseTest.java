/**
 * 
 */
package allocator.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import allocator.domain.*;

/**
 * @author felipebertoldo
 *
 */
public class DatabaseTest {
	
	private Database db;
	private Building buildingObj;
	private Course courseObj;
	private Group groupObj;
	private Professor professorObj;
	private Professor professorObj2;
	private List<Professor> professorList;
	private List<Integer> featuresList;
	private Room roomObj;
	private Session sessionObj;
	
	
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		this.db = new Database();
		assertNotEquals(db,null);
		prepareTesters();
	}

	@Test
	public void testBuildingInsertion() {
		
		db.insert(buildingObj);
		assertEquals(db.getBuildingList().contains(buildingObj), true);
	
	}
	
	@Test
	public void testCourseInsertion() {
		
		db.insert(courseObj);
		assertEquals(db.getCoursesList().contains(courseObj), true);
	
	}
	
	@Test
	public void testGroupInsertion() {
		
		db.insert(groupObj);
		assertEquals(db.getGroupList().contains(groupObj), true);
	
	}
	
	@Test
	public void testProfessorInsertion() {
		
		db.insert(professorObj);
		assertEquals(db.getProfessorList().contains(professorObj), true);
	
	}
	
	@Test
	public void testRoomInsertion() {
		
		db.insert(roomObj);
		assertEquals(db.getRoomList().contains(roomObj), true);
	
	}
	
	@Test
	public void testSessionInsertion() {
		
		db.insert(sessionObj);
		assertEquals(db.getSessionList().contains(sessionObj), true);
	
	}
	
	@Test
	public void testBuildingRemoval() {
		
		db.remove(buildingObj);
		assertNotEquals(db.getBuildingList().contains(buildingObj), true);
	
	}
	
	@Test
	public void testCourseRemoval() {
		
		db.remove(courseObj);
		assertNotEquals(db.getCoursesList().contains(courseObj), true);
	
	}
	
	@Test
	public void testGroupRemoval() {
		
		db.remove(groupObj);
		assertNotEquals(db.getGroupList().contains(groupObj), true);
	
	}
	
	@Test
	public void testProfessorRemoval() {
		
		db.remove(professorObj);
		assertNotEquals(db.getProfessorList().contains(professorObj), true);
	
	}
	
	@Test
	public void testRoomRemoval() {
		
		db.remove(roomObj);
		assertNotEquals(db.getRoomList().contains(roomObj), true);
	
	}
	
	@Test
	public void testSessionRemoval() {
		
		db.remove(sessionObj);
		assertNotEquals(db.getSessionList().contains(sessionObj), true);
	
	}
	
	
	
	/**
	 * Initializes all the objects that will be added to the DB
	 */
	public void prepareTesters() {
		
		buildingObj = new Building("BLD0001");
		courseObj = new Course("CUR", "CUR0001");
		professorObj = new Professor("PRF0001");
		professorObj2 = new Professor("PRF0002");
		professorList = new ArrayList<Professor>();
		professorList.add(professorObj);
		professorList.add(professorObj2);
		featuresList = new ArrayList<Integer>();
		featuresList.add(1);
		featuresList.add(2);
		roomObj = new Room("RM0001", featuresList, 40, true, buildingObj, "Test Room");
		groupObj = new Group(20, professorList, "GRP0001", courseObj);
		sessionObj = new Session(2,2,"13:30",featuresList, groupObj);
		
		
	}

}

