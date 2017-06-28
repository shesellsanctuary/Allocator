/**
 * 
 */
package allocator;
import java.io.File;

/**
 * @author felipebertoldo, emilycalvet, marcellotomazi
 * @version %I%, %G%
 */

public interface INFOperationService {
	
	/**
	 * Imports a file, parse its data and add them to the Database.
	 * @param inputFile the file
	 */
	public void readFile(String pathname);

	/**
	 * With the information found on the input file, finds a schedule for the courses and rooms.
	 */
	public void roomAlloc();

	/**
	 * Exports the allocation results to an file, with the same type of the input file.
	 * @return a file containing the allocation results.
	 */
	public File exportFile();
	
}
