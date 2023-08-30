package com.kk.FileStream;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 字节输出流
 */
public class FileOutputStreamTest {
    public static void main(String[] args) {
        String str = "kkkkkkk 18";
        // true表示内容会追加到文件末尾（append)，false表示覆盖写
        try(FileOutputStream fos = new FileOutputStream("D:\\JAVA\\workSpace\\ioDemo\\a.txt",true)){
            // 将整个字节数组写入到文件中
            fos.write(str.getBytes());
            // 将数据从内存写入到磁盘中
            fos.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
