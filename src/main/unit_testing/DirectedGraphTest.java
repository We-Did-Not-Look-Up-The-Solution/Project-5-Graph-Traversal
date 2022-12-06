package main.unit_testing;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import main.graphs.DirectedGraph;

public class DirectedGraphTest {
	
	/*
	 * Test These methods:
	 * 		addEdge(T begin, T end)
	 * 		getNumberOfEdges()
	 * 		hasEdge(T begin, T end)
	 * 		getBreadthFirstTraversal(T origin)
	 * 		getDepthFirstTraversal(T origin)
	 * 
	 * Cannot test these as they are not implemented and also never used:
	 * 		getCheapestPath(T begin, T end, StackInterface<T> path)
	 * 		getTopologicalOrder()
	 */

	DirectedGraph<Character> testGraph = new DirectedGraph<>();
	
	/**
	 * Make a graph for testing
	 * @param graph the empty graph to fill out
	 */
    public static void createGraph(DirectedGraph<Character> graph) {
		graph.addVertex('a');
		graph.addVertex('b');
		graph.addVertex('c');
		graph.addVertex('d');
		graph.addVertex('e');
		graph.addVertex('f');
		graph.addVertex('g');
		graph.addVertex('h');
		graph.addVertex('i');
		
		// connect the edges
		graph.addEdge('a', 'b');
		graph.addEdge('a', 'd');
		graph.addEdge('a', 'e');
		graph.addEdge('b', 'e');
		graph.addEdge('d', 'g');
		graph.addEdge('e', 'f');
		graph.addEdge('e', 'h');
		graph.addEdge('g', 'h');
		graph.addEdge('f', 'c');
		graph.addEdge('f', 'h');
		graph.addEdge('h', 'i');
		graph.addEdge('c', 'b');
		graph.addEdge('i', 'f');
	}
    
	@Test
    public void testgetNumberOfNodes() {
        createGraph(this.testGraph);
        assertEquals(13, this.testGraph.getNumberOfEdges());
    }
	
    @Test
    public void testIsEmpty() {
        assertEquals(true, this.testGraph.isEmpty());
        createGraph(this.testGraph);
        assertEquals(false, this.testGraph.isEmpty());
    }


    @Test
    public void testHasEdge() {
        createGraph(this.testGraph);
        assertEquals(true, this.testGraph.hasEdge('a', 'b'));
        assertEquals(true, this.testGraph.hasEdge('b','e'));
        assertEquals(true, this.testGraph.hasEdge('g','h'));
        assertEquals(true, this.testGraph.hasEdge('f','c'));
        assertEquals(true, this.testGraph.hasEdge('i','f'));
    }
}
