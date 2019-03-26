import java.util.Vector;

public class Main {

    public static void main(String[] args) {

        GeneralTree tree = new GeneralTree("parent");

        tree.add("parent", "1");
        tree.add("parent", "2");
        tree.add("parent", "3");

        tree.add("1", "11");
        tree.add("1", "12");
        tree.add("1", "13");
        tree.add("2", "21");
        tree.add("2", "22");
        tree.add("3", "31");

        tree.add("22", "221");


        tree.add("11", "111");
        tree.add("31", "311");
        tree.add("31", "312");


        tree.levelOrderPrint();
        System.out.println("\n");

        tree.postOrderSearch("312");
        System.out.println("\n");

        System.out.println(tree.preOrderTraverse());


        System.out.println("\n--------------------part2------------------------\n");


        mdsTree<Integer> tree2 = new mdsTree<>(13,15,71);
        tree2.add(15,12,11);
        tree2.add(2,3,4);
        tree2.add(13,6,7);

        tree2.add(7,8,9);
        tree2.add(16,17,18);
        tree2.add(78,13,19);
        tree2.add(8,90,900);
        tree2.add(61,61,61);

        tree2.print();

        Vector te = tree2.delete(2,3,4);
        BinaryTree.Node node = tree2.find(7,8,9);
        System.out.println("node : " + node.toString());
        node = tree2.find(16,17,18);
        System.out.println("node : " + node.toString());
        node = tree2.find(61,61,61);
        System.out.println("node : " + node.toString());
        //tree.delete(70, 10);
        //      System.out.println(te.get(0)+"  "+ te.get(1));
        tree2.print();


    }
}
