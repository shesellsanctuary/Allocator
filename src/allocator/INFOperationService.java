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
	 * Imports a XML file, parse its data and add them to the Database.
	 * @param inputFile the XML file
	 */
	public void readXML(String pathname);

	/**
	 * With the information found on the input file, finds a schedule for the courses and rooms.
	 */
	public void roomAlloc();

	/**
	 * Exports the allocation results to an XML file.
	 * @return a XML file containing the allocation results.
	 */
	public File exportXML();
	
}
