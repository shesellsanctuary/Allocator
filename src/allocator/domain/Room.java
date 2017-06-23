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
	private boolean avaliableForAllocation;
	private HashMap<String,Boolean> roomMap;
	
	/**
	 * Class constructor specifying the room data.
	 * @param id  						string containing the course ID.
	 * @param features					list containing the features available in this room.
	 * @param numOfPlaces   			the number of places available in this room.
	 * @param avaliableForAllocation	value that indicates whether the room is available to be allocated or not
	 * @param building					the building to which the room belongs.
	 * @param note						string containing the description of the room.
	 */
	public Room(String id, List<Integer> features, int numOfPlaces, boolean avaliableForAllocation, Building building, String note)
	{
		this.id = id;
		this.note = note;
		this.features = features;
		this.building = building;
		this.numOfPlaces = numOfPlaces;
		this.avaliableForAllocation = avaliableForAllocation;
		this.roomMap = new HashMap<String,Boolean>();
	}
	

	public String getID()
	{
		return this.id;
	}
	
	public List<Integer> getFeatures()
	{
		return this.features;
	}
	
	public int getNumberOfPlaces()
	{
		return this.numOfPlaces;
	}
	
	public Boolean getAvaliabilityForAlloc()
	{
		return this.avaliableForAllocation;
	}

	public Building getBuilding()
	{
		return this.building;
	}
	
	public void setAvaliability(Boolean avaliability)
	{
	   this.avaliableForAllocation = avaliability;	
	}
	
	public void alloc(String startTime)
	{
		//TODO implementation
	}
	
	public HashMap<String, Boolean> getRoomMap()
	{
		return this.roomMap;
	}

	public String getNote()
	{
		return note;
	}
	
}
