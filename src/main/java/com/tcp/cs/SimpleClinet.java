package com.tcp.cs;

import java.io.OutputStream;
import java.net.Socket;

public class SimpleClinet {

	public static void ConnectRead(String ip, Integer port) {
		try {
			// 创建Socket实例
			Socket socket = new Socket(ip, port);
			// 获取输出流
			OutputStream outputStream = socket.getOutputStream();
			outputStream.write("hello,world".getBytes());
			// 释放资源
			outputStream.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ConnectRead("localhost", 8888);

	}

}
