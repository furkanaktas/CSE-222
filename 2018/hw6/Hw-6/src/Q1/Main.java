package Q1;

public class Main {

    public static void main(String [] args){
        RedBlackTree tree = new RedBlackTree();
        tree.add(100);
        tree.add(1000);

        for (int i = 0; i <61 ; i++) { // 2^6-1  63 -2 = 61 node daha
            tree.add(999-i);
        }

        RedBlackTree tree2 = new RedBlackTree();

        tree2.add(1000);
        tree2.add(100);

        for (int i = 0; i <61 ; i++) { // 2^6-1  63 -2 = 61 node daha
            tree2.add(101+i);
        }
    }
}
