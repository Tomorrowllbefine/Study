package com.kk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 添加行号
 */
public class PrintWriterTest {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new FileReader("D:\\JAVA\\workSpace\\ioDemo\\aa.txt"));
            PrintWriter pw = new PrintWriter("D:\\JAVA\\workSpace\\ioDemo\\aaaaa.txt");){
            int index =1;
            String temp = "";
            while( ( temp = br.readLine())  != null ){
                pw.println((index++) +". " + temp);
            }
            pw.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
