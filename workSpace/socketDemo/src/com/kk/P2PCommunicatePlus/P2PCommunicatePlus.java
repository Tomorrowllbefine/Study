package com.kk.P2PCommunicatePlus;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 发送消息线程
 */
class Send extends Thread{
    private Socket socket;
    private Scanner scanner;

    public Send() {
    }

    public Send(Socket socket, Scanner scanner) {
        this.socket = socket;
        this.scanner = scanner;
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
 * 接收消息的线程
 */
class Receive extends Thread{
    private Socket socket;
    private Scanner scanner;
    public Receive() {
    }

    public Receive(Socket socket, Scanner scanner) {
        this.socket = socket;
        this.scanner = scanner;
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

/**
 * 主线程
 * 实现点对点聊天（复合代码）
 */
public class P2PCommunicatePlus {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("input server,port / ip,port: ");
            String input = scanner.nextLine();
            String[] items = input.split(",");

            // Server
            if("server".equals(items[0])) {
                serverSocket = new ServerSocket(Integer.parseInt(items[1]));
                System.out.println("Server has been established on port " + items[1] + "....");
                socket = serverSocket.accept();
                System.out.println("TCP links successfully...");
                // 创建接受消息线程
                new Receive(socket,scanner).start();
                // 创建发送消息线程
                new Send(socket,scanner).start();

            }else {
                // Client
                socket = new Socket(items[0], Integer.parseInt(items[1]));
                System.out.println("TCP links successfully...");
                // 创建发送消息线程
                new Send(socket,scanner).start();
                // 创建接收消息线程
                new Receive(socket,scanner).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
