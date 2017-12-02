/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author furkan
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
    //////////////////////////////  Part 1 \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
              
        myStringBuilder build = new myStringBuilder();      
    // System.out.println("-------------------  Part 1 ---------------------");
        try {
            File file = new File("numbers.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            String satir = reader.readLine();
            while (satir!=null)
            {
                build.append(satir);
                satir = reader.readLine();
            }
            reader.close();
            
                file = new File("result1.txt");
                FileWriter writer = new FileWriter(file);
                long startTime = System.nanoTime(); 
                writer.write(build.toString1()+"\n");   // get()
                writer.flush();  
                long endTime = System.nanoTime(); 
                long estimatedTime = endTime - startTime; // Geçen süreyi nanosaniye
                double seconds = (double)estimatedTime; // saniyeye çevirmek için milyar'a bölüyoruz.      
                writer.write("get() toString geçen süre: "+seconds);  
                writer.flush();  
                
                file = new File("result2.txt");
                writer = new FileWriter(file);
                startTime = System.nanoTime(); 
                writer.write(build.toString2()+"\n");   // iterator
                writer.flush();  
                endTime = System.nanoTime(); 
                estimatedTime = endTime - startTime; // Geçen süreyi nanosaniye
                seconds = (double)estimatedTime; // saniyeye çevirmek için milyar'a bölüyoruz.  
                writer.write("Iterator() toString geçen süre: "+seconds);  
                writer.flush();  

                file = new File("result3.txt");
                writer = new FileWriter(file);
                startTime = System.nanoTime(); 
                writer.write(build.toString3()+"\n");   // orjinal toString
                writer.flush();  
                endTime = System.nanoTime(); 
                estimatedTime = endTime - startTime; // Geçen süreyi nanosaniye
                seconds = (double)estimatedTime; // saniyeye çevirmek için milyar'a bölüyoruz.  
                writer.write("Java toString geçen süre: "+seconds);  
                writer.flush();  


            
        } catch (FileNotFoundException ex) {System.out.println("numbers.txt is not opened !");}
        catch (IOException ex) {}
        
        
            
        
///////////////////////////////////  part 2 \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
            System.out.println("------------------  part 2 ------------------");
            
            SingleLinkedList<Object> part2= new SingleLinkedList<>(); 
            for(int i=0;i<25;++i)
                part2.add(i, i);
            
            for(int i=0;i<25;++i)
                System.out.print(part2.get(i)+" ");
                
            System.out.println("\n"+part2.reverseToString());
        
/////////////////////////////////   part 3   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ 
     // System.out.println("--------------------  part 3 --------------------");
            
            
            
            
     //  myAbstarctCollection<Object> part3 = new myAbstarctCollection();     
            
            
            
            
            
            
            
            
            
 /////////////////////////////////   part 4   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ 
     
        System.out.println("--------------------  part 4 --------------------");
            
        SingleLinkedList<Object> list = new SingleLinkedList<>();
        try {
            File file = new File("number100.txt"); 
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            String satir = reader.readLine();
            int i=0;
            while (satir!=null)
            {
                list.add(i,satir);
                satir = reader.readLine();
                i++;
            }
            reader.close();
            
            for(i=0;i<50;++i)  
                list.remove(0);
          
            System.out.println("deletedToString: "+list.deletedToString());

            for(i=0;i<50;++i)
                list.add(i,i+1);

            System.out.println("deletedToString: "+list.deletedToString());
        
        } catch (FileNotFoundException ex) {}
          catch (IOException ex) {}
      
       
        
    }

    
}
