/**
 * Created by jacobkaufmann on 1/20/17.
 */

import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        // The server’s IP address is the computer’s IP address

        // Create a server socket object that listens at port 6789
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(6789);
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
            PrintWriter out = new PrintWriter(theConnection.getOutputStream());

            // Read data from the input stream
            String fromClient = in.readLine();

            // Print the data on the screen
            System.out.println(fromClient);

            // Write the data on the output stream
            out.println("Welcome from the server");

            // Close the connection
            theConnection.close();
            in.close();
            out.close();
        }
    }
}

