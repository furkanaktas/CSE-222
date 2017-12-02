import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by furkan on 18.04.2017.
 */
public class MainQ2 {
    public static void main(String[] args ) {






        try {
            Scanner reader = new Scanner(new File("freq.txt"));

            int size=0;
            while(reader.hasNext()){
                ++size;
                reader.nextLine();
            }
            reader.close();
            reader = new Scanner(new File("freq.txt"));


            String array[][];
            array = new String[size][];
            for (int i = 0; i< size;++i){
                array[i] = new String[2];
            }

            int i=0;
            String line;
            while (reader.hasNext()){
                String tokens[];
                line=reader.nextLine();
                tokens = line.split(" ");
                array[i][0] = tokens[0];
                array[i][1] = tokens[1];
                ++i;
            }

            HuffmanTree.HuffData[] huffArr = new HuffmanTree.HuffData[size];
            for ( i = 0; i <size ; ++i) {
                huffArr[i]= new HuffmanTree.HuffData(Double.parseDouble(array[i][1]), array[i][0].charAt(0));
            }

            HuffmanTree tree = new HuffmanTree();
            tree.buildTree(huffArr);    // ağaç oluşturuldu

            System.out.println(tree.encode("a be"));
            System.out.println(tree.decode("000101011"));


        }
        catch(FileNotFoundException e){System.out.println("Dosya açılamadı !");}









    }
}
