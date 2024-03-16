package edu.neu.coe.info6205.union_find;

import java.util.Random;
import java.util.Scanner;

public class UFClient {
    public static int count(int n) {
        int count = 0;
        UF_HWQUPC ufc = new UF_HWQUPC(n);
        Random random = new Random();
        while (ufc.components() > 1) {
            int x = random.nextInt(n),y = random.nextInt(n);
            ufc.connect(x, y);
            count++;
        }
        return count;
    }
    public static void main(String[] args) {
        // Example: Run the experiment for fixed set of n values if no arguments provided
        System.out.println("Enter a number: ");

        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        System.out.println("For n = " + x + " Connections:" + count(x));

        System.out.println("For fixed set of n values:");

        for (int i = 1000; i < 1000000; i *= 2) {
            int tot = 0;
            for (int j = 0; j < 100; j++){
                tot += count(i);
            }
            int avg = tot / 100;
            System.out.println("Object count(n): " + i + " Pairs(m): " + avg);
        }
    }
}
