
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

class ExtendLinkedList_P2Test {

    private ExtendLinkedList_P2<Course> p2;
    private ListIterator iter;
    @BeforeEach
    void setUp() {
        p2 = new ExtendLinkedList_P2();
        iter = p2.listIterator();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void enable() {
        for (int i = 0; i < 3; i++) {
            p2.disable(i);
        }
        p2.showDisabled();
        for (int i = 0; i < 3; i++) {
            p2.enable(i);
        }
        p2.showDisabled();
    }

    @Test
    void disable() {
        for (int i = 0; i < 3; i++) {
            p2.disable(i);
        }
        p2.showDisabled();
    }

    @Test
    void showDisabled() {
        for (int i = 0; i < 3; i++) {
            p2.disable(i);
        }
        p2.showDisabled();
    }

    @Test
    void get() {
        for (int i = 0; i <3 ; i++) {       // i max 54 , şuanki dosyadaki ders sayısı
            System.out.println(p2.get(i).toString());
        }
        for (int i = 0; i < 3; i++) {
            p2.disable(i);
        }
        System.out.println("After 0 1 2 disaled");
        for (int i = 0; i <3 ; i++) {
            System.out.println(p2.get(i).toString());
        }
    }

    @Test
    void set() {
        Course temp = new Course("1","code code","deneme","deneme","deneme","deneme");
        for (int i = 0; i < 3; i++) {
            System.out.println(p2.get(i));
        }

        for (int i = 0; i < 3; i++) {
            p2.set(i, temp);
        }

        System.out.println("After set(i, element) !!");
        for (int i = 0; i < 3; i++) {
            System.out.println(p2.get(i));
        }
    }

    @Test
    void size() {
        System.out.println(p2.size());
        for (int i = 0; i < 3; i++) {
            p2.disable(i);
        }
        System.out.println("After 0 1 2 disaled");
        System.out.println(p2.size());
    }

    @Test
    void listIterator() {
        // this just return my own CustomIterator
    }

    @Test
    void add() {
        Course temp = new Course("1","code code","deneme","deneme","deneme","deneme");

        int index = p2.size()-1;
        for (int i = index; i > index-3; i--) {
            System.out.println(p2.get(i));
        }

        for (int i = 0; i < 3; i++) {
            p2.add(temp);
        }

        System.out.println("After add !!");
        index = p2.size()-1;
        for (int i = index; i > index-3; i--) {
            System.out.println(p2.get(i));
        }
    }

    @Test
    void add1() {
        Course temp = new Course("1","code code","deneme","deneme","deneme","deneme");
        for (int i = 0; i < 3; i++) {
            System.out.println(p2.get(i));
        }

        for (int i = 0; i < 3; i++) {
            p2.add(i, temp);
        }

        System.out.println("After add(i, element) !!");
        for (int i = 0; i < 3; i++) {
            System.out.println(p2.get(i));
        }

    }

    @Test
    void remove() {
        for (int i = 0; i < 3; i++) {
            System.out.println(p2.get(i));
        }

        for (int i = 0; i < 3; i++) {
            p2.remove(0);
        }

        System.out.println("After remove !!");
        for (int i = 0; i < 3; i++) {
            System.out.println(p2.get(i));
        }
    }
}