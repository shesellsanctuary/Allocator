/**
 * 
 */
package util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author felipebertoldo
 *
 */
public class vectorSort {

	private List<Float> vector;
	/**
	 * 
	 */
	public vectorSort(List<Float> vec) {
		
		vector = vec;
	}
	
	private int maxIndex()
	{
		float maior = vector.get(0);
		int greatestIndex = 0;
		
		for(int i = 0; i < vector.size(); i++)
		{
			if(vector.get(i) > maior)
			{
				greatestIndex = i;
				maior = vector.get(i);
			}
		}
		
		vector.remove(greatestIndex);
		return greatestIndex;
	}
	
	public List<Integer> sort()
	{
		List<Integer> sorted = new ArrayList<Integer>();
		for(int i = 0; i < 100; i++)
		{
			sorted.add(i,maxIndex());
		}
		
		return sorted;
	}
}
