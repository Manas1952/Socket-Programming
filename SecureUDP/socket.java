package SecureUDP;

import java.io.*;
import java.net.*;

public class socket
{


    public static void main(String[] args) throws IOException {


        DatagramSocket socket = new DatagramSocket(9999);
        byte buf[] = new byte[9999];
        DatagramPacket  dp  = new DatagramPacket(buf , 9999);


       // InputStream in = new InputStreamReader(s.getInputStream());
        //BufferedReader bf = new BufferedReader(new InputStreamReader(s.getInputStream()));
        //DataOutputStream dout = new DataOutputStream(s.getOutputStream());


        socket.receive(dp);
        System.out.println(new String(dp.getData() , 0 , dp.getLength()));

        String data = "HTTP/1.1 200 OK\n" +
                "Date: Thu, 07 Apr 2023 00:00:00 GMT\n" +
                "Server: Apache/2.4.41 (Unix)\n" +
                "Last-Modified: Wed, 06 Apr 2023 12:00:00 GMT\n" +
                "ETag: \"abcdefg123456789\"\n" +
                "Accept-Ranges: bytes\n" +
                "Content-Length: 1234\n" +
                "Content-Type: text/html\n" +
                "\n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <title>Welcome to Example.com</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <h1>Hello, world!</h1>\n" +
                "  <p>Welcome to Example.com</p>\n" +
                "</body>\n" +
                "</html>";

        DatagramPacket dps = new DatagramPacket(buf , buf.length , InetAddress.getByName("localhost") , 9999);

        socket.send(dps);




        }

}
