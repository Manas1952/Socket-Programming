package MyGroupChat;

import java.io.*;
import java.net.Socket;

public class UserThread extends Thread {
    Socket socket;
    Server server;
    PrintWriter printWriter;
    UserThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            OutputStream outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream, true);

            String userName = bufferedReader.readLine();

            server.addUserName(userName);

            System.out.println("New User connected: " + userName);
            server.broadcast("New user connected: " + userName, this);

            String clientMessage = "";
            while (clientMessage != null && !clientMessage.equals("EXIT")) {
                clientMessage = bufferedReader.readLine();

                server.broadcast(clientMessage, this);
            }

            server.removeUser(userName, this);

            server.broadcast("[" + userName + "] has left", this);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void sendMessage(String message) {
        printWriter.println(message);
    }
}
