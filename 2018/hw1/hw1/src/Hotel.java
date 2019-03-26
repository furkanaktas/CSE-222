/**
 *
 * @author furkan
 */

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Hotel {

    protected String arrayRoom[];
    protected String ID="",name="",surname="";


    /**
     * It finds line size of file.Then data transfer from file to giving array.
     *
     * @param filename name of the file.
     * @param arr  that will be filled up.
     * @return that filled up array.
     * @throws java.io.FileNotFoundException to realize wheter file is
     * opened previously.
     */
    protected String[] readFile(String filename, String arr[])
                                                        throws FileNotFoundException{

        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(filename));

            int size = sizeOfLines(filename);
            while(size >= arr.length-2) // array yetersizse büyüdü.
                arr=toLarger(arr);

            reader.close();
            reader =new BufferedReader(new FileReader(filename));

            for (int i = 0; i < size; i++)
                arr[i]= reader.readLine();

            return arr;
        }
        catch(FileNotFoundException e){ throw new FileNotFoundException();}
        catch (IOException ex) {}
        return arr;
    }

    /**
     * Data transfer from array to giving file.
     *
     * @param filename name of the file.
     * @param arr  that datas will be read.
     */
    protected void writeFile(String filename,String arr[]){

        int size = size(arr);
        if(size >= 1)
        {
            File file = new File(filename);

            try {
                FileWriter writer = new FileWriter(file);

                for(int i=0;i<size;++i){
                    writer.write(arr[i]+"\n");
                    writer.flush();
                }
            } catch (IOException ex) {

            }
        }
    }


    /**
     * It register the users
     *
     * @param filename is the file name
     * @param input for user input and testing this method
     */
    protected void register(String filename, Scanner input)
    {
        String arr[] = new String[10];
        for (int i = 0; i < 10 ; ++i) {
            arr[i] = "\0";
        }

        String arr2[] = new String[10];
        for (int i = 0; i < 10 ; ++i) {
            arr2[i] = "\0";
        }

        try {
            arr = readFile(filename, arr);
        } catch (FileNotFoundException e) {}

        try {
            if (filename.contentEquals("staff.csv"))
                arr2 = readFile("guests.csv", arr2);
            else
                arr2 = readFile("staff.csv", arr2);
        }catch (FileNotFoundException e){}


        int sumSize = this.size(arr) + this.size(arr2) ;  //  array'in  kullanılan ( "\0"  olmayan ) eleman sayısı
        String arr3[] = new String[sumSize];

        for (int i = 0; i < this.size(arr) ; ++i) {
            arr3[i] = arr[i];
        }
        for (int i = this.size(arr); i < sumSize ; ++i) {
            arr3[i] = arr2[i- this.size(arr)];
        }

        //Scanner input = new Scanner(System.in);

        String id="",pass,name,surname;
        boolean status = true;
        while (status == true)
        {
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.print(  "             Enter new user's ID      : ");
            id = input.next();

            int i;
            for (i = 0; i < sumSize; ++i) {
                String token[] = arr3[i].split(",");

                if(id.contentEquals(token[0]) )             // id'nin  daha önce kullanılıp kullanılmadığına bakılıyor
                {
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("++++++++++++++++++      This ID is already in use!      ++++++++++++++++++");
                    System.out.println("++++++++++++++++++      Please Enter different ID!      ++++++++++++++++++");
                    i = sumSize;
                }
            }
            if (i == sumSize) /* tüm array'e bakılmış ama aynı id yok demektir, yani id kullanılabilir*/
                status = false;

        }

        System.out.print("             Enter new user's Password: ");
        pass = input.next();

        System.out.print("             Enter new user's Name    : ");
        name = input.next();

        System.out.print("             Enter new user's Surname : ");
        surname = input.next();
        surname += input.nextLine();

        try {
            arr[this.size(arr)] = id+","+pass+","+name+","+surname;
        }catch (IndexOutOfBoundsException e)
        {
            arr = this.toLarger(arr);
        }


        this.writeFile(filename, arr);

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+++++++++++++++     You have successfully registered!     ++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    }


    /**
     *  It enters to system as a user
     *
     * @param filename is the name of file
     * @param input for user input and testing this method
     * @return  true on login succes, false on login failure
     */
    public boolean login(String filename, Scanner input){
        BufferedReader reader;
        try {
                reader = new BufferedReader(new FileReader(filename));

                int size = sizeOfLines(filename);

                if (size <= 1)
                {
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("+++++++++++++++++      There is no registered user!      +++++++++++++++++");
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                    return false;
                }

                String arr[] = new String[size];

                reader.close();
                reader =new BufferedReader(new FileReader(filename));

                for (int i = 0; i < size; ++i)
                    arr[i]= reader.readLine();

                System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" +
                                   "                     Please enter ID  : ");
                //Scanner input = new Scanner(System.in);
                String id = input.next();
                id += input.nextLine();

                System.out.print( "                     Please enter PASS: ");

                String pass = input.next();
                pass += input.nextLine();
                //input.close();
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

                for (int i = 0; i < size; ++i) {
                    String token[] = arr[i].split(",");

                    if (id.contentEquals(token[0] ) && pass.contentEquals(token[1]))
                    {
                        this.ID = token[0];
                        this.name = token[2];
                        this.surname = token[3];
                        System.out.println("++++++++++++++++++++++++++++   Login Success!   ++++++++++++++++++++++++++\n"+
                                           "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
                        return true;
                    }
                }
        } catch (FileNotFoundException e) {

            System.err.println("++++++++++++++++++   Login records not found!           ++++++++++++++++++\n"+
                               "++++++++++++++++++   Please check your database file!   ++++++++++++++++++\n"+
                               "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
            return false;
        }
        catch (IOException e){}


        System.out.println("+++++++++++++++++++++++++   Wrong ID or PASSWORD   +++++++++++++++++++++++\n"+
                           "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        return false;
    }

    /**
     *  This function open a file  and return number of lines of the file.
     *
     * @param filename  is name of the file
     * @return  number of lines of the file
     */
    public int sizeOfLines(String filename)
    {
        int size =0;
        BufferedReader reader=null;

        try {
            reader = new BufferedReader(new FileReader(filename));
            String satir;
            while ( (satir = reader.readLine()) !=null)
                ++size;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * It finds size of array(not lenght).
     *
     * @param arr  that it's size will be found.
     * @return  size of array.
     */
    protected int size(String arr[]){

        int number=0;


        for(int i=0;i< arr.length && !(arr[i].contentEquals("\0"));++i)
            number++;

        return number;
    }

    /**
     * Size of coming array goes up 2 times.
     *
     * @param arr   that will grow.
     * @return  that is new array.
     */
    protected String[] toLarger(String arr[]){

        String temp[]  = new String[arr.length];

        for(int i=0;i<arr.length;++i)
            temp[i]=arr[i];

        arr = new String [temp.length*2];

        for(int i=0;i<arr.length;++i)
            arr[i]="\0";

        for(int i=0;i<temp.length;++i)
            arr[i]=temp[i];

        return arr;
    }

    /**
     *  This function get the room number from user
     *
     * @param input for user input and testing this method
     * @return room number
     */
    protected int getNumber(Scanner input)
    {
        int number=0;
       // Scanner reader =null;
        boolean stat = true;
        while (stat)
        {
            boolean exception = false;
            try {
                //reader = new Scanner(System.in);
                number = input.nextInt();
                input.nextLine();
                stat = false;
            }catch (InputMismatchException e)
            {
                input.nextLine();
                exception = true;
                System.err.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.err.println("+++++++++++++++++++++      Please enter number!      +++++++++++++++++++++");
                System.err.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            }
            if ( (number <=0 || number >15) && !exception)
            {
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("++++++++++++++      Please enter number between (1-15)      ++++++++++++++");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                stat = true;
            }
        }
        return number;
    }

    /**
     * This function ask user to continue process.
     *
     * @param input for user input and testing this method
     * @return  boolean  according to answer
     */
    protected boolean askToContinue(Scanner input){

        String answer;
       // Scanner input = new Scanner(System.in);

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.print(  "      Do you want to continue in same category? (Yes/No) : ");
        answer = input.nextLine();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

        return answer.equalsIgnoreCase("yes");
    }
}
