package com.kk.ObjectStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 实现反序列化
 */
public class ObjectStreamTest3 {
    public static void main(String[] args) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\JAVA\\workSpace\\ioDemo\\aaaaa.txt"))){

            User user = (User) ois.readObject();
            System.out.println(user);
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
