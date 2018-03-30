import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) throws Exception {
        //
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(args[0], Integer.parseInt(args[1]));
        } catch (UnknownHostException e) {}
        catch (SocketException e) {}
        catch (IOException e) {}

        // Prepare an object for reading from the input stream
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Prepare an object for writing to the output stream
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        // Write data to the output stream
        if(args[2].equals("GET")) {
            out.println("GET " + args[3] + " HTTP/1.0\r\n");
        }
        else if(args[2].equals("PUT")) {
	    out.println("PUT " + args[3]);

	    File file = new File(args[3]);
	    long length = file.length();
            byte[] bytes = new byte[16 * 1024];
            InputStream input = new FileInputStream(file);
            OutputStream output = clientSocket.getOutputStream();

            int count;
            while ((count = input.read(bytes)) > 0) {
                output.write(bytes, 0, count);
            }

	    input.close();
	    output.close();
        }

        // Read data from the input stream and print the data on the screen
        String replyFromServer;

        while((replyFromServer = in.readLine()) != null) {
            System.out.println(replyFromServer);
        }

        // Close the socket
        clientSocket.close();
        in.close();
        out.close();
	//input.close();
        //output.close();
    }
}
