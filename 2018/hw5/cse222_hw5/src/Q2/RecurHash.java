package Q2;

import Q1.DoubleHash;

import java.util.Collection;
import java.util.LinkedList;

public class RecurHash<E> implements SimpleSet{

    private int size, capacity;
    private double loadThresHold;

    private LinkedList<Entry> [] table;

    public RecurHash(){
        size = 0;
        loadThresHold = 3.0;
        capacity = 23;
        table = new LinkedList[capacity];
    }


    private static class Entry<E>{
        private E keyValue;
        private boolean isRemoved;

        Entry(E keyValue){
            this.keyValue = keyValue;
            this.isRemoved = false;
        }

        void setKeyValue(E keyValue){
            this.keyValue = keyValue;
        }

        E getKeyValue(){
            return keyValue;
        }

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }


    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    /**
     *
     * @param key
     * @return
     */
    private int firstHashCode(E key){
        int hash = key.hashCode();
        if (hash < 0)
            hash *= -1;

        return hash%(makePrime(capacity*15));
    }

    /**
     *
     * @param key
     * @return
     */
    private int secondHashCode(int key){
        return ((key % 17)/3) + 61;
    }

    /**
     *
     */
    private void reHash(){
        capacity *= 2;
        capacity = makePrime(capacity);

        size=0;
        LinkedList<Entry> [] oldTable = table;
        table = new LinkedList[capacity];

        for (int i = 0; i < oldTable.length; ++i) {
            if (oldTable[i] != null )
            {
                for (int j = 0; j < oldTable[i].size(); j++) {
                    add(oldTable[i].get(j));
                }
            }
        }

        System.out.println("reHashed !");
    }

    /**
     *
     * @param number
     * @return
     */
    private int makePrime(int number){
        if (number%2 == 0)
            number++;

        for (int i = 2; i<number ; ++i) { // make capacity  prime
            if (number%i == 0){
                number += 2;   // odd number
                i=2;
            }
        }
        return number;
    }

    /**
     *
     * @param key
     * @return
     */
    private int find(E key){

        int firstHash = firstHashCode(key);
        int index = firstHash%capacity;
        if (table[index] == null)
            return -1;

        else if (key.equals(table[index].get(0)) /*&& !(table[index].isRemoved)*/)
        {
            return index;
        }
        else
        {
            index = ( firstHash + secondHashCode(firstHash))%capacity;
            int i = 2;
            while (table[index] != null /*&& !(key.equals(table[index].getKey()) && !table[index].isRemoved)*/ ){
                index = (firstHash + i*secondHashCode(firstHash))%capacity;
                ++i;
            }
            if (table[index] != null/* && key.equals(table[index].getKey()) && !table[index].isRemoved */)
            {
                return index;
            }
            else
                return -1;
        }
    }

    /*
    @Override
    public void clear() {

    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }*/
}
