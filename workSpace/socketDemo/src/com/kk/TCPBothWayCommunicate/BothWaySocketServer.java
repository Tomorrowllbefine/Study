package com.kk.TCPBothWayCommunicate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class BothWaySocketServer {
    public static void main(String[] args) {

        System.out.println("服务器启动，等待监听...");

        try(ServerSocket serverSocket = new ServerSocket(8888);
            Socket clinetSocket = serverSocket.accept();
            Scanner scanner = new Scanner(System.in);
            BufferedReader br = new BufferedReader(new InputStreamReader(clinetSocket.getInputStream()));
            PrintWriter pw = new PrintWriter(clinetSocket.getOutputStream());
        ){
            System.out.println("TCP links successfully...");
            while(true){
                // 接收客户端传来的消息
                String clientInput = br.readLine();
                if("quit".equals(clientInput)) {
                    System.out.println("连接中断...");
                    break;
                }
                System.out.println("客户端传来: " + clientInput);

                // 传递给客户端消息
                String serverOutput = scanner.nextLine();
                pw.println(serverOutput);
                pw.flush();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
