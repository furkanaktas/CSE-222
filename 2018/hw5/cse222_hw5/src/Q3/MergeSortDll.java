package Q3;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/** Implements the recursive merge sort algorithm. In this version, copies
 *   of the subtables are made, sorted, and then merged.
 *   @author Koffman and Wolfgang
 */

public class MergeSortDll {
    /** Sort the array using the merge sort algorithm.
     pre: table contains Comparable objects.
     post: table is sorted.
     @param table The array to be sorted
     */
    public static < T
            extends Comparable < T >> void sort(List<T> table) {
        // A table with one element is sorted already.
        if (table.size() > 1) {
            // Split table into halves.
            int halfSize = table.size()/ 2;

            LinkedList<T> leftTable = new LinkedList();
            LinkedList<T> rightTable = new LinkedList();


            /*Copy data to temp arrays*/
            ListIterator<T> iteratorL = table.listIterator(0);
            ListIterator<T> iteratorR = table.listIterator(halfSize);

            for (int i=0; i<halfSize; ++i)
                leftTable.add(iteratorL.next());
            for (int j=0; j< (table.size() - halfSize); ++j)
                rightTable.add(iteratorR.next());

            // Sort the halves.
            sort(leftTable);
            sort(rightTable);

            // Merge the halves.
            merge(table, leftTable, rightTable);


        }
    }

    /** Merge two sequences.
     pre: leftSequence and rightSequence are sorted.
     post: outputSequence is the merged result and is sorted.
     @param outputSequence The destination
     @param leftSequence The left input
     @param rightSequence The right input
     */
    private static < T
            extends Comparable < T >> void merge(List<T> outputSequence,
                                                 List<T> leftSequence,
                                                 List<T> rightSequence) {


        Iterator<T> iterL = leftSequence.iterator();
        Iterator<T> iterR = rightSequence.iterator();

        /* Merge the temp arrays */

        // Initial index of merged subarry array
        int index = 0;

        boolean hasNextL = iterL.hasNext(),
                hasNextR = iterR.hasNext();

        T lItem = null,
                rItem = null;

        if (hasNextL)
            lItem = iterL.next();

        if (hasNextR)
            rItem = iterR.next();


        while ( hasNextL && hasNextR)
        {

            if (lItem.compareTo(rItem) != 1) // L <= R
            {
                outputSequence.set(index, lItem);
                hasNextL = iterL.hasNext();
                if (hasNextL)
                    lItem = iterL.next();
            }
            else
            {
                outputSequence.set(index, rItem);
                hasNextR = iterR.hasNext();
                if (hasNextR)
                    rItem = iterR.next();
            }
            ++index;
        }


        /* Copy remaining elements of  leftSequence if any */
        while (hasNextL)
        {
            outputSequence.set(index, lItem);
            hasNextL = iterL.hasNext();
            if (hasNextL)
                lItem = iterL.next();

            ++index;
        }

        /* Copy remaining elements of  rightSequence if any */
        while (hasNextR)
        {
            outputSequence.set(index, rItem);
            hasNextR = iterR.hasNext();
            if (hasNextR)
                rItem = iterR.next();
            ++index;
        }
    }

}
