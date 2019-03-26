/**
 *
 * @author furkan
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Staff extends Library  implements Interface {
    
    
    public Staff(){              // Constructor !!!!!!!
        arrayUser = new ArrayList(); 
        arrayBook = new ArrayList();
        
        arrayUser.add("ID,USER,BOOK");
        arrayBook.add("ID,BOOK,STATUS");
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
        
        
        //Integer size = super.size(arrayBook);
        Integer size = arrayBook.size();
        
        String bookName,bookID,personID,line;
        Scanner input = new Scanner(System.in);
            
        System.out.print("Book Name            : ");
        bookName = input.next();
        bookName += input.nextLine();
        
        int i;
        for(i=0;i<arrayBook.size();++i)
        {   
            if(arrayBook.get(i).indexOf("Deleted") != -1)
            {    
                size=i;     // eğer silinmiş satır varsa yeni kitap oraya
                i=arrayBook.size()+1;      // kayıt edilir
            }
        }        
           
        bookID = size.toString();
        personID = "None";
        line = bookID+","+bookName+","+personID;
        
        if(i == arrayBook.size()+2)     // silinmiş satır varsa 
            arrayBook.set(size, line);
        else
            arrayBook.add(line);
        
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
        
        int size = arrayBook.size();
        
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
            
            if(index >= arrayBook.size())
                System.out.println("No match found! ");
            
            else if( idMacth(bookName, arrayBook.get(index)) == -1 ){//id-kitap eşleşmesi
                System.out.println("No match found! ");
            }
            else
            {
                if(arrayBook.get(index).indexOf("None") != -1) // Kitap sahibi yoksa
                {
                    arrayBook.set(index, "Deleted");
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
            
            if(arrayBook.get(index).indexOf("None") != -1) // Kitap sahibi yoksa
            {
                arrayBook.set(index, "Deleted");
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
        
        Integer size = arrayUser.size();
        
        
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
        arrayUser.add(line);
        System.out.println("You have registered!");
        System.out.println("Your ID number       : "+size);
                
        writeFile("users.cvs",arrayUser);
        
    }
}
