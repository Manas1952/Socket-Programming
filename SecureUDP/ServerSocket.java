package SecureUDP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerSocket {
    public static void main(String[] args) throws IOException {


        java.net.ServerSocket socket = new java.net.ServerSocket(9999);

        Socket s = socket.accept();

//         InputStream in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(new InputStreamReader(s.getInputStream()));
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());


        char buf[] = new char[1024];

        bf.read(buf);

        System.out.println(new String(buf , 0 , buf.length));



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

       dout.writeUTF(data);


    }

}
