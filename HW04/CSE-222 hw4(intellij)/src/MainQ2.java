import jdk.nashorn.internal.runtime.ListAdapter;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 * Created by furkan on 22.03.2017.
 */
public class MainQ2 {

    public static void main(String args[]){

        q2Test();
    }

    public static void q2Test(){

        Vector<Object> queue = new Vector<>();
        Vector<Object> recursive = new Vector<>();
        try {
            File file = new File("test.csv");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            File file2 = new File("testResult_2.csv");
            FileWriter writer = new FileWriter(file2);

            String line;
            line = reader.readLine();    // satır okundu

            while(line != null) {

                String tokens[] = line.split(",");
                try {
                    Integer.parseInt(tokens[0]);
                    myQueue<Integer> qu1 = new myQueue<>();
                    Queue<Integer> re1 = new LinkedList<>();
                    for (int i = 0; i < tokens.length; ++i) {
                        qu1.add(Integer.parseInt(tokens[i]));
                        re1.add(Integer.parseInt(tokens[i]));
                    }
                    queue.add(qu1);
                    recursive.add(re1);
                }catch (NumberFormatException e){
                    try{
                        Double.parseDouble(tokens[0]);
                        myQueue<Double> qu2 = new myQueue<>();
                        Queue<Double> re2 = new LinkedList<>();
                        for (int i = 0; i < tokens.length; ++i) {
                            qu2.add(Double.parseDouble(tokens[i]));
                            re2.add(Double.parseDouble(tokens[i]));
                        }
                        queue.add(qu2);
                        recursive.add(re2);
                    }catch (NumberFormatException ex){

                        if (tokens[0].length() == 1) {
                            myQueue<Character> qu3 = new myQueue<>();
                            Queue<Character> re3 = new LinkedList<>();
                            for (int i = 0; i < tokens.length; ++i) {
                                qu3.add(tokens[i].charAt(0));
                                re3.add(tokens[i].charAt(0));
                            }
                            queue.add(qu3);
                            recursive.add(re3);
                        } else if(tokens[0].length() > 1) {
                            myQueue<String > qu4 = new myQueue<>();
                            Queue<String> re4 = new LinkedList<>();
                            for (int i = 0; i < tokens.length; ++i) {
                                qu4.add(tokens[i]);
                                re4.add(tokens[i]);
                            }
                            queue.add(qu4);
                            recursive.add(re4);
                        }

                    }
                }
                line = reader.readLine();
            }
            reader.close();

            int index;
            for(int i=0;i<queue.size();++i) {
                index=queue.size()-1-i;
                myQueue<Object> temp = (myQueue) queue.get(index);
                temp.reverse();
                write(writer, temp);
                writer.flush();
            }

            writer.write("\nRecursive  \n");   // get()
            writer.flush();

            for(int i=0;i<queue.size();++i) {
                index=recursive.size()-1-i;
                Queue<Object> temp = (Queue) recursive.get(index);
                reverseQueue(temp);
                write(writer, temp);
                writer.flush();

            }

        }
        catch (FileNotFoundException ex) {System.out.println("test.cvs is not opened !");}
        catch (IOException ex) {System.out.println("IO error !");}
    }

    public static void reverseQueue(Queue obj){
        if ( obj.size()-1 == 0) {
            obj.add(obj.poll());
            return;
        }
        else
        {
            Object temp =obj.poll();
            reverseQueue(obj);
            obj.add(temp);
        }
        return ;
    }

    /**
     *  Yollanan yazmak için açılan dosyaya , queue içindeki verileri size ve performans bilgisiyle beraber yazar.
     *
     * @param writer dosyaya yazmak için açılan writer(FileWriter)
     * @param queue  dosyaya yazılacak queue
     * @throws IOException  dosya sorunları için
     */
    public static void write(FileWriter writer, myQueue queue) throws IOException {

        int size = queue.size();                // myQueue  writer
        for (int i = 0; i < size; ++i) {

            if(i != size-1){

                writer.write(queue.get(i) + ",");
                writer.flush();
            }
            else
            {
                writer.write(queue.get(i) + "\n");
                writer.flush();
            }
        }
    }

    /**
     *  Yollanan yazmak için açılan dosyaya , queue içindeki verileri size ve performans bilgisiyle beraber yazar.
     *
     * @param writer dosyaya yazmak için açılan writer(FileWriter)
     * @param queue  dosyaya yazılacak queue
     * @throws IOException  dosya sorunları için
     */
    public static void write(FileWriter writer, Queue queue) throws IOException {

        int size = queue.size();
        for (int i = 0; i < size; ++i) {                // Queue writer

            if(i != size-1){

                writer.write(queue.poll() + ",");
                writer.flush();
            }
            else
            {
                writer.write(queue.poll() + "\n");
                writer.flush();
            }
        }
    }
}
