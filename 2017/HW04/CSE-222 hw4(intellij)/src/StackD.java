import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by furkan on 17.03.2017.
 */
public class StackD<E> implements StackInterface<E> {


    private Queue<Object> queue;


    public StackD(){            //Constructor
        queue = new LinkedList<>();
    }       // Constructor
    
    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     *  Queue ile yapıldığı için, sondaki elemanı başa getirene kadar (size-1) sondan çıkarıp başa ekler.Sondan çıkarır.
     *
     * @return silinen veri
     */
    @Override
    public E pop() {
        for (int i = 0; i < queue.size() - 1; ++i) {
            queue.add(queue.poll());
        }
        if (queue.size() != 0) {
            E temp = (E) queue.poll();
            return temp;
        }

        return null;
    }

    /**
     *  en sona ekler
     *
     * @param item eklenecek veri
     */
    @Override
    public void push(E item) {
        queue.add(item);
        return;
    }
}
