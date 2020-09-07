package grain.algorithm.sort.sorts;

import grain.algorithm.sort.BinaryInsertionSorting;
import grain.algorithm.sort.ComparisonBasedSort;

/**
 * @author wulifu
 */
public class BinaryInsertionSort extends BinaryInsertionSorting implements ComparisonBasedSort {
    public void customBinaryInsert(int[] array, int start, int end) {
        this.binaryInsertSort(array, start, end);
    }

    @Override
    public void runSort(int[] array, int bucketCount) {
        this.binaryInsertSort(array, 0, array.length);
    }
}
