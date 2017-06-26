/**
 * 
 */

package allocator.domain;
import java.util.ArrayList;
import java.util.List;

/**
 * @author felipebertoldo, emilycalvet, marcellotomazi
 * @version %I%, %G%
 */

public class Group {
	
	private String id;
	private Course course;
	private int numberOfStudents;
	private List<Session> sessions;
	private List<Professor> teachers;
	
	/**
	 * Class constructor specifying the data of a single group.
	 * @param numberOfStudents the number of students in this course
	 * @param teachers		   a list of professors who teach this course.
	 * @param id			   the group id
	 * @param course      	   the course to which this group belongs.
	 */
	public Group(int numberOfStudents, List<Professor> teachers, String id, Course course) {

		this.id = id;
		this.course = course;
		this.teachers = teachers;
		this.sessions = new ArrayList<Session>();
		this.numberOfStudents = numberOfStudents;
	}
	
	/**
	 * Gets the number of students in this course.
	 * @return the number of students in this course.
	 */
	public int getNumberOfStudents() {
		
		return this.numberOfStudents;
	}
	
	/**
	 * Gets the list of professors who teach this course.
	 * @return  the list of professors who teach this course.
	 */
	public List<Professor> getTeacher() {
		
		return this.teachers;	
	}

	/**
	 * Gets the course to which this group belongs.
	 * @return  the course to which this group belongs.
	 */
	public Course getCourse() {
		
		return this.course;
	}
	
	/**
	 * Gets the group ID.
	 * @return  the string containing the group ID.
	 */
	public String getId() {
		
		return this.id;
	}
	
	/**
	 * Gets all the sessions of the group.
	 * @return  the list containing the sessions of the group
	 */
	public List<Session> getSessionsList() {
		
		return this.sessions;
	}
	
	/**
	 * Appends a new session to the session list of a course.
	 * @param newSession the session that will be appended.
	 */
	public void addSession(Session newSession) {
		
		sessions.add(newSession);
	}

}
