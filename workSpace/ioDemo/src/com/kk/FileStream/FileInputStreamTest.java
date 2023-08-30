package com.kk.FileStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 文件字节输入流
 */
public class FileInputStreamTest {

    public static void main(String[] args) {

        try(FileInputStream fis = new FileInputStream("D:\\JAVA\\workSpace\\ioDemo\\a.txt")){
            StringBuilder sb = new StringBuilder();
            int temp = 0;
            while( (temp = fis.read()) != -1 ){
                sb.append((char)temp);
            }
            System.out.println(sb);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
