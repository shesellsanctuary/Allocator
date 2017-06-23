/**
 * 
 */
package allocator.data;

import java.util.List;

import allocator.domain.*;

/**
 * @author emilycalvet, felipebertoldo, marcellotomazi
 *
 */
public class Database {

	private List<Room>      roomList;
	private List<Group>     groupList;
	private List<Course>    coursesList;
	private List<Session>   sessionList;
	private List<Building>  buildingsList;
	private List<Professor> professorsList;

	public Database() {}

	public List<Professor> getProfessorList()
	{
		return this.professorsList;
	}
	
	public List<Building> getBuildingList()
	{
		return this.buildingsList;
	}
	
	public List<Room> getRoomList()
	{
		return this.roomList;
	}
	
	public List<Session> getSessionList()
	{
		return this.sessionList;
	}
	
	public List<Course> getCoursesList()
	{
		return this.coursesList;
	}
	
	public List<Group> getGroupList()
	{
		return this.groupList;
	}
/*	Those 3 methods are commented to silence the "no return" warning
	public List<Group> getGroupsCourse(Course course)
	{
		//TODO implement this
		
	}
	
	public List<Session> getSessionsGroup(Group group)
	{
		//implement
	}
	
	public List<Room> getRoomsBuilding(Building building)
	{
		//implement
	}
	*/
}
