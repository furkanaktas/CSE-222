package Q2;

import General.Edge;

public class Main {

    public static void main(String [] args){

        Q2Graph graph = new Q2Graph(15, false);

        graph.makeAcyclicGraph(5, false);

        if (!graph.is_acyclic_graph(graph)){
            System.out.println("This is not acyclic graph !!");
        }

        if (!graph.is_undirected(graph)){
            System.out.println("This is not undirected graph !!");
        }

        for (int i = 0; i <5 ; i++) {
            System.out.println("0,"+i+" " +graph.is_connected(graph, 0, i));

        }

        System.out.println("5,7 " + graph.is_connected(graph, 5,7));

        System.out.println("graph ");
        graph.plot_graph(graph);


        System.out.println("\nfıgure 10.17");
        Q2Graph graph1 = new Q2Graph(10, false);

        graph1.insert(new Edge(0,3));
        graph1.insert(new Edge(0,1));
        graph1.insert(new Edge(1,2));
        graph1.insert(new Edge(1,4));
        graph1.insert(new Edge(1,6));
        graph1.insert(new Edge(1,7));
        graph1.insert(new Edge(2,8));
        graph1.insert(new Edge(2,9));


        if (!graph1.is_acyclic_graph(graph1)){
            System.out.println("This is not acyclic graph !!");
        }

        if (!graph1.is_undirected(graph1)){
            System.out.println("This is not undirected graph !!");
        }

        System.out.println("0,5 "+graph1.is_connected(graph1, 0,5));
        System.out.println("0,9 "+graph1.is_connected(graph1, 0,9));
        System.out.println("0,3 "+graph1.is_connected(graph1, 0,3));

        System.out.println("graph1 ");
        graph1.plot_graph(graph1);

    }
}
