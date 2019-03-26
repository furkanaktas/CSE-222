/**
 * Created by furkan on 17.03.2017.
 */
public class StackC<E>  implements StackInterface<E> {



    private Node<E> head;           // ilk node
    private int size;               // node(veri) sayısı

    private static class Node<E>{

        private E data;         // tutulan veri
        private Node<E> next;   // bir sonraki eleman

        private Node(E dataItem)    // constructor
        {
            data = dataItem;
            next= null;
        }

        private Node(E dataItem, Node<E> nodeRef)   //constructor
        {
            data= dataItem;
            next= nodeRef;
        }
    }

    /**
     *  Verilen index'teki node'u return eder.
     *
     * @param index istenilen node'un index'i
     * @return      istenilen node
     */
    private Node<E> getNode(int index)
    {
        Node<E> node=head;
        for(int i=0;i < index && node != null;++i)
            node = node.next;
        return node;
    }

    /**
     *  Verilen node'dan sonraya yeni node ekler.
     *
     * @param node  yeni node'un ekleneceği node
     * @param item  eklenen veri
     */
    private void addAfter(Node<E> node, E item)
    {
        node.next = new Node<>(item, node.next);
        size++;
    }

    /**
     *  Verilen node'dan sonraki node'u siler.Ve silinen node'u saklar.
     *
     * @param node bu node'dan sonraki node silinir.
     * @return  silinen veri
     */
    private E removeAfter(Node<E> node) // private
    {
        Node<E> temp= node.next;
        if(temp != null)
        {
            node.next = temp.next;
            size--;
            return temp.data;
        }
        else
            return null;
    }

    /**
     *  İstenilen index'e, gönderilen veriye sahip node'u ekler
     *
     * @param index     ekleneceği yer
     * @param item      veri
     */
    private void add(int index, E item){
        if(index < 0 || index > size )
            throw new IndexOutOfBoundsException(Integer.toString(index));

        if(size ==0) {
            head = new Node<>(item, null);
            size++;
        }
        else {
            Node<E> node = getNode(index - 1);
            addAfter(node, item);
        }
    }

    /**
     *  Verilen index'teki node'u siler
     *
     * @param index     gönderilen index
     * @return          silinen veri
     */
    private E remove(int index){
        if(index < 0 || index >= size )
            throw new IndexOutOfBoundsException(Integer.toString(index));

        if(size == 1)
        {
            Node<E> temp = head;
            head = null;
            size--;
            return temp.data;
        }
        else
        {
            Node<E> node = getNode(index - 1);
            return removeAfter(node);
        }
    }

    public StackC(){        // Constructor

        head= null;
        size = 0;
    }


    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }


    /**
     *  en sondan siler
     *
     * @return silinen veri
     */
    @Override
    public E pop() {
        if(size == 0)
            return null;

        return remove(size-1);
    }

    /**
     *  en sona ekler
     *
     * @param item eklenecek veri
     */
    @Override
    public void push(E item) {
        add(size, item);
        return;
    }
}
