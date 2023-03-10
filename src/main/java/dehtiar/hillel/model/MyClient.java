package dehtiar.hillel.model;

import dehtiar.hillel.service.FileSender;
import dehtiar.hillel.service.ServerReader;
import dehtiar.hillel.service.ServerWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.Socket;

/**
 * @author Yaroslav Dehtiar on 27.01.2023
 */
public class MyClient {

  private MyClient() {
  }

  private static class ClientHolder {
    private final static MyClient instance = new MyClient();
  }

  public static MyClient getInstance() {
    return MyClient.ClientHolder.instance;
  }


  public void start() {
    try {
      Socket clientSocket = new Socket("localhost", 10160);
      BufferedReader listener = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      BufferedWriter sender = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
      BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
      FileSender fileSender = new FileSender(clientSocket);

      ServerReader serverReader = new ServerReader(listener);
      serverReader.start();

      ServerWriter serverWriter = new ServerWriter(sender, userInputReader, fileSender);
      serverWriter.start();
    } catch (ConnectException ex) {
      System.out.println("Server is not available. Try again later");
    } catch (IOException ex) {
      System.out.println("Unable to connect to server. Try again later");
    }
  }

  public void closeConnection() {
    System.out.println("You was disconnected from the server. Come back later.");
    System.exit(0);
  }

}
