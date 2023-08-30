package com.kk.Iterator;

import com.kk.Map.User;
import com.kk.Map.UserComparator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 使用Iterator遍历Map
 */
public class IteratorTest03 {
    public static void main(String[] args) {

        Map<User,String> map = new HashMap<>();
        User user1 = new User("kk",18);
        User user2 = new User("oo",19);
        User user3 = new User("aa",19);

        map.put(user1,user1.getName());
        map.put(user2,user2.getName());
        map.put(user3,user3.getName());

        // 方式一：while循环
            // 使用entrySet方法
        Set<Map.Entry<User, String>> entries = map.entrySet();
        Iterator<Map.Entry<User, String>> it1 = entries.iterator();
        while(it1.hasNext()){
            Map.Entry<User, String> entry = it1.next();
            User key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " : " + value);
        }
        System.out.println("-------------------");
            // 使用keySet方法
        Set<User> users = map.keySet();
        Iterator<User> it2 = users.iterator();;
        while(it2.hasNext()){
            User u = it2.next();
            String value = map.get(u);
            System.out.println(u + " : " + value);
        }
        System.out.println("-------------------");

        // 方式二：使用for循环
            // 使用entrySet方法
        for (Iterator<Map.Entry<User,String>> it3 = map.entrySet().iterator(); it3.hasNext();){
            Map.Entry<User,String> entry = it3.next();
            User key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " : " + value);
        }
        System.out.println("-------------------------");

            // 使用keySet方法
        for (Iterator<User> it4 = map.keySet().iterator(); it4.hasNext(); ){
            User user = it4.next();
            String value = map.get(user);
            System.out.println(user + " : " + value);
        }
        System.out.println("-------------------------");
    }
}
