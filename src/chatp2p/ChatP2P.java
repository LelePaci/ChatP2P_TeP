/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatp2p;

import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

/**
 *
 * @author pacie
 */
public class ChatP2P {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SocketException, IOException {
       ClientUDP client = new ClientUDP();
       client.start();
       
       GestioneChat chat = new GestioneChat(client, "NickName1");
       Scanner sc = new Scanner(System.in);
       while(true){
           String input = sc.nextLine();
           if (input.equals("start")) {
               chat.startConnessione();
           }
       }
    }
}
