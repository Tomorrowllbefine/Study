package com.kk;

import java.io.*;

/**
 * 案例3：通过转换流实现键盘输入屏幕输出
 */
public class ConvertStreamTest3 {
    public static void main(String[] args) {
        // 实现 字节流->字符流->缓冲字符流 的转换
        try(BufferedReader br = new BufferedReader( new InputStreamReader( System.in));
            BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( System.out));){
            String inputStr = "";
            while( !( inputStr = br.readLine()).equals("quit")){
                bw.write(inputStr);
                bw.newLine();
                bw.flush();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
