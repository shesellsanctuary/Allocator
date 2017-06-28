/**
 * 
 */
package allocator.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import allocator.data.Database;
import allocator.domain.Session;
import allocator.util.vectorSort;

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
		createNewPopulation();
		populationFitnessCalc();
		int found = checkValidSolution();
		if(found != -1)
		{
			//TODO ACHHHHHHOU		
			solutionIndex = found;
			return;
		}
		
		for(int i = 0; i < maxIterations; i++) 
		{
			transferTenBestSchedulesToNewPopulation();
			crossover();
			mutation();
			currentPopulation = newPopulation;
			found = checkValidSolution();
			if(found != -1)
			{
				//TODO ACHHHHHHOU		
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
	 * Insert solutions with fitness over 0.5 in the new population to be generated
	 * 
	 */
	private void transferTenBestSchedulesToNewPopulation(){
	
		vectorSort preSorted = new vectorSort(currentPopulationFitness);
		
		List<Integer> sorted = preSorted.sort();
		
		for(int i = 0; i < 10; i++)
		{
			newPopulation.add(currentPopulation.get(sorted.get(i))); //TODO LINDO! <3
		}
	
	}
	
	
	/*
	 * Takes two schedules from new population, creates a Child from the Father(first one) and iterates on the Father's sessionMap's size
	 * randomly inserting random number of the Mother's(second) sessionMap's values on the Child's.
	 * The Child is inserted in the new population.
	 */
	private void crossover()
	{
		vectorSort preSorted = new vectorSort(currentPopulationFitness);//TODO deixei isso aqui, mas acho que da pra tirar
		List<Integer> sorted = preSorted.sort();
		
		int slot = -1, randomInt = -1, randomCrossoverNumber = -1;
		Schedule scheduleFather, scheduleMother, scheduleChild;
		Session randomSession;
		String randomSessionId;
		Random randomGenerator = new Random();
		
		for(int i = 0; i < populationSize; i += 2)
		{
			
			scheduleFather = currentPopulation.get(i);
			scheduleMother = currentPopulation.get(i+1);
			scheduleChild = scheduleFather;
			randomCrossoverNumber = randomGenerator.nextInt(scheduleFather.sessionMap.size());
			
			for(int j = 0; j < randomCrossoverNumber; j++)
			{
				
				randomInt = randomGenerator.nextInt(scheduleMother.sessionList.size() - 1);
				randomSession = scheduleMother.sessionList.get(randomInt);
				randomSessionId = randomSession.getId();
				
				slot = scheduleMother.sessionMap.get(randomSessionId);
				
				scheduleChild.sessionMap.put(randomSessionId, slot);

			}
			
			newPopulation.add(scheduleChild); //FILHO
			
		}
	}

	/*
	 * Takes two random indexes out of sessionList size of the schedule and swaps their values of the sessionMap
	 */
	private void mutation()
	{
		int slot1 = -1, slot2 = -1, randomInt1 = -1, randomInt2 = -1;
		Session randomSession1, randomSession2;
		String randomSessionId1, randomSessionId2;
		Random randomGenerator = new Random();
		Schedule schedule;
		
		for (int i = 0; i < 40; i++)
		{
			 schedule = currentPopulation.get(i);
					
					
				randomInt1 = randomGenerator.nextInt(schedule.sessionList.size() - 1);
				randomSession1 = schedule.sessionList.get(randomInt1);
				randomSessionId1 = randomSession1.getId();
					
				randomInt2 = randomGenerator.nextInt(schedule.sessionList.size() - 1);	
				randomSession2 = schedule.sessionList.get(randomInt2);
				randomSessionId2 = randomSession2.getId();
					
				slot1 = schedule.sessionMap.get(randomSessionId1);
				slot2 = schedule.sessionMap.get(randomSessionId2);
					
				schedule.sessionMap.put(randomSessionId1, slot2);
				schedule.sessionMap.put(randomSessionId2, slot1);
					
		}
	}
}
