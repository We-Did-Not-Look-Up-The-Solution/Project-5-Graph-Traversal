package main.graphs;

import java.util.Iterator;
import java.util.NoSuchElementException;
import main.adt.LinkedListWithIterator;
import main.adt.ListWithIteratorInterface;
/**
 A class of vertices for a graph.
 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 5.0
 */
class Vertex<T> implements VertexInterface<T> {
   private T label;
   private ListWithIteratorInterface<Edge> edgeList; // Edges to neighbors
   private boolean visited;                          // True if visited
   private VertexInterface<T> previousVertex;        // On path to this vertex
   private double cost;                              // Of path to this vertex
   
   public Vertex(T vertexLabel) {
      label = vertexLabel;
      edgeList = new LinkedListWithIterator<>();
      visited = false;
      previousVertex = null;
      cost = 0;
   } // end constructor

   /**
    * Test wheter this vertex has a neighbor
    * @return true if has at least one neighbor, else false
    */
   public boolean hasNeighbor() {
	   return !edgeList.isEmpty();
   }
   
   /**
    * Gets the next unvisited neighbor
    * @return The next unvisited neighbor
    */
   public VertexInterface<T> getUnvisitedNeighbor() {
      VertexInterface<T> result = null;
      Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
      while ( neighbors.hasNext() && (result == null) )
      {
         VertexInterface<T> nextNeighbor = neighbors.next();
         if (!nextNeighbor.isVisited())
            result = nextNeighbor;
      } // end while
      return result;
   }
   
   /**
    * Redefine the equals() to correctly compare two verticies
    * @return true if the verticies equal each other, else false
    */
   public boolean equals(Object other) {
      boolean result;
      if ((other == null) || (getClass() != other.getClass()))
         result = false;
      else {  
    	  // The cast is safe within this else clause
         @SuppressWarnings("unchecked")
         Vertex<T> otherVertex = (Vertex<T>)other;
         result = label.equals(otherVertex.label);
      } // end if
      return result;
   }
   
   /**
    * Returns the label of this Vertex
    * @return The label representing this Vertex
    */
   public T getLabel() {
	   return label;
   }

   /**
    * Mark this vertex as visited
    */
   public void visit() {
	   visited = true;
   }

   /**
    * Mark this vertex as not visited
    */
   public void unvisit() {
	   visited = false;
   }

   /**
    * Checks wheter this vertex was visited
    * @return true if this vertex was visited, else false
    */
   public boolean isVisited() {
	   return visited;
   }

   /**
    * Sets the predecessor of this vertex
    * @param the new predecessor
    */
   public void setPredecessor(VertexInterface<T> predecessor) {
	   previousVertex = predecessor;
   }

   /**
    * Gets the predecessor of this vertex
    * @return the predecessor of this vertex
    */
   public VertexInterface<T> getPredecessor() {
   	return previousVertex;
   }

   /**
    * Checks whether this vertex has a predecessor
    * @return true if it does have a predecessor, else false
    */
   public boolean hasPredecessor() {
	   return false;
   }

   /**
    * Set the cost of this vertex
    * @param newCost The new cost of this Vertex
    */
   public void setCost(double newCost) {
	   cost = newCost;
   }

   /**
    * Get the cost of this vertex
    * @return the Cost of this vertex
    */
   public double getCost() {
	   return cost;
   }
   
   /**
    * Connect to Vertices together and set the weight of their edge
    * @param endVertex the vertex that the this vertex will connect to
    * @param edgeWeight The weight of this edge
    * @return true if the operation was successful
    */
   public boolean connect(VertexInterface<T> endVertex, double edgeWeight) {
      boolean result = false;
           if (!this.equals(endVertex)) {  // Vertices are distinct
              Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
              boolean duplicateEdge = false;
              while (!duplicateEdge && neighbors.hasNext()) {
                 VertexInterface<T> nextNeighbor = neighbors.next();
                 if (endVertex.equals(nextNeighbor))
                    duplicateEdge = true;
              } // end while
              if (!duplicateEdge) {
                  edgeList.add(new Edge(endVertex, edgeWeight));
                  result = true;
              }  // end if
           } // end if
   	return result;
   }  // end connect
   
   /**
    * Connect to Vertices together
    * @param endVertex the vertex that the this vertex will connect to
    * @return true if the operation was successful
    */
   public boolean connect(VertexInterface<T> endVertex) {
      return connect(endVertex, 0);
   }
   
   /**
    * Get the iterator that will go through this verticies neighbors
    * @return the iterator
    */
   public Iterator<VertexInterface<T>> getNeighborIterator() {
	   return new NeighborIterator();
   }
   
   /**
    * Get the iterator that will go through this verticies using weight
    * @return the iterator
    */
   public Iterator<Double> getWeightIterator() {
	   return null;
   }
   
   /**
    *	A class that defines how to iterate through the neighbors of this vertex
    */
   private class NeighborIterator implements Iterator<VertexInterface<T>> {
      private Iterator<Edge> edges;
      
      private NeighborIterator() {
         edges = edgeList.getIterator();
      } // end default constructor
      
      public boolean hasNext() {
         return edges.hasNext();
      } // end hasNext
      
      public VertexInterface<T> next() {
         VertexInterface<T> nextNeighbor = null;
         
         if (edges.hasNext()) {
            Edge edgeToNextNeighbor = edges.next();
            nextNeighbor = edgeToNextNeighbor.getEndVertex();
         }
         else
            throw new NoSuchElementException();
         
         return nextNeighbor;
      } // end next
      
      public void remove() {
         throw new UnsupportedOperationException();
      } // end remove
   }

   /**
    *	The class that represents the connection between this vertex and another
    */
   protected class Edge {
      private VertexInterface<T> vertex; // Vertex at end of edge
      private double weight;
      
      protected Edge(VertexInterface<T> endVertex, double edgeWeight)
      {
         vertex = endVertex;
         weight = edgeWeight;
      } // end constructor
      
      protected Edge(VertexInterface<T> endVertex)
      {
         vertex = endVertex;
         weight = 0;
      } // end constructor

      protected VertexInterface<T> getEndVertex()
      {
         return vertex;
      } // end getEndVertex
      
      protected double getWeight()
      {
         return weight; 
      } // end getWeight
   }


} // end Vertex
