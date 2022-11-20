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
*/
public class LinkedDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V>
{
	private Node firstNode; // Reference to first node of chain
	private int  numberOfEntries; 
	
	public LinkedDictionary()
	{
      initializeDataFields();
	} // end default constructor
	
   private void initializeDataFields() {
		firstNode = null;
		numberOfEntries = 0;
	}

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

   
	public V remove(K key) {
	   Node currentNode = firstNode;
	   Node previousNode = null;
	   while (currentNode.getKey() != key) {
		   previousNode = currentNode;
		   currentNode = currentNode.getNextNode();
	   }
	
	   if (currentNode.getNextNode() != null)
		   // the node after the one we want to remove is the next node of the previous node
		   previousNode.setNextNode(currentNode.getNextNode()); 	   
	   
	   V result = currentNode.getValue();
	   currentNode = null;
	   return result;
	}

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
		
