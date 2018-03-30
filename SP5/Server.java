import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws Exception {
        // Create a server socket object
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(Integer.parseInt(args[0]));
        } catch (UnknownHostException e) {}
        catch (SocketException e) {}
        catch (IOException e) {}

        // Make an infinite loop
        while(true) {
            // Accept a connection from a client and create a new socket
            Socket theConnection = serverSocket.accept();

	    // Create a thread for the connection
	    Thread t = new Thread();

            // Prepare an object for reading from the input stream
            BufferedReader in = new BufferedReader(new InputStreamReader(theConnection.getInputStream()));

            // Prepare an object for writing to the output stream
            PrintWriter out = new PrintWriter(theConnection.getOutputStream(), true);

            // Read data from the input stream
            String fromClient = in.readLine();
	}
    }
}
