package com.kk.Generic;

public class MethodGeneric {
    public <T> void setName(T name){
        System.out.println(name);
    }
    public <T> T getAge(T age){
        return age;
    }

    public <T> void method(T ...args){
        for (T item : args) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MethodGeneric methodGeneric = new MethodGeneric();
        methodGeneric.setName("kk");
        Integer age = methodGeneric.getAge(18);
        System.out.println(age);

        // 测试泛型方法的可变参数
        String[] arr1 = new String[]{"k","kk","kkk"};
        Integer[] arr2 = new Integer[]{1,2,3,4,5};
        methodGeneric.method(arr1);
        methodGeneric.method(arr2);
    }
}
