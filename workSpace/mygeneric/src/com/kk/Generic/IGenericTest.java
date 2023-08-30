package com.kk.Generic;

public class IGenericTest {
    public static void main(String[] args) {
        IGeneric<String> ig1 = new IGenericImpl();
        System.out.println(ig1.getValue("kk"));

        IGeneric<String> ig2 = new IGenericImpl1<>();
        System.out.println(ig2.getValue("kkakoka"));
    }
}
