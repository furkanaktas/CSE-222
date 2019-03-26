import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by furkan on 04.04.2017.
 */
public class BinarySearchTree<E> extends BinaryTree implements Iterable {

    /**
     *  Constuctor
     * @param node  yeni root
     */
    private BinarySearchTree(Node node) {
        this.root = node;
    }

    /**
     *  No paramater Consturctor
     */
    public BinarySearchTree() {
        this.root = null;
    }


    /**
     *
     * @return  overrided iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new BinarySearchTree.levelOrderIterator(root);
    }

    /**
     *  iterator
     */
    private class levelOrderIterator implements Iterator {

        private Queue<Node<E>> queue = new LinkedList<>();

        private levelOrderIterator(Node root) {
            if (root == null)
                return;

            queue.offer(root);

        }

        @Override
        public boolean hasNext() {
            return queue.isEmpty()==false;
        }

        @Override
        public Object next() {

            if(!hasNext())
                return "";

            Node temp = queue.poll();

            if (temp.left != null) {
                queue.offer(temp.left);
            }

            if (temp.right != null) {
                queue.offer(temp.right);
            }

            return temp.data;

        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }
    }

    /**
     *
     * @param scan  dosya için scanner
     * @return
     * @throws IOException
     */
    public BinaryTree<String> readBinaryTree(Scanner scan) throws IOException {
        try {

            String data;


            if (scan.hasNext()) {
                data = scan.next();
                try {
                    Integer temp = Integer.parseInt(data);
                    root = new Node(temp);

                } catch (NumberFormatException e) {
                    try {
                        Double temp = Double.parseDouble(data);
                        root = new Node(temp);

                    } catch (NumberFormatException ex) {
                        root = new Node(data);
                    }
                }
            } else
                return null;


            while (scan.hasNext()) {
                data = scan.next();
                try {

                    Integer temp = Integer.parseInt(data);
                    root = helperAdd(temp, root);

                } catch (NumberFormatException e) {
                    try {

                        Double temp = Double.parseDouble(data);
                        root = helperAdd(temp, root);
                    } catch (NumberFormatException ex) {
                        root = helperAdd(data, root);
                    }

                }
            }
            return new BinarySearchTree<E>(root);

        } catch (NullPointerException e) {
            return null;
        }

    }

    /**
     *
     * @param searchTree yazdırılacak tree
     */
    public void levelTreverser(BinarySearchTree searchTree){
        Iterator iter = searchTree.iterator();

        System.out.print("Level order traversal: ");
        for (int i=0;iter.hasNext();++i)
            System.out.print(iter.next()+" ");

        System.out.print("\n");

    }




}






