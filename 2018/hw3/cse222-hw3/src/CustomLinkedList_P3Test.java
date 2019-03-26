import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomLinkedList_P3Test {

    private CustomLinkedList_P3 p3;
    @BeforeEach
    void setUp() {
        p3 = new CustomLinkedList_P3();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void add() {
        Course temp = new Course("1","code code","deneme","deneme","deneme","deneme");
        for (int i = 0; i < p3.size(); i++) {
            p3.next();
        }   // sona gittim

        for (int i = 0; i < 3; i++) {
            p3.add(temp);
        }   // 3 eleman eklendi
        for (int i = 0; i < 3; i++) {
            System.out.println(p3.next());
        }   // o elemanlar astırıldı
    }

    @Test
    void remove() {
        for (int i = 0; i < 3; i++) {
            Course temp = (Course) p3.remove(0);
            System.out.println(temp.toString());
        }
    }

    @Test
    void next() {
        for (int i = 0; i < 3; i++) {
            Course temp = (Course) p3.next();
            System.out.println(temp.toString());
        }
    }

    @Test
    void nextInSemester() {
        for (int i = 0; i < 3; i++) {
            Course temp = (Course) p3.nextInSemester();
            System.out.println(temp.toString());
        }
    }

    @Test
    void size() {
        System.out.println(p3.size());
        for (int i = 0; i < 3; i++) {
            p3.remove(0);
        }
        System.out.println("After 0 1 2 removed");
        System.out.println(p3.size());
    }
}
