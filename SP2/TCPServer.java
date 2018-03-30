/**
 * Created by jacobkaufmann on 1/20/17.
 */

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        // The server’s IP address is the computer’s IP address

        // Create a server socket object that listens at port 6789
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(6021);
        } catch (UnknownHostException e) {}
        catch (SocketException e) {}
        catch (IOException e) {}


        // Make an infinite loop
        while(true) {
            // Accept a connection from the clients and create a new socket
            Socket theConnection = serverSocket.accept();

            // Prepare an object for reading from the input stream
            BufferedReader in = new BufferedReader(new InputStreamReader(theConnection.getInputStream()));

            // Prepare an object for writing to the output stream
            PrintWriter out = new PrintWriter(theConnection.getOutputStream(), true);

            // Read data from the input stream
            String fromClient = in.readLine();

            // Change the message to uppercase
            fromClient = fromClient.toUpperCase();

            // Print the data on the screen
            System.out.println(fromClient);

            // Log the message to a file
            try{
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("messageFromClient.txt", true)));
                writer.println(fromClient);
                writer.close();
            } catch (IOException e) {
                // do something
            }

            // Write the data on the output stream
            out.println(fromClient);

            // Close the connection
            theConnection.close();
            in.close();
            out.close();
        }
    }
}

