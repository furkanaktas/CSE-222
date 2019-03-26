package Q2;

public class Main {
    public static void main(String [] args){

        Integer [] arr = new Integer[25];

        for (int i = 0; i <10 ; i++) {
            arr[i]= i*3;
        }
        // 0 3 6 9 12 15 18 21 24 27

        System.out.println(binarySearch(4, arr, 0, 9));
        System.out.println(binarySearch(3, arr, 0, 9));
        System.out.println(binarySearch(7, arr, 0, 9));
        System.out.println(binarySearch(14, arr, 0, 9));
        System.out.println(binarySearch(27, arr, 0, 9));
        System.out.println(binarySearch(30, arr, 0, 9));
    }

    private static <E extends Comparable> int binarySearch(E item, E[] localItems,int start , int end){

        if (start >= end){

            if (item.compareTo(localItems[start]) > 0){
                return start+1;
            }
            else {
                return start;
            }
        }
        int mid = (start + end)/2;
        if (localItems[mid].compareTo(item) == 0)
            return mid;

        if (item.compareTo(localItems[mid]) > 0){
            return binarySearch(item, localItems, mid+1, end);
        }
        else
        {
            return binarySearch(item, localItems, start, mid);
        }
    }
}
