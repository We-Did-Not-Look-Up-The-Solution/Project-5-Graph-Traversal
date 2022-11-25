package main.unit_testing;

import static org.junit.Assert.assertThrows;

import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.Test;

import main.stack_and_queues.EmptyQueueException;

class EmptyQueueExceptionTest {

	@Test
	void test() {
		ThrowingRunnable throwTest = new ThrowingRunnable() {
			
			@Override
			public void run() throws Throwable {
				throw new EmptyQueueException("Queue was Empty!");
			}
		};
		
		assertThrows(EmptyQueueException.class, throwTest);
	}
}
