/**
 * 
 */
package allocator.domain;

import java.util.List;

/**
 * @author felipebertoldo, emilycalvet, marcellotomazi
 * @version %I%, %G%
 */

public class Session {

	private Room room;
	private int weekday;
	private int duration;
	private String startTime;
	private List<Integer> features;
	
	/**
	 * Class constructor specifying the session data.
	 * @param weekday		the day (2:monday - 6:friday) of the week when the session happens.
	 * @param startTime		the session start time (e.g.: 13:30)
	 * @param features		a list of available features.
	 */
	public Session(int weekday, String startTime, List<Integer> features) 
	{
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
