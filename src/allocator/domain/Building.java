/**
 * 
 */
package allocator.domain;

/**
 * @author felipebertoldo, emilycalvet, marcellotomazi
 * @version %I%, %G%
 */

public class Building {

	private String id;
	
	/**
	 * Class constructor specifying the building ID.
	 * @param id the string containing the building's ID.
	 */
	public Building(String id) {
		
		this.id = id;
	}
	
	/**
	 * Gets the ID of the building.
	 * @return  a string containing the building's ID.
	 */
	public String getId() {
		
		return this.id;
	}

}
