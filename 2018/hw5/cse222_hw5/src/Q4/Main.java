package Q4;

import Q3.MergeSortDll;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import static java.lang.Math.random;

public class Main {

    public static void main(String [] args){

        LinkedList<Double>  list = new LinkedList<>(),  // insertion   times
                            list1= new LinkedList<>(),  // merge   times
                            list2= new LinkedList<>(),  // quick   times
                            list3= new LinkedList<>(),  // heap   times
                            list4= new LinkedList<>();  // mergeDll   times


  /*    test(100  , list, list1, list2, list3, list4);  // 1.
        test(500  , list, list1, list2, list3, list4);
        test(1000 , list, list1, list2, list3, list4);
        test(2000 , list, list1, list2, list3, list4);
        test(3000 , list, list1, list2, list3, list4);
        test(4000 , list, list1, list2, list3, list4);
        test(5000 , list, list1, list2, list3, list4);
        test(6000 , list, list1, list2, list3, list4);
        test(8000 , list, list1, list2, list3, list4);
        test(10000, list, list1, list2, list3, list4); // 10.
*/

        System.out.println("              Insertion     Merge         Quick         Heap          MergeDll    ");
        int size=0;
        for (int i = 0; i < 100; ++i) {
            if (i%10 ==0)
                size = 100;
            else if (i%10 ==1)
                size = 500;
            else if (i%10 ==2)
                size = 1000;
            else if (i%10 ==8)
                size = 8000;
            else if (i%10 ==9)
                size = 10000;
            else
                size += 1000;

            test(size  , list, list1, list2, list3, list4);  // 1.

            System.out.printf("%-5d items:  ",size);
            System.out.println( customFormat(list.get(i))  + "  " +
                                customFormat(list1.get(i)) + "  " +
                                customFormat(list2.get(i)) + "  " +
                                customFormat(list3.get(i)) + "  " +
                                customFormat(list4.get(i))           );

        }

        Vector<Double>  avg = new Vector<>(),
                        avg1 = new Vector<>(),
                        avg2 = new Vector<>(),
                        avg3 = new Vector<>(),
                        avg4 = new Vector<>();

        for (int i = 0; i <10 ; ++i) {
            avg.add(0.0);
            avg1.add(0.0);
            avg2.add(0.0);
            avg3.add(0.0);
            avg4.add(0.0);

        }
        int j;
        for (int i = 0; i < 100; ++i) {
            if      (i%10 ==0)
                j=0;
            else if (i%10 ==1)
                j=1;
            else if (i%10 ==2)
                j=2;
            else if (i%10 ==3)
                j=3;
            else if (i%10 ==4)
                j=4;
            else if (i%10 ==5)
                j=5;
            else if (i%10 ==6)
                j=6;
            else if (i%10 ==7)
                j=7;
            else if (i%10 ==8)
                j=8;
            else
                j=9;

            avg.set (j, avg.get(j)+ list.get(i));
            avg1.set(j, avg1.get(j)+ list1.get(i));
            avg2.set(j, avg2.get(j)+ list2.get(i));
            avg3.set(j, avg3.get(j)+ list3.get(i));
            avg4.set(j, avg4.get(j)+ list4.get(i));     // toplam süre


        }
        for (j = 0; j <10 ; ++j) { // j=0 = 100 , j=1 = 500 , j=2 = 1000 , .... , j=9 = 10000
            avg.set (j, avg.get(j)/10.0);
            avg1.set(j, avg1.get(j)/10.0);
            avg2.set(j, avg2.get(j)/10.0);
            avg3.set(j, avg3.get(j)/10.0);
            avg4.set(j, avg4.get(j)/10.0);          // ortalamalar bulundu
        }

        for (j = 0; j <10 ; ++j) { // j=0 = 100 , j=1 = 500 , j=2 = 1000 , .... , j=9 = 10000
            System.out.print(avg.get(j) +"   "+avg1.get(j)+"   "+avg2.get(j)+"   "+ avg3.get(j) +"   "+ avg4.get(j) +" \n");


        }


    }

    private static void test(int size, List list, List list1, List list2, List list3, List list4){
        MergeSortDll mergeDll = new MergeSortDll();
        InsertionSort insertion = new InsertionSort();
        MergeSort merge = new MergeSort();
        QuickSort quick = new QuickSort();
        HeapSort heap = new HeapSort();


        Integer arr[] = new Integer[size];
        LinkedList<Integer> ll = new LinkedList<>();

        int rand;
        for (int i = 0; i < size ; ++i) {
            rand = (int) (random()*1000);
            arr[i]= rand;
            ll.add(rand);
        }



        long startTime = System.nanoTime();
        insertion.sort(arr);
        double estimatedTime = ((System.nanoTime() - startTime)/ 1000000000.0);
        list.add(estimatedTime);

        startTime = System.nanoTime();
        merge.sort(arr);
        estimatedTime = ((System.nanoTime() - startTime)/ 1000000000.0);
        list1.add(estimatedTime);


        startTime = System.nanoTime();
        quick.sort(arr);
        estimatedTime = ((System.nanoTime() - startTime)/ 1000000000.0);
        list2.add(estimatedTime);

        startTime = System.nanoTime();
        heap.sort(arr);
        estimatedTime = ((System.nanoTime() - startTime)/ 1000000000.0);
        list3.add(estimatedTime);


        startTime = System.nanoTime();
        mergeDll.sort(ll);
        estimatedTime = ((System.nanoTime() - startTime)/ 1000000000.0);
        list4.add(estimatedTime);

    }

    static public String customFormat(double value ) {
        DecimalFormat myFormatter = new DecimalFormat("0.0000000000");
        return myFormatter.format(value);
    }

    /* A utility function to print array of size n */
    static void printArray(Integer arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }
}

