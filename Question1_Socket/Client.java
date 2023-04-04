package Question1_Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client {

    public static void main(String[] args) {

        final String hostName = args[0];

        final int port = Integer.parseInt(args[1]);

        AtomicBoolean alive = new AtomicBoolean(true);

        try (Socket socket = new Socket(hostName, port);
             BufferedReader clientReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            Thread sender = new Thread(() -> {

                try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
                     PrintWriter writer = new PrintWriter(socket.getOutputStream())) {

                    while (alive.get()) {
                        System.out.print("Client: ");

                        writer.println(consoleReader.readLine());

                        writer.flush();
                    }

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }, "ClientSender");

            sender.start();

            String message;

            while (alive.get()) {

                message = clientReader.readLine();

                if (message != null && !message.equals("exit")) {
//                System.out.println("-> " + message.equals("exit"));

                    System.out.println("Server: " + message);

                } else {

                    alive.set(false);
                }
            }
            
            System.exit(0);

        } catch (IOException exception) {

            exception.printStackTrace();

        }

    }

}