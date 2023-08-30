package com.kk.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * Document对象的使用
 */
public class demo04 {
    public static void main(String[] args) throws IOException {

        // 获取XML文件本地路径
        String path = demo04.class.getClassLoader().getResource("com/kk/jsoup/student.xml").getPath();
        Document document = Jsoup.parse(new File(path), "utf-8");

        // 根据css选择器获取元素
        Elements select = document.select("#kk_02");
        Element element = select.get(0);

        System.out.println(element.text());
        System.out.println("——————————————————————————");
        System.out.println(element.html());
        System.out.println("——————————————————————————");
        System.out.println(element.attr("id"));
        // System.out.println("e" + element.attr("english"));  // 获取不到
    }
}
