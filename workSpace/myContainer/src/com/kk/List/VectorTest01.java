package com.kk.List;

import java.util.List;
import java.util.Vector;

public class VectorTest01 {
    public static void main(String[] args) {
        List<Integer> vector = new Vector<>();

        for (int i = 0; i < 10; i++) {
            vector.add(i + 1);
        }
        System.out.println(vector);
        for (Integer i : vector) {
            System.out.print(i + " ");
        }
    }
}
