package com.kk.List;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 需求：
 * 产生1-10之间的随机数([1,10]闭区间)，将不重复的10个随机数放到容器中。
 */
public class ArrayListTest03 {
    public static void main(String[] args) {

        Random random = new Random();
        List<Integer> list =  new ArrayList<>();
        while(true){
            int num = random.nextInt(11);
            if( num != 0 && list.indexOf(num) == -1){
                list.add(num);
            }
            if( list.size() == 10) break;
        }
        System.out.println(list);
    }
}
