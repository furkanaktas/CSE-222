import java.util.LinkedList;
import java.util.Queue;

public class GeneralTree<E> extends BinaryTree<E> {

    public GeneralTree(String parent){
        super(new Node(parent.trim()));
    }

    /**
     *  this function find the parent and add the child, (lefts are child , rights are siblings)
     *
     * @param parent that will be founded
     * @param child that will be added
     * @return true if adding is success
     */
    public boolean add(String parent, String child)
    {
        child  = child.trim();
        Node currentNode = levelOrderSearch(parent);
        if (currentNode == null)
            return false;
        else{
            if (currentNode.left == null)
            {
                currentNode.left = new Node(child); // parent ın hiç  çocuğu yoksa
            }
            else
            {
                currentNode = currentNode.left;
                while (currentNode.right != null){

                    currentNode= currentNode.right;    // en son kardeşe gelene kadar sağa gittik
                }
                currentNode.right = new Node(child);   // kardeş olarak eklendi
            }
        }
        return true;
    }


    /**
     * just call the real traversal.
     *
     *  @param target we will look for
     *  @return the founded Node
     */
    public Node<E> postOrderSearch(String target) {
        Node temp = null;
        temp = helpPostOrder(root, temp, target);
        System.out.println("");
        return temp;
    }


    /**
     * Perform a post-order traversal.
     *
     * @param node  The local root
     * @param returned will be founded root
     * @param target we will look for
     */
    private Node helpPostOrder(Node<E> node, Node<E>returned, String target) {

        if (node == null) {
            return returned;
        }
        else {  // normal binary tree ye göre in order çalışıyor fakat bizim family ağacına göre post order çalışmış oluyor
            returned = helpPostOrder(node.left, returned, target);
            System.out.print(node.data+" ");
            if (node.data.equals(target)) {
                returned = node;
            }
            returned = helpPostOrder(node.right, returned, target);
        }
        return returned;
    }

    /**
     * Perform a iterative level-order traversal with Java Queue.
     *
     * @param target we will look for
     * @return the founded Node
     */
    public Node levelOrderSearch(String target) {

        Queue<Node> queue = new LinkedList<>();

        queue.offer(root);
        while (!queue.isEmpty())
        {
            Node returned = queue.poll();   // queue nun başındaki eleman çıkarıldı
            if (returned.data.equals(target))   // eleman bulunduysa return
                return returned;

            // queue sıralı ilerlediği için, her seferinde level olarak queue ya eklenmiş olur

            if (returned.left != null)
            {
                queue.offer(returned.left);     // sol ağaç ve sağ ağacın tamamı(o leveldeki) queue ya eklendi
                Node temp = returned.left.right;
                while ( temp != null)
                {
                    queue.offer(temp);
                    temp = temp.right;
                }
            }
        }
        return null;
    }

    /**
     *  this function is print version of the levelOrderSearch
     *
     */
    public void levelOrderPrint() {

        Queue<Node> queue = new LinkedList<>();

        queue.offer(root);
        while (!queue.isEmpty())
        {
            Node returned = queue.poll();   // queue nun başındaki eleman çıkarıldı
            if (returned != null)
                System.out.print(returned.data+ " ");

            if (returned.left != null)
            {
                queue.offer(returned.left);     // sol ağaç ve sağ ağacın tamamı(o leveldeki) queue ya eklendi
                Node temp = returned.left.right;
                while ( temp != null)
                {
                    queue.offer(temp);
                    temp = temp.right;
                }
            }
        }
        System.out.println("");
        return;
    }

    /**
     *  returns the string representation of the tree
     *
     * @return string representation of the tree
     */
    public String preOrderTraverse(){
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }
    /**
     * toString method uses the binary tree's preOrderTrevers function
     */
    private void preOrderTraverse(Node<E> node, int depth,
                                 StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("  ");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

}
