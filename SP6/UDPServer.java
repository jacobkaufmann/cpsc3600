/*
    Jacob Kaufmann
    Logan Czarnecki
*/
import java.io.*; 
import java.net.*;
import java.util.Random;

public class UDPServer {   
public static void main(String args[]) throws Exception {
    byte[] receiveData = new byte[1024];     
    byte[] sendData = new byte[1024];
    
    //Create a datagram socket object which is listening on port 6789
    DatagramSocket serverSocket = new DatagramSocket(6023);
    
    //Stay in an infinite loop     
    while(true) {       
       //Create a datagram packet object for each client request       
       DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
       
       //Receive the packet and return the data buffer       
       serverSocket.receive(receivePacket);       
       String sentence = new String (receivePacket.getData(), 0, receivePacket.getLength());
       
       //Print the received sentence on the screen
       System.out.println("FROM CLIENT: " + sentence);
      
       //Sentence to be sent by server
       String toServer = sentence.substring(0);
 
       //Get the address and port of the client packet
       InetAddress IPAddress = receivePacket.getAddress();       
       int port =  receivePacket.getPort();
       
       //Encode string into a sequence of bytes
       sendData = toServer.getBytes();

       //Create a new datagram packet with the capitalized sentence and the client's address and port
       DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

       // Randomly determine whether the packet will be dropped
       Random rand = new Random();
       int decider = rand.nextInt(10);

       if(decider < 7) {
            //Send the packet to the client
            serverSocket.send(sendPacket);
       } else {
	    System.out.println("Drop the message: " + sentence);
       }
    }   
} 
}
