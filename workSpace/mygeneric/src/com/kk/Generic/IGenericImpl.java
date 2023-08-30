package com.kk.Generic;

/**
 * 用法一：在实现接口时传入指定的数据类型
 */
public class IGenericImpl implements IGeneric<String>{
    @Override
    public String getValue(String name) {
        return name;
    }
}
