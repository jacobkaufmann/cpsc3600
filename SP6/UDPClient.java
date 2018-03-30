/*
    Jacob Kaufmann
    Logan Czarnecki
*/
import java.io.*; 
import java.net.*;
public class UDPClient {
public static void main(String args[]) throws Exception {
	byte[] sendData = new byte[1024];
	byte[] receiveData = new byte[1024];
	InetAddress IPAddress = null;
	try {       
		//Initialize InetAddress object
		IPAddress = InetAddress.getByName("imp1.cs.clemson.edu");     
	} catch (UnknownHostException ex) {       
		System.err.println(ex);     
	}

	//Create a datagram socket object
	DatagramSocket clientSocket = new DatagramSocket();

	//Sentence to be sent by client
	String message = "Student A, seq = ";
	String toServer;
	
	for(int i = 0; i < 10; i++) {
		toServer = message + i;

		//Encode string into a sequence of bytes     
		sendData = toServer.getBytes();

		//Create a datagram packet with IP address and port of the server and the sentence
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 6023);

		//Send the packet
		System.out.println("Sending new message to the server: " + toServer);
		clientSocket.send(sendPacket);

		//Create a new datagram packet to get the response
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

		clientSocket.setSoTimeout(5000);

		boolean sent = false;

		while(!sent) {
		try {
			//Receive the packet and return the data buffer
			clientSocket.receive(receivePacket);
			String fromServer = new String(receivePacket.getData(), 0, receivePacket.getLength());
			//Print the data on the screen
                	System.out.println("FROM SERVER: " + fromServer);
			sent = true;
		} catch (SocketTimeoutException e) {
			System.out.println("Time out but the socket has not received any ack");
			System.out.println("Retry sending the message: " + toServer);
			clientSocket.send(sendPacket);
		}
		}
		toServer = "";
	}

	//Close the socket
	clientSocket.close();
} 
}
