/**
 * Created by furkan on 08.05.2017.
 */
public class Main {

    public static void main(String[] args){


        AVLTree tree = new AVLTree();

        tree.add("Nush");
        tree.add("ile");
        tree.add("uslanmayanı");
        tree.add("etmeli");
        tree.add("tekdir");
        tree.add("uslanmayanın");
        tree.add("hakkı");
        tree.add("kötektir");

        System.out.println(tree.toString());
    }
}
