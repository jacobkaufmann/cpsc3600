import java.io.*;
import java.net.*;
import java.util.Scanner;

public class HTTPServer {
    public static void main(String[] args) throws Exception {
        // The server’s IP address is the computer’s IP address

        // Create a server socket object that listens at port 6789
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(6020);
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

            // Print the data on the screen
            System.out.println(fromClient);

            // Read from the file and write the data on the output stream
	    File f = new File("index.html");
	    BufferedReader inFromFile = new BufferedReader(new FileReader(f));
	    String sentence;
            out.println("HTTP/1.0 200 OK\r\n");
            while((sentence = inFromFile.readLine()) != null) {
	        out.println(sentence);
	    }
            // Close the connection
            theConnection.close();
            in.close();
            out.close();
        }
    }
}

