package main.trees;

import java.util.Iterator;
import java.util.NoSuchElementException;

import main.stack_and_queues.LinkedQueue;
import main.stack_and_queues.LinkedStack;
import main.stack_and_queues.QueueInterface;
import main.stack_and_queues.StackInterface;

/**
   A class that implements the ADT binary tree.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public class BinaryTree<T> implements BinaryTreeInterface<T> {
   private BinaryNode<T> root;

   public BinaryTree() {
      root = null;
   }

   public BinaryTree(T rootData) {
      root = new BinaryNode<>(rootData);
   }

   public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
      initializeTree(rootData, leftTree, rightTree);
   }

   public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
      initializeTree(rootData, (BinaryTree<T>)leftTree, (BinaryTree<T>)rightTree);
   }


   private void initializeTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
      root = new BinaryNode<>(rootData);

      if ((leftTree != null) && !leftTree.isEmpty())
         root.setLeftChild(leftTree.root);

      if ((rightTree != null) && !rightTree.isEmpty())
      {
         if (rightTree != leftTree)
            root.setRightChild(rightTree.root);
         else
            root.setRightChild(rightTree.root.copy());
      } // end if

      if ((leftTree != null) && (leftTree != this))
         leftTree.clear();

      if ((rightTree != null) && (rightTree != this))
         rightTree.clear();
   }

   public void setRootData(T rootData) {
      root.setData(rootData);
   }

   @Override
   public T getRootData() {
      if (isEmpty())
         throw new EmptyTreeException();
      else
         return root.getData();
   }

   public boolean isEmpty() {
      return root == null;
   }

   public void clear() {
      root = null;
   }

   protected void setRootNode(BinaryNode<T> rootNode) {
      root = rootNode;
   }

   protected BinaryNode<T> getRootNode() {
      return root;
   }
   
   public int getHeight() {
      int height = 0;
      if (root != null)
         height = root.getHeight();
      return height;
   }

   public int getNumberOfNodes() {
      int numberOfNodes = 0;
      if (root != null)
         numberOfNodes = root.getNumberOfNodes();
      return numberOfNodes;
   }
   
   @Override
	public Iterator<T> getInorderIterator() {
		return new InorderIterator();
	}
   
   @Override
	public Iterator<T> getPreorderIterator() {
		return new PreorderIterator();
	}
   
   @Override
	public Iterator<T> getPostorderIterator() {
		return new PostorderIterator();
	}
   
   @Override
	public Iterator<T> getLevelOrderIterator() {
		return new LevelorderIterator();
	}
   
   /* Left, Root, Right */
   /*
    *      	  (a)
    *      	/	  \
    *      /	   \
    *    (b)       (c)
    *   / 	\	  /
    * (d)   (e) (f)
    * 			  \ 
    * 			  (g)
    * 
    *     d
    *   b b b   e       f   g
    * a a a a a a a - c c c c c -
    */
   private class InorderIterator implements Iterator<T> {
      private StackInterface<BinaryNode<T>> nodeStack;
      private BinaryNode<T> currentNode;

      public InorderIterator() {
         nodeStack = new LinkedStack<>();
         currentNode = root; // set first node as the root
      }

      public boolean hasNext() {
         return !nodeStack.isEmpty() || (currentNode != null);
      }

      /**
       * Returns the next node with this praticular tree traversal. This will be called
       * in an iterative fashion
       */
      public T next() {
         BinaryNode<T> nextNode = null;

         // Get Left (Keep going until the first left with no left child)
         while (currentNode != null) {
            nodeStack.push(currentNode); // Push the current node (root or left)
            currentNode = currentNode.getLeftChild(); // set current node to new left
         } // end while

         // Once at last left with no child, this left becomes Root, now get right of this root
         if (!nodeStack.isEmpty()) {
            nextNode = nodeStack.pop(); // Pop the last left node and set as the nextNode (Will return this)
            currentNode = nextNode.getRightChild(); // Get the right node of this root and set it as the current node
         }
         else
            throw new NoSuchElementException();

         return nextNode.getData(); // The value of the node we popped off
      } // end next

      public void remove()
      {
         throw new UnsupportedOperationException();
      } // end remove
   }

   /* Root, Left, Right */
   /*
    *      	  (a)
    *      	/	  \
    *      /	   \
    *    (b)       (c)
    *   / 	\	  /
    * (d)   (e) (f)
    * 			  \ 
    * 			  (g)
    * 
    *             d
    *       b   e e e
    * a - c c c c c c c - f - g -
    */
   private class PreorderIterator implements Iterator<T> {
	   private StackInterface<BinaryNode<T>> nodeStack;
	      private BinaryNode<T> currentNode;

	      public PreorderIterator() {
	         nodeStack = new LinkedStack<>();
	         currentNode = root;
	         if (currentNode != null) {
		        	nodeStack.push(currentNode); 
		         }
	      }

	      public boolean hasNext() {
	         return !nodeStack.isEmpty() || (currentNode != null);
	      } // end hasNext

	      /**
	       * Returns the next node with this praticular tree traversal. This will be called
	       * in an iterative fashion
	       */
	      public T next() {
	         BinaryNode<T> nextNode = null;
	         
	         // Push node (first wil be Root)
	         
	         if (!nodeStack.isEmpty()) {
	        	 nextNode = nodeStack.pop(); // pop node and set as currentNode (Would become new root
	        	 
	        	 // get right
	        	 currentNode = nextNode.getRightChild();
	        	 if (currentNode != null)
	        		 nodeStack.push(currentNode); // Get right of currentNode and push
	        	 currentNode = nextNode.getLeftChild();
	        	 if (currentNode != null)
	        		 nodeStack.push(currentNode); // Get left of currentNode and push
	         } else {
	        	 throw new NoSuchElementException();
	         }
	         return nextNode.getData(); // Return the node that was popped off the stack
	         
	      }

	      public void remove() {
	         throw new UnsupportedOperationException();
	      }
   }
   
   /* Left, Right, Root */
   private class PostorderIterator implements Iterator<T> {
	   private StackInterface<BinaryNode<T>> nodeStack;
	      private BinaryNode<T> currentNode;

	      public PostorderIterator() {
	         nodeStack = new LinkedStack<>();
	         currentNode = root;
	         if (currentNode != null) {
		        	nodeStack.push(currentNode); // push node (first is root)
		         }
	      }

	      public boolean hasNext() {
	         return !nodeStack.isEmpty() || (currentNode != null);
	      }

	      /**
	       * Returns the next node with this praticular tree traversal. This will be called
	       * in an iterative fashion
	       */
	      public T next() {
	         BinaryNode<T> nextNode = null;
	         
	         // keep going until we get to the leftmost node without children
	         while (currentNode != null) {
	            nodeStack.push(currentNode); // Push the current node (root or left)
	            currentNode = currentNode.getLeftChild(); // set current node to new left
	         } // end while
	         
	         if (!nodeStack.isEmpty()) {
	        	 nextNode = nodeStack.peek();
	        	 
	        	 // if left most node does not have a child
	        	 if (nextNode.getRightChild() == null) {
	        		 nextNode = nodeStack.pop(); // Then pop the node on top and set as nextNode
	        		 currentNode = nodeStack.peek(); // Get the top node
	        	 }
	        	 
	        	 // get the right child of the currentNode
	        	 currentNode = currentNode.getRightChild();
	        	 
	        	 
	         } else {
	        	 throw new NoSuchElementException();
	         }
	         return nextNode.getData(); // Return the node that was popped off the stack
	         
	      }

	      public void remove() {
	         throw new UnsupportedOperationException();
	      }
   }
   
   /* Visit each level from left to right */
   private class LevelorderIterator implements Iterator<T> {
	   private QueueInterface<BinaryNode<T>> nodeQueue;
	      private BinaryNode<T> currentNode;

	      public LevelorderIterator() {
	         nodeQueue = new LinkedQueue<>();
	         currentNode = root;
	         
	         // Queue first node (first wil be Root)
	         if (currentNode != null)
	        	 nodeQueue.enqueue(currentNode);
	      }

	      public boolean hasNext() {
	         return !nodeQueue.isEmpty() || (currentNode != null);
	      } // end hasNext

	      /**
	       * Returns the next node with this praticular tree traversal. This will be called
	       * in an iterative fashion
	       */
	      public T next() {
	         BinaryNode<T> nextNode = null;
	         
	         // Dequeue top node / dequeue first node
	         if (!nodeQueue.isEmpty()) {
	        	 nextNode = nodeQueue.dequeue();
	        	 
	        	// queue left node of current node
	        	 currentNode = nextNode.getLeftChild();
	        	 if (currentNode != null)
	        		 nodeQueue.enqueue(currentNode);
	        	 
	        	// queue right node of current node
	        	 currentNode = nextNode.getRightChild();
	        	 if (currentNode != null)
	        		 nodeQueue.enqueue(currentNode);
	        	 
	         } else {
	        	 throw new NoSuchElementException();
	         }
	         return nextNode.getData(); // Return the node that was popped off the stack
	         
	      }

	      public void remove() {
	         throw new UnsupportedOperationException();
	      }
   }
}