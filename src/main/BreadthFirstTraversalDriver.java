package main;

import main.graphs.DirectedGraph;
import main.stack_and_queues.QueueInterface;

public class BreadthFirstTraversalDriver {

	public static void main(String[] args) {
		DirectedGraph<Character> directedCharGraph = new DirectedGraph<>();
		
		/*
		 * Note: Breadth-First traversal for trees is the same traversal as Level Order!
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
		QueueInterface<Character> breadthFirstTraversal = directedCharGraph.getBreadthFirstTraversal('a');
		
		for (int i = 0; i < directedCharGraph.getNumberOfEdges(); i++) {
			if (!breadthFirstTraversal.isEmpty()) {
				Character next = breadthFirstTraversal.dequeue();
				System.out.print(next + " ");
			}
		}
		
		// Answer: a b d e g f h c i
	}
}
