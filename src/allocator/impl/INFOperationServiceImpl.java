/**
 * 
 */
package allocator.impl;

import java.io.File;

import allocator.INFOperationService;
import allocator.data.Database;
import allocator.util.FileRep;

/**
 * @author felipebertoldo, emilycalvet, marcellotomazi
 * @version %I%, %G%
 */

public class INFOperationServiceImpl implements INFOperationService {

	private Database database;
	private FileRep file;
	
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
	public void readFile(String pathname) {
		
		this.file = new FileRep(pathname);
		file.read();
		this.database = file.getDatabase();
	}

	/* (non-Javadoc)
	 * @see allocator.INFOperationService#roomAlloc()
	 */
	@Override
	public void roomAlloc() {
		
		int solutionFound;
		
		if (database != null) {
			Alloc geneticAllocator = new Alloc();
			geneticAllocator.init(database);

			solutionFound = geneticAllocator.getSolutionIndex();
			
			if (solutionFound != -1) {
				
				System.out.println("Iuhuul! A perfect solution was found! Sounds like everything will be normal this semester.");
			}
			else {
				
				System.out.println("Ouch! Some classes will not have rooms this semester. Its time to give teachers a vacation...");
			}
			
		}
		else {
			// roomAlloc was called before readFile, or the database was not initialized successfully
			System.out.println("Oops! There is nothing in our database! We need an input file...");
		}
		
	}

	/* (non-Javadoc)
	 * @see allocator.INFOperationService#exportXML()
	 */
	@Override
	public File exportFile() {
		if (database != null) {
			// TODO
		}
		else {
			// TODO
		}
		return null;
	}

}
