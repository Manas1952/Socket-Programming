package MyGroupChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server {
    int port;
    Set<String> userNames = new HashSet<>();
    Set<UserThread> userThreads = new HashSet<>();

    Server (int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        Server server = new Server(1234);
        server.start();
    }

    void start() {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {

            while (true) {
                Socket socket = serverSocket.accept();

                UserThread newUser = new UserThread(socket, this);
                userThreads.add(newUser);

                newUser.start();
            }

        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    void broadcast(String clientMessage, UserThread sendingUserThread) {
        for (UserThread userThread: userThreads) {
            if (userThread != sendingUserThread) {
                userThread.sendMessage(clientMessage);
            }
        }
    }

    void addUserName(String userName) {
        userNames.add(userName);
    }

    void removeUser(String removedUserName, UserThread userToRemove) {
        userThreads.remove(userToRemove);
        userNames.remove(removedUserName);
        System.out.println("[" + removedUserName + "] quits.");
    }
}
