/**
 * Created by jacobkaufmann on 1/20/17.
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {
    public static void main(String args[]) throws Exception {

        // In TCP/IP protocol, each computer has an IP address
        // In TCP/IP, different services on the same machine have different ports
        // The port number & IP address define the TCP/IP connection

        // localhost is a special address used to designate the loopback address
        // localhost has the IP address 127.0.0.1

        // Create the client socket with the address: localhost, port 6789
        Socket clientSocket = null;
        try {
            clientSocket = new Socket("joey10.cs.clemson.edu", 6021);
        } catch (UnknownHostException e) {}
        catch (SocketException e) {}
        catch (IOException e) {}


        // Prepare an object for reading from the input stream
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Prepare an object for writing to the output stream
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        // Read input from the command line
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Send the message to the server
        out.println(input);

        // Read data from the input stream
        String replyFromServer = in.readLine();

        // Print the data on the screen
        System.out.println(replyFromServer);

        // Close the socket
        clientSocket.close();
        in.close();
        out.close();
    }
}

