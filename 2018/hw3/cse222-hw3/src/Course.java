public class Course
{
    public String semester;
    public String code;
    public String title;
    public String ECTS_credit;
    public String GTU_credit;
    public String HTL;

    Course(String semstr, String code, String title, String ECTS, String GTU, String HTL)
    {
        this.semester    = semstr;
        this.code        = code;
        this.title       = title;
        this.ECTS_credit = ECTS;
        this.GTU_credit  = GTU;
        this.HTL         = HTL;

    }

    /**
     *  This returns course info
     *
     * @return  string that is formatted course info
     */
    public  String toString()
    {
        String [] codeToken = code.split(" ");
        if (codeToken.length == 2)
            return String.format("%-4s %-4s %-4s %-63s %s  %-3s  %s",semester, codeToken[0], codeToken[1], title, ECTS_credit, GTU_credit, HTL);
        else
            return String.format("%-4s %-4s %-63s %s  %-3s  %s",semester, codeToken[0], title, ECTS_credit, GTU_credit, HTL);
    }

}