/**
 *
 * @author furkan
 */
import java.io.FileNotFoundException;
import java.util.Scanner;

public class User extends Library  implements Interface{
    
    public User(){      // Constructor
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
            
            System.out.print("        ++++++++++++++++++++++++++++++++++++"
                + "++++++++\n      ++++++++++++++   1. Borrowing   +++++++++"
          +"+++++++\n    ++++++++++++++     2. Returning     ++++++++++++++++\n"
                    + "                              ");
            option = input.next();
            
            switch (option) {
                case "1":
                    barrowBook();
                    exit = super.askToContinue();
                    break;
                case "2":
                    returnBook();
                    exit = super.askToContinue();
                    break;
                default:
                    System.out.println("You entered invalid option ! ");
                    exit = super.askToContinue();
                    break;
            }
        }
    }
    
    /**
     *  First of all, if file is opened previously, datas read from file
     * to array with readFile function.Then if default array size is less than
     * file lines, array grows as required with toLarger function in readFile
     * function.Then take information from user.At last, datas is writed in file 
     * by writeFile function.
     * 
     */
    private void barrowBook(){
        
        try {
            arrayBook = readFile("books.cvs", arrayBook);
            
        } 
        catch (FileNotFoundException ex) 
        {
            System.err.println("There is no saved book! ");
            System.err.println("You can't barrow book! ");
            return;
        }
        
        try {
            arrayUser = readFile("users.cvs", arrayUser);
        } 
        catch (FileNotFoundException ex) 
        {
            System.err.println("You are not member of library! ");
            System.err.println("You can't barrow book! ");
            return;
        }
        
        int size = super.size(arrayBook);
        
        if(size <= 1){
            System.out.println("There is no saved book! ");
            System.out.println("You can't barrow book! ");
            return;
        }
        
        size = super.size(arrayUser);
        if(size <= 1){
            System.out.println("You are not member of library! ");
            System.out.println("You can't barrow book! ");
            return;
        }
        
        String name,surname,user;
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter name of person : ");
        name = input.next();
        name += input.nextLine();
        System.out.print("Surname              : ");
        surname = input.next();
        user = name.toLowerCase()+surname.toUpperCase();
        
        Integer indexUser = search(user, arrayUser);
        
        if(indexUser ==-2)     // 1'den fazla kullanıcı eşleşmesi varsa 
        {  
            System.out.print("More than one match found!\n"
                                                + "Enter ID of person   : ");
            indexUser = input.nextInt();
            
            if( idMacth(user, arrayUser[indexUser]) == -1 ){ //isim-id eşleşmesi
                System.out.println("No match found! ");
            }
            else
            {
                if(arrayUser[indexUser].indexOf("None") == -1 )
                    System.out.println("You have already barrowed"
                                      + " a book! You can't barrow new book.");
                else{
                    System.out.print("Enter book name      : ");
                    name = input.next(); //name kitap ismi oldu
                    name += input.nextLine();
                    
                    Integer indexBook = search(name, arrayBook);
                    
                    if(indexBook == -2) // 1'den fazla kitap eşleşmesi varsa 
                    {
                        System.out.print("More than one match found!\n"
                                          + "Enter ID of book     : ");
                        indexBook = input.nextInt();
            
 /*kitap-id eşleşmesi*/ if( idMacth(name, arrayBook[indexBook]) == -1 )
                        { //
                            System.out.println("No match found! ");
                        }
                        else
                        {
/*kitabın sahibi yoksa*/    if(arrayBook[indexBook].indexOf("None")== -1)
                                System.out.println("This book is already "
                                                    + "barrowed by someone !");
                            else
                            {
                                arrayUser[indexUser]=lineChanger(arrayUser[indexUser],indexBook.toString());
                                arrayBook[indexBook]=lineChanger(arrayBook[indexBook],indexUser.toString());
                                System.out.println("You have barrowed book!");
                            }
                        }
                    }
                    else if(indexBook == -1) // kitap yoksa
                        System.out.println("No match found! ");
                    else
                    {       // aynı isimli tek kitap varsa
                        if(arrayBook[indexBook].indexOf("None")== -1)
 /*kitap sahibi yoksa*/     System.out.println("This book is already "
                                                    + "barrowed by someone !");
                            else
                            {   
                                arrayUser[indexUser]=lineChanger(arrayUser[indexUser],indexBook.toString());
                                arrayBook[indexBook]=lineChanger(arrayBook[indexBook],indexUser.toString());
                                System.out.println("You have barrowed book!");
                            }
                    }
                            
                }
            }    
        }
        else if(indexUser == -1) // kullanıcı eşleşmesi yoksa
            System.out.println("No match found! ");
        
        else{   //aynı isimli tek kullanıcı varsa
            
            if(arrayUser[indexUser].indexOf("None") == -1 )
                System.out.println("You have already barrowed"
                                      + " a book! You can't barrow new book.");
            else{
                System.out.print("Enter book name      : ");
                name = input.next(); //name kitap ismi oldu
                name += input.nextLine();
                    
                Integer indexBook = search(name, arrayBook);
                    
                if(indexBook == -2)
                {
                    System.out.print("More than one match found!\n"
                                         + "Enter ID of book     : ");
                    indexBook = input.nextInt();
            
                    if( idMacth(name, arrayBook[indexBook]) == -1 )
                    { //
                        System.out.println("No match found! ");
                    }
                    else
                    {
                        if(arrayBook[indexBook].indexOf("None")== -1)
                            System.out.println("This book is already "
                                                    + "barrowed by someone !");
                        else
                        {
                            arrayUser[indexUser]=lineChanger(arrayUser[indexUser],indexBook.toString());
                            arrayBook[indexBook]=lineChanger(arrayBook[indexBook],indexUser.toString());
                            System.out.println("You have barrowed book!");
                        }
                                
                    }
                }
                else if(indexBook == -1)
                    System.out.println("No match found! ");
                else
                {
                    if(arrayBook[indexBook].indexOf("None")== -1)
                        System.out.println("This book is already "
                                                    + "barrowed by someone !");
                    else
                    {
                        arrayUser[indexUser]=lineChanger(arrayUser[indexUser],indexBook.toString());
                        arrayBook[indexBook]=lineChanger(arrayBook[indexBook],indexUser.toString());
                        System.out.println("You have barrowed book!");
                    }
                }
                            
            }
            
        }
        writeFile("books.cvs", arrayBook);
        writeFile("users.cvs", arrayUser);
                
    }
    
    /**
     *  First of all, if file is opened previously, datas read from file
     * to array with readFile function.Then if default array size is less than
     * file lines, array grows as required with toLarger function in readFile 
     * function.Then take information from user.At last, datas is writed in file 
     * by writeFile function.
     * 
     */
    private void returnBook(){
        
        try {
            arrayBook = readFile("books.cvs", arrayBook);
        } 
        catch (FileNotFoundException ex) 
        {
            System.err.println("There is no saved book! ");
            System.err.println("You can't return book! ");
            return;
        }
        
        try {
            arrayUser = readFile("users.cvs", arrayUser);
        } 
        catch (FileNotFoundException ex) 
        {
            System.err.println("You are not member of library! ");
            System.err.println("You can't return book! ");
            return;
        }
        
        
        int size = super.size(arrayBook);
        
        if(size <= 1){
            System.out.println("There is no saved book! ");
            System.out.println("You can't return book! ");
            return;
        }
        
        size = super.size(arrayUser);
        if(size <= 1){
            System.out.println("You are not member of library! ");
            System.out.println("You can't return book! ");
            return;
        }
        
        String name,surname,user;
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter name of person : ");
        name = input.next();
        name += input.nextLine();
        System.out.print("Surname              : ");
        surname = input.next();
        user = name.toLowerCase()+surname.toUpperCase();
        
        Integer indexUser = search(user, arrayUser);
        
        if(indexUser ==-2)   // 1'den fazla eşleşme varsa 
        {   
            System.out.print("More than one match found!\n"
                                       + "Enter ID of person   : ");
            indexUser = input.nextInt();
            
            if( idMacth(user, arrayUser[indexUser]) == -1 ){ //
                System.out.println("No match found! ");
            }
            else
            {
                if(arrayUser[indexUser].indexOf("None") != -1 )
                    System.out.println("You don't have a book!\n"
                                               + "You can't return this book!");
                else{
                    System.out.print("Enter book name      : ");
                    name = input.next(); //name kitap ismi oldu
                    name += input.nextLine();
                    
                    Integer indexBook = search(name, arrayBook);
                    
                    if(indexBook == -2)
                    {
                        System.out.print("More than one match found!\n"
                                         + "Enter ID of book     : ");
                        indexBook = input.nextInt();
            
                        if( idMacth(name, arrayBook[indexBook]) == -1 )
                        { //
                            System.out.println("No match found! ");
                        }
                        else
                        {
                            if(arrayBook[indexBook].indexOf("None") != -1)
                                System.out.println("This book dosen't have"
                                     +" a user!\nYou can't return this book!");
                            else
                            {
                                if(arrayUser[indexUser].indexOf(indexBook.toString()) != -1)
                                {
                                    arrayUser[indexUser]=lineChanger(arrayUser[indexUser],"None");
                                    arrayBook[indexBook]=lineChanger(arrayBook[indexBook],"None");
                                    System.out.println("You have returned book!");
                                }
                                else
                                    System.out.println("This book is not yours !");
                            }
                                
                        }
                    }
                    else if(indexBook == -1)
                        System.out.println("No match found! ");
                    else
                    {
                        if(arrayBook[indexBook].indexOf("None") != -1)
                            System.out.println("This book dosen't have a user!"
                                              + "You can't return this book!");
                        else
                        {
                            if(arrayUser[indexUser].indexOf(indexBook.toString()) != -1){
                                
                                arrayUser[indexUser]=lineChanger(arrayUser[indexUser],"None");
                                arrayBook[indexBook]=lineChanger(arrayBook[indexBook],"None");
                                System.out.println("You have returned book!");
                            }
                            else
                                System.out.println("This book is not yours !");
                        }
                    }
                            
                }
            }    
        }
        else if(indexUser == -1)
            System.out.println("No match found! ");
        
        else
        {
            if(arrayUser[indexUser].indexOf("None") != -1 )
                System.out.println("You don't have a book!\n"
                                            + "You can't return this book!");
            else
            {
                System.out.print("Enter book name      : ");
                name = input.next(); //name kitap ismi oldu
                name += input.nextLine();
                    
                Integer indexBook = search(name, arrayBook);
                    
                if(indexBook == -2)
                {
                    System.out.print("More than one match found!\n"
                                         + "Enter ID of book     : ");
                    indexBook = input.nextInt();
            
                    if( idMacth(name, arrayBook[indexBook]) == -1 )
                    { // id-kitap eşleşmesi
                        System.out.println("No match found! ");
                    }
                    else
                    {
                        if(arrayBook[indexBook].indexOf("None") != -1)
                            System.out.println("This book dosen't have a user!"
                                            + "You can't return this book!");
                        else
                        {
                            if(arrayUser[indexUser].indexOf(indexBook.toString()) != -1)
                            {
                                arrayUser[indexUser]=lineChanger(arrayUser[indexUser],"None");
                                arrayBook[indexBook]=lineChanger(arrayBook[indexBook],"None");
                                System.out.println("You have returned book!");
                            }
                            else
                                System.out.println("This book is not yours !");
                        }
                                
                    }
                }
                else if(indexBook == -1)
                    System.out.println("No match found! ");
                else
                {
                    if(arrayBook[indexBook].indexOf("None") != -1)
                        System.out.println("This book dosen't have a user!"
                                            + "You can't return this book!");
                    else
                    {
                        if(arrayUser[indexUser].indexOf(indexBook.toString()) != -1)
                        {
                            arrayUser[indexUser]=lineChanger(arrayUser[indexUser],"None");
                            arrayBook[indexBook]=lineChanger(arrayBook[indexBook],"None");
                            System.out.println("You have returned book!");
                        }
                        else
                            System.out.println("This book is not yours !");
                    }
                }
            }
        }
        
        writeFile("books.cvs", arrayBook);
        writeFile("users.cvs", arrayUser);
    
    }
    
    /**
     *  First of all,it assigns line until last occurence of "," to part1.Then,
     * it adds data(part2) to part1.Then return (part1 + part2). 
     * 
     * @param line that is string to change.
     * @param data that is to change with.
     * @return  that is new line.
     */
    
    private String lineChanger(String line, String data){
        
        String part1,part2;
        int index;
        
        index= line.lastIndexOf(",");         /*son virgül index'i*/          
        part1= line.substring(0, index+1);  /*son virgüle kadar alt string'e*/  
        part2=data;                  /*part2=None silme ibaresi*/ 
        line = part1+part2;             /* satır yenisiyle değişti*/
        
        return line;
    }
    
}
