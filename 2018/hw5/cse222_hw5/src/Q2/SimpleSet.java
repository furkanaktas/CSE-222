package Q2;

import java.util.Collection;

public interface SimpleSet {

    public int size();
    public boolean isEmpty();
    public boolean contains(Object o);
    public boolean add(Object o);
    public boolean remove(Object o);
    public boolean addAll(Collection c);
    public boolean removeAll(Collection c);
    public boolean retainAll(Collection c);
    public boolean containsAll(Collection c);

}
