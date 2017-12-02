
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author furkan
 */

public class myStringBuilder {
    
    private LinkedList<Object> list;
    
    public myStringBuilder(){       // constructor
        list = new LinkedList<>();
    }
    
    public void append(Object item){
        list.add(list.size(), item);  // son elemanın index'i
    }
    
    /**
     *  list'teki veriyi get fonk ile okur belli düzende  String'e çevirir.
     * 
     * @return String'e çevrilen veri 
     */
    
    public String toString1(){
        String line= null;
        
        if(list.size() >0)
            line="[";
        
        for(int i=0;i<list.size();++i)
        {    
            line += list.get(i);
            if(i != list.size()-1)
                line += ", ";
            else
                line += "]";
        }
        return line;
    }
    
    /**
     *  list'teki veriyi iterator ile okur belli düzende  String'e çevirir.
     * 
     * @return String'e çevrilen veri 
     */
    public String toString2(){
        
        Iterator iter = list.iterator();
        String line= null;
        
        if(list.size() >0)
            line="[";
        
        
        while(iter.hasNext())
        {   
            line += iter.next();
            if(iter.hasNext())
                line +=", ";
            else
                line += "]";
        }
        return line;
    }
    
    /**
     *  list'teki veriyi LinkedList toString methodu ile String'e çevirir.
     * 
     * @return String'e çevrilen veri 
     */
    public String toString3(){
        return list.toString();
    }
    
}
