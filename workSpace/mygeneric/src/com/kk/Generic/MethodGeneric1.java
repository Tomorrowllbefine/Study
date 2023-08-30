package com.kk.Generic;

public class MethodGeneric1<T> {

    public static <T> T getName(T name){
        return name;
    }
    public static <T> void setAge(T age){
        System.out.println(age);
    }

    public static void main(String[] args) {
        MethodGeneric1.setAge(18);
        String name = MethodGeneric1.getName("kk");
        System.out.println(name);
    }
}
