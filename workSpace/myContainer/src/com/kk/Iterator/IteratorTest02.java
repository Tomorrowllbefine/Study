package com.kk.Iterator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 使用Iterator遍历Set
 */
public class IteratorTest02 {
    public static void main(String[] args) {

        Set<String> set = new HashSet<>();

        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");

        Iterator<String> it = set.iterator();

        // 方式一：使用while循环进行遍历
        while( it.hasNext() ){
            String value = it.next();
            System.out.println(value);
        }
        System.out.println("------------------");

        // 方式二：使用for循环进行遍历
        for ( Iterator<String> it1 = set.iterator(); it1.hasNext(); ){
            String value = it1.next();
            System.out.println(value);
        }
    }
}
