package com.kk.TCPOnewayCommunicate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class OneWaySocketClient {
    public static void main(String[] args) {
        // 创建客户端
        try(Socket serverSocket = new Socket("127.0.0.1",8888);
            Scanner scanner = new Scanner(System.in);
            // 基于服务端的Socket创建对应的输出流
            PrintWriter pw = new PrintWriter(serverSocket.getOutputStream());
            // 基于服务端的Socket创建对应的输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        ){
            while(true){
                String str = scanner.nextLine();
                // 客户端发送消息给服务端
                pw.println(str);
                pw.flush();
                // 判断是否退出
                if("quit".equals(str)) break;

                // 获取服务端返回的响应
                String serverInput = br.readLine();
                System.out.println("服务端返回: " + serverInput);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
