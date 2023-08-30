package com.kk;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * try-with-resource写法
 */
public class Test02 {
    public static void main(String[] args) {

        try (FileInputStream fis = new FileInputStream("D:\\JAVA\\workSpace\\ioDemo\\a.txt")) {
            StringBuilder sb = new StringBuilder();
            int temp = 0;
            while ((temp = fis.read()) != -1) {
                sb.append((char) temp);
            }
            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
