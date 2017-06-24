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
	 * @param duration		how long the session lasts
	 * @param startTime		the session start time (e.g.: 13:30)
	 * @param features		a list of available features.
	 */
	public Session(int weekday, int duration, String startTime, List<Integer> features) 
	{
		this.weekday = weekday;
		this.duration = duration;
		this.startTime = startTime;
		this.features = features;
	}
	
	/**
	 * Sets the room where the session will take place
	 * @param room  the room
	 */
	public void setRoom(Room room)
	{
		this.room = room;
	}
	
	/**
	 * Gets the room where the session takes place
	 * @return  the room
	 */
	public Room getRoom()
	{
		return this.room;
	}
	
	/**
	 * Gets the weekday when the session happens 
	 * (2: monday - 6: friday)
	 * @return  the number that indicates the weekday
	 */
	public int getWeekday()
	{
		return this.weekday;
	}
	
	/**
	 * Gets the start time of the session (e.g.: 13:30)
	 * @return  string containing the start time
	 */
	public String getStartTime()
	{
		return this.startTime;
		
	}
	
	/**
	 * Gets the list of features that the session needs
	 * @return  the list with all the features
	 */
	public List<Integer> getFeaturesList()
	{
		return this.features;
	}
	
	/**
	 * Gets the number that shows how long the session takes
	 * @return  the duration
	 */
	public int getDuration()
	{
		return this.duration;
	}

}
