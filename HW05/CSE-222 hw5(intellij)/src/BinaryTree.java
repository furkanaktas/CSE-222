import java.io.*;
import java.rmi.UnexpectedException;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Stack;


public class BinaryTree <E extends Comparable < E > >
        implements Serializable, Iterable {


    /** Class to encapsulate a tree node. */
    protected static class Node < E >
            implements Serializable {
        // Data Fields
        /** The information stored in this node. */
        protected E data;

        /** Reference to the left child. */
        protected Node < E > left;

        /** Reference to the right child. */
        protected Node < E > right;

        // Constructors
        /** Construct a node with given data and no children.
         @param data The data to store in this node
         */
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }

    }



    // Data Field
    /** The root of the binary tree */
    protected Node < E > root;

    /**
     *  no parameter constructor
     */
    public BinaryTree() {
        root = null;
    }

    /**
     *
     * @param root  yeni root
     */
    private BinaryTree(Node < E > root) {
        this.root = root;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }
    /** Perform a preorder traversal.
     @param node The local root
     @param depth The depth
     @param sb The string buffer to save the output
     */
    private void preOrderTraverse(Node < E > node, int depth,
                                  StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("  ");
        }
        if (node == null) {
            sb.append("null\n");
        }
        else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    /**
     *
     * @param scan  dosya için scanner
     * @return      yeni tree
     * @throws IOException
     */
    public BinaryTree readBinaryTree(Scanner scan) throws IOException, UnexpectedException {
        // Read a line and trim leading and trailing spaces.
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
            }
            else
                return null;


            while (scan.hasNext()) {
                data = scan.next();
                try {

                    Integer temp = Integer.parseInt(data);
                    root = helperAdd((E) temp, root);

                } catch (NumberFormatException e) {
                    try {

                        Double temp = Double.parseDouble(data);
                        root = helperAdd((E) temp, root);
                    } catch (NumberFormatException ex) {
                        root = helperAdd((E) data, root);
                    }

                }
            }
            return new BinaryTree(root);

        } catch (NullPointerException e) {
            return null;
        }

    }

    /**
     *
     * @param item  eklenecek item
     * @param root  verilen root
     * @return  yeni root
     */
    protected Node helperAdd(E item, Node root){

        if (item.compareTo((E) root.data) > 0) {
            if (root.right == null) {
                root.right = new Node(item);
            }
            else
                helperAdd(item, root.right);
        } else if (item.compareTo((E) root.data) < 0) {
            if (root.left == null){
                root.left = new Node(item);
            }
            else
                helperAdd(item, root.left);
        }
        return root;
    }

    /**
     *
     * @return overrided iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new TreeIter(root);
    }

    /**
     *  iterator
     */
    private  class TreeIter implements Iterator{

        private Stack<Node<E>> stack= new Stack<Node<E>>();

        private TreeIter(Node root){

            if (root == null)
                return;

            stack.push(root);


        }

        /**
         *
         * @return  stack boş değilse true
         */
        @Override
        public boolean hasNext() {
            return stack.isEmpty() == false;
        }

        /**
         *
         * @return pre order sistemideki sıradaki eleman
         */
        @Override
        public Object next() {

            if(!hasNext())
                return "";

            Node temp = stack.peek();
            stack.pop();

            if (temp.right != null) {
                stack.push(temp.right);
            }
            if (temp.left != null) {
                stack.push(temp.left);
            }

            return temp.data;
        }

        @Override
        public void remove() { throw new UnsupportedOperationException();
        }

    }

    /**
     *
     * @param tree  yazdırılacak tree
     */
    public void preTreverser(BinaryTree tree){
        Iterator iter = tree.iterator();
        System.out.print("Pre order traversal: ");
        for (int i=0;iter.hasNext();++i)
            System.out.print(iter.next()+" ");

        System.out.print("\n");

    }

}