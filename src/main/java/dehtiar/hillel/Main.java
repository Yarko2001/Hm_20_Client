package dehtiar.hillel;

import dehtiar.hillel.model.MyClient;

/**
 * @author Yaroslav Dehtiar on 27.01.2023
 */
public class Main {

  public static void main(String[] args) {
    MyClient myClient = MyClient.getInstance();
    myClient.start();
  }

}
