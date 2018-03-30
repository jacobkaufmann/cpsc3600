import java.io.*;
import java.net.*;
import java.util.Scanner;

public class HTTPClient {
    public static void main(String args[]) throws Exception {
        Socket clientSocket = null;
        try {
            clientSocket = new Socket("www.google.com", 80);
        } catch (UnknownHostException e) {}
        catch (SocketException e) {}
        catch (IOException e) {}

        // Prepare an object for reading from the input stream
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Prepare an object for writing to the output stream
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        // Write data to the output stream
        out.println("GET /index.html HTTP/1.0\r\n");

        // Read data from the input stream and print the data on the screen
        String replyFromServer;

        while((replyFromServer = in.readLine()) != null) {
            System.out.println(replyFromServer);
        }

        // Close the socket
        clientSocket.close();
        in.close();
        out.close();
    }
}
