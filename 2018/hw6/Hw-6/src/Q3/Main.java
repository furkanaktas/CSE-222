package Q3;

public class Main {

    public static void main(String [] args){

        BinaryTree qwe = new BinaryTree();

        qwe.root = new BinaryTree.Node(10);

        qwe.root.left = new BinaryTree.Node(8);

        qwe.root.right = new BinaryTree.Node(14);
        qwe.root.right.right = new BinaryTree.Node(15);
        //qwe.root.right.right.right = new BinaryTree.Node(16);
        //qwe.root.right.right.right.right = new BinaryTree.Node(17);
        //qwe.root.right.right.right.right.right = new BinaryTree.Node(18);

        AVLTree tree = new AVLTree(qwe);



        for (int i = 0; i <1000 ; i++) {
            //Integer t = (int)Math.random()*1000;
            if (!tree.contains(i))
            {
                if (!tree.add(i))
                    System.out.println("not added");

                if (!tree.isAVL(tree.root)){
                    System.out.println("Tree is not AVL now!!!");
                }
            }
        }
        if (tree.isAVL(tree.root)){
            System.out.println("Still AVL !!!");
        }else
            System.out.println(tree.toString());


        for (int i = 1; i <1000 ; i*=2) {
            Integer t = (int)(Math.random()*1000);
            if (tree.contains(t))
            {
                System.out.println(t+ " will delete");
                tree.delete(t);
                if (!tree.contains(t)) {
                    System.out.println(t+ " is deleted");
                    if (!tree.isAVL(tree.root)){
                        System.out.println("Tree is not AVL now!!!");
                    }
                }
            }
        }

    }
}
