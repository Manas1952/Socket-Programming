import java.net.*;
import java.io.*;

public class Program1_Server extends Thread {
    public static void main(String[] args) {

        ServerSocket serverSocket;
        int port = Integer.parseInt(args[0]);

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is up on PORT: " + port + "\n" + "-> " + serverSocket.getLocalSocketAddress());

            Socket server = serverSocket.accept();

            DataInputStream in = new DataInputStream(server.getInputStream());
            System.out.println(in.readUTF());

            DataOutputStream out = new DataOutputStream(server.getOutputStream());

            out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!");

            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}