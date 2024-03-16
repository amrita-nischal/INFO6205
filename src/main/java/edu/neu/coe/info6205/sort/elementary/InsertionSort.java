/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;
import java.util.Arrays;
import java.util.Random;

/**
 * Class InsertionSort.
 *
 * @param <X> the underlying comparable type.
 */
public class InsertionSort<X extends Comparable<X>> extends SortWithHelper<X> {

    /**
     * Constructor for any sub-classes to use.
     *
     * @param description the description.
     * @param N           the number of elements expected.
     * @param config      the configuration.
     */
    protected InsertionSort(String description, int N, Config config) {
        super(description, N, config);
    }

    /**
     * Constructor for InsertionSort
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public InsertionSort(int N, Config config) {
        this(DESCRIPTION, N, config);
    }

    public InsertionSort(Config config) {
        this(new BaseHelper<>(DESCRIPTION, config));
    }

    /**
     * Constructor for InsertionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public InsertionSort(Helper<X> helper) {
        super(helper);
    }

    public InsertionSort() {
        this(BaseHelper.getHelper(InsertionSort.class));
    }

    /**
     * Sort the sub-array xs:from:to using insertion sort.
     *
     * @param xs   sort the array xs from "from" to "to".
     * @param from the index of the first element to sort
     * @param to   the index of the first element not to sort
     */
    public void sort(X[] xs, int from, int to) {
        final Helper<X> helper = getHelper();
        // TO BE IMPLEMENTED

        for (int i = from + 1; i < to; i++) {
            for (int j = i; j > from; j--) {
                if (!helper.swapStableConditional(xs, j)) {
                    break;
                }
            }
        }


    }


    public static void main(String[] args) {
        int[] testSizes = {100, 200, 400, 800, 1600};
        for (int n : testSizes) {
            Integer[] randomArray = createRandomArray(n);
            Integer[] orderedArray = createOrderedArray(n);
            Integer[] partiallyOrderedArray = createPartiallyOrderedArray(n);
            Integer[] reverseOrderedArray = createReverseOrderedArray(n);

            sort("Random Array", randomArray);
            sort("Ordered Array", orderedArray);
            sort("Partially-Ordered Array", partiallyOrderedArray);
            sort("Reverse-Ordered Array", reverseOrderedArray);
        }
    }

    private static void sort(String desc, Integer[] array) {
        InsertionSort<Integer> sorter = new InsertionSort<>();

        // warming up the sort function
        for (int i = 0; i < 10; i++) {
            sorter.sort(array);
        }

        Benchmark_Timer<Integer[]> timer = new Benchmark_Timer<>(
                desc,
                null,
                b -> new InsertionSort<Integer>().sort(b, 0, b.length),
                null
        );

        double time = timer.run(array, 10);
        System.out.println(desc + " of size " + array.length + ": " + time + " ms");
    }

    private static Integer[] createRandomArray(int n) {
        Random random = new Random();
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(n);
        }
        return arr;
    }

    private static Integer[] createOrderedArray(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }

    private static Integer[] createPartiallyOrderedArray(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n / 2; i++) {
            arr[i] = i;
        }
        Random random = new Random();
        for (int i = n / 2; i < n; i++) {
            arr[i] = random.nextInt(n);
        }
        return arr;
    }

    private static Integer[] createReverseOrderedArray(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = n - i;
        }
        return arr;
    }

    public static final String DESCRIPTION = "Insertion sort";

    public static <T extends Comparable<T>> void sort(T[] ts) {
        new InsertionSort<T>().mutatingSort(ts);
    }
}