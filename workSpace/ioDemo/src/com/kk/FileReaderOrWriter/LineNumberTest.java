package com.kk.FileReaderOrWriter;

import java.io.*;

/**
 * 为文件的内容添加行号
 */
public class LineNumberTest {
    public static void main(String[] args) {

        try(BufferedReader br = new BufferedReader(new FileReader("D:\\JAVA\\workSpace\\ioDemo\\aa.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\JAVA\\workSpace\\ioDemo\\aaa.txt"))){

            int index = 1; // 定义序号
            String temp = "";
            while( (temp = br.readLine()) != null){
                bw.write(index +". " +  temp);
                index++;
                bw.newLine(); // 换行
            }
            bw.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
