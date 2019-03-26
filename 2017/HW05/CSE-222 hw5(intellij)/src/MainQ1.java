import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by furkan on 04.04.2017.
 */
public class MainQ1 {

    public static void main(String args[]){
        BinaryTree<Integer> tree = new BinaryTree<>();
        BinarySearchTree<Integer> searchTree = new BinarySearchTree<>();

        Scanner reader=null;

        try{
            reader = new Scanner(new FileReader("test.txt"));
            tree = (BinaryTree) tree.readBinaryTree(reader);
            tree.preTreverser(tree);
            //  System.out.print(a.toString());
            reader.close();


            reader = new Scanner(new FileReader("test.txt"));
            searchTree = (BinarySearchTree) searchTree.readBinaryTree(reader);
            searchTree.levelTreverser(searchTree);
            //  System.out.print(a.toString());
            reader.close();
        }
        catch (FileNotFoundException e){System.out.print("File not found !\n");}
        catch (IOException e){System.out.print("IO error !\n");}


    }
}
