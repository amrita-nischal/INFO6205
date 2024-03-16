package edu.neu.coe.info6205.threesum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import edu.neu.coe.info6205.util.Stopwatch;
import java.util.Random;

/**
 * Implementation of ThreeSum which follows the approach of dividing the solution-space into
 * N sub-spaces where each sub-space corresponds to a fixed value for the middle index of the three values.
 * Each sub-space is then solved by expanding the scope of the other two indices outwards from the starting point.
 * Since each sub-space can be solved in O(N) time, the overall complexity is O(N^2).
 * <p>
 * NOTE: The array provided in the constructor MUST be ordered.
 */
public class ThreeSumQuadratic implements ThreeSum {
    /**
     * Construct a ThreeSumQuadratic on a.
     * @param a a sorted array.
     */
    public ThreeSumQuadratic(int[] a) {
        this.a = a;
        length = a.length;
    }

    public Triple[] getTriples() {
        List<Triple> triples = new ArrayList<>();
        for (int i = 0; i < length; i++) triples.addAll(getTriples(i));
        Collections.sort(triples);
        return triples.stream().distinct().toArray(Triple[]::new);
    }

    /**
     * Get a list of Triples such that the middle index is the given value j.
     *
     * @param j the index of the middle value.
     * @return a Triple such that
     */
    public List<Triple> getTriples(int j) {
        List<Triple> triples = new ArrayList<>();
        // TO BE IMPLEMENTED  : for each candidate, test if a[i] + a[j] + a[k] = 0.

        int left = 0;
        int right = length - 1;
        while (left < j && right > j) {
            int sum = a[left] + a[j] + a[right];
            if (sum == 0) {
                triples.add(new Triple(a[left], a[j], a[right]));
                left++;
                right--;
            } else if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }
        return triples;
//throw new RuntimeException("implementation missing");
    }
    public static int[] createArray(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Array size must be greater than zero.");
        }

        int[] randomArray = new int[N];
        Random random = new Random();

        for (int i = 0; i < N; i++) {
            // Generating random integers (you can adjust the range as needed)
            randomArray[i] = random.nextInt(21) - 10; // Generates random integers between -100 and 100
        }

        return randomArray;
    }
    public static void main(String args[]){
        int[] array800 = createArray(800);
        int[] array1600 = createArray(1600);
        int[] array3200 = createArray(3200);
        int[] array6400 = createArray(6400);
        int[] array12800 = createArray(12800);

        List<int[]> N = new ArrayList<>();

        N.add(array800);
        N.add(array1600);
        N.add(array3200);
        N.add(array6400);
        N.add(array12800);

        Triple[] triples = null;

        for(int i=0;i<N.size();i++) {
            ThreeSumQuadratic t = new ThreeSumQuadratic(N.get(i));
            Stopwatch watch = new Stopwatch();

            try {
                triples = t.getTriples();
                System.out.println(" for N="+ N.get(i).length+", Time: " + watch.lap() + " milliseconds for ThreeSumQuadratic");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                watch.close();
            }
            Arrays.stream(triples)
                    .forEach(triple -> System.out.println("x: " + triple.x + ", y: " + triple.y + ", z: " + triple.z));
        }
    }

    private final int[] a;
    private final int length;
}