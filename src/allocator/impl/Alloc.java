/**
 * 
 */
package allocator.impl;

import java.util.ArrayList;
import java.util.List;

import allocator.data.Database;
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
	protected Database database;
	
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
			// salvar xml
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
				// salvar xml
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
	
	private void crossover()
	{
		vectorSort preSorted = new vectorSort(currentPopulationFitness);
		List<Integer> sorted = preSorted.sort();
		
		for(int i = 0; i < populationSize; i += 2)
		{
			//TODO cruzamento
			newPopulation.add(null); //FILHO
			
		}
	 
	}

	
	private void mutation()
	{
		for(int i = 0; i < 40; i++)
		{
			
		}
	}
}