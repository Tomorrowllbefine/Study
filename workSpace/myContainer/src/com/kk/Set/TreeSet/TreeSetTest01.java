package com.kk.Set;

import sun.security.ec.ECDSAOperations;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetTest01 {

    public static void main(String[] args) {
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("a");
        treeSet.add("d");
        treeSet.add("b");
        treeSet.add("e");
        System.out.println(treeSet);

        Set<Integer> treeSet1 = new TreeSet<>();
        treeSet1.add(1);
        treeSet1.add(4);
        treeSet1.add(2);
        treeSet1.add(3);
        System.out.println(treeSet1);
    }
}
