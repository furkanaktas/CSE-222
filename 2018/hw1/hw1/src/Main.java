/**
 *
 * @author furkan
 */

import java.util.Scanner;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main (String[]args){

        Scanner input = new Scanner(System.in);
        Receptionist receptionist = new Receptionist();
        Guest guest = new Guest();

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n"+
                           "++++++++++++++++++  Welcome To Hotel Management System  ++++++++++++++++++\n"+
                           "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

        boolean exit = true;
        while (exit == true) {
            String option;


            System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" +
                             "+++++++++++++++++++++     1 for Receptionist Login   +++++++++++++++++++++\n" +
                             "+++++++++++++++++++++     2 for Guest Login          +++++++++++++++++++++\n" +
                             "+++++++++++++++++++++     3 for Register             +++++++++++++++++++++\n" +
                             "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" +
                             "                                   ");

            option = input.next();
            option += input.nextLine();
            String id="", pass="";
            switch (option) {
                case "1":
                    if ( true == receptionist.login("staff.csv", input) )
                        receptionist.userInteface(input);

                    exit = askToContinue(input);
                    break;

                case "2":

                    if ( true == guest.login("guests.csv", input) )
                        guest.userInteface(input);

                    exit = askToContinue(input);
                    break;

                case "3":

                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("+++++++++   1 for new Guest                                      +++++++++");
                    System.out.println("+++++++++   2 for new Receptionist (need to sign in as staff)    +++++++++");
                    System.out.print(  "                                  ");


                    option = input.next();
                    option += input.nextLine();
                    if (option.contentEquals("1"))
                    {
                        guest.register("guests.csv", input);
                    }
                    else if (option.contentEquals("2"))
                    {
                        if ( true == receptionist.login("staff.csv", input) ) {
                            receptionist.register("staff.csv", input);
                        }
                    }
                    else
                    {
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("++++++++++++++++++      You entered invalid option!      +++++++++++++++++");
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                    }

                    exit = askToContinue(input);
                    break;

                default:
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("++++++++++++++++++      You entered invalid option!      +++++++++++++++++");
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

                    exit = askToContinue(input);
                    break;
            }
        }
        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n"+
                             "++++++++++++++++++++++++++++++++ Good Bye ++++++++++++++++++++++++++++++++\n"+
                             "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");


    }

    /**
     *  This function ask user to continue process.
     *
     *  @param input for user input and testing this method
     *  @return  boolean  according to answer
     */
    public static boolean askToContinue(Scanner input){

        String answer;
        //Scanner input = new Scanner(System.in);

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.print(  "            Do you want to make process? (Yes/No) : ");
        answer = input.nextLine();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

        return answer.equalsIgnoreCase("yes"); // yes ise return true
    }
}
