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

public class Building {

	private String id;
	private List<Room> rooms; 
	
	/**
	 * Class constructor specifying the building ID.
	 * @param id the string containing the building's ID.
	 */
	public Building(String id) {
		
		this.id = id;
		this.rooms = new ArrayList<Room>();
	}
	
	/**
	 * Gets the ID of the building.
	 * @return  a string containing the building's ID.
	 */
	public String getId() {
		
		return this.id;
	}
	
	/**
	 * TODO
	 * @return
	 */
	public List<Room> getRoomList() {
		
		return this.rooms;
	}
	
	/**
	 * TODO
	 */
	public void addRoom(Room newRoom) {
		
		rooms.add(newRoom);
	}

}
