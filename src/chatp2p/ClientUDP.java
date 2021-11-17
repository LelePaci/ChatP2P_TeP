/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatp2p;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pacie
 */
public class ClientUDP extends Thread {

    private int port = 2003;
    private DatagramSocket client;
    
    private InetAddress lastAddress;    

    public ClientUDP() throws SocketException {
        this.port = 2003;
        this.client = new DatagramSocket(port);
        this.lastAddress = null;
    }

    @Override
    public void run() {
        while(true){
            try {
                ricevi();
            } catch (IOException ex) {
                Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Messaggio ricevi() throws IOException{
        byte[] buffer = new byte[1500];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        client.receive(packet);
        String messaggioRicevuto = new String(packet.getData(), 0, packet.getLength());
        lastAddress = packet.getAddress();
        
        System.out.println(messaggioRicevuto);
        return Messaggio.fromCSV(messaggioRicevuto);
    }
    
    public void invia(String risposta) throws IOException{
        byte[] responseBuffer = risposta.getBytes();
        DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
        responsePacket.setAddress(lastAddress);
        responsePacket.setPort(port);
        client.send(responsePacket);
    }
}
