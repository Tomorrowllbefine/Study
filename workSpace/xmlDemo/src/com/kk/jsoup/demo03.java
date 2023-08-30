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
public class demo03 {
    public static void main(String[] args) throws IOException {

        // 获取XML文件本地路径
        String path = demo03.class.getClassLoader().getResource("com/kk/jsoup/student.xml").getPath();
        Document document = Jsoup.parse(new File(path), "utf-8");

        // 根据id获取元素
        Element kk01 = document.getElementById("kk_01");
        System.out.println(kk01);

        System.out.println("——————————————————————————");

        // 根据标签名获取元素
        Elements age = document.getElementsByTag("age");
        System.out.println(age);

        System.out.println("——————————————————————————");

        // 根据属性获取元素
        Elements english = document.getElementsByAttribute("english");
        Elements id = document.getElementsByAttribute("id");
        System.out.println(english);
        System.out.println("——————————————————————————");
        System.out.println(id);
        System.out.println("——————————————————————————");

        // 根据属性名 = 属性值 获取元素
        Elements elementsByAttributeValue = document.getElementsByAttributeValue("id", "kk_02");
        System.out.println(elementsByAttributeValue);
        System.out.println("——————————————————————————");

        // 根据css选择器获取元素
        Elements select = document.select("#kk_02");
        System.out.println(select);
        System.out.println("——————————————————————————");

    }
}
