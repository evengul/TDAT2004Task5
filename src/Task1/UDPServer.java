package Task1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;

public class UDPServer {
    public static void main(String[] args){
        try(DatagramSocket serverSocket = new DatagramSocket(3001)){
            byte[] receiveData = new byte[1024];
            byte[] sendData;
            while (true){
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String input = new String(receivePacket.getData());
                System.out.println("Server got " + input);
                String[] inputs = input.split(" ");
                char operator = inputs[0].charAt(0);
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                if (operator == 'x'){
                    sendData = "x".getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    serverSocket.send(sendPacket);
                    break;
                }
                String result;
                try{
                    int num1 = Integer.parseInt(inputs[1].trim());
                    int num2 = Integer.parseInt(inputs[2].trim());

                    if (operator == '+'){
                        result = String.valueOf(num1 + num2);
                    }
                    else if (operator == '-'){
                        result = String.valueOf(num1 - num2);
                    }
                    else{
                        result = "Error, not a valid operator";
                    }
                }
                catch (NumberFormatException e){
                    result = "Error, not a number";
                }
                sendData = result.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }
        }catch(IOException e){
            System.out.println("Error!");
        }
    }
}
