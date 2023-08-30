package com.kk.Generic;

public class IGenericImpl1<T> implements IGeneric<T>{
    @Override
    public T getValue(T name) {
        return name;
    }
}
