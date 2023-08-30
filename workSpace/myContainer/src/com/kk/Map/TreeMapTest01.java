package com.kk.Map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapTest01 {
    public static void main(String[] args) {

        Map<User,String> map = new TreeMap<>(new UserComparator());
        User user1 = new User("kk",18);
        User user2 = new User("ll",19);
        User user3 = new User("aa",19);

        map.put(user1,user1.getName());
        map.put(user2,user2.getName());
        map.put(user3,user3.getName());

        Set<User> users = map.keySet();
        for (User u : users) {
            System.out.println(u + " : " + map.get(u));
        }
    }
}
