package main.unit_testing;

import static org.junit.Assert.assertThrows;

import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.Test;

import main.trees.EmptyTreeException;

class EmptyTreeExceptionTest {

	@Test
	void test() {
		ThrowingRunnable throwTest = new ThrowingRunnable() {
			
			@Override
			public void run() throws Throwable {
				throw new EmptyTreeException("Tree was Empty!");
			}
		};
		
		assertThrows(EmptyTreeException.class, throwTest);
		
	}

}
