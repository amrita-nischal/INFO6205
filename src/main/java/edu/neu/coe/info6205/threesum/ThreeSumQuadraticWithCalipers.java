package edu.neu.coe.info6205.threesum;

import edu.neu.coe.info6205.util.Stopwatch;

import java.util.*;
import java.util.function.Function;

/**
 * Implementation of ThreeSum which follows the approach of dividing the solution-space into
 * N sub-spaces where each subspace corresponds to a fixed value for the middle index of the three values.
 * Each subspace is then solved by expanding the scope of the other two indices outwards from the starting point.
 * Since each subspace can be solved in O(N) time, the overall complexity is O(N^2).
 * <p>
 * The array provided in the constructor MUST be ordered.
 */
public class ThreeSumQuadraticWithCalipers implements ThreeSum {
    /**
     * Construct ints ThreeSumQuadratic on ints.
     *
     * @param ints a sorted array.
     */
    public ThreeSumQuadraticWithCalipers(int[] ints) {
        this.a = ints;
        length = ints.length;
    }

    /**
     * Get an array or Triple containing all of those triples for which sum is zero.
     *
     * @return a Triple[].
     */
    public Triple[] getTriples() {
        List<Triple> triples = new ArrayList<>();
        Collections.sort(triples); // ???
        for (int i = 0; i < length - 2; i++)
            triples.addAll(calipers(a, i, Triple::sum));
        return triples.stream().distinct().toArray(Triple[]::new);
    }

    /**
     * Get a set of candidate Triples such that the first index is the given value i.
     * Any candidate triple is added to the result if it yields zero when passed into function.
     *
     * @param a        a sorted array of ints. This method is concerned only with the partition of a starting with index i+1.
     * @param i        the index of the first element of resulting triples.
     * @param function a function which takes a triple and returns a value which will be compared with zero.
     * @return a List of Triples.
     */
    public static List<Triple> calipers(int[] a, int i, Function<Triple, Integer> function) {
        List<Triple> triples = new ArrayList<>();
        // TO BE IMPLEMENTED  : use function to qualify triples and to navigate otherwise.

        int n = a.length;
        int left = i+1, right = n-1;

        while(left<right){
            Triple triple = new Triple(a[i],a[left],a[right]);
            int sum = function.apply(triple);

            if(sum ==0){
                triples.add(triple); left++; right--;}
            else if(sum < 0)
                left++;
            else if(sum>0)
                right--;

        }

        return triples;
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
            ThreeSumQuadraticWithCalipers t = new ThreeSumQuadraticWithCalipers(N.get(i));

            Stopwatch watch = new Stopwatch();

            try {
                triples = t.getTriples();
                System.out.println(" for N="+ N.get(i).length+", Time: " + watch.lap() + " milliseconds for ThreeSumQuadraticWithCalipers");
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