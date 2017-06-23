/**
 * 
 */
package allocator;

import java.io.File;

/**
 * @author felipebertoldo
 *
 */
public interface INFOperationService {
	
	public void readXML(File inputFile);

	
	public void roomAlloc();

	public File exportXML();
	

}
