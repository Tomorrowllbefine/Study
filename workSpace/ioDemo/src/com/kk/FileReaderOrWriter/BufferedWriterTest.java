package com.kk.FileReaderOrWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 字符输出缓冲流
 */
public class BufferedWriterTest {
    public static void main(String[] args) {

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\JAVA\\workSpace\\ioDemo\\aa.txt"))){
            bw.write("白日依山尽");
            bw.newLine(); // 换行
            bw.write("黄河入海流");
            bw.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
