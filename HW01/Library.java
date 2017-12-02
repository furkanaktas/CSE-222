/**
 *
 * @author furkan
 */

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;


public abstract class Library {
    
    protected String arrayUser[];
    protected String arrayBook[];

    /**
     * This function ask user to continue process.
     * 
     * @return  boolean  according to answer
     */
    protected boolean askToContinue(){
        
        String answer; 
        Scanner input = new Scanner(System.in);
        
        System.out.print("\n    Do you want to continue in same category? "
                                                            + "(Yes/No) : ");
        answer = input.nextLine();
        
        return answer.equalsIgnoreCase("yes");

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
     * It finds size of array(not lenght).
     * 
     * @param arr  that it's size will be found.
     * @return  size of array.
     */
    protected int size(String arr[]){
        
        int number=0;
        
        for(int i=0;!(arr[i].contentEquals("\0"));++i)
            number++;
        
        return number;
    }
    
    /**
     * It finds line size of file.Then data transfer from file to giving array.
     * 
     * @param name file name.
     * @param arr  that will be filled up.
     * @return that filled up array. 
     * @throws java.io.FileNotFoundException to realize wheter file is 
     * opened previously.
     */
    protected String[] readFile(String name,String arr[])
                                                   throws FileNotFoundException{
        
        BufferedReader reader;
        int size=0;
        try{
            reader = new BufferedReader(new FileReader(name));
            
            String satir = reader.readLine();
            while (satir!=null)
            {
                if(satir.length()>0)
                    size++;
                
                satir = reader.readLine();                
            }  
                
            while(size >= arr.length-2) // array yetersizse büyüdü.
                arr=toLarger(arr);
                   
            reader.close();
            reader =new BufferedReader(new FileReader(name));
                
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
     * @param name file name.
     * @param arr  that datas will be read.  
     */
    protected void writeFile(String name,String arr[]){
        
        int size = size(arr);
        if(size >= 1)
        {
            File file = new File(name);
             
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
     * It searchs for target in giving array.  
     * 
     * @param  target that is searching for.
     * @param  arr  that will look for target.
     * @return -1 for no match,-2 for more thane one matches,
     *  others is index of match(is valid for one match !)
     */
    protected int search(String target, String arr[]){
        
        int deger,index=0,repeat=0;
        String temp;
        for(int i=0;i<arr.length;++i)
        {
            target = target.toLowerCase();
            temp = arr[i].toLowerCase();
            
            deger = temp.indexOf(target);
            
            if(deger != -1) //eşleşme varsa 
            {
                index=i;
                repeat++;
                
                if(repeat >1)       // birden fazla eşleşme varsa 
                    return -2;
            }
            if(repeat==1 && i==arr.length-1) // tek eşleşme varsa
                return index;
        }
        return -1;
    }
    
    /**
     * It searchs for ID match in giving line.
     * 
     * @param  ID that is searching for.
     * @param  line  that will look for ID.
     * @return -1 for no match (by default of indexof function),
     *  others is index of match(by defaultly)
     */
    protected int idMacth(String ID,String line)
    {   
        String temp;
        ID = ID.toLowerCase();
        temp = line.toLowerCase();
        
        return temp.indexOf(ID);
    }
}
