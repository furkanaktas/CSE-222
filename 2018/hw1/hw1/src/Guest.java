/**
 *
 * @author furkan
 */
import java.io.*;
import java.util.Scanner;

public class Guest  extends Hotel implements UI{

    public Guest()  {      // Constructor

        arrayRoom = new String [16];

        for(int i=0;i<16;++i){
            arrayRoom[i] ="\0";
        }

        try {
            Scanner temp = new Scanner(new FileReader("guests.csv"));
        } catch (FileNotFoundException e) {
            FileWriter writer = null;
            try {
                writer = new FileWriter(new File("guests.csv"));
                writer.write("ID,PASSWORD,NAME,SURNAME\n");
                writer.flush();
                writer.write("guest,guest,name,surname\n");
                writer.flush();
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }

    /**
     *  It guides user with user's choises.
     *
     *  @param input for user input and testing this method
     */
    @Override
    public void userInteface(Scanner input) {

        boolean exit=true;
        while(exit == true)
        {
            String option;

            System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n"+
                             "++++++++++++++++++++      1 for Book a room             ++++++++++++++++++\n"+
                             "++++++++++++++++++++      2 for Cancel reservation      ++++++++++++++++++\n"+
                             "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n"+
                             "                                  ");

            option = input.next();
            option += input.nextLine();

            switch (option) {
                case "1":
                    bookRoom(input);
                    exit = super.askToContinue(input);
                    break;
                case "2":
                    cancelReservation(input);
                    exit = super.askToContinue(input);
                    break;
                default:
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("++++++++++++++++++      You entered invalid option!      +++++++++++++++++");
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                    exit = super.askToContinue(input);
                    break;
            }
        }
    }

    /**
     *  It book the room that is wanted
     *
     *  @param input for user input and testing this method
     */
    private void bookRoom(Scanner input)
    {
        try {
            arrayRoom = readFile("rooms.csv", arrayRoom);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        boolean stat = true;
        while (stat)
        {

            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.print("                   Which room do you want? (1-15): ");
            int number= getNumber(input);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            String token [] = arrayRoom[number].split(",");
            if (token[1].contentEquals("none"))
            {
                arrayRoom[number] = number+","+"booked"+","+ID+","+name+","+surname;
                writeFile("rooms.csv", arrayRoom);
                System.out.println("++++++++++++      You have successfully booked the room!      ++++++++++++");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                stat = false;
            }
            else
            {
                System.out.println("+++++++++++++++++      This room is already in use!      +++++++++++++++++");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

                System.out.print(  "         Do you want to choose another room? (yes/no): ");

                //Scanner reader = new Scanner(System.in);
                String ans = input.next();
                ans += input.nextLine();
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                if (!ans.equalsIgnoreCase("yes"))
                    stat = false;
            }
        }
    }

    /**
     *  It cancels the room that is wanted
     *
     *  @param input for user input and testing this method
     */
    private void cancelReservation(Scanner input)
    {
        try {
            arrayRoom = readFile("rooms.csv", arrayRoom);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        boolean stat = true;
        while (stat)
        {
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.print(  "         Which room do you want to cancel? (1-15): ");
            int number = getNumber(input);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            String token [] = arrayRoom[number].split(",");
            if (token[2].contentEquals(ID) && token[1].contentEquals("booked"))
            {
                arrayRoom[number] = number+",none,none,none,none";
                writeFile("rooms.csv", arrayRoom);
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("+++++++      You have successfully cancelled the reservation!      +++++++");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                stat = false;
            }
            else
            {
                if (token[2].contentEquals(ID))
                {
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("++++++++++++++      This room is not in booked statue!      ++++++++++++++");
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                }
                else
                {
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("++++++++++++++      This room is not your reservation!      ++++++++++++++");
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                }

                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.print(  "         Do you want to choose another room? (yes/no): ");

                //Scanner reader = new Scanner(System.in);
                String ans = input.next();
                ans += input.nextLine();
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

                if (!ans.equalsIgnoreCase("yes"))
                    stat = false;
            }
        }
    }
}
