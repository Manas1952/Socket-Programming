package SecureUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class Reciever {
    public static void main(String args[]) throws Exception{

        //receiving server's public key
        DatagramSocket datagramSocket1 = new DatagramSocket(9999);
        byte[] buffer = new byte[294];
        DatagramPacket datagramPacket1 = new DatagramPacket(buffer, buffer.length);
        datagramSocket1.receive(datagramPacket1);

        // converting byte key into PublicKey(i.e. server's PU key)
        byte[] publicKeyBytesReceived = datagramPacket1.getData();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytesReceived);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey serverPublicKey = keyFactory.generatePublic(keySpec);


        // generating key pair
        Signature sign = Signature.getInstance("SHA256withRSA");
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair pair = keyPairGen.generateKeyPair();

        // sending my public key
        DatagramSocket datagramSocket2 = new DatagramSocket();

        DatagramPacket datagramPacket2 = new DatagramPacket(pair.getPublic().getEncoded(), pair.getPublic().getEncoded().length, InetAddress.getByName("10.20.40.234") , 1234);

        datagramSocket2.send(datagramPacket2);

        // encrypting with server PU key
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        cipher.init(Cipher.ENCRYPT_MODE, serverPublicKey);

        byte[] input =  "welcome".getBytes();
        cipher.update(input);

        byte[] cipherText = cipher.doFinal();

        DatagramPacket datagramPacket = new DatagramPacket(cipherText, cipherText.length, InetAddress.getByName("10.20.40.234"), 1234);

        datagramSocket2.send(datagramPacket);
        System.out.println(new String(cipherText, "UTF8"));
    }
}