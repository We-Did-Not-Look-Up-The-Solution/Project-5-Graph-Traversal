package main.unit_testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.Test;

import main.adt.LinkedDictionary;
import main.stack_and_queues.EmptyQueueException;

class LinkedDictionaryTest {

	/**
	 * Test these methods:
	 * 		initializeDataFields()
	 * 		isEmpty()
	 * 		getSize()
	 * 		clear() (May need to be corrected!)	
	 * 		
	 * Test these methods if you want:
	 * 		getKeyIterator()
	 * 		getValueIterator()
	 * 		and the classes they use (KeyIterator and ValueIterator)
	 */
	@Test
	void testDictionary() {
		LinkedDictionary<Character, Integer> dictionary = new LinkedDictionary<>();
		
		ThrowingRunnable throwTest = new ThrowingRunnable() {
			
			@Override
			public void run() throws Throwable {
				dictionary.getValue('a');
			}
		};
		
		assertThrows(NullPointerException.class, throwTest);
		
		assertEquals(true, dictionary.isEmpty());
		
		dictionary.add('a', 1);
		dictionary.add('b', 3);
		dictionary.add('c', 5);
		dictionary.add('d', 6);
		dictionary.add('e', 9);
		
		assertEquals(false, dictionary.isEmpty());
		
		assertEquals(5, dictionary.getSize());
		
		LinkedDictionary<Integer, Character> testDictionary = new LinkedDictionary<>();
		
		int counter = 0;
		for (int i = 0; i < 100; i++) {
			testDictionary.add(counter, 'a');
			counter++;
		}
		
		assertEquals(100, testDictionary.getSize());
		
		testDictionary.clear();
		
		assertEquals(true, testDictionary.isEmpty());
		
		
	}

}
