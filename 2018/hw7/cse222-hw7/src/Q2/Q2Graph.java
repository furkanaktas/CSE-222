package Q2;

import General.Edge;
import General.Graph;
import General.MyGraph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Q2Graph extends MyGraph {


    /**
     * Construct a graph with the specified number of
     * vertices and directionality
     *
     * @param numV     - The number of vertices
     * @param directed - The directionality flag
     */
    public Q2Graph(int numV, boolean directed) {
        super(numV, directed);
    }

    /**
     *
     * @param g the graph to be searched
     * @param v1 start vertex
     * @param v2 end vertex
     * @return true if vertexies are connected
     */
    public boolean is_connected(Graph g, int v1, int v2){

        int vertexSize = g.getNumV();
        if (v1<0 || v1>=vertexSize || v2<0 || v2>=vertexSize){
            System.err.println("Betwenn 0-"+vertexSize+" are vertexies");
            return false;
        }

        Queue< Integer > theQueue = new LinkedList< Integer >();

        boolean[] visited = new boolean[vertexSize];

        visited[v1] = true;
        theQueue.offer(v1);

        while (!theQueue.isEmpty()) {

            int current = theQueue.remove();
            /* Examine each vertex, neighbor, adjacent to current. */
            Iterator< Edge > itr = g.edgeIterator(current);
            while (itr.hasNext()) {
                Edge edge = itr.next();

                int neighbor = edge.getDest();
                if ( neighbor == v2)
                    return true;

                if (!visited[neighbor]) {
                    // Mark it identified.
                    visited[neighbor] = true;
                    // Place it into the queue.
                    theQueue.offer(neighbor);
                }
            }
            // Finished visiting current.
        }
        return false;
    }
}
