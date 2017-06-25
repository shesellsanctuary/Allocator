/**
 * 
 */
package allocator.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import allocator.data.Database;
import allocator.domain.Room;

/**
 * @author felipebertoldo, emilycalvet, marcellotomazi
 * @version %I%, %G%
 */

public class Schedule {
	
	private int mutationSize;
	private float fitnessValue;
	private List<Integer> meetCriteria;
	private List<Integer> scheduleAllocation;
	private HashMap<String,Integer> sessionMap;
	private int numberOfCrossoverPoints;
	private int roomCount;
	private Database database;
	
	/**
	 * Class constructor
	 */
	public Schedule(Database db) {
	
		meetCriteria = new ArrayList<Integer>();
		scheduleAllocation = new ArrayList<Integer>();
		sessionMap = new HashMap<String,Integer>();
		database = db;
	}
	
	/**
	 * TODO
	 */
	public void createSolution() {
		
		
	}
	
	/**
	 * TODO
	 */
	public void calculateFitness() {
		//TODO
	}
	
	/**
	 * TODO
	 * @return
	 */
	public float getFitness() {
		return this.fitnessValue;
	}

}
