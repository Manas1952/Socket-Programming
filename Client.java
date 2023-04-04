import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
   public static void main(String[] args) {
      String hostName = args[0];
      int port = Integer.parseInt(args[1]);
      Scanner typeIN = new Scanner(System.in);
      Socket client;

      try {
         client = new Socket(hostName, port);

         DataInputStream in;
         DataOutputStream out;
         String message;

         while (true) {

            out = new DataOutputStream(client.getOutputStream());
            System.out.print("Client : ");
            message = typeIN.nextLine();

            out.writeUTF(message);
            if (message.equalsIgnoreCase("EXIT"))
               break;

            in = new DataInputStream(client.getInputStream());
            message = in.readUTF().toString();

            if (message.equalsIgnoreCase("EXIT"))
               break;

            System.out.println("Server : " + message);
         }

         client.close();
         typeIN.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

   }
}