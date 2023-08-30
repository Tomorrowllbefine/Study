package com.kk.List;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest02 {

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        System.out.println("list1 : " +list1);

        List<String> list2 = new ArrayList<>();
        list2.add("c");
        list2.add("d");
        list2.add("e");
        System.out.println("list2 : " + list2);

        // list1并list2
        list1.addAll(list2);
        System.out.println("list1并list2 : " +list1);

        // list1交list2
        list1.retainAll(list2);
        System.out.println("list1交list2 : " +list1);

        // list3差list1
        List<String> list3 = new ArrayList<>();
        list3.add("a");
        list3.add("b");
        list3.add("c");
        System.out.println("list3 : " + list3);
        list3.removeAll(list1);
        System.out.println("list3差集list1 : " + list3);

    }
}
