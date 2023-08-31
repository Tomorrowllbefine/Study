package com.kk.TCPCommunicateDemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class BasicClientSocket {
    public static void main(String[] args) {
        // 创建客户端
        try(Socket socket = new Socket("127.0.0.1",8888);
            // 创建向服务端发送消息的输出流对象
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
        ){
            pw.println("Hello Server！");
            pw.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
