/**
 * Created by furkan on 17.03.2017.
 */
import java.util.ArrayList;

public class StackA<E>  extends ArrayList implements StackInterface<E>   {

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    /**
     *  sondaki veriyi çıkarır
     *
     * @return  çıkarılan data
     */
    @Override
    public E pop() {
        if(super.size() == 0)
            return null;

        E temp = (E) super.get(super.size()-1);
        super.remove(super.size()-1);
        return temp;
    }

    /**
     *  gelen veriyi sona ekler
     *
     * @param item eklenecek item
     */
    @Override
    public void push(E item) {
        super.add(super.size(), item);
        return;
    }
}
