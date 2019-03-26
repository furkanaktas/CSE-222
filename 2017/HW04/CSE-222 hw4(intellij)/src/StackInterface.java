/**
 * Created by furkan on 17.03.2017.
 */
public interface StackInterface<E> {


    public void push(E item);
    public E pop();
    public boolean isEmpty();
    public int size();
}
