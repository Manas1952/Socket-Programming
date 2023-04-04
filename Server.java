import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
   public static void main(String[] args) {
      ServerSocket serverSocket;
      Scanner typeIN = new Scanner(System.in);
      int port = Integer.parseInt(args[0]);
      try {
         serverSocket = new ServerSocket(port);
         System.out.println("Server is up on PORT: " + port + "\n");

         Socket server = serverSocket.accept();

         DataInputStream in;
         DataOutputStream out;
         String message;

         while (true) {
            in = new DataInputStream(server.getInputStream());
            message = in.readUTF().toString();

            if (message.equalsIgnoreCase("EXIT"))
               break;

            System.out.print("Client : " + message + "\nServer : ");
            out = new DataOutputStream(server.getOutputStream());
            message = typeIN.nextLine();
            out.writeUTF(message);
            if (message.equalsIgnoreCase("EXIT"))
               break;
         }

         server.close();
         typeIN.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}