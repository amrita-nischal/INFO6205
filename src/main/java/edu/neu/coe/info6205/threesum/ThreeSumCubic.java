package edu.neu.coe.info6205.threesum;

import edu.neu.coe.info6205.util.Stopwatch;

import java.util.*;

/**
 * Implementation of ThreeSum which follows the brute-force approach of
 * testing every candidate in the solution-space.
 * The array provided in the constructor may be randomly ordered.
 * <p>
 * This algorithm runs in O(N^3) time.
 */
class ThreeSumCubic implements ThreeSum {
    /**
     * Construct a ThreeSumCubic on a.
     * @param a an array.
     */
    public ThreeSumCubic(int[] a) {
        this.a = a;
        length = a.length;
    }

    public Triple[] getTriples() {
        List<Triple> triples = new ArrayList<>();
        for (int i = 0; i < length; i++)
            for (int j = i + 1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    if (a[i] + a[j] + a[k] == 0)
                        triples.add(new Triple(a[i], a[j], a[k]));
                }
            }
        Collections.sort(triples);
        return triples.stream().distinct().toArray(Triple[]::new);
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
        int[] array800 = createArray(100);
        int[] array1600 = createArray(200);
        int[] array3200 = createArray(400);
        int[] array6400 = createArray(800);
        int[] array12800 = createArray(1600);

        List<int[]> N = new ArrayList<>();

        N.add(array800);
        N.add(array1600);
        N.add(array3200);
        N.add(array6400);
        N.add(array12800);

        Triple[] triples = null;

        for(int i=0;i<N.size();i++) {
            ThreeSumCubic t = new ThreeSumCubic(N.get(i));
            Stopwatch watch = new Stopwatch();

            try {
                triples = t.getTriples();
                System.out.println(" for N="+ N.get(i).length+", Time: " + watch.lap() + " milliseconds for ThreeSumCubic");
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