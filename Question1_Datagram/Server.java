package Question1_Datagram;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(3001);
        } catch (SocketException e) {
            System.out.println("-> " + e);
        }

        System.out.println("App-1 is up on the PORT: " + 3001);

        Thread thread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            DatagramSocket socket1 = null;
            try {
                socket1 = new DatagramSocket();
            } catch (SocketException e) {
                e.printStackTrace();
            }

            DatagramPacket packet;

            String message;

            try {
                while (true) {
                    message = scanner.nextLine();


                    packet = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getLocalHost(), 1234);

                    System.out.println("sending...");
                    socket1.send(packet);
                    System.out.println("sent");

                    if (message.equals("exit")) {
                        System.exit(0);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                socket1.close();
            }
        });

        thread.start();

        try {
            while (true) {
                DatagramPacket packet;
                String message;
//                InetAddress ip = InetAddress.getByName("localhost");

                byte buffer[] = new byte[1024];
                packet = new DatagramPacket(buffer, 1024);
                socket.receive(packet);

                message = new String(packet.getData(), 0, packet.getLength());

                if (message.equals("exit")) {
                    System.exit(0);
                }

                System.out.println("App_2 : " + message);

//                message = "Hey, App_2. This is App_1.";
//
//                packet = new DatagramPacket(message.getBytes(), message.length(), ip, 3002);
//
//                socket.send(packet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}

