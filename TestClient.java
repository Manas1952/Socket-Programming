import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TestClient {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket("localhost", 5000);

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        while (true) {
            System.out.print("Enter message: ");
            String message = scanner.nextLine();

            output.println(message);

            String response = input.readLine();
            System.out.println("Server response: " + response);
        }
    }
}