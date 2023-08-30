package com.kk.Generic;

public class Generic<T> {
    private T value;
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }

    public static void main(String[] args) {

        // 指定T为String
        Generic<String> generic1 = new Generic<>();
        generic1.setValue("kk");
        String value1 = generic1.getValue();
        System.out.println(value1);

        // 指定类型为Integer
        Generic<Integer> generic2 = new Generic<>();
        generic2.setValue(25);
        Integer value2 = generic2.getValue();
        System.out.println(value2);
    }
}
