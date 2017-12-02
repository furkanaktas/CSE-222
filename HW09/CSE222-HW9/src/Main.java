import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        File graphData = new File("ExampleIGraphInputOrOutputFile.txt");
        Scanner scnr = new Scanner(graphData );

        //------------------------------------- Method 2 -------------------------------------------------------
        ListGraph lgObj = (ListGraph) AbstractGraph.createGraph(scnr, false, "List");

        System.out.println("Test breadthFirstSearch");
        System.out.println("-----------------------");
        for (int i = 0; i < lgObj.getNumV(); i++) {
            int[] a = lgObj.breadthFirstSearch(i);
            System.out.print(i+"  --> ");
            for (int j = 0; j < a.length; j++) {
                System.out.print(a[j]+"  ");
            }
            System.out.println("");
        }
        //--------------------------------------------------------------------------------------------------------
        //---------------------------------------- Method 3 ------------------------------------------------------
        System.out.println("\nTest isBipartiteUndirectedGraph");
        System.out.println("-------------------------------");
        if (lgObj.isBipartiteUndirectedGraph()) {
            System.out.println("Bipartite!");
        }
        else {
            System.out.println("Not Bipartite!");
        }
        //--------------------------------------------------------------------------------------------------------
        //----------------------------------------- Method 1 -----------------------------------------------------
        //Method 1 test ediyoruz ve sonucu dosyaya yazıyoruz.
        System.out.println("\nTest addRandomEdgesToGraph");
        System.out.println("--------------------------");

        lgObj.addRandomEdgesToGraph(10);
        lgObj.writeGraphToFile ("out.txt");         // writeGraphToFile
        scnr.close();
        //---------------------------------------------------------------------------------------------------------
        //---------------------------------------- Method 4 -------------------------------------------------------
        scnr = new Scanner(graphData );

        MatrixGraph mgObj = (MatrixGraph) AbstractGraph.createGraph(scnr, false, "Matrix");

        scnr.close();
        scnr = new Scanner(graphData );

        lgObj = (ListGraph) AbstractGraph.createGraph(scnr, false, "List");


        Graph arr[]= lgObj.getConnectedComponentUndirectedGraph();

        System.out.println("\nTest getConnectedComponentUndirectedGraph");
        System.out.println("-----------------------------------------");
        for (int k = 0; k < arr.length; k++) {
            System.out.println("Graph "+k+":");
            for (int i = 0; i < arr[k].getNumV(); i++)
            {
                for (int j = 0; j < arr[k].getNumV(); j++)
                {
                    if(arr[k].isEdge(i,j))
                    {
                        System.out.println(i+"  "+j);
                    }
                }
            }
        }
        //----------------------------------------------------------------------------------------------------------
    }
}
