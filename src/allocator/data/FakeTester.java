/**
 * 
 */
package allocator.data;

import allocator.impl.INFOperationServiceImpl;

/**
 * @author felipebertoldo
 *
 */
public class FakeTester {

	/**
	 * 
	 */
	public FakeTester() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		INFOperationServiceImpl teste = new INFOperationServiceImpl();
		teste.readFile("C:/Users/Marcello/Desktop/TCP/Allocator/src/allocator/data/short.xml");
	    teste.roomAlloc();
	    System.out.println("SUUUUCESSO");

	}

}
