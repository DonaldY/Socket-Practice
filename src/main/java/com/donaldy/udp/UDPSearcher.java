package com.donaldy.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP 搜索者
 *
 * 监听 - 发送
 */
public class UDPSearcher {

    public static void main(String[] args) throws IOException {
        System.out.println("UDPSearcher Started.");

        // 作为搜索方，让系统自动分配端口
        DatagramSocket datagramSocket = new DatagramSocket();

        // 构建一份回送数据
        String responseData = "HelloWorld!";
        byte[] responseDataBytes = responseData.getBytes();

        // 直接根据发送者构建一份回送信息
        DatagramPacket requestPacket = new DatagramPacket(responseDataBytes,
                responseDataBytes.length);
        requestPacket.setAddress(InetAddress.getLocalHost());
        requestPacket.setPort(2000);

        datagramSocket.send(requestPacket);

        // 构建接收实体
        final byte[] buf = new byte[512];
        DatagramPacket receivePack = new DatagramPacket(buf, buf.length);

        // 接收
        datagramSocket.receive(receivePack);

        // 打印接收到的信息与发送者的信息
        // 发送者的IP地址
        String ip = receivePack.getAddress().getHostAddress();
        int port = receivePack.getPort();
        int dataLen = receivePack.getLength();
        String data = new String(receivePack.getData(), 0, dataLen);

        System.out.println("UDPSearcher receive from ip: " + ip + "\tport: " + port + "\tdata: " + data);


        // 完成
        System.out.println("UDPSearcher Finished.");
        datagramSocket.close();
    }
}
