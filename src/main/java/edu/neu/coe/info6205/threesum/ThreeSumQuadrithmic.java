package edu.neu.coe.info6205.threesum;

import edu.neu.coe.info6205.util.Stopwatch;

import java.util.*;

/**
 * Implementation of ThreeSum which follows the simple optimization of
 * requiring a sorted array, then using binary search to find an element x where
 * -x the sum of a pair of elements.
 * <p>
 * The array provided in the constructor MUST be ordered.
 * <p>
 * This algorithm runs in O(N^2 log N) time.
 */
class ThreeSumQuadrithmic implements ThreeSum {
    /**
     * Construct a ThreeSumQuadrithmic on a.
     * @param a a sorted array.
     */
    public ThreeSumQuadrithmic(int[] a) {
        this.a = a;
        length = a.length;
    }

    public Triple[] getTriples() {
        List<Triple> triples = new ArrayList<>();
        for (int i = 0; i < length; i++)
            for (int j = i + 1; j < length; j++) {
                Triple triple = getTriple(i, j);
                if (triple != null) triples.add(triple);
            }
        Collections.sort(triples);
        return triples.stream().distinct().toArray(Triple[]::new);
    }

    public Triple getTriple(int i, int j) {
        int index = Arrays.binarySearch(a, -a[i] - a[j]);
        if (index >= 0 && index > j) return new Triple(a[i], a[j], a[index]);
        else return null;
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
            ThreeSumQuadrithmic t = new ThreeSumQuadrithmic(N.get(i));
            Stopwatch watch = new Stopwatch();

            try {
                triples = t.getTriples();
                System.out.println(" for N="+ N.get(i).length+", Time: " + watch.lap() + " milliseconds for ThreeSumQuadrithmic");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                watch.close();
            }
            Arrays.stream(t.getTriples())
                    .forEach(triple -> System.out.println("x: " + triple.x + ", y: " + triple.y + ", z: " + triple.z));
        }
    }

    private final int[] a;
    private final int length;

    public int[] getA(){
        return this.a;
    }
}