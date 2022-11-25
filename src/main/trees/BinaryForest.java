package main.trees;

/**
 * A cleverly-named class that makes two trees that are compatible with the traversal order of the same name
 * @author CarltheSpiny
 *
 */
public class BinaryForest {
	BinaryTree<Character> dftCharTree; // Depth First Traversal Compatible Tree
	BinaryTree<Character> bftCharTree; // Breadth First Traversal Compatible Tree

	/**
	 * Default Constructor
	 */
	public BinaryForest() {
		dftCharTree = null;
		bftCharTree = null;
		createDepthFirstTree();
		createBreadthFirstTree();
	}

	/**
	 * Create the Depth First Tree using the BinaryNode class
	 */
	private void createDepthFirstTree() {
		BinaryNode<Character> leftChild_4 = new BinaryNode<Character>('i'); // i (L: null, R:null)
		BinaryNode<Character> rightChild_4 = new BinaryNode<Character>('d'); // d (L: null, R:null)
		
		BinaryNode<Character> leftChild_3 = new BinaryNode<Character>('h', leftChild_4, rightChild_4); // h (L: i, R: d)
		BinaryNode<Character> rightChild_3 = new BinaryNode<Character>('g'); // b (L: null, R:null)
		
		BinaryNode<Character> leftChild_2 = new BinaryNode<Character>('e'); // b (L: null, R:null)
		BinaryNode<Character> rightChild_2 = new BinaryNode<Character>('f'); // b (L: null, R:null)
		
		BinaryNode<Character> leftChild_1 = new BinaryNode<Character>('b', leftChild_2, rightChild_2); // b (L: e, R: f)
		BinaryNode<Character> rightChild_1 = new BinaryNode<Character>('c', leftChild_3, rightChild_3); // c (L: h, R: g)
		
		BinaryNode<Character> root = new BinaryNode<Character>('a', leftChild_1, rightChild_1); // a (L: b, R: c)
		
		BinaryTree<Character> charTree = new BinaryTree<Character>('a');
		charTree.setRootNode(root);
		setDFTTree(charTree); // Set this tree as the one we just made
	}
	
	/**
	 * Create the Breadth First Tree using the BinaryNode class
	 */
	private void createBreadthFirstTree() {
		BinaryNode<Character> leftChild_4 = new BinaryNode<Character>('c'); // c (L: null, R:null)
		BinaryNode<Character> rightChild_4 = new BinaryNode<Character>('i'); // i (L: null, R:null)
		
		BinaryNode<Character> leftChild_3 = new BinaryNode<Character>('e', leftChild_4, rightChild_4); // e (L: c, R: i)
		BinaryNode<Character> rightChild_3 = new BinaryNode<Character>('g'); // g (L: null, R:null)
		
		BinaryNode<Character> leftChild_2 = new BinaryNode<Character>('f'); // f (L: null, R:null)
		BinaryNode<Character> rightChild_2 = new BinaryNode<Character>('h'); // h (L: null, R:null)
		
		BinaryNode<Character> leftChild_1 = new BinaryNode<Character>('b', leftChild_3, rightChild_3); // b (L: e, R: g)
		BinaryNode<Character> rightChild_1 = new BinaryNode<Character>('d', leftChild_2, rightChild_2); // d (L: f, R: h)
		
		BinaryNode<Character> root = new BinaryNode<Character>('a', leftChild_1, rightChild_1); // a (L: b, R: d)
		
		BinaryTree<Character> charTree = new BinaryTree<Character>('a');
		charTree.setRootNode(root);
		setBFTTree(charTree); // Set this tree as the one we just made
	}
	
	/**
	 * Get the Depth First Tree
	 * @return the tree compatible with depth-first traversal
	 */
	public BinaryTree<Character> getDFTTree() {
		return dftCharTree;
	}
	
	/**
	 * Get the Breadth First Tree
	 * @return the tree compatible with breadth-first traversal
	 */
	public BinaryTree<Character> getBFTTree() {
		return bftCharTree;
	}
	
	/**
	 * Set a tree as the new Depth First Tree
	 * @param newTree the new tree
	 * @return Result of setting the tree
	 */
	private boolean setDFTTree(BinaryTree<Character> newTree) {
		dftCharTree = newTree;
		return true;
	}
	
	/**
	 * Set a tree as the new Breadth First Tree
	 * @param newTree the new tree
	 * @return Result of setting the tree
	 */
	private boolean setBFTTree(BinaryTree<Character> newTree) {
		bftCharTree = newTree;
		return true;
	}
}
