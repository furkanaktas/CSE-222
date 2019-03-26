import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ExtendLinkedList_P2<Object>  extends LinkedList<Object>  {

    private int sizeOfEnable;

    ArrayList<Object> tempList;
    public ExtendLinkedList_P2()
    {
        sizeOfEnable =super.size();
        tempList = new ArrayList<>();

        try {
            Scanner reader = new Scanner(new File("Courses(CSV).csv"));

            reader.nextLine();
            while (reader.hasNextLine() == true)
            {
                String line = reader.nextLine();
                String [] tokens = line.split(";");     // dosyadan okunan dersler  listeye eklendi

                for (int i = 0; i < 6; ++i) {
                    tokens[i] = tokens[i].trim();
                }
                this.add((Object) new Course(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]));
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *  this function enable back the disabled item at the given index
     *
     * @param index that will be enabled
     */
    public void enable(int index)
    {
        if (index > super.size() || index < 0)
            throw new IndexOutOfBoundsException(Integer.toString(index));

        if (super.get(index) == null)
        {
            ++sizeOfEnable;
            super.set(index, tempList.get(index));

            int reduce=-1;
            for (int i = 0; i < index ; i++) {
                if(i==0)
                    reduce =0;
                if (super.get(i) == null)
                {
                    ++reduce;
                }
            }
            if (reduce != 0 && reduce !=-1)
            {
                System.out.println("---------------------------------------------------------------------------");
                System.out.println(index+". indexten önce "+reduce+" tane 'disable' eleman bulunduğu için "+(index-reduce)+". index'e eklendi!");
                System.out.println("---------------------------------------------------------------------------");
            }

        }
        else{
            System.out.println("This element is already enabled!!");
        }
    }

    /**
     *  This function disable the item at the given index
     *
     * @param index that will be disabled
     */
    public void disable(int index)
    {
        if (index > super.size() || index < 0)
            throw new IndexOutOfBoundsException(Integer.toString(index));

        if (super.get(index) != null)
        {
            --sizeOfEnable;
            super.set(index, null);
        }
        else
        {
            System.out.println("This element is already disabled!!");
        }
    }

    /**
     *  This function shows the disabled item
     */
    public void showDisabled()
    {
        int count =0;
        for (int i = 0; i < super.size(); i++) {
            if (super.get(i) == null){
                if (count == 0)
                {
                    System.out.println("---------------------------------------------------------------------------");
                    System.out.println("Disaled Items :");
                }
                System.out.println(tempList.get(i).toString());
                count =1;
            }
        }
        System.out.println("---------------------------------------------------------------------------");
        if (count == 0)
            System.out.println("There is nothing to show!!");
    }

    /**
     *  This function get the element at the index without counting disaled item
     *
     * @param index of item that we want
     * @return the item that we want
     */
    @Override
    public Object get(int index) {

        int size = super.size();
        int returnIndex=-1;
        for (int i = 0; i < size; ++i) {
            if( super.get(i) != null)
            {
                ++returnIndex;
            }
            if(returnIndex == index)
            {
                returnIndex=i;
                i=size;
            }
        }
        if (returnIndex == -1)
            throw new NoSuchElementException();

        return super.get(returnIndex);
    }

    /**
     *  this function set element at the index with (Object)element, without counting disaled item
     *
     * @param index that will be set
     * @param element that is new item to be set
     * @return the element previously at the specified position
     */
    @Override
    public Object set(int index, Object element) {

        int size = super.size();
        int returnIndex=-1;

        for (int i = 0; i < size; ++i) {
            if( super.get(i) != null)
            {
                ++returnIndex;
            }
            if(returnIndex == index)
            {
                returnIndex=i;
                i=size;
            }
        }
        if (returnIndex == -1)
            throw new NoSuchElementException();

        tempList.set(returnIndex, element);
        return super.set(returnIndex, element);
    }


    /**
     *  This function returns the size without counting disaled item
     * @return the size of enabled items
     */
    @Override
    public int size() {
        return sizeOfEnable;
    }

    /**
     *  This function returns my custom iterator "CustomIterator()"
     *
     * @return  my custom iterator , CustomIterator()
     */
    @Override
    public ListIterator<Object> listIterator() {
        return  new CustomIterator();
    }


    /**
     *  this function add the item at end of the list
     *
     * @param o  item to be added
     * @return  true on succes , false on failure
     */
    @Override
    public boolean add(Object o) {
        tempList.add( o);
        ++sizeOfEnable;
        return super.add(o);
    }

    /**
     *  this function add the "(Object)element" at the given index without counting disaled item
     *
     * @param index to be added
     * @param element   to be added
     */
    @Override
    public void add(int index, Object element) {
        if (index == sizeOfEnable)
            add(element);

        else if (index<sizeOfEnable && index >=0)
        {
            int i, j=0, returnIndex=-1;
            int size= super.size();
            for (i = 0; i < size; ++i) {
                if( super.get(i) != null)
                {
                    ++returnIndex;
                }
                if(returnIndex == index)
                {
                    j=i;
                    super.add(i, element);
                    tempList.add(i, element);
                    i=size+1;
                }
            }
            for (i = j+2; i < size; ++i) {
                if (super.get(i) == null)       // eklenen index'ten sonraki null değerler 1 index geri taşındı
                {
                    Object temp = tempList.get(i);
                    tempList.set(i, tempList.get(i-1));     // swap
                    tempList.set(i-1, temp);

                    super.set(i, super.get(i-1));       // swap
                    super.set(i-1, null);
                }
            }

            ++sizeOfEnable;
        }
        else{
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     *  this removes the item at the given index nad returns item
     *
     * @param index to be removed
     * @return  the removed item
     */
    @Override
    public Object remove(int index) {

        Object returnObj=null;
        if (index<sizeOfEnable && index >=0)
        {
            int j=0, returnIndex=-1;
            int size= super.size();
            for (int i = 0; i < size; ++i) {
                if( super.get(i) != null)
                {
                    ++returnIndex;
                }
                if(returnIndex == index)
                {
                    if (super.get(super.size()-1) == null) // eğer as listede son eleman disable ise
                    {                                      // remove işlemi disabled itemlerin konumunu bozar
                        throw new UnsupportedOperationException("While the last element is disabled, you can't remove item!!");
                    }
                    j=i;
                    returnObj= super.remove(i);
                    tempList.remove(i);
                    i=size;
                }
            }
            for (int i=size-2; i>=j ; --i) {         // son elemanın null olmaması gerekli bu yüzden size-2

                if (super.get(i) == null)           // silinen index'ten sonraki null değerler 1 index ileri taşındı
                {
                    Object temp = tempList.get(i);
                    tempList.set(i, tempList.get(i+1)); // swap
                    tempList.set(i+1, temp);

                    temp = super.get(i);
                    super.set(i, super.get(i+1));   // swap
                    super.set(i+1, temp);

                }
            }
            --sizeOfEnable;
        }
        else {
            throw new IndexOutOfBoundsException();
        }


        return returnObj;
    }


    /**
     *   this class my own iterator class, it does same processes as java.listIterator()
     *  but without counting disaled item
     */
    private  class CustomIterator implements ListIterator<Object>
    {
        private int nextOrPrev;
        private int current;
        private CustomIterator()
        {
            nextOrPrev=1;
            current =-1;
        }

        @Override
        public boolean hasNext() {
            try{
                ExtendLinkedList_P2.this.get(current+1);
                return true;
            }catch (NoSuchElementException e)
            {
                return false;
            }
        }

        @Override
        public Object next() {
            if (hasNext()) {
                nextOrPrev=2;
                ++current;
                return ExtendLinkedList_P2.this.get(current);
            }
            else
                throw new NoSuchElementException();
        }

        @Override
        public boolean hasPrevious() {
            try{
                ExtendLinkedList_P2.this.get(current);
                return true;
            }catch (NoSuchElementException e)
            {
                return false;
            }
        }

        @Override
        public Object previous() {
            if (hasPrevious()){
                nextOrPrev=0;
                --current;
                return ExtendLinkedList_P2.this.get(current+1);
            }
            else
                throw new NoSuchElementException();

        }

        @Override
        public int nextIndex() {
            if (current == ExtendLinkedList_P2.this.size()-1)
                return ExtendLinkedList_P2.this.size();
            return current+1;
        }

        @Override
        public int previousIndex() {
            if (current !=-1)
                return current-1;

            return -1;
        }

        @Override
        public void remove() {
            if (nextOrPrev !=1) // next yada prev kullanılmış olmalı
            {
                if(nextOrPrev == 2) //next ise
                {
                    ExtendLinkedList_P2.this.remove(current);
                    --current;
                }
                else if(nextOrPrev == 0)    // prev ise
                    ExtendLinkedList_P2.this.remove(current+1);

                nextOrPrev=1;

            }
            else
                throw new IllegalStateException();
        }

        @Override
        public void set(Object e) {
            if (nextOrPrev != 1)    //  next yada prev kullanılmış olmalı
            {
                if(nextOrPrev == 2) //next ise
                    ExtendLinkedList_P2.this.set(current, e);
                else if(nextOrPrev == 0)    // prev ise
                    ExtendLinkedList_P2.this.set(current+1, e);
            }
            else
                throw new IllegalStateException();
        }

        @Override
        public void add(Object e) {
            ExtendLinkedList_P2.this.add(current+1, e);
            current++;
            nextOrPrev=1;
        }
    }



}
