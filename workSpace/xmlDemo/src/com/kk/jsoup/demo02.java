package com.kk.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Jsoup对象的使用
 */
public class demo02 {

    public static void main(String[] args) throws IOException {
        // 解析本地XML
        String path = demo02.class.getClassLoader().getResource("com/kk/xsd/student.xml").getPath();
        Document document = Jsoup.parse(new File(path), "utf-8");
        System.out.println(document);

        System.out.println("———————————————————————————————————————————————————————————————————");
        // 解析XML字符串
        Document document1 = Jsoup.parse("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<students\n" +
                "    xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "    xsi:schemaLocation = \"http://www.itbaizhan.cn/xml  student.xsd\"\n" +
                "    xmlns = \"http://www.itbaizhan.cn/xml\">\n" +
                "\n" +
                "    <student number=\"kk_01\">\n" +
                "        <name>kkkkkkk</name>\n" +
                "        <age>18</age>\n" +
                "        <sex>male</sex>\n" +
                "    </student>\n" +
                "\n" +
                "    <student number=\"kk_02\">\n" +
                "        <name>kk</name>\n" +
                "        <age>18</age>\n" +
                "        <sex>male</sex>\n" +
                "    </student>\n" +
                "\n" +
                "</students>", "utf-8");
        System.out.println(document1);

        System.out.println("———————————————————————————————————————————————————————————————————");

        // 解析URL中的网页源文件
        Document document2 = Jsoup.parse(new URL("https://www.baidu.com"), 2000);
        System.out.println(document2);

    }
}
