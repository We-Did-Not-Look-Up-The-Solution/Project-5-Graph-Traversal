package main.adt;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
   A class that implements the ADT dictionary by using a chain of linked nodes.
   The dictionary is sorted and has distinct search keys.
   Search keys and associated values are not null.
  
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
 * @param <K> A comparable value to be used as a key
 * @param <V> The data type to be used for the value
*/
public class LinkedDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V>
{
	private Node firstNode; // Reference to first node of chain
	private int  numberOfEntries; 
	
	/** Default Constructor */
	public LinkedDictionary() {
      initializeDataFields();
	}
	
	/** Initialize data fields to non-empty values */
	private void initializeDataFields() {
		firstNode = null;
		numberOfEntries = 0;
	}

	/** Add this entry using the passed key and value 
	 * @param key the representation of this value
	 * @param value the value of this entry
	 * @return The entry added
	 */
	public V add(K key, V value)	{
		V result = null;
		if ((key == null) || (value == null))
			throw new IllegalArgumentException("Cannot add null to a dictionary.");
		else {
			// Search chain until you either find a node containing key
			// or locate where it should be
			Node currentNode = firstNode;
			Node nodeBefore = null;
         
			while ( (currentNode != null) && (key.compareTo(currentNode.getKey()) > 0) ) {
				nodeBefore = currentNode;
				currentNode = currentNode.getNextNode();
			} // end while
         
			if ( (currentNode != null) && key.equals(currentNode.getKey()) ) {
				// Key in dictionary; replace corresponding value
				result = currentNode.getValue(); // Get old value
				currentNode.setValue(value);     // Replace value
			}
			else { // Key not in dictionary; add new node in proper order
				// Assertion: key and value are not null
				Node newNode = new Node(key, value); // Create new node
            
            if (nodeBefore == null) { // Add at beginning (includes empty chain)
               newNode.setNextNode(firstNode);
               firstNode = newNode;
            }
            else {                               // Add elsewhere in non-empty chain
               newNode.setNextNode(currentNode); // currentNode is after new node
               nodeBefore.setNextNode(newNode);  // nodeBefore is before new node
            } // end if

            numberOfEntries++;                   // Increase length for both cases
         } // end if
      } // end if

		return result;
	} // end add

   
	/**
	 * Remove the entry using the specfied key
	 * @param key The representation of the entry
	 * @return the entry being removed
	 */
	public V remove(K key) {
	   Node currentNode = firstNode;
	   Node previousNode = null;
	   while (currentNode.getKey() != key) {
		   previousNode = currentNode;
		   currentNode = currentNode.getNextNode();
	   }
	
	   if (currentNode.getNextNode() != null && previousNode != null) {
		   // the node after the one we want to remove is the next node of the previous node
		   previousNode.setNextNode(currentNode.getNextNode());
	   }
	   
	   V result = currentNode.getValue();
	   currentNode = null;
	   this.numberOfEntries--;
	   return result;
	}

	/**
	 * Gets the value of the entry with the specfied key
	 * @param key The key of the desired entry
	 * @return The value of the entry
	 * 
	 */
	public V getValue(K key) {
		Node currentNode = firstNode;
		while (!currentNode.getKey().equals(key)) {
			currentNode = currentNode.getNextNode();
		}
		
		return currentNode.getValue();
	}

	public boolean contains(K key) {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	@Override
	public int getSize() {
		return numberOfEntries;
	}

	@Override
	public void clear() {
		int copyOf = numberOfEntries;
		for(int i = 0; i < copyOf; i++)
			remove(firstNode.key);
	}
   
	public Iterator<K> getKeyIterator() {
		return new KeyIterator();
	}
    
    public Iterator<V> getValueIterator() {
    	return new ValueIterator();
    }
   
    private class KeyIterator implements Iterator<K> {
      private Node nextNode;

		private KeyIterator() {
			nextNode = firstNode;
		}
		
		public boolean hasNext() {
			return nextNode != null;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public K next() {
		   K result;
		   if (hasNext()) {
		      result = nextNode.getKey();
		      nextNode = nextNode.getNextNode();
		   }
		   else
		      throw new NoSuchElementException("Illegal call to next(); " +
		                                       "iterator is after end of list.");
		   return result;
		}
	}
   
    private class ValueIterator implements Iterator<V> {
        private Node nextNode;

  		private ValueIterator() {
  			nextNode = firstNode;
  		}
  		
  		public boolean hasNext() {
  			return nextNode != null;
  		}
  		
  		public void remove() {
  			throw new UnsupportedOperationException();
  		}
  		
  		public V next() {
  		   V result;
  		   if (hasNext()) {
  		      result = nextNode.getValue();
  		      nextNode = nextNode.getNextNode();
  		   }
  		   else
  		      throw new NoSuchElementException("Illegal call to next(); " +
  		                                       "iterator is after end of list.");
  		   return result;
  		}

  	}
	
    private class Node {
		private K key;
		private V value;
		private Node next; // Link to next node
      
		private Node(K searchKey, V dataValue) {
			key = searchKey;
			value = dataValue;
		} // end constructor
      
		public void setValue(V newValue) {
			value = newValue;
		}

		public V getValue() {
			return value;
		}

		public K getKey() {
			return key;
		}

		private Node getNextNode() {
			return next;
		}
      
		private void setNextNode(Node nextNode) {
			next = nextNode;
		} // end setNextNode
    }
}
		
