package OneServerMultipleClient_Datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
    public static void main(String[] args) {
        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(8080);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        String message;
        while (true) {

            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
            try {
                serverSocket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            message = new String(packet.getData(), 0, packet.getLength());

            System.out.println(message);

            message = "computed by server: " + message;

            try {
                DatagramPacket packetToSend = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getLocalHost(), 9090);
                serverSocket.send(packetToSend);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
