/**
 * 
 */
package allocator.domain;

/**
 * @author felipebertoldo
 *
 */
public class Course {
	private String id;
	private String name;
	/**
	 * 
	 */
	public Course(String name, String id) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.id   = id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getId()
	{
		return this.id;
	}

}
