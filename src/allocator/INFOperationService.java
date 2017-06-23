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
	 * Imports a XML file that will be parsed and will have its data added to the database.
	 * @param inputFile the XML file
	 */
	public void readXML(File inputFile);

	/**
	 * The Room Allocator
	 */
	public void roomAlloc();

	/**
	 * Exports the allocation results to an XML file.
	 * @return a XML file containing the allocation results.
	 */
	public File exportXML();
	
}
