package LargeFileTCP;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Reciever {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket(InetAddress.getByName("localhost"), 1234);
        byte[] contents = new byte[10000];

        FileOutputStream fos = new FileOutputStream("/home/manas/dummy_output.html");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        InputStream is = socket.getInputStream();

        int bytesRead = 0;
        while((bytesRead=is.read(contents))!=-1) {
            bos.write(contents, 0, bytesRead);
            System.out.println(contents[0]);
        }
        bos.flush();
        socket.close();
        System.out.println("File saved successfully!");
    }
}