import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CustomLinkedList_P3<Object>{

    private Node<Object> head, tail, current;
    private int size;
    private int [] sizeOfCoursInSmstr;
    private int start;

    public CustomLinkedList_P3()   // Costructor
    {
        head = null;
        tail = null;
        current = null;
        size = 0;
        start =0;   //  next() ve nextInSemester() ile  head değerinin return edilmesi için tutuldu
        sizeOfCoursInSmstr = new int[8];  // 8 dönem var, her somestırdaki ders sayısı
        for (int i: sizeOfCoursInSmstr)
        {
            sizeOfCoursInSmstr[i]=0;
        }

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
                Course temp = new Course(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
                this.add((Object) temp);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static class Node<Object>{           // inner class

        private Object data;                 // tutulan veri
        private Node<Object> next;           // bir sonraki eleman
        private Node<Object> nextInSemester; // aynı somestırdaki  bir sonraki eleman

        private Node(Object dataItem)    // constructor
        {
            data = dataItem;
            next= null;
            nextInSemester = null;
        }

    }

    /**
     *  This returns the node in the given index
     *
     * @param index  of the wanted node
     * @return      istenilen node
     */
    private Node<Object> getNode(int index)
    {
        if(index < 0 || index >= size )
            throw new IndexOutOfBoundsException(Integer.toString(index));

        Node<Object> node=head;
        for(int i=0;i < index && node != null;++i)
            node = node.next;
        return node;
    }

    /**
     *  This adds the element at the end of the list and if list has
     *
     * @param item  will be added
     */
    public void add(Object item)
    {
        int smstr = Integer.parseInt(((Course)item).semester);
        if (smstr < 1 || smstr >8)
            throw new IndexOutOfBoundsException("least 1, most 8 semester");

        if(size == 0)
        {
            head = new Node<Object>(item);
            tail = head;
            head.nextInSemester = head;
            ++sizeOfCoursInSmstr[smstr-1];
            ++size;
        }
        else
        {
            tail.next = new Node<>(item);
            tail = tail.next;
            ++sizeOfCoursInSmstr[smstr-1];

            if (sizeOfCoursInSmstr[smstr-1] == 1)
            {
                tail.nextInSemester = tail;      // somestır daki o ders ilk defa eklendiyse,  next'i kendisi
            }

            if (item.getClass() == Course.class )       // item  Course  objesiyse
            {
                for (int i = 0; i < size; i++) {
                    Node temp = getNode(i);
                    Node temp2 = temp;
                    if (((Course)item).semester.contentEquals(((Course)temp.data).semester))    // Semester lar  aynı mı?
                    {
                        for (int j = 0; j < sizeOfCoursInSmstr[smstr-1]-2; j++) {
                            temp = temp.nextInSemester;     // aynı somestırdaki son derse gelene kadar next
                        }
                        temp.nextInSemester = tail;
                        temp.nextInSemester.nextInSemester = temp2;  // circular oldugundan, son eleman o somestırdaki ilk dersi gösteriyor
                        i=size;
                    }
                }
            }
            ++size;
        }
    }

    /**
     *  this removes the element of the given index
     *
     * @param index     index that will be deleted
     * @return          data that is deleted
     */
    public Object remove(int index){
        if(index < 0 || index >= size )
            throw new IndexOutOfBoundsException(Integer.toString(index));

        if(index == 0)
        {
            Object temp     = head.data;
            Node temp2 = head;

            int smstr       = Integer.parseInt(((Course)temp).semester);
            --sizeOfCoursInSmstr[smstr-1];  // aynı somestırdaki ders sayısı,  azaltıldı

            while (temp2.nextInSemester != head)
                temp2 = temp2.nextInSemester;       // 1 sonraki eleman kendisi olana kadar gitti

            if (sizeOfCoursInSmstr[smstr-1] == 0)   // o somestırda başka ders kalmadıysa
                temp2.nextInSemester =null;
            else
                temp2.nextInSemester = temp2.nextInSemester.nextInSemester; // elaman silindi

            if(current == head)
            {
                start =0;        // start 0 yapılarak, next fonksiyonu çalıştığında current'ın  yeni head olması sağlandı.
                current =null;
            }

            head = head.next;   //eleman silindi
            --size;
            return temp;
        }
        else
        {
            Node<Object> node = getNode(index-1);
            Object temp = node.next.data;
            Node<Object> temp2 = node.next;


            int smstr       = Integer.parseInt(((Course)temp).semester);
            --sizeOfCoursInSmstr[smstr-1];      // aynı somestırdaki ders sayısı,  azaltıldı

            while (temp2.nextInSemester != node.next)
                temp2 = temp2.nextInSemester;           // 1 sonraki eleman kendisi olana kadar gitti


            if (sizeOfCoursInSmstr[smstr-1] == 0)   // o somestırda başka ders kalmadıysa
                temp2.nextInSemester =null;
            else
                temp2.nextInSemester = temp2.nextInSemester.nextInSemester; // elaman silindi

            if(current == node.next)    // silinen eleman current  ise
                current = node;         // current silinenin 1 öncesi oldu. (next deyince sıradaki elaman gelmiş olacak)

            node.next = node.next.next;     // eleman  silindi
            --size;
            return temp;
        }

    }

    /**
     *  This returns  data of the next node
     *
     * @return next element after the current element
     */
    public Object next() {
        if (current == null && start !=0)
            throw new NoSuchElementException("");

        if(start ==0 && head !=null)
        {
            current = head;
            start++;
            return current.data;
        }
        else{
            if(current.next !=null)
            {
                current = current.next;
                return current.data;
            }
            else
            {
                throw new NoSuchElementException("You arrived the end of the list!!");
            }
        }

    }

    /**
     *  This returns the next element  in same semester after current
     *
     * @return  next element after the current (next Course in same Semester)
     */
    public Object nextInSemester()
    {
        if (current == null && start !=0)       // eğer current null ise, ve
            throw new NoSuchElementException("You have no course in this semester!!");

        if(start ==0 && head !=null)
        {
            current = head;
            start++;
            return current.data;
        }
        else{
            if (current == null)
                throw new NoSuchElementException("You have no course in this semester!!");

            current = current.nextInSemester;
            return current.data;
        }

    }

    /**
     *  returns the size of the list
     *
     * @return size of the list
     */
    public int size(){
        return this.size;
    }
}
