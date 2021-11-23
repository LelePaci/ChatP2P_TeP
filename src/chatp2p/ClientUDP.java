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
    private Connessione connessione;
    private Nickname n;
    private GestioneChat chat;

    public ClientUDP(Nickname n, Connessione connessione) throws SocketException, UnknownHostException {
        this.port = 2003;
        this.client = new DatagramSocket(port);
        this.lastAddress = null;
        this.connessione = connessione;
        this.n = n;
        this.chat = chat;
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
                        connessione.setCanText(true);
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
        //responsePacket.setAddress(InetAddress.getByName(("172.22.198.197")));
        //responsePacket.setAddress(InetAddress.getByName(("172.16.102.110")));
        responsePacket.setAddress(connessione.getAddress());
        responsePacket.setPort(port);
        client.send(responsePacket);
    }

    private void richiestaConnessione(String nick) throws IOException {

        if (connessione.getAddress() == null) {
            //Chiedo all'utente se vuole confermare la connessione
            
            Condivisa.f.Popup();
            connessione.setPending(true);
            connessione.setAddress(lastAddress);
            connessione.setConnectionNickname(nick);
            //connessione.setAddress(lastAddress);
            //invia("y;" + n.getNickname());
        } else if (lastAddress != connessione.getAddress()) {
            invia("n;");
        }
    }
}
