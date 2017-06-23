/**
 * 
 */
package allocator.domain;

/**
 * @author felipebertoldo, emilycalvet, marcellotomazi
 * @version %I%, %G%
 */

public class Professor {

	private String name;

	/**
	 * Class constructor specifying  the professor name.
	 * @param name string with the professor name
	 */
	public Professor(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the name of the professor.
	 * @return  the string containing the professor name.
	 */
	public String getName()
	{
		return this.name;
	}

}
