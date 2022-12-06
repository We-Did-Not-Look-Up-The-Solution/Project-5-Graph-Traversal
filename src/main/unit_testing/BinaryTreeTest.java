package main.unit_testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import main.trees.BinaryForest;
import main.trees.BinaryTree;

/**
 * Tests the class {@link main.trees.BinaryTree}
 * @author wwwyv
 *
 */
class BinaryTreeTest {
	
	/**
	 * Test these methods:
	 * 		isEmpty()
	 * 		clear()
	 * 		and all the Iterators
	 */

	@Test
	void test() {
		// Make a tree with BinaryForest
		BinaryForest testForest = new BinaryForest();
		
		BinaryTree<Character> emptyTest = testForest.getBFTTree();
		assertEquals(false, emptyTest.isEmpty());
		emptyTest.clear();
		assertEquals(true, emptyTest.isEmpty());
		
		BinaryTree<Character> iteratorTest = testForest.getDFTTree();
		
		// AssertLinesMatch
		// AssertIterableEquals
		Iterable<Character> inorderTest = new ArrayList<>(Arrays.asList('e', 'b', 'f', 'a', 'i', 'h', 'd', 'c', 'g'));
		Iterable<Character> levelorderTest = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'e', 'f', 'h', 'g', 'i', 'd'));
		Iterable<Character> preorderTest = new ArrayList<>(Arrays.asList('a', 'b', 'e', 'f', 'c', 'h', 'i', 'd', 'g'));
		
	}

}
