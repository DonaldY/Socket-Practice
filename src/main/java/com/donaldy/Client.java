package com.donaldy;

import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();

        // 超时时间
        socket.setSoTimeout(3000);

        // 连接本地， 端口2000， 超时时间3000ms
        socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 2000), 3000);

        System.out.println("已发起服务器链接，");
        System.out.println("客户端信息： " + socket.getLocalAddress() + "P: " + socket.getLocalPort());
        System.out.println("服务器信息： " + socket.getLocalAddress() + "P: " + socket.getPort());

        try {
            // 发送接收数据
            todo(socket);
        } catch (Exception e) {
            System.out.println("异常关闭");
        }

        socket.close();
        System.out.println("客户端已退出。。。");
    }

    private static void todo(Socket client) throws IOException {
        // 键盘输入
        InputStream inputStream = System.in;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        // 得到Socket输出流，并转换为打印流
        OutputStream outputStream = client.getOutputStream();
        PrintStream socketPrintStream = new PrintStream(outputStream);

        // 得到Socket输入流
        InputStream inputStream1 = client.getInputStream();
        BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(inputStream1));

        boolean flag = true;
        do {
            // 键盘读取一行
            String str = bufferedReader.readLine();
            // 发送到服务器
            socketPrintStream.println(str);

            // 从服务器读取一行
            String echo = socketBufferedReader.readLine();
            if ("bye".equalsIgnoreCase(echo)) {
                flag = false;
            }

            System.out.println(echo);
        } while (flag);
    }
}
