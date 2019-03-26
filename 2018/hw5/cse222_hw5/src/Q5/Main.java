package Q5;

import Q3.MergeSortDll;
import Q4.HeapSort;
import Q4.InsertionSort;
import Q4.MergeSort;
import Q4.QuickSort;

import java.util.LinkedList;

public class Main {

    public static void main(String args[]){

        Q4.InsertionSort insert = new InsertionSort(); // ters sıralı n  n^2
        Q4.MergeSort merge = new MergeSort();       //her merge'ün kendi arasında yer değiştirmesi gerektiği durum (max comparasion(swap)) always nlogn
        Q4.QuickSort quick = new QuickSort();       // en küçük veya en büyük pivot seçilirse  n^2 ,  nlogn
        Q4.HeapSort heap = new HeapSort();       //  worst case swap sayısı ile alakalı, heapsort max heap yaparak çalışır,  always nlogn
        MergeSortDll mergeDll = new MergeSortDll();

        Integer arr[];
        int size;

        LinkedList<Double>  times = new LinkedList<>(),
                            times1 = new LinkedList<>(),
                            times2 = new LinkedList<>(),
                            times3 = new LinkedList<>(),
                            times4 = new LinkedList<>();

        for (int i = 0; i < 4; ++i) {
            if (i == 0)
                size = 100;
            else if (i == 1)
                size = 1000;
            else if (i == 2)
                size = 5000;
            else
                size = 10000;

        //---------------------------------- insertion sort worst -------------------------------------------------

            arr = new Integer[size];
            makeInsertWorst(size, arr);

            long startTime = System.nanoTime();
            insert.sort(arr);
            double estimatedTime = ((System.nanoTime() - startTime)/ 1000000000.0);
            times.add(estimatedTime);


        //---------------------------------- merge sort worst -------------------------------------------------
            arr = new Integer[size];
            makeMergeWorst(size, arr);
            startTime = System.nanoTime();
            merge.sort(arr);
            estimatedTime = ((System.nanoTime() - startTime)/ 1000000000.0);
            times1.add(estimatedTime);
        //------------------------------------------------------------------------------------------------------

        //---------------------------------- Quick sort worst -------------------------------------------------
            arr = new Integer[size];
            makeQuickHeapWorst(size, arr);
            startTime = System.nanoTime();
            quick.sort(arr);
            estimatedTime = ((System.nanoTime() - startTime)/ 1000000000.0);
            times2.add(estimatedTime);

        //------------------------------------------------------------------------------------------------------

        //---------------------------------- heap sort worst -------------------------------------------------
            arr = new Integer[size];
            makeQuickHeapWorst(size, arr);
            startTime = System.nanoTime();
            heap.sort(arr);
            estimatedTime = ((System.nanoTime() - startTime)/ 1000000000.0);
            times3.add(estimatedTime);

        //---------------------------------- mergeDll sort worst -------------------------------------------------
            arr = new Integer[size];
            makeMergeWorst(size, arr);
            LinkedList<Integer> temp = new LinkedList<>();
            for (Integer val:
                 arr) {
                temp.add(val);
            }
            startTime = System.nanoTime();
            mergeDll.sort(temp);
            estimatedTime = ((System.nanoTime() - startTime)/ 1000000000.0);
            times4.add(estimatedTime);
        //------------------------------------------------------------------------------------------------------

        }

        for (int i = 0; i <4 ; ++i) {   // prints  working time
            System.out.print(times.get(i) +" "+ times1.get(i) +" "+times2.get(i) +" "+times3.get(i) +" "+times4.get(i) +"\n");
        }



    }


    /**
     *
     * @param size  array size
     * @param arr   will be created
     */
    private static void makeInsertWorst(int size, Integer [] arr){
        for (int i = 0; i < size; ++i) {
            arr[i] = size-i;
        }
    }

    /**
     *
     * @param size  array size
     * @param arr   will be created
     */
    private static void makeMergeWorst(int size, Integer [] arr){
        for (int i = 0; i < size; ++i) {
            arr[i] = i+1;
        }
        seperate(arr);
    }

    /**
     *
     * @param size  array size
     * @param arr   will be created
     */
    private static void makeQuickHeapWorst(int size, Integer [] arr){
        for (int i = 0; i < size; ++i) {
            arr[i] = i;
        }
    }



    /**
     *
     * @param arr
     */
    private static <T> void seperate(T[] arr) {

        if(arr.length<=1)
            return;

        if(arr.length==2)
        {
            T swap=arr[0];
            arr[0]=arr[1];
            arr[1]=swap;
            return;
        }

        int i,j;
        int m = (arr.length + 1) / 2;
        T left[]  = (T[]) new Object[m];
        T right[] = (T[]) new Object[arr.length-m];

        for(i=0,j=0;i<arr.length;i=i+2,j++) //Storing alternate elements in left subarray
            left[j]=arr[i];

        for(i=1,j=0;i<arr.length;i=i+2,j++) //Storing alternate elements in right subarray
            right[j]=arr[i];

        seperate(left);
        seperate(right);
        mergeWorst(arr, left, right);
    }

    /**
     *
     * @param arr
     * @param left
     * @param right
     */
    private static <T> void mergeWorst(T[] arr, T[] left, T[] right) {
        int i,j;
        for(i=0;i<left.length;i++)
            arr[i]=left[i];
        for(j=0;j<right.length;j++,i++)
            arr[i]=right[j];
    }

}
