import java.util.LinkedList;
import java.util.ListIterator;

public class Main {


    public static void main(String[] args) {

        System.out.println("--------------------------    part 1        ------------------------------\n");

        GTU_Courses_P1 courses = new GTU_Courses_P1();


        System.out.println("listSemesterCourses");
        LinkedList<Course> listCourse = courses.listSemesterCourses(2);
        for (int i = 0; i < listCourse.size(); ++i) {
            System.out.println(listCourse.get(i).toString());
        }

        System.out.println("\ngetByCode");
        Course course = courses.getByCode("CSE 107");
        System.out.println(course.toString());


        System.out.println("\ngetByRange");
        listCourse = courses.getByRange(2, 6);
        for (int i = 0; i < listCourse.size(); ++i) {
            System.out.println(listCourse.get(i).toString());
        }

//----------------------------------------------------------------------------------------------------------------------
        System.out.println("\n--------------------------    part 2        ------------------------------\n");

        ExtendLinkedList_P2<Course> a = new ExtendLinkedList_P2<>();

        for (int i = 0; i < 6; i++) {
            System.out.println(a.get(i).toString());
        }


        a.disable(0);
        a.disable(2);
        a.disable(4);
        a.showDisabled();

        a.remove(0);
        System.out.println("0: "+a.get(0).toString());

        //a.enable(0);
        //System.out.println(a.get(0).toString());

        a.showDisabled();

        a.enable(2);
        System.out.println(a.get(1).toString()+"\n");
        a.showDisabled();
        a.enable(0);
        System.out.println("\n"+a.get(0).toString());

        ListIterator<Course> iter = a.listIterator();


        System.out.println("\n");


        Course temp = new Course("1","code code","deneme","deneme","deneme","deneme");
        System.out.println(iter.next().toString());
        System.out.println(iter.next().toString());
        iter.remove();
        iter.add(temp);
        //iter.set(temp);
        System.out.println(iter.next().toString());

//---------------------------------------------------------------------------------------------------------------------

        System.out.println("\n--------------------------    part 3        ------------------------------\n");

        CustomLinkedList_P3 p3 = new CustomLinkedList_P3();

        temp = new Course("1","code code","deneme","deneme","deneme","deneme");

        p3.add(temp);

        System.out.println(p3.next());
        System.out.println(p3.next());
        System.out.println(p3.nextInSemester());
        System.out.println(p3.nextInSemester());
        System.out.println(p3.nextInSemester());

        System.out.println(p3.remove(p3.size()-1));

    }
}
