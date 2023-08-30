package com.kk.Set;

import java.util.HashSet;
import java.util.Set;

public class HashSetTest01 {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();

        boolean tag1 = set.add("a");
        boolean tag2 = set.add("b");
        boolean tag3 = set.add("c");
        boolean tag4 = set.add("a");
        System.out.println(tag1 + " " + tag2+" " + tag3 + " "+ tag4);
        System.out.println(set);

        // 删除元素
        boolean tag5 =  set.remove("a");
        System.out.println("删除返回值:" + tag5);
        System.out.println(set);

        // 获取大小
        System.out.println("大小: " + set.size());

    }
}
