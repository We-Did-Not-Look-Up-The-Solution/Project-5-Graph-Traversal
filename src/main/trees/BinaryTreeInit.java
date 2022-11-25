package main.trees;

public class BinaryTreeInit {
	BinaryTree<Character> characterTree;

	public BinaryTreeInit() {
		characterTree = null;
		createTree();
	}

	private void createTree() {
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
		setTree(charTree); // Set this tree as the one we just made
	}
	
	public BinaryTree<Character> getTree() {
		return characterTree;
	}
	
	private boolean setTree(BinaryTree<Character> newTree) {
		characterTree = newTree;
		return true;
	}
}
