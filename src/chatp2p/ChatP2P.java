/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatp2p;

import java.net.SocketException;

/**
 *
 * @author pacie
 */
public class ChatP2P {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SocketException {
       ClientUDP client = new ClientUDP();
       client.start();
    }
}
