package com.kk.FileReaderOrWriter;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.FileReader;
import java.io.IOException;

/**
 * 文件字符输入流
 */
public class FileReaderTest {
    public static void main(String[] args) {

        try(FileReader fr = new FileReader("D:\\JAVA\\workSpace\\ioDemo\\aa.txt")){
            int temp = 0;
            StringBuilder sb = new StringBuilder();
            while( ( temp = fr.read()) != -1){
                sb.append((char)temp);
            }
            System.out.println(sb);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
