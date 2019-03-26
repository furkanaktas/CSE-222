package Q1;

import General.Edge;

public class Main {

    public static void main(String [] args){

        Q1Graph graph = new Q1Graph(10, true);

        graph.makeAcyclicGraph(20,true);    // random weighted

        if (!graph.is_acyclic_graph(graph)){
            System.out.println("This is not acyclic graph !!");
        }

        if (graph.is_undirected(graph)){
            System.out.println("This is not directed graph !!");
        }

        System.out.println(graph.shortest_path(graph, 0, 8));
        System.out.println(graph.shortest_path(graph, 1, 9));
        System.out.println(graph.shortest_path(graph, 0, 5));

        System.out.println("graph ");
        graph.plot_graph(graph);

        System.out.println("\nfıgure 10.17");
        Q1Graph graph1 = new Q1Graph(10, true);

        graph1.insert(new Edge(0,3, 5));
        graph1.insert(new Edge(0,1, 2));
        graph1.insert(new Edge(1,2, 7));
        graph1.insert(new Edge(1,4, 3));
        graph1.insert(new Edge(1,6, 8));
        graph1.insert(new Edge(1,7, 1));
        graph1.insert(new Edge(2,8, 2));
        graph1.insert(new Edge(2,9, 6));
        graph1.insert(new Edge(4,5, 1));


        if (!graph1.is_acyclic_graph(graph1)){
            System.out.println("This is not acyclic graph !!");
        }

        if (graph1.is_undirected(graph1)){
            System.out.println("This is not directed graph !!");
        }

        System.out.println(graph1.shortest_path(graph1, 0, 5));
        System.out.println(graph1.shortest_path(graph1, 0, 2));
        System.out.println(graph1.shortest_path(graph1, 1, 9));

        System.out.println("graph1 ");
        graph1.plot_graph(graph1);



    }

}
