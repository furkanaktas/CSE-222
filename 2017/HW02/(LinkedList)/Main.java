/**
 *
 * @author furkan
 */

import java.util.Scanner;
//Linked list
public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        Staff staff = new Staff();
        User user = new User();
        
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++"
        + "++++++++++++\n+++++++++++ Welcome To Library Management System +++"
    + "++++++++\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        
        boolean exit=true;
        while(exit == true)
        {
            int option;
            Scanner input = new Scanner(System.in);
            
            System.out.print("\n       ++++++++++++++     1. Staff     "
            + "++++++++++++++     \n         ++++++++++++++   2. User    +++++"
       + "+++++++++    \n              ++++++++++++++++++++++++++++++++\n"
                    + "                              ");
            
            option = input.nextInt();
            switch (option) {
                case 1:
                    staff.managementShell();
                    exit = askToContinue();
                    break;
                case 2:
                    user.managementShell();
                    exit = askToContinue();
                    break;
                default:
                    System.out.println("      ++++++++++ You entered invalid"
                                                    + " option! ++++++++++ ");
                    exit = askToContinue();
                    break;
            }
        }
        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++"
 +"++++++++++++\n+++++++++++++++++++++++++ Good Bye +++++++++++++++++++++++++\n"
             + "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
    
    /**
     * This function ask user to continue process.
     * 
     * @return  boolean  according to answer
     */
    public static boolean askToContinue(){
        
        String answer; 
        Scanner input = new Scanner(System.in);
        
        System.out.print("\n         Do you want to make process? (Yes/No) : ");
        answer = input.nextLine();
        
        return answer.equalsIgnoreCase("yes"); // yes ise return true

    }
    
}