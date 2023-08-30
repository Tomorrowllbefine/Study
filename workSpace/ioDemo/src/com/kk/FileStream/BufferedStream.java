package com.kk.FileStream;

import java.io.*;

/**
 * 字节缓冲流的基本使用
 */
public class BufferedStream {
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        copyFile3("D:\\JAVA\\workSpace\\ioDemo\\1.png","D:\\JAVA\\workSpace\\ioDemo\\4.png");
        long t2 = System.currentTimeMillis();
        System.out.println("使用缓冲字节流耗时: " + (t2-t1));
    }

    /**
     * 使用字节缓冲流进行文件复制
     * @param sour 源文件
     * @param dest 目的文件
     */
    public static void copyFile3(String sour, String dest){

        try(FileInputStream fis = new FileInputStream(sour);
            FileOutputStream fos = new FileOutputStream(dest);
            // 创建缓冲字节流(处理流)
            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedOutputStream bos = new BufferedOutputStream(fos);){

            int temp = 0;
            while( ( temp = bis.read()) != -1){
                bos.write(temp);
            }
            bos.flush();
        }catch (IOException e  ){
            e.printStackTrace();
        }
    }
}
