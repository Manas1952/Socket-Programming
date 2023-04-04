import java.net.*;
import java.io.*;

public class TestServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server is running.");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            String message;
            while ((message = input.readLine()) != null) {
                System.out.println("Received message from client: " + message);
                output.println("Server received message: " + message);
            }
        }
    }
}