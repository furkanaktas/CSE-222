import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneralTreeTest {

    GeneralTree tree,tree2;
    @BeforeEach
    void setUp() {
        tree  = new GeneralTree("parent");
        tree2 = new GeneralTree("William I");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void add() {
        for (int i = 0; i < 5; i++) {
            tree.add("parent", "child"+i);
            tree.add("parent", "child1"+i);
            tree.add("parent", "child2"+i);

            tree.add("child"+i, "child"+i);
            tree.add("child1"+i, "child"+i);
            tree.add("child2"+i, "child"+i);
        }

        tree2.add("William I", "Robert");
        tree2.add("William I", "William II");
        tree2.add("William I", "Adela");
        tree2.add("William I", "Henry I");

        tree2.add("Robert", "William");
        tree2.add("Adela", "Stephan");
        tree2.add("Henry I", "William");
        tree2.add("Henry I", "Matilda");

        tree2.add( "Matilda", "Henry II");

        tree2.add( "Henry II", "Henry");
        tree2.add( "Henry II", "Richard I");
        tree2.add( "Henry II", "Geoffrey");
        tree2.add( "Henry II", "John");

        tree2.add( "Geoffrey", "Arthur");
        tree2.add( "John", "Henry III");
        tree2.add( "John", "Richard");


        tree2.add( "Henry III", "Edward I");
        tree2.add( "Henry III", "Edmund");

        tree2.add( "Edward I", "Edward II");
        tree2.add( "Edward I", "Thomas");
        tree2.add( "Edward I", "Edmund");

        tree2.add( "Edward II", "Edward III");



    }

    @Test
    void postOrderSearch() {
        add();
        BinaryTree.Node node = tree.postOrderSearch("child3");
        System.out.println(node.data);

        node = tree2.postOrderSearch("John");
        System.out.println(node.data);

    }

    @Test
    void levelOrderSearch() {
        add();
        BinaryTree.Node node = tree.levelOrderSearch("child3");
        System.out.println(node.data);
        node = tree2.levelOrderSearch("John");
        System.out.println(node.data);

    }

    @Test
    void preOrderTraverse() {
        add();
        System.out.println(tree.preOrderTraverse());
        System.out.println(tree2.preOrderTraverse());
    }
}