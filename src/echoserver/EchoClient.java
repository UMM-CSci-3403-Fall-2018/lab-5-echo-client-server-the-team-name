package echoserver;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class EchoClient {
  public static final int portNumber = 6013;

  public static void main(String[] args) throws IOException {
    String server;
    // Use "127.0.0.1", i.e., localhost, if no server is specified.
    if (args.length == 0) {
      server = "127.0.0.1";
    } else {
      server = args[0];
    }

    try {
      // Connect to the server
      Socket socket = new Socket(server, portNumber);

      // Initialize standard inputs/outputs and streams
      InputStream sinput = System.in;
      OutputStream soutput = System.out;

      InputStream input = socket.getInputStream();
      OutputStream output = socket.getOutputStream();


      // Send and receive data to/from the server
      int next_byte;
      while ((next_byte = sinput.read()) != -1) {
        output.write(next_byte);
        next_byte = input.read();
        soutput.write(next_byte);
      }

      // Close all sockets when we're done reading from them
      sinput.close();
      soutput.close();
      input.close();
      output.close();

    // Provide some minimal error handling.
    } catch (ConnectException ce) {
      System.out.println("We were unable to connect to " + server);
      System.out.println("You should make sure the server is running.");
    } catch (IOException ioe) {
      System.out.println("We caught an unexpected exception");
      System.err.println(ioe);
    }
  }
}
