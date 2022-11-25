package main.trees;

/**
 * 
 * @author wwwyv
 *
 */
public class BinaryForest {
	BinaryTree<Character> dftCharTree;
	BinaryTree<Character> bftCharTree;

	public BinaryForest() {
		dftCharTree = null;
		bftCharTree = null;
		createDepthFirstTree();
		createBreadthFirstTree();
	}

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
	
	public BinaryTree<Character> getDFTTree() {
		return dftCharTree;
	}
	
	public BinaryTree<Character> getBFTTree() {
		return bftCharTree;
	}
	
	private boolean setDFTTree(BinaryTree<Character> newTree) {
		dftCharTree = newTree;
		return true;
	}
	
	private boolean setBFTTree(BinaryTree<Character> newTree) {
		bftCharTree = newTree;
		return true;
	}
}
