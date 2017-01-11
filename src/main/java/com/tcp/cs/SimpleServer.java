package com.tcp.cs;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class SimpleServer {

	public static void StartServer(String ip, Integer port) {
		InputStream inputStream;
		Socket socket;
		StringBuilder s = new StringBuilder();
		try {
			// 服务器端接收消息的类。定制端口号为8888
			ServerSocket serviceSocket = new ServerSocket(port);
			
			// 获取socket。这个方法是阻塞式的
			socket = serviceSocket.accept();
			inputStream = socket.getInputStream();
//			read(socket.getLocalSocketAddress())
			byte buf[] = new byte[1024];
			int len = 0;
			while ((len = (inputStream.read(buf))) > 0) {
				s.append(new String(buf, 0, len));
			}
			// 打印客户端的消息
			System.out.println(s.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

	}

}
