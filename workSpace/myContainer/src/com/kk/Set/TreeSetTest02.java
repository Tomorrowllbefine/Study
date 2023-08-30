package com.kk.Set;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetTest02 {

    public static void main(String[] args) {

//        Set<User> treeSet = new TreeSet<>( (o1,o2) -> o1.getAge() - o2.getAge());
        Set<User> treeSet = new TreeSet<>();
        treeSet.add(new User("kk",23));
        treeSet.add(new User("ll",13));
        treeSet.add(new User("oo",23));
        System.out.println(treeSet);
    }
}
