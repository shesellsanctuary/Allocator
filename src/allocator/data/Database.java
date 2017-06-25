/**
 * 
 */
package allocator.data;
import java.util.ArrayList;
import java.util.List;

import allocator.domain.*;

/**
 * @author felipebertoldo, emilycalvet, marcellotomazi
 * @version %I%, %G%
 */

public class Database {

	private List<Room>      roomList;
	private List<Group>     groupList;
	private List<Course>    coursesList;
	private List<Session>   sessionList;
	private List<Building>  buildingsList;
	private List<Professor> professorsList;
	
	private static final String T_ROOM = "allocator.domain.Room";
	private static final String T_GROUP = "allocator.domain.Group";
	private static final String T_COURSE = "allocator.domain.Course";
	private static final String T_SESSION = "allocator.domain.Session";
	private static final String T_BUILDING = "allocator.domain.Building";
	private static final String T_PROFESSOR = "allocator.domain.Professor";

	/**
	 * Class constructor
	 */
	public Database() {
		
		this.roomList = new ArrayList<Room>();
		this.groupList = new ArrayList<Group>();
		this.coursesList = new ArrayList<Course>();
		this.sessionList = new ArrayList<Session>();
		this.buildingsList = new ArrayList<Building>();
		this.professorsList = new ArrayList<Professor>();
		
	}

	/**
	 * Inserts newData to the Database, at the right place.
	 * @param newData object that will be added.
	 */
	public void insert(Object newData) {
		
		String objType = newData.getClass().getName();
		
		switch(objType) {
		
			case T_ROOM: 
				this.roomList.add((Room) newData); break;
			case T_GROUP:
				this.groupList.add((Group) newData); break;
			case T_COURSE:
				this.coursesList.add((Course) newData); break;
			case T_SESSION:
				this.sessionList.add((Session) newData); break;
			case T_BUILDING:
				this.buildingsList.add((Building) newData); break;
			case T_PROFESSOR:
				this.professorsList.add((Professor) newData); break;
		}
	}
	
	/**
	 * Removes trashData from the Database.
	 * @param trashData object that will be removed.
	 */
	public void remove(Object trashData) {
		
		String objType = trashData.getClass().getName();
		
		switch(objType) {
		
			case T_ROOM: 
				this.roomList.remove((Room) trashData); break;
			case T_GROUP:
				this.groupList.remove((Group) trashData); break;
			case T_COURSE:
				this.coursesList.remove((Course) trashData); break;
			case T_SESSION:
				this.sessionList.remove((Session) trashData); break;
			case T_BUILDING:
				this.buildingsList.remove((Building) trashData); break;
			case T_PROFESSOR:
				this.professorsList.remove((Professor) trashData); break;
		}
	}
	
	/**
	 * Gets the list of professors stored in the database.
	 * @return  a list containing the professors.
	 */
	public List<Professor> getProfessorList() {
		
		return this.professorsList;
	}
	
	/**
	 * Gets the list of buildings stored in the database.
	 * @return  a list containing all the buildings.
	 */
	public List<Building> getBuildingList() {
		
		return this.buildingsList;
	}
	
	/**
	 * Gets the list of rooms stored in the database.
	 * @return  a list containing all the rooms.
	 */
	public List<Room> getRoomList() {
		
		return this.roomList;
	}
	
	/**
	 * Gets the list of sessions stored in the database.
	 * @return  a list containing all the sessions.
	 */
	public List<Session> getSessionList() {
		
		return this.sessionList;
	}
	
	/**
	 * Gets the list of courses stored in the database.
	 * @return  a list containing all the courses.
	 */
	public List<Course> getCoursesList() {
		
		return this.coursesList;
	}
	
	/**
	 * Gets the list of groups stored in the database.
	 * @return  a list containing all the groups.
	 */
	public List<Group> getGroupList() {
		
		return this.groupList;
	}

	/**
	 * Gets the list of groups that belong to a specific course.
	 * @return  a list containing all those groups.
	 */
	public List<Group> getGroupsCourse(Course course) {
		
		//TODO: how to get all the groups of a course if group hasn't a Course attribute?
		//		we need to check that! @see Groups.java
		return null;
	}
	
	/**
	 * Gets the list of sessions that belong to a specific course.
	 * @return  a list  containing all those sessions.
	 */
	public List<Session> getSessionsGroup(Group group) {
		
		return group.getSessions();
	}
	
	
}
