/**
 * 
 */
package allocator.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author felipebertoldo
 *
 */
public class FileRepTest {

	FileRep tester;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		tester = new FileRep("./src/allocator/data/inputTest.xml");
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
