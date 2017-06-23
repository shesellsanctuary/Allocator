/**
 * 
 */
package allocator.domain;

/**
 * @author felipebertoldo, emilycalvet, marcellotomazi
 * @version %I%, %G%
 */

public class Course {
	
	private String id;
	private String name;
	
	/**
	 * Class constructor specifying the course name and its ID.
	 * @param name the string containing the course name.
	 * @param id   the string containing the course ID.
	 */
	
	public Course(String name, String id) {
		
		this.name = name;
		this.id   = id;
	}
	
	/**
	 * Gets the name of the course.
	 * @return a string containing the course name.
	 */
	public String getName() {
		
		return this.name;
	}
	
	/**
	 * Gets the ID of the course.
	 * @return a string containing the course ID.
	 */
	public String getId() {
		
		return this.id;
	}

}
