/**
 * 
 */
package allocator.impl;

import java.io.File;

import allocator.INFOperationService;
import allocator.data.Database;
import allocator.util.XMLFile;

/**
 * @author felipebertoldo, emilycalvet, marcellotomazi
 * @version %I%, %G%
 */

public class INFOperationServiceImpl implements INFOperationService {

	private Database database;
	private XMLFile xml;
	
	/**
	 * Class constructor.
	 */
	public INFOperationServiceImpl() {
		
		this.database = null;
	}

	
	/* (non-Javadoc)
	 * @see allocator.INFOperationService#readXML(java.io.File)
	 */
	@Override
	public void readXML(String pathname) {
		
		this.xml = new XMLFile(pathname);
		xml.read();
		this.database = xml.getDatabase();
	}

	/* (non-Javadoc)
	 * @see allocator.INFOperationService#roomAlloc()
	 */
	@Override
	public void roomAlloc() {
		
		if (database != null) {
			Alloc geneticAllocator = new Alloc();
			geneticAllocator.init(database);
		}
		else {
			// TODO
		}
		
	}

	/* (non-Javadoc)
	 * @see allocator.INFOperationService#exportXML()
	 */
	@Override
	public File exportXML() {
		if (database != null) {
			// TODO
		}
		else {
			// TODO
		}
		return null;
	}

}
