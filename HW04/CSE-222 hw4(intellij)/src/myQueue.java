import java.util.Iterator;
import java.util.Queue;

/**
 * Created by furkan on 18.03.2017.
 */
public class myQueue<E> extends KWLinkedList {

    /**
     *  en sona ekler
     *
     * @param item eklenecek veri
     */
    public void add(E item){
        super.add(super.size(),item);
    }

    /**
     *  ilk elemanı siler
     */
    public void remove(){
        super.remove(0);
    }

    public E get(int index){

        return (E) super.get(index);
    }

    public void reverse(){
        super.reverse();
    }

}
