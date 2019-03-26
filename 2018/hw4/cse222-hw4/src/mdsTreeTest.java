import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class mdsTreeTest {
    mdsTree tree;
    @BeforeEach
    void setUp() {
        tree = new mdsTree(40, 45);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void add() {
        System.out.println("------------ add -------------");
        tree.print();
        System.out.println("After the adding process");
        tree.add(15, 70);
        tree.add(70, 10);
        tree.add(69, 50);
        tree.add(66, 85);
        tree.add(85, 90);
        tree.print();
    }

    @Test
    void contains() {
        add();
        System.out.println("------------ contains -------------");

        if (tree.contains(40, 45))
            System.out.println("true");
        else
            System.out.println("false");
        if (tree.contains(69,50))
            System.out.println("true");
        else
            System.out.println("false");

    }

    @Test
    void find() {
        add();
        System.out.println("------------ find -------------");

        BinaryTree.Node node = tree.find(40, 45);
        System.out.println("node : " + node.toString());
        node = tree.find(70, 10);
        System.out.println("node : " + node.toString());
    }

    @Test
    void delete() {
        add();
        System.out.println("---------------- delete --------------");

        Vector te = tree.delete(40, 45);
        //System.out.println(te.get(0)+"  "+ te.get(1));
        System.out.println("After delete 40,45");
        tree.print();
        te = tree.delete(70, 10);
        //System.out.println(te.get(0)+"  "+ te.get(1));
        System.out.println("After delete 70,10");
        tree.print();

    }

    @Test
    void remove() {
        add();
        System.out.println("---------------- remove --------------");

        if (tree.remove(40, 45))
            System.out.println("true");
        else
            System.out.println("false");

        System.out.println("After remove 40,45");
        tree.print();

        if (tree.remove(69,50))
            System.out.println("true");
        else
            System.out.println("false");

        System.out.println("After remove 69,50");
        tree.print();
    }
}