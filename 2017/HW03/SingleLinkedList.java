

import java.util.Vector;


/**
 *
 * @author furkan
 * @param <E>
 */

//import java.util.LinkedList;

public class SingleLinkedList <E>  {

    private Node<E> head;           // ilk node
    private int size;               // node(veri) sayısı
    private Vector<Node<E>> arrDeleted;     // silinen node'ların tutulduğu yer 
                                                      //(Part4)
    private static class Node<E>{
    
        private E data;         // tutulan veri
        private Node<E> next;   // bir sonraki eleman
        
        private Node(E dataItem)    // constructor
        {
            data = dataItem;
            next= null;
        }        
        
        private Node(E dataItem, Node<E> nodeRef)   //constructor
        {
            data= dataItem;
            next= nodeRef;
        }        
    }
    
    /**
     *  Verilen index'teki node'u return eder.
     * 
     * @param index istenilen node'un index'i
     * @return      istenilen node
     */
    private Node<E> getNode(int index)
    {
        Node<E> node=head;
        for(int i=0;i < index && node != null;++i)
            node = node.next;
        return node;
    }
    /**
     *  Verilen node'dan sonraya yeni node ekler.Eğer daha önceden silinmiş 
     * node varsa onu kullanır.
     * 
     * @param node  yeni node'un ekleneceği node
     * @param item  eklenen veri
     */
    private void addAfter(Node<E> node, E item)
    {
        if( arrDeleted.isEmpty() )
            node.next = new Node<>(item, node.next);
        else
        {
           Node<E> temp =node.next;
           node.next = arrDeleted.get(arrDeleted.size()-1);
           node.next.data = item ;
           node.next.next = temp;
           arrDeleted.remove(arrDeleted.size()-1);
        }    
        
        size++;
    }
    
    /**
     *  Verilen node'dan sonraki node'u siler.Ve silinen node'u saklar.
     * 
     * @param node bu node'dan sonraki node silinir.
     * @return  silinen veri
     */
    private E removeAfter(Node<E> node) // private
    {
        Node<E> temp= node.next;
        if(temp != null)
        {
            arrDeleted.add(temp);   // silinen node  , saklandı
            node.next = temp.next;
            size--;
            return temp.data;
        }
        else
            return null;
    }
    
    /**
     *  Verilen node'dan en sona kadar gider ve sondan başa doğru String'leri
     * ekleyerek return eder.
     *
     * @param node  head
     * @return  ters çevrilmiş String
     */
    private String reverse(Node<E> node){   // RECURSİVE REVERSE(part 2)
       
        if(node != null)
            return reverse(node.next)+", "+node.data;
        else
            return "";
    }
    
    public SingleLinkedList() {         // constructor
        arrDeleted = new Vector<>();
        head= null;
        size = 0;
    }
    
    /**
     *  Verilen veriyi listenin en başına ekler.Eğer önceden silinmiş node varsa
     * onu kullanır.
     * 
     * @param item   eklenecek veri
     */
    public void addFirst(E item){
        if(arrDeleted.isEmpty())
            head= new Node<>(item, head);
            
        else
        {
            Node<E> temp = head;
            head = arrDeleted.get(arrDeleted.size()-1);
            head.data = item ;
            head.next = temp;
            arrDeleted.remove(arrDeleted.size()-1);
        }
        
        size++;
    }
    
    /**
     *  listenin ilk elemanını siler.Silinen node saklanır
     * 
     * @return silinen  veri
     */
    public E removeFirst()
    {
        Node<E> temp = head;
        if(head != null)
        {  
            arrDeleted.add(temp);   // silinen node saklandı.
            head = head.next;
        
        }
        if(temp != null)
        {
            size--;
            return temp.data;
        }
        else
            return null;
    }
    
    /**
     *  Verilen index'teki veriyi getNode fonk yardımıyla return eder.
     * 
     * @param index  istenilen verinin index'i
     * @return      istenilen veri
     */
    public E get(int index){
        
        if(index < 0 || index >= size )
            throw new IndexOutOfBoundsException(Integer.toString(index));
    
        Node<E> node = getNode(index);
        return node.data;
    }
    /*
    public E set(int index, E newValue){
        if(index <0 || index >= size)
            throw new IndexOutOfBoundsException(Integer.toString(index));
        
        Node<E> node = getNode(index);
        E result = node.data;
        node.data = newValue;
        return result;
    }*/
    
    /**
     *  İstenilen index'e, gönderilen veriye sahip node'u ekler
     * 
     * @param index     ekleneceği yer
     * @param item      veri
     */
    public void add(int index, E item){
        if(index < 0 || index > size )
            throw new IndexOutOfBoundsException(Integer.toString(index));
        
        if(index == 0)
            addFirst(item);
        else
        {
            Node<E> node = getNode(index-1);
            addAfter(node, item);
        }    
            
    }
    
    /**
     *  Verilen index'teki node'u siler
     *  
     * @param index     gönderilen index
     * @return          silinen veri
     */
    public E remove(int index){
        if(index < 0 || index >= size )
            throw new IndexOutOfBoundsException(Integer.toString(index));
        
        if(index == 0)
            return removeFirst();
        else
        {
            Node<E> node = getNode(index-1);
            return removeAfter(node);
        }    
            
    }
    
    /**
     *  reverse fonk. dan gelen string'i  ufak düzenlemelerle return eder.
     * 
     * @return  yeni String
     */
    public String reverseToString(){            //part2
        String line ;
        line = reverse(head);
        
        if(line.compareTo("") != 0)
        {   
            line = line.substring(line.indexOf(",")+1);
            line = line.replaceFirst(" ", "[");
            line += "]";
        }    
        return line;
    }
   
    /**
     *  
     * @return  silinen node'ların adresleri(String)
     */
    public String deletedToString(){        // part 4
    
        String line="There is no data to show ! ";
        
        if( !arrDeleted.isEmpty())
            line="[";
        
        for(int i=0;i<arrDeleted.size();++i)
        {    
            if(i != arrDeleted.size()-1)
                line += arrDeleted.get(i)+", ";
            else
                line += arrDeleted.get(i)+"]";
        
        }
        return line;
    
    }
    
    /**
     * 
     * @return  listenin node sayısı
     */
    public int size(){return size;}
    
    
    
}
