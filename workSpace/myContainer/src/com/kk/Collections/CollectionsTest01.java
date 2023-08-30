package com.kk.Collections;

import com.kk.Map.User;
import com.kk.Map.UserComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.*;

/**
 * 排序sort的使用
 */
public class CollectionsTest01 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("d");
        list.add("c");
        list.add("b");
        System.out.println("排序前: " + list);
        Collections.sort(list);
        System.out.println("排序后: " + list);

        System.out.println("-------------------");
        List<User> list1 = new ArrayList<>();
        User user1 = new User("kk",18);
        User user2 = new User("ll",19);
        User user3 = new User("aa",19);
        list1.add(user2);
        list1.add(user1);
        list1.add(user3);

        System.out.println("排序前: " + list1);
        Collections.sort(list1, new UserComparator() {
        }); // 按年龄升序、名字字典序升序
        System.out.println("排序后: " + list1);


    }
}
