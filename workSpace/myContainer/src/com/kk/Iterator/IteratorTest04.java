package com.kk.Iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 测试删除元素
 */
public class IteratorTest04 {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        // 朴素for循环中删除元素
        /*for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            list.remove(i);
            i--;
        }
        System.out.println(list);
        System.out.println("--------------------");*/

        // foreach循环中删除元素
        /*for (String it : list) {
            list.remove(it);
            System.out.println(it);
        }*/


        // Iterator + for循环
        for (Iterator<String> it = list.iterator(); it.hasNext(); ) {
            String next = it.next();
            //it.remove();
            list.remove(next);
        }
        System.out.println(list);
    }
}
