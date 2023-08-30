package com.kk.Set;

import java.util.HashSet;
import java.util.Set;

public class HashSetTest02 {
    public static void main(String[] args) {

        Set<User> set = new HashSet<>();

        User u1 = new User("kk",18);
        User u2 = new User("kk",18);

        System.out.println(u1 == u2);

        System.out.println(set.add(u1));
        System.out.println(set.add(u2));
    }
}
