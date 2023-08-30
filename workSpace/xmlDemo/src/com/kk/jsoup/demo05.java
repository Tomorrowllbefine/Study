package com.kk.jsoup;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Xpath解析器的使用
 */
public class demo05 {

    public static void main(String[] args) throws IOException, XpathSyntaxErrorException {
        String path = demo05.class.getClassLoader().getResource("com/kk/jsoup/student.xml").getPath();
        // 获取document对象
        Document document = Jsoup.parse(new File(path), "utf-8");
        // 将Document对象转化为JXDocument对象
        JXDocument jxDocument = new JXDocument(document);
        // 调用selN方法获取 List<JXNode>对象
        List<JXNode> jxNodes = jxDocument.selN("//name");
        // 遍历List<JXNode>，调用JXNode的getElement() ，返回Element对象
        for( JXNode jxNode : jxNodes){
            Element element = jxNode.getElement();
            // 处理Element对象（假设输出）
            System.out.println(element);
        }
    }
}
