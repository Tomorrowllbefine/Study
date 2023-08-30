package com.kk.Iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *  使用Iterator遍历List
 */
public class IteratorTest01 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        // 遍历方式一：while循环
        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            String value = it.next();
            System.out.println(value);
        }
        System.out.println("------------------");

        // 遍历方式二：for循环
        for (Iterator<String> it1 = list.iterator(); it1.hasNext(); ){
            String value = it1.next();
            System.out.println(value);
        }
        System.out.println("------------------");

    }
}
