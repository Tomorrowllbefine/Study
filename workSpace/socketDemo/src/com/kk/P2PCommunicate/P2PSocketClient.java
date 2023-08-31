package com.kk.P2PCommunicate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client发送消息的线程
 */
class ClientSend extends Thread{
    private Socket socket;

    public ClientSend() {
    }

    public ClientSend(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        this.sendMsg();
    }

    private void sendMsg() {
        try(Scanner scanner = new Scanner(System.in);
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
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
 * Client接收消息的线程
 */
class ClientReceive extends Thread{
    private Socket socket;
    public ClientReceive() {
    }

    public ClientReceive(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        this.receiveMsg();
    }

    private void receiveMsg() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            while(true){
                String msg = br.readLine();
                System.out.println("Server Send: " + msg);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

public class P2PSocketClient {

    /**
     * 主线程
     * @param args
     */
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("127.0.0.1",8888);
            System.out.println("连接成功...");
            // 实例化发送消息的线程
            new Send(socket).start();
            // 实例化接收消息的现车个
            new Receive(socket).start();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
