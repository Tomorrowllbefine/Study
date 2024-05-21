package com.kk.Set;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class HashSetTest03 {
    public static void main(String[] args) {

        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while(true){
            int num = random.nextInt(11);
            set.add(num);
            if (set.size() == 11) break;
        }
        set.remove(0);
        System.out.println(set);
    }
}
