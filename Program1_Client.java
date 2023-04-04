import java.net.*;
import java.io.*;

public class Program1_Client {
    public static void main(String[] args) {
        String hostName = args[0];
        int port = Integer.parseInt(args[1]);
        try {
            Socket client = new Socket(hostName, port);

            OutputStream outToServer = client.getOutputStream();

            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF("Hello from " + client.getLocalSocketAddress());

            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            System.out.println("Server says " + in.readUTF());

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}