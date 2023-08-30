package com.kk.FileReaderOrWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 字符输入缓冲流
 */
public class BufferedReaderTest {
    public static void main(String[] args) {
        // 简化创建
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\JAVA\\workSpace\\ioDemo\\aa.txt"))){
            String temp = "";
            // readLine() 一次读取一行
            while( (temp = br.readLine()) != null){
                System.out.println(temp);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
