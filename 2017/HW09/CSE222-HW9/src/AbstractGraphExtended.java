import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by furkan on 18.05.2017.
 */
public abstract class AbstractGraphExtended extends AbstractGraph{

    private  boolean []visited; //  getConnectedComponentUndirectedGraph  için
    private  int []painted;  // isBipartiteUndirectedGraph  için

    public AbstractGraphExtended(int numV, boolean directed)
    {
        super(numV,directed);

    }

    /**
     *
     * @param edgeLimit  eklenecek üst sınır
     * @return  eklenen edge sayısı
     */
    public int addRandomEdgesToGraph (int edgeLimit)
    {
        int random,src,dest,num;
        random = (int )(Math.random() * edgeLimit + 1);
        System.out.println("random : "+random);
        System.out.println("Eklenenler : ");

        num=0;
        for (int i = 0; i < random ; i++) {
            src=(int )(Math.random() * getNumV() + 1);
            dest=(int )(Math.random() * getNumV() + 1);

            if(src < getNumV() && dest < getNumV()) {
                if(!isEdge(src, dest) && !isEdge(dest,src)) {
                    ++num;
                    System.out.println(src+","+dest);
                    insert(new Edge(src, dest, 1));
                }
            }
        }
        System.out.println("(Eğer undirected ise tersleride eklenir!)");
        System.out.println("Eklenme miktarı: "+num);

        return num;
    }

    /**
     *
     * @param start ağacı istenilen vertex
     * @return  istenilen vertex'in ağacı
     */
    public int [] breadthFirstSearch (int start)
    {
        Queue < Integer > theQueue = new LinkedList < Integer > ();
        // Declare array parent and initialize its elements to –1.
        int[] parent = new int[this.getNumV()];
        for (int i = 0; i < this.getNumV(); i++) {
            parent[i] = -1;
        }
        // Declare array identified and
        // initialize its elements to false.
        boolean[] identified = new boolean[this.getNumV()];
    /* Mark the start vertex as identified and insert it
       into the queue */
        identified[start] = true;
        theQueue.offer(start);
    /* While the queue is not empty */
        while (!theQueue.isEmpty()) {
      /* Take a vertex, current, out of the queue.
       (Begin visiting current). */
            int current = theQueue.remove();
      /* Examine each vertex, neighbor, adjacent to current. */
            Iterator < Edge > itr = this.edgeIterator(current);
            while (itr.hasNext()) {
                Edge edge = itr.next();
                int neighbor = edge.getDest();
                // If neighbor has not been identified
                if (!identified[neighbor]) {
                    // Mark it identified.
                    identified[neighbor] = true;
                    // Place it into the queue.
                    theQueue.offer(neighbor);
          /* Insert the edge (current, neighbor)
             into the tree. */
                    parent[neighbor] = current;
                }
            }
            // Finished visiting current.
        }
        return parent;

    }

    /**
     *
     * @return  connected graphları içeren graph array
     */
    public Graph [] getConnectedComponentUndirectedGraph ()
    {

        ArrayList<Graph> grph = new ArrayList<>();

        visited = new boolean[getNumV()];
        for (int i = 0; i < getNumV(); i++) {
            visited[i]=false;
        }

        int src=0;
        boolean stop = true;
        while (stop)
        {
            ArrayList<Edge> edgeArr = new ArrayList<>();
            Graph temp;

            traversal(src,edgeArr);

            ArrayList<Integer> vertexes = new ArrayList<>();
            int key;
            for (int i = 0; i < edgeArr.size(); i++)
            {
                key=0;
                for (int j = 0; j < vertexes.size(); j++) {
                    if(edgeArr.get(i).getSource() == vertexes.get(j))
                        key=1;
                }
                if (key ==0)
                    vertexes.add(edgeArr.get(i).getSource());

                key=0;
                for (int j = 0; j < vertexes.size(); j++) {
                    if(edgeArr.get(i).getDest() == vertexes.get(j))
                        key=1;
                }
                if (key ==0)
                    vertexes.add(edgeArr.get(i).getDest());           // tüm vertex ler  ekleniyor
              
            }
            for (int i = 0; i <vertexes.size() ; i++) {
                int min=vertexes.get(i);
                int index=i;
                for (int j = i; j <vertexes.size() ; j++) {           // vertexler  küçükten büyüğe sıralanıyor
                    if(min > vertexes.get(j))
                    {
                        index=j;
                        min=vertexes.get(j);
                    }
                }
                int tempVer=vertexes.get(i);
                vertexes.set(i,vertexes.get(index));
                vertexes.set(index,tempVer);
            }

            for (int i = 0; i < edgeArr.size(); i++) {
                int source=0,dest=0;
                boolean stop1=false,stop2=false;
                for (int j = 0; j < vertexes.size(); j++) {
                    if(edgeArr.get(i).getSource() != vertexes.get(j) && !stop1)
                    {
                        ++source;               // eşleşme olana kadar artırıldı yeni sayısı verildi
                    }
                    else
                    {
                        stop1=true;             // dest daha fazla artmasını engellemek için
                    }
                    if(edgeArr.get(i).getDest() != vertexes.get(j) && !stop2)
                    {
                        ++dest;                 // eşleşme olana kadar artırıldı yeni sayısı verildi
                    }
                    else
                    {
                        stop2=true;             // dest daha fazla artmasını engellemek için
                    }
                }
                edgeArr.set(i,new Edge(source,dest,1));  //eski edge yeni sayılarla güncellendi

            }

            if(this instanceof MatrixGraph)
            {
                temp = new MatrixGraph(vertexes.size(),isDirected());
            }
            else
            {
                temp = new ListGraph(vertexes.size(),isDirected());
            }

            for (int i = 0; i < edgeArr.size(); i++) {
                temp.insert(edgeArr.get(i));            // edgeler graph a eklendi
            }

            for (int i = 0; i <getNumV() ; i++)
            {
                if(visited[i] == true && i == getNumV()-1)
                {
                    stop = false;       // tüm vertex ler gezildiyse
                }

                if(visited[i] == false )
                {
                    src = i;
                    i = getNumV(); // döngü sonlanması için
                }
            }
            if(vertexes.size() !=0)
                grph.add(temp);
        }

        Graph result[] = new Graph[grph.size()];
        for (int i = 0; i < grph.size(); i++) {             // ArrayList  ten  graph array e alındı
            result[i]=grph.get(i);
        }

        return result;
    }

    /**
     *
     * @return  bipartitute olamasına göre true yada false
     */
    public boolean isBipartiteUndirectedGraph (){

        painted= new int[getNumV()];
        for (int i = 0; i < getNumV(); i++) {
            painted[i]=-1;
        }

        ArrayList<Edge> edgeArr = new ArrayList<>();

        for (int i = 0; i <getNumV() ; i++) {

            int arr[] = breadthFirstSearch(i);

            for (int j = 0; j < arr.length; j++) {
                if(arr[j] !=-1)
                {
                    int key=0;
                    Edge temp = new Edge(arr[j],j);
                    for (int k = 0; k < edgeArr.size(); k++) {
                        if(temp.equals(edgeArr.get(k)))
                        {
                            key=1;
                        }
                    }
                    if(key ==0)
                    {
                        edgeArr.add(temp);
                    }
                }
            }
        }
        for (int i = 0; i < edgeArr.size(); i++) {
            Edge temp = edgeArr.remove(0);
            if (painted[edgeArr.get(i).getSource()] != -1)
            {
                if(painted[temp.getSource()] !=-1 && painted[temp.getDest()] !=-1)
                {
                    if (painted[temp.getSource()] == painted[temp.getDest()])
                        return false;
                }
                else
                {
                    if (painted[temp.getSource()] == painted[temp.getDest()])
                        return false;

                    if (painted[temp.getSource()] == 0)
                        painted[temp.getDest()] = 1;
                    else
                        painted[temp.getDest()] = 0;
                }
            }
            else
            {
                painted[temp.getSource()] =0;
                painted[temp.getSource()] =1;
            }
        }
        return true;
    }

    /**
     *
     * @param filename  edgelrin yazılacağı dosya ismi
     */
    public void writeGraphToFile(String filename){

        BufferedWriter writer;
        ArrayList<Edge> tempEdge;

        try {
            writer = new BufferedWriter(new FileWriter(filename));
            writer.write(getNumV()+"\n");
            writer.flush();

            tempEdge= new ArrayList<>();
            for (int i = 0; i < getNumV(); i++)
            {
                for (int j = 0; j < getNumV(); j++)
                {
                    if(isEdge(i,j))
                    {
                        int key=0;
                        for (int k = 0; k < tempEdge.size(); ++k)
                        {
                            if (j == tempEdge.get(k).getSource() && i == tempEdge.get(k).getDest())
                            {
                                key=1;
                            }
                        }
                        if (key ==0)
                        {
                            tempEdge.add(new Edge(i,j));
                        }
                    }
                }
            }
            for (int i = 0; i <tempEdge.size() ; i++) {
                writer.write(tempEdge.get(i).getSource()+" "+tempEdge.get(i).getDest()+"\n");
                writer.flush();
            }
        } catch (IOException ex) {
            System.out.println("Dosya açılamadı");
        }

    }

    /**
     *
     * @param start  hangi vertexden başlayacağı
     * @param edgeArr   edgelerin ekleneceği ArrayList
     */
    private void traversal(int start, ArrayList<Edge> edgeArr) {

        Queue < Integer > theQueue = new LinkedList();
    /* Mark the start vertex as identified and insert it into the queue */
        visited[start] = true;
        theQueue.offer(start);
    /* While the queue is not empty */
        while (!theQueue.isEmpty())
        {
        /* Take a vertex, current, out of the queue. (Begin visiting current). */
            int current = theQueue.remove();
            /* Examine each vertex, neighbor, adjacent to current. */
            Graph temp = this;
            Iterator < Edge > itr = temp.edgeIterator(current);
            while (itr.hasNext()) {
                Edge edge = itr.next();
                int key=0;
                for (int i = 0; i < edgeArr.size() ; i++) {
                    if (edge.getDest() == edgeArr.get(i).getSource() && edge.getSource() == edgeArr.get(i).getDest())
                        key=1;
                }
                if(key == 0)
                    edgeArr.add(edge);

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
    }

}
