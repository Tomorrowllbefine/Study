package com.kk.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Crawler {
    public static void main(String[] args) throws IOException {

        int a = 0;
        int b = 0;
        String path = Crawler.class.getClassLoader().getResource("com/kk/crawler/crawler.xml").getPath();
        Document document1 = Jsoup.parse(new File(path), "utf-8");
        Elements max = document1.getElementsByTag("max");
        Elements min = document1.getElementsByTag("min");
        a = Integer.parseInt(min.get(0).text());
        b = Integer.parseInt(max.get(0).text());

        for (int i = a; i <= b; i++) {
            try{
                Document document = Jsoup.parse(new URL("http://daily.zhihu.com/story/" + i), 3000);
                //System.out.println(document);

                Elements headerImgEle = document.getElementsByAttributeValue("alt", "头图");
                Elements titleEle = document.select(".DailyHeader-title");
                Elements authorEle = document.select(".author");
                Elements contentEle = document.select(".content");
                System.out.println("头图: " + headerImgEle.get(0).attr("src"));
                System.out.println("标题: " + titleEle.get(0).text());
                System.out.println("作者: " + authorEle.get(0).text());
                System.out.println("内容: " + contentEle.get(0).text());
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
