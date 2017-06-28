/**
 * 
 */
package allocator.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import allocator.data.Database;
import allocator.domain.Session;

/**
 * @author felipebertoldo, emilycalvet, marcellotomazi
 * @version %I%, %G%
 */

public class Alloc {
	
	public static final int populationSize = 100;
	public static final int survivors = 10;
	public static final int sufferedMutation = 40;
	public static final int maxIterations = 1500;
	private List<Schedule> newPopulation;
	private List<Schedule> currentPopulation;
	private List<Float> currentPopulationFitness;
	private int solutionIndex;
	private int roomCount;
	private Database database;
	
	/**
	 * Class constructor
	 */
	public Alloc() {
		newPopulation = new ArrayList<Schedule>();
		currentPopulation = new ArrayList<Schedule>();
		currentPopulationFitness = new ArrayList<Float>();
	}
	
	public int getSolutionIndex(){
		return solutionIndex;
	}
	
	public List<Schedule> getCurrentPopulation(){
		return currentPopulation;
	}
	/**
	 * Initializes the genetic algorithm.
	 */
	public void init(Database database)
	{
		this.database= database;
		roomCount = database.getRoomList().size();
		createNewPopulation();
		populationFitnessCalc();
		int found = checkValidSolution();
		if(found != -1)
		{
			// One of the schedules in the current population has an fitnessValue equal to 1. We found a solution!	
			solutionIndex = found;
			return;
		}
		
		for(int i = 0; i < maxIterations; i++) 
		{
			transferTenBestSchedulesToNewPopulation();
			crossover();
			mutation();
			currentPopulation = newPopulation;
			populationFitnessCalc();
			found = checkValidSolution();
			if(found != -1)
			{
				// One of the schedules in the current population has an fitnessValue equal to 1. We found a solution!		
				solutionIndex = found;
				return;
			}	
		}
		solutionIndex = -1;
		return;
	}

	
	/**
	 * Creates a initial population with populationSize size. 
	 * To do this, it calls ** METODO DA SCHEDULE **
	 */
	private void createNewPopulation() {

		// Creates #populationSize possible solutions
		for(int i = 0; i < populationSize; i++)
		{
			Schedule newOne = new Schedule(database);			
			newOne.createSolution();
			currentPopulation.add(newOne);
		}
	}
	

	/**
	 * Calculate the fitness of every member of the population, and adds, at the currentPopulationFitness vector corresponding index, the fitness value.
	 * 
	 */
	private void populationFitnessCalc() {
		currentPopulationFitness = new ArrayList<Float>();
		
		for(int i = 0; i < populationSize; i++)
		{
			Schedule sched = currentPopulation.get(i);
			sched.calculateFitness();
			float fitness = sched.getFitness();
			currentPopulationFitness.add(fitness);
		}
	}
	
	/**
	 * Check whether a solution is valid (solution.fitness == 1), and returns its index.
	 * @return  index of a valid solution. -1 otherwise.
	 */
	private int checkValidSolution() {		
		for(int i = 0; i < populationSize; i++)
		{
			if(currentPopulationFitness.get(i) == 1)
			{
				return i;
			}
		}
		return -1;
	}


	/**
	 * TODO
	 * Insert top10 solutions in the new population to be generated
	 * 
	 */
	private void transferTenBestSchedulesToNewPopulation(){
	
		int index = -1;
			
		for(int i = 0; i < 10; i++)
		{
			
			index = findMaxValueList();
			newPopulation.add(currentPopulation.get(index));
		}
	}
	
	
	/*
	 * Takes two schedules from new population, creates a Child from the Father(first one) and iterates on the Father's sessionMap's size
	 * randomly inserting random number of the Mother's(second) sessionMap's values on the Child's.
	 * The Child is inserted in the new population.
	 */
	private void crossover()
	{
	
		int slot = -1, randomInt = -1, randomCrossoverNumber = -1;
		Schedule scheduleFather, scheduleMother;
		Session randomSession;
		String randomSessionId;
		HashMap<String,Integer> sessionMapChild, sessionMapMother;
		Random randomGenerator = new Random();
		
		for(int i = 0; i < populationSize; i += 2)
		{
			
			Schedule scheduleChild = new Schedule(database);
			
			scheduleFather = currentPopulation.get(i);
			scheduleMother = currentPopulation.get(i+1);
			scheduleChild = scheduleFather;
			
			sessionMapMother = scheduleMother.getSessionMap();
			sessionMapChild = scheduleChild.getSessionMap();
			
			randomCrossoverNumber = randomGenerator.nextInt(scheduleFather.getSessionMap().size());
			
			for(int j = 0; j < randomCrossoverNumber; j++)
			{
				
				randomInt = randomGenerator.nextInt(database.getSessionList().size() - 1);
				randomSession = database.getSessionList().get(randomInt);
				randomSessionId = randomSession.getId();
				
				slot = sessionMapMother.get(randomSessionId);
				
				sessionMapChild.put(randomSessionId, slot);
			}
			
			scheduleChild.setSessionMap(sessionMapChild);			
			newPopulation.add(scheduleChild); // Child Schedule, originated from the crossover of two other Schedules	
		}
	}

	/*
	 * Takes two random indexes out of sessionList size of the schedule and swaps their values of the sessionMap
	 */
	private void mutation()
	{
		int slot1 = -1, slot2 = -1, randomInt1 = -1, randomInt2 = -1, weekday = -1, startTime;
		Session randomSession1, randomSession2;
		String randomSessionId1, randomSessionId2;
		Random randomGenerator = new Random();
		Schedule schedule;
		HashMap<String,Integer> sessionMap;
		List<Integer> possibleSlotsSchedule = new ArrayList<Integer>();
		
		for (int i = 0; i < 40; i++)
		{
			schedule = currentPopulation.get(i);
			sessionMap = schedule.getSessionMap();
					
			randomInt1 = randomGenerator.nextInt(database.getSessionList().size() - 1);
			randomSession1 = database.getSessionList().get(randomInt1);
			
			randomSessionId1 = randomSession1.getId();
			
			startTime = schedule.discoverStartTime(randomSession1.getStartTime());
			weekday = randomSession1.getWeekday();
			
			possibleSlotsSchedule = createListOfPossibleSlotsMutation(startTime, weekday);
			
			randomInt2 = randomGenerator.nextInt(possibleSlotsSchedule.size() - 1);	
			randomSession2 = database.getSessionList().get(possibleSlotsSchedule.get(randomInt2));
			randomSessionId2 = randomSession2.getId();
				
			slot1 = sessionMap.get(randomSessionId1);
			slot2 = sessionMap.get(randomSessionId2);
				
			sessionMap.put(randomSessionId1, slot2);
			sessionMap.put(randomSessionId2, slot1);
			
			schedule.setSessionMap(sessionMap);	
			
			newPopulation.add(schedule); // Mutated Schedule
					
		}
	}
	
	private List<Integer> createListOfPossibleSlotsMutation(int startTime, int weekday) {
		
		int firstSlot =  roomCount*4*(weekday-1) + startTime;
		int lastSlot = firstSlot + roomCount*4;
		List<Integer> possibleSlotsSchedule = new ArrayList<Integer>();
		
		for (int slot = firstSlot; slot < lastSlot; slot =+ 4) {
					
			possibleSlotsSchedule.add(slot);
		}
		
		return possibleSlotsSchedule;
	}
	
	private int findMaxValueList() {
		int index = -1;
		float largest = -1;
		float invalid = -1;
		
		for (int i = 0; i < currentPopulationFitness.size(); i++)
		{
		    if ( currentPopulationFitness.get(i) > largest )
		    {
		        largest = currentPopulationFitness.get(i);
		        index = i;
		    }
		}
		
		currentPopulationFitness.add(index, invalid);
		
		return index;	
	}
}
