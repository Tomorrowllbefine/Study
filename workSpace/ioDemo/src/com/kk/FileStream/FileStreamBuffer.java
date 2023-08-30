package com.kk.FileStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 采用字节缓冲区进行文件复制
 */
public class FileStreamBuffer {
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        copyFile1("D:\\JAVA\\workSpace\\ioDemo\\1.png","D:\\JAVA\\workSpace\\ioDemo\\2.png");
        long t2 = System.currentTimeMillis();
        System.out.println("未引入缓冲区耗时: " + (t2 - t1));

        long t3 = System.currentTimeMillis();
        copyFile2("D:\\JAVA\\workSpace\\ioDemo\\1.png","D:\\JAVA\\workSpace\\ioDemo\\3.png");
        long t4 = System.currentTimeMillis();
        System.out.println("引入缓冲区后耗时: " + (t4 - t3));


    }

    /**
     * 实现文件的拷贝
     * @param sour 源文件
     * @param dest 目的文件
     */
    public static void copyFile1(String sour, String dest){

        try(FileInputStream fis = new FileInputStream(sour);
            FileOutputStream fos = new FileOutputStream(dest);){
            int temp = 0;
            while( ( temp = fis.read()) != -1){
                fos.write(temp);
            }
            // 将文件从内存写入磁盘
            fos.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 实现文件的拷贝
     * @param sour 源文件
     * @param dest 目的文件
     */
    public static void copyFile2(String sour, String dest){

        try(FileInputStream fis = new FileInputStream(sour);
            FileOutputStream fos = new FileOutputStream(dest);){

            byte[] buffer  = new byte[1024]; // 缓冲数组
            int temp = 0;
            while( ( temp = fis.read(buffer)) != -1){
                // 每次都从buffer中取本次相对位置0开始temp字节长度的内容写入内存
                fos.write(buffer,0,temp);
            }
            // 将文件从内存写入磁盘
            fos.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}


