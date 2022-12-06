package main.unit_testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import main.trees.BinaryForest;

/**
 * Test the methods of the {@link BinaryForest} class
 * 
 * @author CarltheSpiny
 *
 */
class BinaryForestTest {

	/*
	 * Test these methods:
	 * 		getDFTTree()
	 * 		getBFTTree()
	 */
	@Test
	void test() {
		BinaryForest forestTest = new BinaryForest();
		BinaryForest dupForest = new BinaryForest();
		
		// Should equal eachother as they are from same class
		assertEquals(4, forestTest.getDFTTree().getHeight());
		assertNotEquals(forestTest.getDFTTree(), dupForest.getBFTTree());
		
		assertEquals(4, forestTest.getBFTTree().getHeight());
		assertNotEquals(forestTest.getBFTTree(), dupForest.getDFTTree());
		
		assertEquals(9, forestTest.getBFTTree().getNumberOfNodes());
	}

}
