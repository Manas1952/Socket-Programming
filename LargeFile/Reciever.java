package LargeFile;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Reciever {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(1234);
        byte[] buffer = new byte[2048];

        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
        boolean transfer = true;
        String data;
        while (transfer) {
            datagramSocket.receive(datagramPacket);
            data = new String(datagramPacket.getData());
            if (data == null) {
                transfer = false;
            }
            System.out.println(data);

        }
    }
}
