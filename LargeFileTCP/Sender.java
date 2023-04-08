package LargeFileTCP;

import java.awt.image.DataBufferUShort;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Sender {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1234);

        Socket socket = serverSocket.accept();
        File file = new File("/home/manas/dummy.html");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));

        OutputStream outputStream = socket.getOutputStream();

        byte[] buffer;
        long fileLenght = file.length();
        System.out.println("file length: " + fileLenght);
        long current = 0;
        int size = 10000;

            System.out.println("Sending...");
        while (current != fileLenght) {
            if (fileLenght - current >= size) {
                current += size;
            }
            else {
                size = (int) (fileLenght - current);
                current = fileLenght;
            }
            buffer = new byte[size];

            bufferedInputStream.read(buffer, 0, size);
            outputStream.write(buffer);
            System.out.println(buffer);
        }

        outputStream.flush();
    }
}
