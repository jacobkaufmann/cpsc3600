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
            // Accept a connection from the clients and create a new socket
            Socket theConnection = serverSocket.accept();

            // Prepare an object for reading from the input stream
            BufferedReader in = new BufferedReader(new InputStreamReader(theConnection.getInputStream()));

            // Prepare an object for writing to the output stream
            PrintWriter out = new PrintWriter(theConnection.getOutputStream(), true);

            // Read data from the input stream
            String fromClient = in.readLine();

            String[] command = fromClient.split(" ");

          if(command[0].equals("GET") && (command[2].equals("HTTP/1.0") || command[2].equals("HTTP/1.1"))) {
                out.println("200 OK");
		if(command[1].substring(0,1).equals("/"))
		      command[1] = command[1].substring(1); 
                
		File f = new File(command[1]);

		// If the file exists, read from the file and write the data to the output stream
                if(f.exists() && f.isFile()) {
                    BufferedReader inFromFile = new BufferedReader(new FileReader(f));
                    String sentence;
                    while((sentence = inFromFile.readLine()) != null) {
                        out.println(sentence);
                    }
                } else {
                    out.println("404 not found");
                }
          } else if(command[0].equals("PUT")) {
	   	// File file = new File(command[1]);
		
		InputStream input = theConnection.getInputStream();
		OutputStream output = new FileOutputStream(command[1]);

		byte[] bytes = new byte[16*1024];

        	int count;
        	while ((count = input.read(bytes)) > 0) {
            	    output.write(bytes, 0, count);
        	}
		
		out.println("200 OK File Created");
		input.close();
		output.close();
	  }

            // Print the data on the screen
            System.out.println(fromClient);

            // Read from the file and write the data on the output stream

            // Close the connection
            theConnection.close();
            in.close();
            out.close();
        }
    }
}

