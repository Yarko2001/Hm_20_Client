package dehtiar.hillel.service;

import dehtiar.hillel.model.MyClient;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Yaroslav Dehtiar on 27.01.2023
 */
public class ServerReader extends Thread{

  BufferedReader listener;

  public ServerReader(BufferedReader listener) {
    this.listener = listener;
  }

  @Override
  public void run() {

    String msg = "";
    while (true) {
      try {
        if ((msg = listener.readLine()) == null) break;
        System.out.println(msg);
      } catch (IOException e) {
        MyClient.getInstance().closeConnection();
      }
    }
  }

}
