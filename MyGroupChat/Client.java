package MyGroupChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("10.20.40.234", 1234);


        Thread readingThread = new Thread(() -> {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                while (true) {
                    System.out.println(bufferedReader.readLine());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        readingThread.start();

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);
        String message = "";
        while (!message.equals("EXIT")) {
            message = scanner.nextLine();
            printWriter.println(message);

        }
        System.exit(0);
        socket.close();
    }
}
