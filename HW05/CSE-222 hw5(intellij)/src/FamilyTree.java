import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.Scanner;


/**
 * Created by furkan on 04.04.2017.
 */
public class FamilyTree<E extends Comparable < E >> extends BinaryTree {

    private  String name,parent,nickname,nick;
    int count;
    /**
     *
     * @param name ilk kişi
     */
    public FamilyTree(String name){
        this.root = new Node(name.trim().toLowerCase());
    }



    /**
     *
     * @param node  yeni root
     */
    private FamilyTree(Node node){
        this.root = node;
    }


    /**
     *
     * @param name      eklenecek kişi
     * @param parent    kişini ailesi
     * @param nickname  ailenin  lakabı
     * @return
     */
    public boolean add(String name, String parent, String nickname) throws  IOException{

        String tokens[] = nickname.toLowerCase().split("-");

        this.name = name.toLowerCase().trim();
        this.nick = tokens[0].trim();
        count =0;
        try {
            if (this.nick.compareTo("ebu") == 0) {
                this.parent = parent.toLowerCase().trim();
                this.nickname = tokens[1].trim();
                search(root); //parent bulduk
            } else {
                this.parent = tokens[1].trim();
                this.nickname = parent.toLowerCase().trim();
                search(root); //parent bulduk
            }
            if(count ==0)
                throw new IOException("Eşleşme bulunamadı, veri eklenemedi !");
            else if(count > 1)
                throw new IOException("Same parent and nickname !");
        }catch (UnexpectedException e){throw new IOException(e.toString());}


        return true;
    }

    /**
     *
     * @param scan  gelen reader
     * @return
     * @throws IOException
     */
    @Override
    public BinaryTree readBinaryTree(Scanner scan) throws IOException{

        String data;

        if (scan.hasNext()) {
            data = scan.nextLine().toLowerCase().trim();

            if (((E)data).compareTo((E)root.data) !=0){
                return null;
            }
        }
        else
            return null;


        while (scan.hasNext()) {
            data =  scan.nextLine().toLowerCase();
            String tokens[]= data.split(",");
            name = tokens[0].trim();

            String token2[] = tokens[2].trim().split("-");

            nick = token2[0].trim();

            count=0;
            try {
                if (nick.compareTo("ebu") == 0) {
                    parent = tokens[1].trim();
                    nickname = token2[1].trim();


                    search(root); //parent bulduk
                } else {
                    parent = token2[1].trim();
                    nickname = tokens[1].trim();

                    search(root); //parent bulduk
                }
                if(count ==0)
                    throw new IOException("Eşleşme bulunamadı, veri eklenemedi !");
                else if(count > 1)
                    throw new IOException("Same parent and nickname !");


            }catch (UnexpectedException e){throw new IOException(e.toString());}
        }
        return new FamilyTree<E>(root);
    }

    /**
     *
     * @param root      gönderilen root
     * @return
     */
    private Node search(Node root) throws UnexpectedException{
        if(root != null)
        {
            if(root.data.equals(parent)){

                if(nick.compareTo("ebu")==0) {
                    if (root.left == null ) {
                        if(name.equals(nickname)) {         // parentın ilk çoçuguysa ve  name ve nickname eşleştiyse
                            root.left = new Node(name);
                            count++;
                        }
                        else
                            return null;
                    }
                    else {
                        if (root.left.data.equals(nickname)) {

                            if(root.left.data.equals(name))
                            {
                                throw new UnexpectedException("Aynı isimde kardeş eklenemez !");

                            }
                            else
                                goRight(name, root.left);
                        }
                        else{
                            return null;
                        }
                    }
                }
                else
                {
                    try {
                        if (root.left != null) {

                            Node temp = ibnFinder(nickname, name, root.left);

                            if (temp == null)
                                return null;
                        }
                        else
                            return  null;
                    }catch (UnexpectedException e){throw e;}
                }
                try {
                    if (root.left != null) {
                        //Node temp =
                        search(root.left);

                    }
                    if (root.right != null /*&& temp == null*/) {
                        search(root.right);
                    }
                }catch (UnexpectedException e){throw e;}

            } else {

                try {
                    if (root.left != null) {
                        //Node temp =
                        search(root.left);

                    }
                    if (root.right != null /*&& temp == null*/) {
                        search(root.right);
                    }
                }catch (UnexpectedException e){throw e;}
                return root;
            }
        }

        return root;
    }

    /**
     *
     * @param name eklenecek kişi
     * @param root verilen node
     * @return
     */
    private Node goRight(String name, Node root) throws UnexpectedException {
        if(root.right != null && name.equals(root.right.data)){
            throw new UnexpectedException("Aynı isimde kardeş eklenemez !\n");
        }
        if (root.right == null){
            root.right = new Node(name);
            count++;
            return root;
        }
        else
            goRight(name, root.right);

        return root;
    }

    /**
     *
     * @param nickname eklenen kişinin ailesi (anne/baba)
     * @param name eklenecek kişi
     * @param root gelen root
     * @return
     */
    private Node ibnFinder(String nickname, String name, Node root) throws UnexpectedException{
        if (nickname.equals(root.data)){
            if(root.left == null)
            {
                root.left = new Node<>(name);
                count++;
                return root;
            }

        }
        else {
            if(root.right != null)
                ibnFinder(nickname, name, root.right);
            else
                return null;
        }
        return root;
    }
}
