package com.kk.TCPBothWayCommunicate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BothWaySocketClient {
    public static void main(String[] args) {

        try(Socket socket = new Socket("127.0.0.1",8888);
            Scanner scanner = new Scanner(System.in);
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
            while(true){
                // 向服务端发送消息
                String clientOutput = scanner.nextLine();
                pw.println(clientOutput);
                pw.flush();
                if( "quit".equals(clientOutput)){
                    System.out.println("连接中断...");
                    break;
                }

                // 获取服务端的输入信息
                String serverInput = br.readLine();
                System.out.println("服务端传来: " + serverInput);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
