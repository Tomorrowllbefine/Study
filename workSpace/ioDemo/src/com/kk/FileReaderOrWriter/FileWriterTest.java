package com.kk.FileReaderOrWriter;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 文件字符输出流
 */
public class FileWriterTest {
    public static void main(String[] args) {

        try(FileWriter fw = new FileWriter("D:\\JAVA\\workSpace\\ioDemo\\aa.txt",true)){
            String str = "当执行IO操作处理的文件类型是文本文件时" +
                    "，可以使用文件字符流进行读写，会以字符为单位进行处理；";
            fw.write(str);
            fw.flush();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
}
