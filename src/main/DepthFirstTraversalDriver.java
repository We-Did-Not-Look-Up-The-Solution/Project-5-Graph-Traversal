package main;

import java.util.Iterator;

import main.graphs.DirectedGraph;
import main.stack_and_queues.QueueInterface;
import main.trees.BinaryTree;
import main.trees.BinaryTreeInit;

public class DepthFirstTraversalDriver {

	public static void main(String[] args) {
		DirectedGraph<Character> directedCharGraph = new DirectedGraph<>();
		BinaryTreeInit treeMaker = new BinaryTreeInit();
		BinaryTree<Character> charTree = treeMaker.getTree();
		
		/*
		 * Note: Depth-First Traversal for trees is traversal of the left tree and then the right tree (Preorder) 
		 */
		
		// dd the verticies
		directedCharGraph.addVertex('a');
		directedCharGraph.addVertex('b');
		directedCharGraph.addVertex('c');
		directedCharGraph.addVertex('d');
		directedCharGraph.addVertex('e');
		directedCharGraph.addVertex('f');
		directedCharGraph.addVertex('g');
		directedCharGraph.addVertex('h');
		directedCharGraph.addVertex('i');
		
		// connect the edges
		directedCharGraph.addEdge('a', 'b');
		directedCharGraph.addEdge('a', 'd');
		directedCharGraph.addEdge('a', 'e');
		directedCharGraph.addEdge('b', 'e');
		directedCharGraph.addEdge('d', 'g');
		directedCharGraph.addEdge('e', 'f');
		directedCharGraph.addEdge('e', 'h');
		directedCharGraph.addEdge('g', 'h');
		directedCharGraph.addEdge('f', 'c');
		directedCharGraph.addEdge('f', 'h');
		directedCharGraph.addEdge('h', 'i');
		directedCharGraph.addEdge('c', 'b');
		directedCharGraph.addEdge('i', 'f');
		
		// Breadth First Traversal
		QueueInterface<Character> depthFirstTraversal = directedCharGraph.getDepthFirstTraversal('a');
		Iterator<Character> charIterator = charTree.getPreorderIterator();
		
		System.out.print("Iterating through a Tree with Depth-First Traversal: ");
		while (charIterator.hasNext()) {
			System.out.print(charIterator.next() + " ");
		}
		
		for (int i = 0; i < directedCharGraph.getNumberOfEdges(); i++) {
			if (!depthFirstTraversal.isEmpty()) {
				Character next = depthFirstTraversal.dequeue();
				System.out.print(next + " ");
			}
		}
		
		
	}
	
	
}
