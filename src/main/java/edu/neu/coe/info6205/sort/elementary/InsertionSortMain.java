package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;

import java.util.ArrayList;
import java.util.Random;

public class InsertionSortMain {

    public static void main(String[] args) {
        InsertionSort<Integer> insertionSort = new InsertionSort<>();
        Random random = new Random();
        for (int i = 2000; i < 35000; i = i * 2) {
            ArrayList<Integer> random_array_list = new ArrayList<>();
            ArrayList<Integer> ordered_array_list = new ArrayList<>();
            ArrayList<Integer> reversed_array_list = new ArrayList<>();
            ArrayList<Integer> partially_ordered_array_list = new ArrayList<>();
            for (int j = 0; j < i; j++) random_array_list.add(random.nextInt(i));
            for (int j = 0; j < i; j++) ordered_array_list.add(j + 1);
            for (int j = i; j > 0; j--) reversed_array_list.add(j);
            for (int j = 0; j < i; j++) {
                if (j > i / 2) partially_ordered_array_list.add(random.nextInt(i));
                else partially_ordered_array_list.add(j);
            }


            Integer[] random_array = random_array_list.toArray(new Integer[0]);
            Integer[] ordered_array = ordered_array_list.toArray(new Integer[0]);
            Integer[] reversed_array = reversed_array_list.toArray(new Integer[0]);
            Integer[] partially_ordered_array = partially_ordered_array_list.toArray(new Integer[0]);

            Benchmark<Boolean> benchmarkRandom = new Benchmark_Timer<>("randomSort", b -> {
                insertionSort.sort(random_array.clone(), 0, random_array.length);
            });

            double resultRandom = benchmarkRandom.run(true, 10);
            Benchmark<Boolean> benchmarkOrdered = new Benchmark_Timer<>("orderedSort", b -> {
                insertionSort.sort(ordered_array.clone(), 0, ordered_array.length);
            });
            double resultOrdered = benchmarkOrdered.run(true, 10);

            Benchmark<Boolean> benchmarkReverse = new Benchmark_Timer<>("reverseSort", b -> {
                insertionSort.sort(reversed_array.clone(), 0, reversed_array.length);
            });
            double resultReverse = benchmarkReverse.run(true, 10);
            Benchmark<Boolean> benchmarkPartiallyOrdered = new Benchmark_Timer<>("partiallyOrderedSort", b -> {
                insertionSort.sort(partially_ordered_array.clone(), 0, partially_ordered_array.length);
            });
            double resultPartiallyOrdered = benchmarkPartiallyOrdered.run(true, 10);
            System.out.println("Array Size: " + i);
            System.out.println("Random Array: " + resultRandom);
            System.out.println("Ordered Array: " + resultOrdered);
            System.out.println("Reversed Array: " + resultReverse);
            System.out.println("Partially Ordered Array: " + resultPartiallyOrdered + "\n\n");

        }
    }
}
