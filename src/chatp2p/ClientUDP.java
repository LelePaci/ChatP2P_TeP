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

    private int port;
    private DatagramSocket client;

    private InetAddress lastAddress;

    public ClientUDP() throws SocketException, UnknownHostException {
        this.port = 2003;
        this.client = new DatagramSocket(port);
        this.lastAddress = null;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Messaggio m=ricevi();
                switch (m.comando) {
                    case "c":
                        richiestaConnessione(m.dati);
                        break;
                    case "y":
                        Condivisa.frame.setConnessione();
                        break;
                    case "n":
                        break;
                    default:
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Messaggio ricevi() throws IOException, Exception {
        byte[] buffer = new byte[1500];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        client.receive(packet);
        String messaggioRicevuto = new String(packet.getData(), 0, packet.getLength());
        lastAddress = packet.getAddress();
        System.out.println(messaggioRicevuto);
        return Messaggio.fromCSV(messaggioRicevuto);
    }

    public synchronized void invia(String risposta) throws IOException {
        byte[] responseBuffer = risposta.getBytes();
        DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
        responsePacket.setAddress(Condivisa.connessione.getAddress());
        responsePacket.setPort(port);
        client.send(responsePacket);
    }

    private void richiestaConnessione(String nick) throws IOException {
        if (Condivisa.connessione.getAddress() == null) {
            //Chiedo all'utente se vuole confermare la connessione
            Condivisa.connessione.setAddress(lastAddress);
            Condivisa.connessione.setConnectionNickname(nick);
            int index = Condivisa.frame.PopupConfermaConnessione();
            if (index == 0) {
                Condivisa.chat.accettaConnessione();
            }
            if (index == 1) {
                Condivisa.chat.rifiutaConnessione();
            }
        } else if (lastAddress != Condivisa.connessione.getAddress()) {
            
            invia("n;");
        }
        if(Condivisa.connessione.getAddress() == lastAddress){
            invia("y;" + Condivisa.nickname.getNickname());
        }
        System.out.println(lastAddress.toString());
            System.out.println(Condivisa.connessione.getAddress());
    }
}
