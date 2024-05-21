package com.kk.Map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapTest01 {
    public static void main(String[] args) {

        Map<String,String> map = new HashMap<>();
        String put1 = map.put("a", "A");
        String put2 = map.put("b", "B");
        String put3 = map.put("c", "C");
        String put4 = map.put("a", "AA"); // 返回被覆盖的 A
        System.out.println(put1 + "\t"+put2 + "\t"+put3 + "\t"+put4);
        System.out.println("size = "+ map.size());

        System.out.println("----------");
        // 获取元素1：根据指定key获取value
        String v = map.get("a");
        System.out.println("key:a"+ " -- "+"value:"+v);
        System.out.println("----------");

        // 获取元素2：通过遍历获取value
        Set<String> keys = map.keySet();
        for (String k : keys) {
            String value = map.get(k);
            System.out.println("key:"+ k +  " -- "+"value:"+value);
        }
        System.out.println("----------");

        // 获取元素3：通过Map.Entry获取键值对遍历
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String,String>  entry :  entries ) {
            System.out.println(entry.getClass());
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("key:"+ key +  " -- "+"value:"+ value);
        }
        System.out.println("----------");


        // 并集操作
        Map<String,String> map1 = new HashMap<>();
        map1.put("f","F");
        map1.put("c","CC");
        Set<Map.Entry<String, String>> entries1 = map1.entrySet();
        for (Map.Entry<String, String> entry : entries1) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("key:"+ key +  " -- "+"value:"+value);
        }
        System.out.println("\nmap.putAll(map1): ");
        map.putAll(map1);
        Set<Map.Entry<String, String>> entries2 = map.entrySet();
        for (Map.Entry<String, String> e : entries2) {
            String key = e.getKey();
            String value = e.getValue();
            System.out.println("key:"+ key +  " -- "+"value:"+ value);
        }
        System.out.println("----------");

        // 删除容器中的元素
        String a = map.remove("a");
        System.out.println(a);
        for (Map.Entry<String, String> e : entries2) {
            String key = e.getKey();
            String value = e.getValue();
            System.out.println("key:"+ key +  " -- "+"value:"+ value);
        }
        System.out.println("----------");

        // 判断元素的key / value 是否存在
        System.out.println("key \"a\" in map: " + map.containsKey("a"));
        System.out.println("value \"B\" in map: " + map.containsValue("B"));
    }
}
