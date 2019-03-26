package Q1;

public class DoubleHash<K,V> implements SimpleMap{

    private int size, capacity, deletedSize;
    private double loadBound;
    private Entry<K,V> [] table;

    public DoubleHash(){
        size = 0;
        capacity = 23;
        loadBound = 0.75;
        deletedSize =0;
        table = new Entry[capacity];
    }

    private static class Entry<K,V>{
        private K key;
        private V value;
        private boolean isRemoved;

        Entry(K key, V value){
            this.key = key;
            this.value = value;
            this.isRemoved = false;
        }

        void setValue(V value){
            this.value = value;
        }

        K getKey(){
            return key;
        }
        V getValue(){
            return value;
        }

    }


    /**
     *
     * @param key
     * @return
     */
    @Override
    public Object get(Object key) {
        int index = find((K)key);
        if (index != -1)
            return table[index].getValue();

        return null;
    }

    /**
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Object put(Object key, Object value) {
        if (( (double)(size+ deletedSize) / capacity) >= loadBound)
            reHash();

        int firstHash = firstHashCode((K)key);
        int index = firstHash%capacity;
        if (table[index] == null)
        {
            table[index] = new Entry(key, value);
        }
        else
        {
            index = (firstHash + secondHashCode(firstHash))%capacity;
            int i =2;
            while (table[index] != null){
                //System.out.println(firstHash+ "  "+ i*secondHashCode(index));

                index = (firstHash + i*secondHashCode(firstHash))%capacity;
                ++i;
            }
            //System.out.println("Collision !!! "+ key);
            table[index] = new Entry(key, value);
        }
        ++size;
        return null;
    }

    /**
     *
     * @param key
     * @return
     */
    @Override
    public Object remove(Object key) {
        int index = find((K)key);
        if (index != -1)
        {
            Entry temp = table[index];
            table[index].isRemoved = true;
            --size;
            ++deletedSize;
            return temp.getValue();
        }

        return null;
    }


    /**
     *  This returns true if the key exits in the table
     *
     * @param key that is looking for
     * @return true if the key exits in the table
     */
    @Override
    public boolean containsKey(Object key) {
        return (find((K) key) != -1);
    }

    /**
     *  This returns true if the value is in the table
     *
     * @param value item that is looking for
     * @return true, if value is in the table
     */
    @Override
    public boolean containsValue(Object value) {
        int count=0;
        for (int i = 0; count< size ; ++i) {
            if (table[i] != null && !table[i].isRemoved)
            {
                if (table[i].getValue().equals(value))
                    return true;

                ++count;
            }
        }
        return false;
    }

    /**
     *
     * @return the size of table item
     */
    @Override
    public int size() {
        return size;
    }

    /**
     *
     *
     * @return true if  size == 0
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     *  This returns string representation of the table
     *
     * @return table as a string
     */
    @Override
    public String toString() {//----------
        String temp = "";

        for (int i = 0; i < capacity; ++i) {
            if (table[i] != null && !(table[i].isRemoved))
                temp += "Key: "+table[i].getKey()+" Value: "+ table[i].getValue()+"\n";
        }
        return temp;
    }


    /**
     *
     * @param key
     * @return first hashCode
     */
    private int firstHashCode(K key){
        int hash = key.hashCode();
        if (hash < 0)
            hash *= -1;

        return hash%(makePrime(capacity*15));
    }

    /**
     *
     * @param key
     * @return  the second hashCode
     */
    private int secondHashCode(int key){
        return ((key % 17)/3) + 61;
    }

    /**
     *  This increases the capacity and rebuild the table.
     */
    private void reHash(){
        capacity *= 2;
        capacity = makePrime(capacity);

        size=0;
        deletedSize =0;
        Entry [] oldTable = table;
        table = new Entry[capacity];

        for (int i = 0; i < oldTable.length; ++i) {
            if (oldTable[i] != null && !(oldTable[i].isRemoved))
                put(oldTable[i].getKey(), oldTable[i].getValue());
        }

        System.out.println("reHashed !");
    }

    /**
     *  This returns first prime number bigger than given number
     *
     * @param number lower bound number
     * @return prime number
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
     *  This returns the index of item that has given key , if key is not present or item is deleted returns -1
     *
     * @param key is looking for
     * @return  the index of key
     */
    private int find(K key){

        int firstHash = firstHashCode(key);
        int index = firstHash%capacity;
        if (table[index] == null)
            return -1;

        else if (key.equals(table[index].getKey()) && !(table[index].isRemoved))
        {
            return index;
        }
        else
        {
            index = ( firstHash + secondHashCode(firstHash))%capacity;
            int i = 2;
            while (table[index] != null && !(key.equals(table[index].getKey()) && !table[index].isRemoved) ){
                index = (firstHash + i*secondHashCode(firstHash))%capacity;
                ++i;
            }
            if (table[index] != null && key.equals(table[index].getKey()) && !table[index].isRemoved )
            {
                return index;
            }
            else
                return -1;
        }
    }


    /*----------------------------------------------------------------------------------------------------------
    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }*/
}
