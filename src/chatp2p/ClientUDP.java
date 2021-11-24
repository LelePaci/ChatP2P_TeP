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

    private final int port;
    private final DatagramSocket client;

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
                Messaggio m = receive();
                switch (m.comando) {
                    case "c":
                        connectionRequest(m.dati);
                        break;
                    case "y":
                        if (m.dati.length()>=1) {
                            Condivisa.connessione.setConnectionNickname(m.dati);
                        }else{
                            Condivisa.connessione.setConnectionNickname(Condivisa.connessione.getTempNickname());
                        }

                        Condivisa.connessione.setAddress(Condivisa.connessione.getTempAddress());
                        Condivisa.frame.setConnessione();
                        Condivisa.frame.PopupInformativo("Connessione stabilita con " + Condivisa.connessione.getConnectionNickname());
                        break;
                    case "n":
                        Condivisa.connessione.setTempAddress(null);
                        break;
                    case "m":
                        Condivisa.frame.addTextToList(Condivisa.connessione.getConnectionNickname(), m.dati);
                        break;
                    case "e":
                        Condivisa.frame.chiudiConnessione();
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

    public Messaggio receive() throws IOException, Exception {
        byte[] buffer = new byte[1500];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        client.receive(packet);
        String messageReceived = new String(packet.getData(), 0, packet.getLength());
        lastAddress = packet.getAddress();
        System.out.println(messageReceived);
        return Messaggio.fromCSV(messageReceived);
    }

    public synchronized void send(InetAddress address, Messaggio message) throws IOException {
        byte[] buffer = Messaggio.toCSV(message).getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        packet.setAddress(address);
        packet.setPort(port);
        client.send(packet);
    }

    private void connectionRequest(String nickname) throws IOException {
        if (Condivisa.connessione.CanConnect()) {
            Condivisa.connessione.setTempAddress(lastAddress);
            Condivisa.connessione.setTempNickname(nickname);
            Condivisa.connessione.CanConnect(false);

            int index = Condivisa.frame.PopupConfermaConnessione();
            if (index == 0) {
                Condivisa.chat.accettaConnessione();
            }
            if (index == 1) {
                Condivisa.chat.rifiutaConnessione();
            }
        }else{
            send(lastAddress, new Messaggio("n", ""));
        }
    }
}
