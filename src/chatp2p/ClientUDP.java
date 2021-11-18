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

    public ClientUDP(Nickname n) throws SocketException, UnknownHostException {
        this.port = 2003;
        this.client = new DatagramSocket(port);
        this.lastAddress = null;
        this.connessione = new Connessione();
        this.n = n;
    }

    @Override
    public void run() {
        while (true) {
            try {
                switch (ricevi().comando) {
                    case "c":
                        richiestaConnessione();
                        break;
                    case "y":
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
        responsePacket.setAddress(InetAddress.getByName(("93.66.23.99")));
        responsePacket.setPort(port);
        client.send(responsePacket);
    }

    private void richiestaConnessione() throws IOException {
        if (connessione.getAddress() == null) {
            connessione.setAddress(lastAddress);
            invia("y;" + n.getNickname());
        } else if (lastAddress != connessione.getAddress()) {
            invia("n;");
        } else {
            invia("y;" + n.getNickname());
        }
    }
}
