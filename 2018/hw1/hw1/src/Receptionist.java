/**
 *
 * @author furkan
 */

import java.io.*;
import java.util.Scanner;

public class Receptionist extends Hotel implements UI {

    public Receptionist(){              // Constructor !!!!!!!

        arrayRoom = new String [16];
        for(int i=0;i<16;++i){
            arrayRoom[i]="\0";
        }

        try {
            Scanner temp = new Scanner(new FileReader("rooms.csv"));
        } catch (FileNotFoundException e) {
            FileWriter writer = null;
            try {
                writer = new FileWriter(new File("rooms.csv"));
                writer.write("ROOM,STATUS,ID,NAME,SURNAME\n");
                writer.flush();
                for (int i = 0; i < 15; ++i) {
                    writer.write((i+1)+",none,none,none,none\n");
                    writer.flush();
                }
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        try {
            Scanner temp = new Scanner(new FileReader("staff.csv"));
        } catch (FileNotFoundException e) {
            FileWriter writer = null;
            try {
                writer = new FileWriter(new File("staff.csv"));
                writer.write("ID,PASSWORD,NAME,SURNAME\n");
                writer.flush();
                writer.write("admin,admin,name,surname\n");
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
                             "+++++++++++++++++++      1 for Book a Room             +++++++++++++++++++\n"+
                             "+++++++++++++++++++      2 for Cancel reservation      +++++++++++++++++++\n"+
                             "+++++++++++++++++++      3 for Check In                +++++++++++++++++++\n"+
                             "+++++++++++++++++++      4 for Check Out               +++++++++++++++++++\n"+
                             "                                   ");

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
                case "3":
                    checkIn(input);
                    exit = super.askToContinue(input);
                    break;
                case "4":
                    checkOut(input);
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
     * it books the room if room is avaliable to book.
     *
     * @param input for user input and testing this method
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
            System.out.print(  "             Which room do you want? (1-15): ");
            int number = getNumber(input);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            String token [] = arrayRoom[number].split(",");
            if (token[1].contentEquals("none"))
            {
                String info[] = new String[3];
                for (int i = 0; i <3 ; i++)
                    info[i] ="";
                getInfo(info, input);
                if (info[0].length() != 0)
                {
                    arrayRoom[number] = number+","+"booked"+","+info[0]+","+info[1]+","+info[2];
                    writeFile("rooms.csv", arrayRoom);
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("++++++++++++      You have successfully booked the room!      ++++++++++++");
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                    stat = false;
                }
                else
                    stat = false;
            }
            else
            {
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("+++++++++++++++++      This room is already in use!      +++++++++++++++++");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                System.out.print(  "       Do you want to choose another room? (yes/no): ");
                //Scanner reader = new Scanner(System.in);
                String ans = input.next();
                ans += input.nextLine();
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

                if (!ans.equalsIgnoreCase("yes"))
                    stat = false;
            }
        }
    }

    /**
     * it books the room if room is avaliable to book.
     *
     * @param input for user input and testing this method
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

            //Scanner reader = new Scanner(System.in);

            String info[] = new String[3];

            for (int i = 0; i <3 ; i++)
                info[i] ="";
            getInfo(info, input);

            String token [] = arrayRoom[number].split(",");
            if (token[1].contentEquals("booked"))
            {

                if (token[2].contentEquals(info[0]))       // söylenen id ile db' deki id eşleşirse
                {
                    arrayRoom[number] = number+",none,none,none,none";
                    writeFile("rooms.csv", arrayRoom);
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("+++++++      You have successfully cancelled the reservation!      +++++++");
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                    stat = false;
                }
                else if (info[0].length() != 0)
                {
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("++++++++++++      This room is not booked by your side!      +++++++++++++");
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.print(  "        Do you want to choose another room? (yes/no): ");
                    String ans = input.next();
                    ans += input.nextLine();
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

                    if (!ans.equalsIgnoreCase("yes")) {
                        stat = false;
                    }
                }
                else
                    stat = false;   // verilen ıd  ile kullanıcı yoksa
            }
            else
            {
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("+++++++++++++++++      Please book the room first!      ++++++++++++++++++");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                stat = false;
            }
        }
    }

    /**
     * it checkes in the room that is booked.
     *
     * @param input for user input and testing this method
     */
    private void checkIn(Scanner input)
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
            System.out.print(  "         Which room do you want to check in? (1-15): ");
            int number = getNumber(input);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");


            String info[] = new String[3];
            for (int i = 0; i <3 ; i++)
                info[i] ="";
            getInfo(info, input);

            String token [] = arrayRoom[number].split(",");
            if (token[1].contentEquals("booked"))
            {

                if (token[2].contentEquals(info[0]))       // söylenen id ile db' deki id eşleşirse
                {
                    arrayRoom[number] = number+","+"checkIn"+","+info[0]+","+info[1]+","+info[2];
                    writeFile("rooms.csv", arrayRoom);
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("++++++++++++++      You have successfully checked in!      +++++++++++++++");
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    stat = false;
                }
                else if (info[0].length() != 0)
                {
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("++++++++++++      This room is not booked by your side!      +++++++++++++");
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                    System.out.print(  "        Do you want to choose another room? (yes/no): ");
                    String ans = input.next();
                    ans += input.nextLine();
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

                    if (!ans.equalsIgnoreCase("yes")) {
                        stat = false;
                    }
                }
                else
                    stat = false;
            }
            else
            {
                if (token[1].contentEquals("checkIn"))
                {
                    if (token[2].contentEquals(info[0]))    // oda kişiye aitse
                    {
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("++++++++      This room is already checked in by your side!      +++++++++");
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                    }
                    else
                    {
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("+++++++++++++++++++      This room is not yours!      ++++++++++++++++++++");
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                    }

                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.print(  "         Do you want to choose another room? (yes/no): ");
                    String ans = input.next();
                    ans += input.nextLine();
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

                    if (!ans.equalsIgnoreCase("yes"))
                        stat = false;
                }
                else
                {
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("+++++++++++++++++      Please book the room first!      ++++++++++++++++++");
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                    stat = false;
                }
            }
        }
    }

    /**
     *  it checkes out the room that is checked in.
     *
     * @param input for user input and testing this method
     */
    private void checkOut(Scanner input)
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
            System.out.print(  "          Which room do you want to check out? (1-15): ");
            int number = getNumber(input);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            //Scanner reader = new Scanner(System.in);

            String info[] = new String[3];

            for (int i = 0; i <3 ; i++)
                info[i] ="";
            getInfo(info, input);

            String token [] = arrayRoom[number].split(",");
            if (token[1].contentEquals("checkIn"))
            {

                if (token[2].contentEquals(info[0]))       // söylenen id ile db' deki id eşleşirse
                {
                    arrayRoom[number] = number+",none,none,none,none";
                    writeFile("rooms.csv", arrayRoom);
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("++++++++++++++      You have successfully checked out!      ++++++++++++++");
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                    stat = false;
                }
                else if (info[0].length() != 0)
                {
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("++++++++++      This room is not checked in by your side!      +++++++++++");
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.print(  "        Do you want to choose another room? (yes/no): ");
                    String ans = input.next();
                    ans += input.nextLine();
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

                    if (!ans.equalsIgnoreCase("yes")) {
                        stat = false;
                    }
                }
                else
                    stat = false;   // verilen ıd  ile kullanıcı yoksa
            }
            else
            {
                if (token[1].contentEquals("booked"))
                {
                    if (token[2].contentEquals(info[0]))       // söylenen id ile db' deki id eşleşirse
                    {
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("+++++      This room is booked by your side but not checked in!      +++++");
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                    }
                    else
                    {
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("+++++++++++++++++++      This room is not yours!      ++++++++++++++++++++");
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    }

                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.print(  "        Do you want to choose another room? (yes/no): ");
                    String ans = input.next();
                    ans += input.nextLine();
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

                    if (!ans.equalsIgnoreCase("yes"))
                        stat = false;
                }
                else
                {
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("+++++++++++++      Please you check in the room first!      ++++++++++++++");
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                    stat = false;
                }

            }
        }
    }

    /**
     *  It gets the user ID from user and return the user's info .
     *
     * @param info they hold the return info
     * @param input for user input and testing this method
     */
    private void getInfo(String [] info, Scanner input)
    {
        //Scanner input = new Scanner(System.in);
        String id="";
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.print(  "                   Please say your ID: ");
        id = input.next();
        id += input.nextLine();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        String arr[] = new String [10];
        for (int i=0;i<10;++i)
            arr[i]="\0";

        try {
            arr = readFile("guests.csv", arr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        boolean stat= false;
        int size = size(arr);
        for (int i = 0; i < size; ++i) {
            String token[] = arr[i].split(",");
            if (token[0].contentEquals(id))
            {
                info[0] = token[0]; //id
                info[1] = token[2]; //name
                info[2] = token[3]; //surname
                stat = true;
                i=size;
            }
        }

        if (stat == false)
        {
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("+++++++++++++++++++      You are not registered!      ++++++++++++++++++++");
            System.out.println("+++++++++++++++++++      Please register first!       ++++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
            return;
        }
    }
}
