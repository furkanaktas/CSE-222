package Q1;
import General.Graph;
import Q2.Q2Graph;

import java.util.HashSet;
import java.util.Stack;
import java.util.Vector;

public class Q1Graph extends Q2Graph {

    /**
     * Construct a graph with the specified number of
     * vertices and directionality
     *
     * @param numV     - The number of vertices
     * @param directed - The directionality flag
     */
    public Q1Graph(int numV, boolean directed) {
        super(numV, directed);
    }



    /**
     *
     * @param graph the graph to be searched
     * @param start start vertex
     * @param pred arr that has parent info according to smallest distance
     * @param dist arr that has distance info
     */
    private void dijkstrasAlgorithm(Graph graph, int start, int[] pred, double[] dist) {

        int numV = graph.getNumV();
        HashSet< Integer > vMinusS = new HashSet < Integer > (numV);
        // Initialize V–S.
        for (int i = 0; i < numV; i++) {
            if (i != start) {
                vMinusS.add(i);
            }
        }
        // Initialize pred and dist.
        for (int v : vMinusS) {
            pred[v] = start;
            dist[v] = graph.getEdge(start, v).getWeight();
        }
        // Main loop
        while (vMinusS.size() != 0) {
            // Find the value u in V–S with the smallest dist[u].
            double minDist = Double.POSITIVE_INFINITY;
            int u = -1;
            for (int v : vMinusS) {
                if (dist[v] < minDist) {
                    minDist = dist[v];
                    u = v;
                }
            }
            // Remove u from vMinusS.
            vMinusS.remove(u);
            // Update the distances.


            int delete = -1;
            for (int v : vMinusS) {
                if (graph.isEdge(u, v)) {
                    double weight = graph.getEdge(u, v).getWeight();
                    if (dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        pred[v] = u;
                    }
                }
                else {
                    if (u == -1)
                        delete = v;
                }
            }
            if (u == -1)
                vMinusS.remove(delete);
        }
    }

    /**
     *
     * @param g the graph to be searched
     * @param v1 start vertex
     * @param v2 end vertex
     * @return the shortest path
     */
    public Vector shortest_path(Graph g, int v1, int v2){
        int vertexSize = g.getNumV();
        if (v1<0 || v1>=vertexSize || v2<0 || v2>=vertexSize){
            System.err.println("Betwenn 0-"+vertexSize+" are vertexies");
            return null;
        }
        Vector<Integer> path = new Vector<>();

        if (is_connected(g, v1, v2)){

            int [] pred= new int[vertexSize];
            double [] dist= new double[vertexSize];
            try{
                dijkstrasAlgorithm(g, v1, pred, dist );
            }catch (ArrayIndexOutOfBoundsException e){
                System.err.println(v1+" to "+v2 + " doesn't exits!");
                return null;
            }

            Stack<Integer> tempPath = new Stack<>();
            int index= v2;
            int count = 0;

            tempPath.add(index);
            while (dist[index] != 0.0 && count != vertexSize){
                index= pred[index];
                tempPath.add(index);
                ++count;
            }

            while (!tempPath.isEmpty()){
                path.add(tempPath.pop());
            }
        }
        else
            System.err.println(v1+" to "+v2 + " doesn't have path!");

        return path;
    }
}
