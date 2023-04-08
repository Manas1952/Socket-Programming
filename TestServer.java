import java.io.IOException;
import java.net.*;

public class TestServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(6666);

        byte[] buffer = new byte[20];
        String payload = "data data data data ";
        DatagramPacket datagramPacket = new DatagramPacket(payload.getBytes(), payload.length(), InetAddress.getByName("10.20.40.234"), 1234);
        datagramSocket.send(datagramPacket);
    }
}
