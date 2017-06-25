/**
 * 
 */
package allocator.data;
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

	/**
	 * Class constructor
	 */
	public Database() {
		
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
	
	/**
	 * Gets the list of rooms that belong to a specific building.
	 * @return  a list containing all those groups.
	 */
	public List<Room> getRoomsBuilding(Building building) {
		
		//TODO: IMPOSSIBLE, once a building has only its name and ID.
		//      We should change Building/Room relationship? One building has a room list
		return null;
	}
	
}
