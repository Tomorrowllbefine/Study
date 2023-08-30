package com.kk.ObjectStream;

import java.io.*;

/**
 * 基本使用
 */
public class ObjectStreamTest1 {
    public static void main(String[] args) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\JAVA\\workSpace\\ioDemo\\aaaaa.txt",false));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\JAVA\\workSpace\\ioDemo\\aaaaa.txt"));){

            // 写入文件
            oos.writeInt(100);
            oos.writeChar('a');
            oos.writeBoolean(false);
            oos.writeUTF("卡卡的");
            oos.flush(); // 刷新

            // 读出文件内容
            System.out.println("int: "+ois.readInt());
            System.out.println("char: "+ois.readChar());
            System.out.println("boolean: "+ois.readBoolean());
            System.out.println("String: "+ois.readUTF());

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
