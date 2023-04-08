package LargeFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("/home/manas/dummy.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        DatagramSocket datagramSocket = new DatagramSocket();
        DatagramPacket datagramPacket;

        String string;
        while ((string = bufferedReader.readLine()) != null) {
            System.out.println(string);
            datagramPacket = new DatagramPacket(string.getBytes(), string.length(), InetAddress.getByName("10.20.40.234"), 1234);
            datagramSocket.send(datagramPacket);
        }
        datagramSocket.close();
        fileReader.close();
        bufferedReader.close();
    }
}
