package OneServerMultipleClient_Datagram;

import java.io.IOException;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String message = "two";
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(9090);
            DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getLocalHost(), 8080);
            socket.send(packet);

            DatagramPacket packetToRecieve = new DatagramPacket(new byte[1024], 1024);
            socket.receive(packetToRecieve);

            String recivedMessage = new String(packetToRecieve.getData(), 0, packetToRecieve.getLength());

            System.out.println(recivedMessage);

        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
