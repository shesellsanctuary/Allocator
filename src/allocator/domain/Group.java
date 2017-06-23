/**
 * 
 */
package allocator.domain;

import java.util.List;

/**
 * @author felipebertoldo
 *
 */
public class Group {
	private int numberOfStudents;
	private List<Professor> teacher;
	private String id;
	private Course course;
	private List<Session> session;
	
	/**
	 * 
	 */
	public Group(int numberOfStudents, Professor teacher, String id, Course course) {
		// TODO Auto-generated constructor stub
		this.numberOfStudents = numberOfStudents;
		//add PROFESSOR on the list
		this.id = id;
		this.course = course;
		
	}
	
	public int getNumberOfStudents()
	{
		return this.numberOfStudents;
	}
	/* 
	public Professor getTeacher()
	{
		// What to return?
		
	}
	*/
	public Course getCourse()
	{
		return this.course;
	}
	
	public String getId()
	{
		return this.id;
	}
	
	public void addSession(Session session)
	{
		// APPEND session
	}

}
