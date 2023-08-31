package com.kk.P2PCommunicate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Server发送消息的线程
 */
class Send extends Thread{
    private Socket socket;
    public Send(){}
    public Send(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        this.SendMsg();
    }

    private void SendMsg() {
        try(Scanner scanner = new Scanner(System.in);
            PrintWriter pw = new PrintWriter(this.socket.getOutputStream());
        ){
            while(true){
                String msg = scanner.nextLine();
                pw.println(msg);
                pw.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

/**
 * Server接收消息的线程
 */
class Receive extends Thread{
    private Socket socket;
    public Receive(){}
    public Receive(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        this.receiveMsg();
    }

    private void receiveMsg() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))){
            while(true){
                String msg = br.readLine();
                System.out.println("client send: " +msg);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
/**
 * 主线程
 */
public class P2PSocketServer {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(8888);){
            System.out.println("服务器启动，等待连接...");
            Socket clientSocket = serverSocket.accept(); // 获取与客户端对应的socket
            System.out.println("TCP连接成功...");
            // 创建接收消息的线程并调用发送消息的方法
            new Receive(clientSocket).start();
            // 创建发送消息的线程并调用接收消息的方法
            new Send(clientSocket).start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
