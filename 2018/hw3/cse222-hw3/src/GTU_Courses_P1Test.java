import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class GTU_Courses_P1Test {

    private GTU_Courses_P1 p1;
    private LinkedList list;

    @BeforeEach
    void setUp() {
        p1 = new GTU_Courses_P1();
        list = new LinkedList();
    }


    @Test
    void getByCode() {

        System.out.println(p1.getByCode("CSE 101").toString());
        System.out.println(p1.getByCode("CSE 102").toString());
        System.out.println(p1.getByCode("CSE 107").toString());
        System.out.println(p1.getByCode("MatH 101").toString());


    }

    @Test
    void listSemesterCourses() {

        for (int i = 1; i <= 8; i++) {   // 8 semester var
            list = p1.listSemesterCourses(i);
            for (int j = 0; j <list.size() ; j++) {
                System.out.println(list.get(j));
            }
            System.out.println("\n");
        }
    }

    @Test
    void getByRange() {

        for (int i = 0; i+5 < 54; i++) {   // dosyada 54 satırlık ders var
            list = p1.getByRange(i,i+5);
            for (int j = 0; j <list.size() ; j++) {
                System.out.println(list.get(j));
            }
            System.out.println("\n");
        }
    }
}