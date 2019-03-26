/**
 *
 * @author furkan
 */

import java.io.FileNotFoundException;
import java.util.Scanner;


public class Staff extends Library  implements Interface {
    
    
    public Staff(){              // Constructor !!!!!!!
        arrayUser = new String [10]; 
        arrayBook = new String [10];
        
        for(int i=0;i<10;++i){
            arrayUser[i]="\0";
            arrayBook[i]="\0";
        }
        
        arrayUser[0]="ID,USER,BOOK";
        arrayBook[0]="ID,BOOK,STATUS";
    }
    
    /**
     *  It guides user with user's choises. 
     * 
     */
    @Override
    public void managementShell(){
    
        boolean exit=true;
        while(exit == true)
        {
            String option;
            Scanner input = new Scanner(System.in);
            
            System.out.print("           +++++++++++++++++++++++++++++++++"
             + "++++++\n        ++++++++++++   1. Add Book       ++++++++++++\n"
                  + "     +++++++++++++     2. Remove Book      ++++++++++++\n"
                + "   +++++++++++++       3. Register User      ++++++++++++\n"
                    + "                              ");
            option = input.next();
            
            switch (option) {
                case "1":
                    addBook();
                    exit = super.askToContinue();
                    break;
                case "2":
                    removeBook();
                    exit = super.askToContinue();
                    break;
                case "3":
                    registerUser();
                    exit = super.askToContinue();
                    break;
                default:
                    System.out.println("      ++++++++++ You entered invalid"
                                                    + " option! ++++++++++ ");
                    exit = super.askToContinue();
                    break;
            }
        }
    }


    
    /**
     *  First of all, if file is opened previously, datas read from file
     * to array with readFile function.Then if default array size is less than
     * file lines, array grows as required with toLarger function in readFile
     * function.Then take information from user.At last, datas is writed
     * in file by writeFile function.
     * 
     */
    
    private void addBook(){
        
        try {
            arrayBook = readFile("books.cvs",arrayBook);
        } 
        catch (FileNotFoundException ex) {}
        
        
        Integer size = super.size(arrayBook);
        
        String bookName,bookID,personID,line;
        Scanner input = new Scanner(System.in);
            
        System.out.print("Book Name            : ");
        bookName = input.next();
        bookName += input.nextLine();
            
        for(int i=1;i<arrayBook.length;++i)
        {
            if(arrayBook[i].indexOf("Deleted") != -1)
            {    
                size=i;     // eğer silinmiş satır varsa yeni kitap oraya
                i=arrayBook.length;      // kayıt edilir
            }
        }        
            
        bookID = size.toString();
        personID = "None";
        line = bookID+","+bookName+","+personID;
            
        arrayBook[size] = line;
        System.out.println("Book is added!");
        writeFile("books.cvs",arrayBook);    
        
    }
    
    /**
     *  First of all, if file is opened previously, datas read from file
     * to array with readFile function.Then if default array size is less than
     * file lines, array grows as required with toLarger function in readFile
     * function.Then take information from user.If there is more than 
     * one same book name,it wants ID of book.At last, datas is writed 
     * in file by writeFile function.
     * 
     */
    private void removeBook(){
        
        try{
            arrayBook = readFile("books.cvs", arrayBook);
        }
        catch(FileNotFoundException e){
            System.err.println("There is no saved book! ");
            System.err.println("You can't remove book! ");
            return;
        }
        
        int size = super.size(arrayBook);
        
        if(size <= 1){
            System.out.println("There is no saved book.");
            System.err.println("You can't remove book! ");
            return;
        }
        
        String bookName,ID;
        int index;
        Scanner input = new Scanner(System.in);
            
        System.out.print("Enter remove book    : ");
        bookName = input.next();
        bookName += input.nextLine();
        
        index = search(bookName,arrayBook);
        
        if(index ==-2)   // 1'den fazla eşleşme varsa 
        {      
            System.out.print("More than one match found!\n"
                                             + "Enter ID of book     : ");
            index = input.nextInt();
            
            if( idMacth(bookName, arrayBook[index]) == -1 ){//id-kitap eşleşmesi
                System.out.println("No match found! ");
            }
            else
            {
                if(arrayBook[index].indexOf("None") != -1) // Kitap sahibi yoksa
                {
                    arrayBook[index]="Deleted";
                    System.out.println("Book is deleted !");
                }
                else
                    System.out.println("This book have a user!\n"
                                                 + "You can't delete it!");
            }    
        }
        else if(index == -1) // kitap kayıtlı değilse
            System.out.println("No match found! ");
        
        else{
            
            if(arrayBook[index].indexOf("None") != -1) // Kitap sahibi yoksa
            {
                arrayBook[index]="Deleted";
                System.out.println("Book is deleted !");
            }
            else
                System.out.println("This book have a user!\n"
                                                 + "You can't delete it!");
        }
        writeFile("books.cvs", arrayBook);
    }
    
    /**
     *  First of all, if file is opened previously, datas read from file
     * to array with readFile function.Then if default array size is less than
     * file lines, array grows as required with toLarger function in readFile
     * function.Then take information from user.At last, datas is writed in file 
     * by writeFile function.
     * 
     */
    private void registerUser(){
        
        try {
            arrayUser = readFile("users.cvs",arrayUser);
        } 
        catch (FileNotFoundException ex) {}
        
        Integer size = super.size(arrayUser);
        
        
        String ID,name,surname,book,line;
        Scanner input = new Scanner(System.in);
            
        System.out.print("Enter Name           : ");
        name = input.next();
        name += input.nextLine();
        System.out.print("Surname              : ");
        surname = input.next();
                    
        ID = size.toString();
        name = name.toLowerCase();
        surname = surname.toUpperCase();
        book = "None";
            
        line = ID +","+name+surname+","+book;
        arrayUser[size]=line;
        System.out.println("You have registered!");
        System.out.println("Your ID number       : "+size);
                
        writeFile("users.cvs",arrayUser);
        
    }
}
