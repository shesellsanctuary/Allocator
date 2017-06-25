/**
 * 
 */
package allocator.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author felipebertoldo, emilycalvet, marcellotomazi
 * @version %I%, %G%
 */

public class Schedule {
	
	private int mutationSize;
	private float fitnessValue;
	private List<Integer> meetCriteria;
	private List<Integer> scheduleAllocation;
	private HashMap<String,Integer> classMap;
	private int numberOfCrossoverPoints;
//	private float crossoverProbability;
	//private float mutationProbability;
	
	/**
	 * Class constructor
	 */
	public Schedule() {
		
		meetCriteria = new ArrayList<Integer>();
		scheduleAllocation = new ArrayList<Integer>();
		classMap = new HashMap<String,Integer>();
	}
	
	public void createSchedule()
	{
		//TODO
	}

}
