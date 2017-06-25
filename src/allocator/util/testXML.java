/**
 * 
 */
package allocator.util;

/**
 * @author felipebertoldo
 *
 */
public class testXML {

	/**
	 * 
	 */
	public testXML() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XMLFile arq = new XMLFile("./src/allocator/data/full.xml");
		arq.read();

	}

}
