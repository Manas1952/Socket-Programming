import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;

public class TestClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(1234, InetAddress.getByName("localhost"));
        System.out.println(InetAddress.getLocalHost());
        System.out.println(datagramSocket.getLocalAddress());
        byte[] buffer = new byte[10];
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
        datagramSocket.receive(datagramPacket);
        String data = new String(datagramPacket.getData());
        System.out.println(data);
    }
}