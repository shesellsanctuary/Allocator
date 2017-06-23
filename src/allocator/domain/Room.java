/**
 * 
 */
package allocator.domain;

import java.util.HashMap;
import java.util.List;

/**
 * @author felipebertoldo
 *
 */
public class Room {

	private String id;
	private List<Integer> features;
	private int numOfPlaces;
	private boolean avaliableForAllocation;
	private Building building;
	private String note;
	private HashMap<String,Boolean> roomMap;
	
	/**
	 * 
	 */
	public Room(String id, List<Integer> features, int numOfPlaces, boolean avaliableForAllocation, Building building, String note) {
		this.id = id;
		this.features = features;
		this.numOfPlaces = numOfPlaces;
		this.avaliableForAllocation = avaliableForAllocation;
		this.building = building;
		this.note = note;
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
		System.out.println( "teste");
		// implementar
	}
	
	public HashMap<String, Boolean> getRoomMap()
	{
		return this.roomMap;
	}

	public void setNote(String note)
	{
		return;
	}
	
}
