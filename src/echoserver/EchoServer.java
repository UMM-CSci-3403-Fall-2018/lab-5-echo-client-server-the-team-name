package echoserver;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class EchoServer {

  public static final int portNumber = 6013;

  public static void main(String[] args) {
    try {
      // Start listening on the specified port
      ServerSocket sock = new ServerSocket(portNumber);

      // Run forever, which is common for server style services
      while (true) {
        // Wait until someone connects, thereby requesting a date
        Socket client = sock.accept();
        System.out.println("Got a request!");

        // Initialize input and output streams
        InputStream input = client.getInputStream();
        OutputStream output = client.getOutputStream();

        // Create int byte_type and run while loop returning client bytes
        int next_byte;
        while ((next_byte = input.read()) != -1) {
          output.write(next_byte);
        }

        // Close the client socket since we're done.
        client.close();
      }
    // *Very* minimal error handling.
    } catch (IOException ioe) {
      System.out.println("We caught an unexpected exception");
      System.err.println(ioe);
    }
  }
}
