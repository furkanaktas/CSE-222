import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Vector;

public class mdsTree<E extends Comparable<E>> extends BinaryTree<E> implements SearchTree<E> {

    private int dimensionBound;
    private Vector<E> list;

    public mdsTree(E... values){
        list = new Vector<>();
        for (E item: values) {
            list.add(item);
        }
        root = new Node(list);
        dimensionBound = list.size();

    }


    /**
     *  this function gets elements and add the appropriate place in the tree
     *
     * @param item The item to be inserted
     * @return true if element('s) is(are) added
     */
    @Override
    public boolean add(E... item) {

        if (contains(item))     // eleman önceden ekliyse false
        {
            return false;
        }
        if (root == null)   // ağaçta eleman yoksa
        {
            root = new Node(list);  // list contains fonksiyonu içinde initialize edildi
            dimensionBound = list.size();
        }

        Node temp = root;
        int size = height(root);
        for (int i = 0; i < size ; ++i) {
            int comp = list.get(i%dimensionBound).compareTo((E) (((Vector)temp.data).get(i%dimensionBound)));
            if ( comp < 0  )    // sol ağaçtan devam
            {
                if (temp.left == null) // eğer solda eleman yoksa yeni elemanı buraya ekler
                {
                    temp.left = new Node(list);
                    return true;
                }
                temp = temp.left;
            }
            else  if (comp > 0) // sağ ağaçtan devam
            {
                if (temp.right == null)     // eğer sağda eleman yoksa yeni elemanı buraya ekler
                {
                    temp.right = new Node(list);
                    return true;
                }
                temp = temp.right;
            }
            else
            {
                //throw new NoSuchElementException("Elements have to be not equal");
                if (temp.left == null)
                {
                    temp.left = new Node(list);
                    return true;
                }
                temp = temp.left;
            }

        }

        return false;
    }

    /**
     *  this function searching for the target and return true if target is in the tree
     *
     * @param target Item being sought in tree
     * @return true if target is in the tree, or false othercases
     */
    @Override
    public boolean contains(E... target) {
        int size = height(root);
        list = new Vector<>();
        for (E i: target) {
            list.add(i);    // elemanlar Vector'e alındı
        }

        if (list.size() != dimensionBound)  // root taki parametre miktarı ne ise sonrakilerde aynı olmalı
            throw new IndexOutOfBoundsException("Item size must be "+dimensionBound+" because of that root has "+dimensionBound);


        Node temp = root;
        for (int i = 0; i < size ; ++i) {
            int comp = list.get(i%dimensionBound).compareTo((E) (((Vector)temp.data).get(i%dimensionBound)));
            if ( comp < 0  )    // aranan elaman ağaçtaki o andaki leveldeki itemden küçse sol ağaçtan devam
            {
                if (temp.left == null)
                {
                    return false;
                }
                temp = temp.left;
            }
            else  if (comp > 0) // aranan eleman büyükse, sağdan devam
            {
                if (temp.right == null)
                {
                    return false;
                }
                temp = temp.right;
            }
            else
            {
                int j =0;   // tüm dimension lar eşit mi diye bakıyoruz
                while (list.get(j%dimensionBound).compareTo((E) (((Vector)temp.data).get(j%dimensionBound))) == 0 && j < dimensionBound)
                {
                    ++j;
                }

                if (j == dimensionBound) // tüm dimension lar eşit se
                    return true;
                else
                {
                    if (temp.left == null)
                    {
                        return false;
                    }
                    temp = temp.left;
                }
            }
        }
        return false;
    }

    /**
     *  this function find the target and retrn node on succes or null on failure
     *
     * @param target The item being sought
     * @return the node of founded element
     */
    @Override
    public Node find(E... target) {
        if(contains(target))
        {
            int size = height(root);
            Node temp = root;
            for (int i = 0; i < size ; ++i) {
                int comp = list.get(i%dimensionBound).compareTo((E) (((Vector)temp.data).get(i%dimensionBound)));
                if ( comp < 0  )    // sol ağaçtan devam
                {
                    temp = temp.left;
                }
                else  if (comp > 0) // sağ ağaçtan devam
                {
                    temp = temp.right;
                }
                else      // eşitse bulduk
                {
                    int j =0;
                    while (list.get(j%dimensionBound).compareTo((E) (((Vector)temp.data).get(j%dimensionBound))) == 0 && j < dimensionBound)
                    {
                        ++j;
                    }
                    if (j == dimensionBound)
                        return temp;
                    else
                    {
                        if (temp.left == null)
                        {
                            return null;
                        }
                        temp = temp.left;
                    }
                }
            }
        }
        return null;
    }

    /**
     *  this function deletes the item and returns that item as a vector
     *
     * @param target Item to be removed
     * @return  the deleted element, returns Vector because of we may have multiple elements
     */
    @Override
    public Vector<E> delete(E... target) {
        if(contains(target))
        {
            int size = height(root);
            Node temp = root;
            for (int i = 0; i < size ; ++i) {
                int comp = list.get(i%dimensionBound).compareTo((E) (((Vector)temp.data).get(i%dimensionBound)));
                if ( comp < 0  )    // sol ağaçtan devam
                {
                    comp = list.get((i+1)%dimensionBound).compareTo((E)(((Vector)temp.left.data).get((i+1)%dimensionBound)));
                    if ( comp == 0) // bir sonraki eleman aradığımız eleman ise
                    {
                        Node temp2 = temp.left.right;
                        if (temp2 == null)
                        {
                            Node returned = temp.left;
                            temp.left = temp.left.left;
                            //System.out.println("sol 1");
                            rebuildTree();
                            return ((Vector)returned.data);

                        }
                        else
                        {
                            while (temp2.left != null)      // temp2 nin solu null olana kadar
                                temp2 = temp2.left;

                            temp2.left = temp.left.left;
                            Node returned = temp.left;
                            temp.left = temp.left.right;
                            //System.out.println("sol 2");
                            rebuildTree();
                            return ((Vector)returned.data);
                        }


                    }
                    else
                        temp = temp.left;
                }
                else  if (comp > 0) // sağ ağaçtan devam
                {
                    comp = list.get((i+1)%dimensionBound).compareTo((E)(((Vector)temp.right.data).get((i+1)%dimensionBound)));
                    if ( comp == 0)
                    {
                        Node temp2 = temp.right.left;
                        if (temp2 == null)
                        {
                            Node returned = temp.right;
                            temp.right = temp.right.right;
                            rebuildTree();
                           // System.out.println("sağ 1");
                            return ((Vector)returned.data);

                        }
                        else
                        {
                            while (temp2.right != null)      // temp2 nin sağı null olana kadar
                                temp2 = temp2.right;

                            temp2.right = temp.right.right;
                            Node returned = temp.right;
                            temp.right = temp.right.left;
                            rebuildTree();
                            //System.out.println("sağ 2");
                            return ((Vector)returned.data);
                        }
                    }
                    else
                        temp = temp.right;
                }
                else    // root
                {
                    Node temp2 = temp;
                    Node returned = temp;
                    if (temp2.right != null) {
                        if (temp2.right.left != null) {
                            temp2 = temp2.right;
                            while (temp2.left.left != null)      // temp2 nin solunun solu null olana kadar
                                temp2 = temp2.left;

                            Node temp3 = temp2.left;
                            temp2.left = temp2.left.right;

                            temp3.left = temp.left;
                            temp3.right = temp.right;
                            root = temp3;
                            rebuildTree();
                           // System.out.println("root 1");
                            return ((Vector) returned.data);
                        } else {
                            temp2.right.left = temp.left;

                            root = temp2.right;
                           // System.out.println("root 2");
                            //print();

                            rebuildTree();
                            return ((Vector) returned.data);
                        }
                    }
                    else if (temp2.left != null) {
                        if (temp2.left.right != null) {
                            temp2 = temp2.left;
                            while (temp2.right.right != null)      // temp2 nin sağının sağı null oalan kadar
                                temp2 = temp2.right;

                            Node temp3 = temp2.right;
                            temp2.right = temp2.right.left;

                            temp3.right = temp.right;
                            temp3.left = temp.left;
                            root = temp3;
                           // System.out.println("root 3");
                            rebuildTree();
                            return ((Vector) returned.data);
                        } else {
                            temp2.left.right = temp.right;
                            root = temp2.left;
                           // System.out.println("root 4");
                            rebuildTree();
                            return ((Vector) returned.data);
                        }
                    }
                    else
                        root = null;

                    return ((Vector)returned.data);
                }
            }
        }
        return null;
    }

    /**
     *  tihs function remove the item and return true on succes
     *
     * @param target Item to be removed
     * @return true on succes
     */
    @Override
    public boolean remove(E... target) {
        if (delete(target) != null)
            return true;

        return false;
    }

    /**
     *  this function return the height of tree
     *
     * @param root node that is start point to find height (generally root node)
     * @return height of tree
     */
    private int height(Node root)
    {
        if (root == null)
            return 0;
        else
        {
            /* compute  height of each subtree */
            int lheight = height(root.left);
            int rheight = height(root.right);

            /* use the larger one */
            if (lheight > rheight)
                return(lheight+1);
            else
                return(rheight+1);
        }
    }


    /**
     *   this function rebuild the tree after deleting process
     */
    private void rebuildTree(){
        Vector tree = new Vector();
        tree = postOrderTravers(root, tree);
        root = null;
        root = new Node(((Node)tree.get(0)).data);
        for (int i = 1; i < tree.size() ; ++i) {
            @SuppressWarnings({"unchecked"})
            E arr[]  = newArray(dimensionBound);

            for (int j = 0; j < dimensionBound; ++j) {
                arr[j] = (E)((Vector)((Node)tree.get(i)).data).get(j);
            }


            add(arr);
        }
        return;
    }


    /**
     *  This function is for creating E array
     *
     * @param length array length
     * @param array that is created
     * @return the array (E[])
     */
    private  E[] newArray(int length, E... array)
    {
        return Arrays.copyOf(array, length);
    }

    /**
     *  this function prints the tree with post order
     */
    public void print(){
        postOrderTravers(root);
    }

    /**
     * Perform a post-order traversal for printing.
     *
     * @param node  The local root

     */
    private void postOrderTravers(Node node) {

        if (node == null) {
            return ;
        }
        else {
            postOrderTravers(node.left);
            postOrderTravers(node.right);

            for (int i = 0; i <dimensionBound ; i++) {
                System.out.print(((Vector)node.data).get(i)+" ");
            }
            System.out.println("");


        }
        return ;
    }

    /**
     * Perform a post-order traversal for rebuildTree function.
     *
     * @param node  The local root
     * @param returned will be founded root
     */
    private Vector<Node> postOrderTravers(Node node, Vector returned) {

        if (node == null) {
            return null;
        }
        else {
            postOrderTravers(node.left, returned);
            postOrderTravers(node.right, returned);

            returned.add(node);


        }
        return returned;
    }




}
