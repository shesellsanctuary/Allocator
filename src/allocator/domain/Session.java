/**
 * 
 */
package allocator.domain;

import java.util.List;

/**
 * @author felipebertoldo
 *
 */
public class Session {

	private Room room;
	private int weekday;
	private int duration;
	private String startTime;
	private List<Integer> features;
	
	/**
	 * 
	 */
	public Session(int weekday, String startTime, List<Integer> features) {
		// TODO Auto-generated constructor stub
		this.weekday = weekday;
		this.startTime = startTime;
		this.features = features;
	}
	
	public void setRoom(Room room)
	{
		this.room = room;
	}
	
	public Room getRoom()
	{
		return this.room;
	}
	
	public int getWeekday()
	{
		return this.weekday;
	}
	
	public String getStartTime()
	{
		return this.startTime;
		
	}
	
	public List<Integer> getFeaturesList()
	{
		return this.features;
	}

}
