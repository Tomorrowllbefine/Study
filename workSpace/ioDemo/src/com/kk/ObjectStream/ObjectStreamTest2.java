package com.kk.ObjectStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 实现序列化
 */
public class ObjectStreamTest2 {
    public static void main(String[] args) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\JAVA\\workSpace\\ioDemo\\aaaaa.txt"))){

            User user = new User(1,"kk",18);
            oos.writeObject(user);
            oos.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
