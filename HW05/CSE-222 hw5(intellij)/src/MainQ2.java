import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by furkan on 05.04.2017.
 */
public class MainQ2 {

    public static void main(String args[]){

        FamilyTree family = new FamilyTree("Hasan");

        Scanner reader=null;

        try{
            reader = new Scanner(new FileReader("family.txt"));
            family= (FamilyTree) family.readBinaryTree(reader);
            family.preTreverser(family);

            family.add("veli","hays","ibn-Hasan");

            family.preTreverser(family);

        }
        catch (FileNotFoundException e){System.out.print(e.toString());}
        catch (IOException e){System.out.print(e.toString());}


    }
}
