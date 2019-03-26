package Q3;

import General.Edge;

public class Main {

    public static void main(String [] args){


        Q3Graph graph = new Q3Graph(10, false);
        graph.makeCyclicGraph(20);

        int arr [] = graph.breadthFirstSearch(0);

        for (int i: arr
                ) {
            System.out.print(i+ " ");
        }
        System.out.println("");

        arr  = graph.depthFirstSearch(0);

        for (int i: arr
                ) {
            System.out.print(i+ " ");
        }
        System.out.println("");

        System.out.println("graph ");
        graph.plot_graph(graph);

        System.out.println(graph.primsAlgorithm(graph, 0));     // spanning tree




        System.out.println("\nQ4 'teki oluşan graph");

        Q3Graph graph1 = new Q3Graph(7, false);

        graph1.insert(new Edge(0,1));
        graph1.insert(new Edge(0,5));
        graph1.insert(new Edge(1,2));
        graph1.insert(new Edge(2,4));
        graph1.insert(new Edge(2,6));
        graph1.insert(new Edge(2,5));
        graph1.insert(new Edge(4,3));
        graph1.insert(new Edge(5,4));


        arr  = graph1.breadthFirstSearch(1);

        for (int i: arr
             ) {
            System.out.print(i+ " ");
        }
        System.out.println("");

        arr  = graph1.depthFirstSearch(1);

        for (int i: arr
                ) {
            System.out.print(i+ " ");
        }
        System.out.println("");


        System.out.println("graph1 ");
        graph1.plot_graph(graph1);

        System.out.println(graph1.primsAlgorithm(graph1, 0)); // spanning tree
    }

}
