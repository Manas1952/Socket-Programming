package Question1_Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server {

    public static void main(String[] args) {

        final int port = Integer.parseInt(args[0]);

        Thread.currentThread().setName("ServerReceiver");

        AtomicBoolean alive = new AtomicBoolean(true);

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            Socket socket = serverSocket.accept();

            System.out.println("Connection Established!!!");

            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Thread sender = new Thread(() -> {

                try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
                     PrintWriter writer = new PrintWriter(socket.getOutputStream())) {

                    while (alive.get()) {
                        System.out.print("Server: ");

                        writer.println(consoleReader.readLine());

                        writer.flush();
                    }

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }, "ServerSender");

            sender.start();

            String message;

            while (alive.get()) {

                message = serverReader.readLine();

                if (message != null && !message.equals("exit")) {

                    System.out.println("-> " + message.equals("exit"));
                    System.out.println("Client: " + message);

                } else {

                    alive.set(false);
                }

            }

            serverReader.close();

            System.exit(0); //Don't know correct to use it or not

        } catch (IOException exception) {

            exception.printStackTrace();

        }

    }


}
