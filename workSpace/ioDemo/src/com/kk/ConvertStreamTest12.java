package com.kk;

import java.io.*;

/**
 * 转换流的使用2
 */
public class ConvertStreamTest12 {
    public static void main(String[] args) {
        // 依次创建转换流(字节输入流)、字符缓冲流、字符输出缓冲流
        try(InputStreamReader isr = new InputStreamReader(new FileInputStream("D:\\JAVA\\workSpace\\ioDemo\\aa.txt"),"utf-8");
            BufferedReader br = new BufferedReader(isr);
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\JAVA\\workSpace\\ioDemo\\aaaa.txt"));){
            //
            StringBuilder sb = new StringBuilder();
            String temp = null;
            int index = 1;
            while( ( temp = br.readLine()) != null){
                bw.write(index + ". "+temp);
                bw.newLine();
                index++;
            }
            bw.flush();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
