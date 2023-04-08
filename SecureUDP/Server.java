package SecureUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;

public class Server {
    public static void main(String args[]) throws Exception{

        Signature sign = Signature.getInstance("SHA256withRSA");

        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

        keyPairGen.initialize(2048);

        KeyPair pair = keyPairGen.generateKeyPair();

        System.out.println("->" + pair.getPublic().toString() + "<-");

        // sending PU key
        DatagramSocket datagramSocket = new DatagramSocket();

        DatagramPacket datagramPacket1 = new DatagramPacket(pair.getPublic().getEncoded(), pair.getPublic().getEncoded().length, InetAddress.getByName("10.20.40.234") , 9999);

        datagramSocket.send(datagramPacket1);

        //receiving PU key

        DatagramSocket datagramSocket1 = new DatagramSocket(1234);
        byte[] buffer = new byte[294];

        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);

        datagramSocket1.receive(datagramPacket);

        // converting to PublicKey class
        byte[] publicKeyBytesReceived = datagramPacket.getData();

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytesReceived);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKeyReceived = keyFactory.generatePublic(keySpec);

        System.out.println("->"+publicKeyReceived.toString()+ "<-");

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        // receiving data
        byte[] buffer1 = new byte[256];
        DatagramPacket datagramPacket2 = new DatagramPacket(buffer1, buffer1.length);
        datagramSocket1.receive(datagramPacket2);

        //Decrypting the text
        cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());

        byte[] decipheredText = cipher.doFinal(buffer1);

        System.out.println(new String(decipheredText  , Charset.defaultCharset()));
        System.out.println(Charset.defaultCharset());
    }

}
