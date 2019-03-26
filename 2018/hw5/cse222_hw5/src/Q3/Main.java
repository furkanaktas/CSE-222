package Q3;

import java.util.LinkedList;

import static java.lang.Math.random;

public class Main {


    public static void main(String [] args)
    {

        System.out.println("--------------------- Merge Dll ---------------------");
        MergeSortDll list = new MergeSortDll();

        LinkedList<Integer> ll = new LinkedList<>();

        for (int i = 0; i < 25 ; i++) {
            ll.add((int) (random()*1000));
        }

        System.out.println(ll.toString());
        list.sort(ll);
        System.out.println(ll.toString());
    }
}
