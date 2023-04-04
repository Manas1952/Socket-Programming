package Question1_Datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatagramSocket socket = null;
        DatagramPacket packet;
        String message;
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            System.out.println("-> " + e);
        }
//        System.out.println("App_2 is up on PORT: " + 3002);

//                InetAddress ip = InetAddress.getByName("localhost");

        Thread recieverThread = new Thread(() -> {
            DatagramSocket socket1 = null;
            try {
                socket1 = new DatagramSocket(1234);
            } catch (SocketException e) {
                System.out.println("-> " + e);
            }

            String message1;
            while (true) {
                try {
                    DatagramPacket packet1 = new DatagramPacket(new byte[1024], 1024);
                    System.out.println("recieving...");
                    socket1.receive(packet1);
                    System.out.println("recieved");

                    message1 = new String(packet1.getData(), 0, packet1.getLength());
                    if (message1.equals("exit")) {
                        System.exit(0);
                    }

                    System.out.println(message1);
                } catch (IOException e) {
                    System.out.println("-> " + e);
                }
            }
        });

        recieverThread.start();

        try {
            while (true) {


                message = scanner.nextLine();


                packet = new DatagramPacket(message.getBytes(), message.length(), InetAddress
                        .getLocalHost(), 3001);

                socket.send(packet);

                if (message.equals("exit")) {
                    System.exit(0);
                }
//                byte buffer[] = new byte[1024];
//                packet = new DatagramPacket(buffer, 1024);
//                socket.receive(packet);
//
//                message = new String(packet.getData(), 0, packet.getLength());
//
//                System.out.println("App_1 : " + message);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            socket.close();
        }
    }
}
