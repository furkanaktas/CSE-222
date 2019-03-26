import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class GTU_Courses_P1 {

    private LinkedList<Course> courses;

    GTU_Courses_P1()
    {
        courses = new LinkedList<>();

        try {
            Scanner reader = new Scanner(new File("Courses(CSV).csv"));

            reader.nextLine();
            while (reader.hasNextLine() == true)
            {
                String line = reader.nextLine();
                String [] tokens = line.split(";");     // dosyadan okunan dersler  listeye eklendi

                for (int i = 0; i < 6; ++i) {
                    tokens[i] = tokens[i].trim();
                }
                courses.add(new Course(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]));
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    /**
     *  this returns the course that name is given course code
     *
     * @param code  course code
     * @throws IllegalStateException  if there isn't any course as "code" named
     * @return  course or null
     */
    public Course getByCode(String code)
    {
        for (int i = 0; i < courses.size(); ++i) {
            if (courses.get(i).code.compareToIgnoreCase(code) == 0)
                return courses.get(i);
        }
        throw new IllegalStateException("There isn't any course as \"code\" named");
    }

    /**
     *  This returns the courses in the given semester number
     *
     * @param semester  number of semester
     * @return  the courses in the given semester number
     * @throws IndexOutOfBoundsException  we have 8 semester then index between 1-8
     * @throws IllegalStateException    if there isn't any course in the given semester
     */
    public LinkedList<Course> listSemesterCourses(int semester)
    {
        if (semester >8 || semester < 1)
            throw new IndexOutOfBoundsException("Only 1-8 semesters are available!!");

        LinkedList<Course> list = new LinkedList<>();

        for (int i = 0; i < courses.size(); ++i) {
            if (Integer.parseInt(courses.get(i).semester) == semester)
                list.add(courses.get(i));
        }
        if (list.size() == 0)
            throw new IllegalStateException("There isn't any course in this semester!!");

        return list;
    }

    /**
     *  This returns the Courses between start_index and last_index
     *
     * @param start_index   first index to return
     * @param last_index    last index to return
     * @return              ArrayList with Courses
     * @throws IndexOutOfBoundsException    if indexes are not between 0 and size
     * @throws IllegalStateException if there isn't any course in the given range
     */
    public LinkedList<Course> getByRange(int start_index, int last_index) throws IndexOutOfBoundsException
    {
        if (start_index < 0 || last_index >= courses.size())
            throw new IndexOutOfBoundsException();

        if (start_index > last_index)
            throw new IllegalStateException(start_index+" > "+last_index+" this is not valid !!");

        LinkedList<Course> list = new LinkedList<>();

        for (int i = start_index; i <= last_index; ++i) {
            list.add(courses.get(i));
        }

        return list;
    }
}
