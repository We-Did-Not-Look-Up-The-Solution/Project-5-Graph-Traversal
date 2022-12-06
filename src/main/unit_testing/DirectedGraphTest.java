package main.unit_testing;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class DirectedGraphTest {
	
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

    public static void createGraph(DirectedGraph<Character> graph)
    {
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
	}
	@Test
    public void testgetNumberOfNodes()
    {
        createGraph(testGraph);
        assertEquals(13, testGraph.getNumberOfEdges());
    }
    @Test
    public void testIsEmpty()
    {
        assertEquals(true, testGraph.isEmpty());
        createGraph(testGraph);
        assertEquals(false, testGraph.isEmpty());
    }


    @Test
    public void testHasEdge()
    {
        createGraph(testGraph);
        assertEquals(true, testGraph.hasEdge("A","B"));
        assertEquals(true, testGraph.hasEdge("B","E"));
        assertEquals(true, testGraph.hasEdge("G","H"));
        assertEquals(true, testGraph.hasEdge("F","C"));
        assertEquals(true, testGraph.hasEdge("I","F"));
    }
}


}
