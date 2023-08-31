package com.kk.One2MoreCommunicate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 处理消息的线程
 */
class Msg extends Thread{
    private Socket socket;

    public Msg(){}
    public Msg(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        this.msg();
    }

    private void msg() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
        ){
            while(true){
                String str = br.readLine();
                pw.println("reply from server: " + str + " [200:ok]"); // 应答
                pw.flush();
            }
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("links failed...");
        }
    }
}


/**
 * 主线程
 * 实现一对多的应答服务器
 */
public class EchoServer {
    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(8888);){
            System.out.println("Server has been established on port 8888...");
            while(true){
                Socket socket = server.accept();
                System.out.println("TCP links successfully...");
                new Msg(socket).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
