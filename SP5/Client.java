import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) throws Exception {
        
        Socket clientSocket = null;
        try {
            clientSocket = new Socket("joey7.cs.clemson.edu", 6021);
        } catch (UnknownHostException e) {}
        catch (SocketException e) {}
        catch (IOException e) {}

        // Prepare an object for reading from the input stream
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Prepare an object for writing to the output stream
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);


	Scanner scanner = new Scanner(System.in);

	out.println("HELLO");

	String userInput;

	while(!(userInput = scanner.nextLine()).equals("QUIT")) {
		out.println(scanner.nextLine();
	}

	clientSocket.close();
	in.close();
	out.close();
	}
}
