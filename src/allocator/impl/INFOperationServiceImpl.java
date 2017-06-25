/**
 * 
 */
package allocator.impl;

import java.io.File;

import allocator.INFOperationService;

/**
 * @author felipebertoldo, emilycalvet, marcellotomazi
 * @version %I%, %G%
 */

public class INFOperationServiceImpl implements INFOperationService {

	/**
	 * Class constructor.
	 */
	public INFOperationServiceImpl() {
		
	}

	
	/* (non-Javadoc)
	 * @see allocator.INFOperationService#readXML(java.io.File)
	 */
	@Override
	public void readXML(File inputFile) {
		// TODO 

	}

	/* (non-Javadoc)
	 * @see allocator.INFOperationService#roomAlloc()
	 */
	@Override
	public void roomAlloc() {
		
		Alloc geneticAllocator = new Alloc();
		geneticAllocator.init();
		
		
	}

	/* (non-Javadoc)
	 * @see allocator.INFOperationService#exportXML()
	 */
	@Override
	public File exportXML() {
		// TODO 
		return null;
	}

}
