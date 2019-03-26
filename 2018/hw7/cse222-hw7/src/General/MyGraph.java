package General;
import java.util.*;

public abstract class MyGraph extends MatrixGraph {

    /**
     * Construct a graph with the specified number of
     * vertices and directionality
     *
     * @param numV     - The number of vertices
     * @param directed - The directionality flag
     */
    public MyGraph(int numV, boolean directed) {
        super(numV, directed);
    }

    /**
     *
     * @param numberOfEdges size of egdes to be created
     * @param isWeighted true if edges are to be weigted
     */
    public void makeAcyclicGraph(int numberOfEdges, boolean isWeighted){

        if (isDirected()){
            double rand;
            int vertexBase=0, vertex=1, vertexSize= getNumV();

            for (int i = 0; i <numberOfEdges ; ++i) {

                if (isWeighted) {
                    rand = Math.random()*100;
                    insert(new Edge(vertexBase,vertex,rand));
                }
                else {
                    insert(new Edge(vertexBase,vertex));
                }
                ++vertex;

                if (vertex == vertexSize)
                {
                    ++vertexBase;
                    vertex = vertexBase+1;
                }
                if (vertexBase == vertexSize-1)
                {
                    System.err.println("maximum directed acyclic situation (at least for my case)! : "+ i);
                    i = numberOfEdges;  // break for
                }
            }
        }
        else {
            double rand;
            int vertex=1, vertexSize= getNumV();

            for (int i = 0; i <numberOfEdges ; ++i) {

                if (isWeighted) {
                    rand = Math.random()*100;
                    insert(new Edge(0,vertex,rand));
                }
                else{
                    insert(new Edge(0,vertex));
                }
                ++vertex;

                if (vertex == vertexSize)
                {
                    System.err.println("maximum undirected acyclic situation (at least for my case)! : "+ i);
                    i = numberOfEdges;  // break for
                }
            }
        }
    }

    /**
     *
     * @param numberOfEdges size of egdes to be created
     */
    public void makeCyclicGraph(int numberOfEdges){

        int vertexBase=0, vertex=0, vertexSize= getNumV();

        for (int i = 0; i <numberOfEdges ; ++i) {

            insert(new Edge(vertexBase,vertex));

            ++vertex;

            if (vertex == vertexSize)
            {
                ++vertexBase;
                vertex = 0;
            }
            if (vertexBase == vertexSize)
            {
                System.out.println("You reach the maximum cyclic situation (at least for my case)! : "+ i);
                i = numberOfEdges;  // break for
            }

        }
    }

    /**
     *
     * @param g the graph to be searched
     * @return true if graph is acyclic
     */
    public boolean is_acyclic_graph(Graph g){
        int vertexSize = g.getNumV();
        for (int i = 0; i < vertexSize; ++i) {
            boolean [] visited = new boolean[vertexSize];
            visited[i] = true;
            if (is_acyclic(g, visited, i, i, 0) == false)
                return false;
        }
        return true;
    }

    /**
     *
     * @param g the graph to be searched
     * @param visited keeps visited status
     * @param base start vertex
     * @param tempBase temprory start vertex
     * @param isFirstTime to avoid that first neighbor is cycle for undrected graph
     * @return true if graph is acyclic
     */
    private boolean is_acyclic(Graph g, boolean [] visited, int base, int tempBase, int isFirstTime){
        Iterator< Edge > itr = g.edgeIterator(tempBase);

        while (itr.hasNext()) {
            Edge edge = itr.next();
            int neighbor = edge.getDest();

            if (tempBase == base)
                isFirstTime = 0;

            if (isFirstTime > 1 && !isDirected())
            {
                if (neighbor == base) {
                    return false;
                }
            }
            else if (isDirected()){
                if (neighbor == base) {
                    return false;
                }
            }
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                return is_acyclic(g, visited, base, neighbor, ++isFirstTime);
            }

        }
        return true;
    }

    /**
     *
     * @param g the graph to be searched
     * @return true if graph is undirected
     */
    public boolean is_undirected(Graph g) {

        Queue < Integer > theQueue = new LinkedList();
        ArrayList<Edge> edgeArr = new ArrayList<>();
        boolean[] visited = new boolean[g.getNumV()];

        /* Mark the start vertex as identified and insert it into the queue */
        visited[0] = true;
        theQueue.offer(0);
        /* While the queue is not empty */
        while (!theQueue.isEmpty())
        {
            /* Take a vertex, current, out of the queue. (Begin visiting current). */
            int current = theQueue.remove();
            /* Examine each vertex, neighbor, adjacent to current. */
            Graph temp = g;
            Iterator < Edge > itr = temp.edgeIterator(current);
            while (itr.hasNext()) {
                Edge edge = itr.next();
                int key=0;
                for (int i = 0; i < edgeArr.size() ; i++) {
                    if (edge.getDest() == edgeArr.get(i).getSource() && edge.getSource() == edgeArr.get(i).getDest())
                        key=1;
                }
                if(key == 0)
                {
                    Edge reverseEdge = g.getEdge(edge.getDest(),edge.getSource());
                    if (edge.getWeight() != reverseEdge.getWeight()){
                        return false;
                    }
                    edgeArr.add(edge);
                }

                int neighbor = edge.getDest();
                // If neighbor has not been identified
                if (!visited[neighbor]) {
                    // Mark it identified.
                    visited[neighbor] = true;
                    // Place it into the queue.
                    theQueue.offer(neighbor);
                    /* Insert the edge (current, neighbor) into the tree. */

                }
            }
            // Finished visiting current.
        }

        return true;
    }

    /**
     *
     * @param g graph to be printed
     */
    public void plot_graph(Graph g){

        int numV = g.getNumV();
        String allGraph="";
        for (int start=0; start< numV; ++start){
            String localGraph="";
            boolean[] visited = new boolean[numV];

            visited[start] = true;
            localGraph += start+" -> ";

            Iterator < Edge > itr = g.edgeIterator(start);
            while (itr.hasNext()) {
                Edge edge = itr.next();
                int neighbor = edge.getDest();

                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    localGraph += neighbor+" -> ";
                }
            }
            allGraph += localGraph+"\n";

        }
        System.out.print(allGraph);
    }
}
