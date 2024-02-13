package edu.neu.coe.info6205.union_find;

import java.util.*;

public class UF_Client {
    public static int count(int n) {
        int count = 0;
        UF_HWQUPC uf_hwqupc = new UF_HWQUPC(n);
        Random r = new Random();
        while ((uf_hwqupc.components()) > 1) {
            int a = r.nextInt(n);
            int b = r.nextInt(n);

            uf_hwqupc.connect(a, b);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {

        System.out.println("Number: ");

        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        System.out.println("Objects: " + number + " Connections:" + count(number));

        System.out.println("Relationship between m and n: ");

        for (int i = 1000; i < 1000000; i *= 2) {
            int total = 0;
            for (int j = 0; j < 100; j++){
                total += count(i);
            }
            int meanOfNumber = total / 100;
            System.out.println("Object number: " + i + " Pairs: " + meanOfNumber);
        }
    }
}