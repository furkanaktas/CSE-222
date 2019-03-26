package Q3;

import General.Edge;
import General.Graph;
import General.MyGraph;

import java.util.*;

public class Q3Graph extends MyGraph {

    /**
     * Construct a graph with the specified number of
     * vertices and directionality
     *
     * @param numV     - The number of vertices
     * @param directed - The directionality flag
     */
    public Q3Graph(int numV, boolean directed) {
        super(numV, directed);
    }





    /**
     *
     * @param start the start vertex
     * @return  arr that has parent info
     */
    public int[] depthFirstSearch(int start){
        boolean [] visited = new boolean[getNumV()];

        int [] arr = new int[getNumV()];
        arr[start] = -1;
        visited[start]= true;

        depthFirstSearch(start, visited, arr);

        return arr;

    }

    /**
     *
     * @param start  start vertex
     * @param visited   keeps visited status
     * @param resultArr the result of process
     * @return junk int
     */
    private int depthFirstSearch(int start, boolean[] visited, int[] resultArr){

        Iterator< Edge > itr = edgeIterator(start);
        while (itr.hasNext()) {
            Edge edge = itr.next();
            int neighbor = edge.getDest();

            if (!visited[neighbor]) {
                resultArr[neighbor] = start;
                visited[neighbor] = true;
                depthFirstSearch(neighbor, visited, resultArr);
            }

        }
        return 0;
    }

    /**
     *
     * @param graph the weigted graph to be searched
     * @param start start vertex
     * @return  an ArrayList of edges
     */
    public ArrayList<Edge> primsAlgorithm(Graph graph, int start){
        ArrayList<Edge> result = new ArrayList<>();
        int numV = graph.getNumV();

        Set<Integer> vMinusS = new HashSet<>();

        Queue<Edge> pQ = new PriorityQueue<>(numV, (e1,e2) -> Double.compare(e1.getWeight(), e2.getWeight()));

        for (int i = 0; i < numV; i++) {
            if (i != start ){
                vMinusS.add(i);
            }
        }

        int current = start;
        while (vMinusS.size() != 0){
            Iterator<Edge> iter = graph.edgeIterator(current);

            while (iter.hasNext()){
                Edge edge = iter.next();
                int dest = edge.getDest();
                if (vMinusS.contains(dest)){
                    pQ.add(edge);
                }
            }

            int dest = -1;
            Edge edge = null;

            do {
                edge = pQ.remove();
                dest = edge.getDest();
            }while (!vMinusS.contains(dest));

            vMinusS.remove(dest);
            result.add(edge);
            current = dest;
        }

        return result;
    }
}
