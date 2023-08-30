package com.kk.List;

import java.util.ArrayList;
import java.util.List;

/**
 *  ArrayList的基本使用
 */
public class ArrayListTest01 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i+1);
        }
        System.out.println(list);
        System.out.println(list.size());

        // 修改元素
        list.set(0,100);
        Integer integer = list.get(0);
        System.out.println("get(0) = " + integer);
        System.out.println(list);
        System.out.println(list.size());


        // 删除元素
        Integer remove = list.remove(0);
        System.out.println("removed = " + remove);
        System.out.println(list);
        System.out.println(list.size());

        // 返回第一个匹配索引
        int index = list.indexOf(8);
        System.out.println("8 in index: "+ index);

        // 清除操作
        list.clear();
        System.out.println(list);
        System.out.println(list.size());

    }
}
