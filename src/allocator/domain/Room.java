/**
 * 
 */

package allocator.domain;
import java.util.HashMap;
import java.util.List;

/**
 * @author felipebertoldo, emilycalvet, marcellotomazi
 * @version %I%, %G%
 */

public class Room {

	private String id;
	private String note;
	private int numOfPlaces;
	private Building building;
	private List<Integer> features;
	private boolean availableForAllocation;
	private HashMap<String,Boolean> roomMap;
	
	/**
	 * Class constructor specifying the room data.
	 * @param id						string containing the course ID.
	 * @param features					list containing the features available in this room.
	 * @param numOfPlaces				the number of places available in this room.
	 * @param availableForAllocation	value that indicates whether the room is available to be allocated or not
	 * @param building					the building to which the room belongs.
	 * @param note						string containing the description of the room.
	 */
	public Room(String id, List<Integer> features, int numOfPlaces, boolean availableForAllocation, Building building, String note)
	{
		this.id = id;
		this.note = note;
		this.features = features;
		this.building = building;
		this.numOfPlaces = numOfPlaces;
		this.availableForAllocation = availableForAllocation;
		this.roomMap = new HashMap<String,Boolean>();
	}
	

	/**
	 * Gets the room ID.
	 * @return  the string containing the room ID.
	 */
	public String getID() {
		
		return this.id;
	}
	
	/**
	 * Gets all the room features.
	 * @return  the list with every room features.
	 */
	public List<Integer> getFeatures() {
		
		return this.features;
	}
	
	/**
	 * Gets the number of seats the room has
	 * @return  the number of seats
	 */
	public int getNumberOfPlaces() {
		
		return this.numOfPlaces;
	}
	
	/**
	 * Returns whether the room is available or not
	 * @return  the bool value that indicates the room is allocable or not
	 */
	public Boolean getAvaliabilityForAlloc() {
		
		return this.availableForAllocation;
	}

	/**
	 * Gets the building to which the room belongs
	 * @return  the building to which the room belongs
	 */
	public Building getBuilding() {
		
		return this.building;
	}
	
	/**
	 * Sets the availability of the room 
	 * @param availability  true for available, false otherwise
	 */
	public void setAvailability(Boolean availability) {
		
	   this.availableForAllocation = availability;	
	}
	
	/**
	 * Allocates the room
	 * @param startTime  string that contains the start time of the activity that will occupy the room
	 */
	public void alloc(String startTime) {
		
		//TODO implementation
	}
	
	/**
	 * Gets the RoomMap TODO: a better comment
	 * @return RoomMap
	 */
	public HashMap<String, Boolean> getRoomMap() {
		
		return this.roomMap;
	}

	/**
	 * Gets the description of the room
	 * @return  the string with the description 
	 */
	public String getNote() {
		
		return note;
	}
	
}
