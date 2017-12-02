import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by furkan on 17.03.2017.
 */
public class StackB<E>  implements StackInterface<E> {

    private ArrayList<Object> array;


    public StackB(){
        array = new ArrayList<>();          //Constructor
    }

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     *  sondaki veriyi siler
     *
     * @return silinen veri
     */
    @Override
    public E pop() {

        if(array.isEmpty() == true)
            return null;

        E temp = (E) array.get(array.size()-1);
        array.remove(array.size()-1);


        return temp;
    }

    /**
     *  gelen veriyi sona ekler
     *
     * @param item eklenecek veri
     */
    @Override
    public void push(E item) {

        array.add(array.size(),item);
        return;
    }
}
