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
      this.root = null;
   }

   public BinaryTree(T rootData) {
      this.root = new BinaryNode<>(rootData);
   }

   public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
      initializeTree(rootData, leftTree, rightTree);
   }

   public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
      initializeTree(rootData, (BinaryTree<T>)leftTree, (BinaryTree<T>)rightTree);
   }


   private void initializeTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
      this.root = new BinaryNode<>(rootData);

      if ((leftTree != null) && !leftTree.isEmpty())
         this.root.setLeftChild(leftTree.root);

      if ((rightTree != null) && !rightTree.isEmpty())
      {
         if (rightTree != leftTree)
            this.root.setRightChild(rightTree.root);
         else
            this.root.setRightChild(rightTree.root.copy());
      } // end if

      if ((leftTree != null) && (leftTree != this))
         leftTree.clear();

      if ((rightTree != null) && (rightTree != this))
         rightTree.clear();
   }

   public void setRootData(T rootData) {
      this.root.setData(rootData);
   }

   @Override
   public T getRootData() {
      if (isEmpty())
         throw new EmptyTreeException();
      else
         return this.root.getData();
   }

   public boolean isEmpty() {
      return this.root == null;
   }

   public void clear() {
      this.root = null;
   }

   protected void setRootNode(BinaryNode<T> rootNode) {
      this.root = rootNode;
   }

   protected BinaryNode<T> getRootNode() {
      return this.root;
   }
   
   public int getHeight() {
      int height = 0;
      if (this.root != null)
         height = this.root.getHeight();
      return height;
   }

   public int getNumberOfNodes() {
      int numberOfNodes = 0;
      if (this.root != null)
         numberOfNodes = this.root.getNumberOfNodes();
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
         this.nodeStack = new LinkedStack<>();
         this.currentNode = BinaryTree.this.root; // set first node as the root
      }

      public boolean hasNext() {
         return !this.nodeStack.isEmpty() || (this.currentNode != null);
      }

      /**
       * Returns the next node with this praticular tree traversal. This will be called
       * in an iterative fashion
       */
      public T next() {
         BinaryNode<T> nextNode = null;

         // Get Left (Keep going until the first left with no left child)
         while (this.currentNode != null) {
            this.nodeStack.push(this.currentNode); // Push the current node (root or left)
            this.currentNode = this.currentNode.getLeftChild(); // set current node to new left
         } // end while

         // Once at last left with no child, this left becomes Root, now get right of this root
         if (!this.nodeStack.isEmpty()) {
            nextNode = this.nodeStack.pop(); // Pop the last left node and set as the nextNode (Will return this)
            this.currentNode = nextNode.getRightChild(); // Get the right node of this root and set it as the current node
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
	         this.nodeStack = new LinkedStack<>();
	         this.currentNode = BinaryTree.this.root;
	         if (this.currentNode != null) {
		        	this.nodeStack.push(this.currentNode); 
		         }
	      }

	      public boolean hasNext() {
	         return !this.nodeStack.isEmpty() || (this.currentNode != null);
	      } // end hasNext

	      /**
	       * Returns the next node with this praticular tree traversal. This will be called
	       * in an iterative fashion
	       */
	      public T next() {
	         BinaryNode<T> nextNode = null;
	         
	         // Push node (first wil be Root)
	         
	         if (!this.nodeStack.isEmpty()) {
	        	 nextNode = this.nodeStack.pop(); // pop node and set as currentNode (Would become new root
	        	 
	        	 // get right
	        	 this.currentNode = nextNode.getRightChild();
	        	 if (this.currentNode != null)
	        		 this.nodeStack.push(this.currentNode); // Get right of currentNode and push
	        	 this.currentNode = nextNode.getLeftChild();
	        	 if (this.currentNode != null)
	        		 this.nodeStack.push(this.currentNode); // Get left of currentNode and push
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
	         this.nodeStack = new LinkedStack<>();
	         this.currentNode = BinaryTree.this.root;
	         if (this.currentNode != null) {
		        	this.nodeStack.push(this.currentNode); // push node (first is root)
		         }
	      }

	      public boolean hasNext() {
	         return !this.nodeStack.isEmpty() || (this.currentNode != null);
	      }

	      /**
	       * Returns the next node with this praticular tree traversal. This will be called
	       * in an iterative fashion
	       */
	      public T next() {
	         BinaryNode<T> nextNode = null;
	         
	         // keep going until we get to the leftmost node without children
	         while (this.currentNode != null) {
	            this.nodeStack.push(this.currentNode); // Push the current node (root or left)
	            this.currentNode = this.currentNode.getLeftChild(); // set current node to new left
	         } // end while
	         
	         if (!this.nodeStack.isEmpty()) {
	        	 nextNode = this.nodeStack.peek();
	        	 
	        	 // if left most node does not have a child
	        	 if (nextNode.getRightChild() == null) {
	        		 nextNode = this.nodeStack.pop(); // Then pop the node on top and set as nextNode
	        		 this.currentNode = this.nodeStack.peek(); // Get the top node
	        	 }
	        	 
	        	 // get the right child of the currentNode
	        	 this.currentNode = this.currentNode.getRightChild();
	        	 
	        	 
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
	         this.nodeQueue = new LinkedQueue<>();
	         this.currentNode = BinaryTree.this.root;
	         
	         // Queue first node (first wil be Root)
	         if (this.currentNode != null)
	        	 this.nodeQueue.enqueue(this.currentNode);
	      }

	      public boolean hasNext() {
	         return !this.nodeQueue.isEmpty() || (this.currentNode != null);
	      } // end hasNext

	      /**
	       * Returns the next node with this praticular tree traversal. This will be called
	       * in an iterative fashion
	       */
	      public T next() {
	         BinaryNode<T> nextNode = null;
	         
	         // Dequeue top node / dequeue first node
	         if (!this.nodeQueue.isEmpty()) {
	        	 nextNode = this.nodeQueue.dequeue();
	        	 
	        	// queue left node of current node
	        	 this.currentNode = nextNode.getLeftChild();
	        	 if (this.currentNode != null)
	        		 this.nodeQueue.enqueue(this.currentNode);
	        	 
	        	// queue right node of current node
	        	 this.currentNode = nextNode.getRightChild();
	        	 if (this.currentNode != null)
	        		 this.nodeQueue.enqueue(this.currentNode);
	        	 
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