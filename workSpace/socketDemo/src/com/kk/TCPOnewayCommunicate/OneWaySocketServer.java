package com.kk.TCPOnewayCommunicate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class OneWaySocketServer {
    public static void main(String[] args) {
        System.out.println("服务器开始启动，监听端口中...");
        try(ServerSocket serverSocket = new ServerSocket(8888);
            Socket clientSocket = serverSocket.accept();
            // 获取对客户端发送来消息的缓冲字符输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // 获取对客户端输出信息的字符输出流
            PrintWriter pw = new PrintWriter(clientSocket.getOutputStream());
        ){
            System.out.println("TCP连接成功...");

            while(true){
                String str = "";
                str = br.readLine();
                if ("quit".equals(str)) break;
                System.out.println("客户端传来: " + str);
                pw.println(str);
                pw.flush();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
