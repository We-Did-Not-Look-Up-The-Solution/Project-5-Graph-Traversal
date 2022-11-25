package main.trees;

public class EmptyTreeException extends RuntimeException {

	private static final long serialVersionUID = -6015409829356873336L;
	
	public EmptyTreeException() {
		super();
	}
	
	public EmptyTreeException(String message) {
		super(message);
	}
}
