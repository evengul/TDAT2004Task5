package Task1;

import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args){
        try(BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket clientSocket = new DatagramSocket();){
            InetAddress IPAddress = InetAddress.getByName("localhost");
            byte[] sendData;
            byte[] receivedData = new byte[1024];
            System.out.println("Enter an operation in the form of OP NUM NUM, " +
                    "where OP is either + or -, and NUM can be any integer under 2M:");
            String input = userInput.readLine().trim();
            sendData = input.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 3001);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receivedData, receivedData.length);
            clientSocket.receive(receivePacket);
            String result = new String(receivePacket.getData());
            System.out.println("Result: " + result);
        }
        catch (Exception e){
            System.out.println("Error!");
        }
    }
}
