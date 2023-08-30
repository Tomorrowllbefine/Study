package com.kk.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * 获取XML中所有学生的姓名
 */
public class demo01 {
    public static void main(String[] args) throws IOException {

        // 加载XML文档进入内存，获取DOM对象Document
            // 获取类加载器
        ClassLoader classLoader = demo01.class.getClassLoader();
            // 使用类加载器，找到XML文档的路径
        String path = classLoader.getResource("com/kk/xsd/student.xml").getPath();
            // 加载XML文档进入内存，并转成Document对象
        Document document = Jsoup.parse(new File(path), "utf-8");

        // 获取对应的标签Element对象
        Elements name = document.getElementsByTag("name");

        // 获取数据
        for (Element e:  name) {
            String text = e.text();
            System.out.println(text);
        }
    }
}
