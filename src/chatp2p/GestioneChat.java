package chatp2p;

import java.io.IOException;
import java.net.InetAddress;

/**
 *
 * @author pacie
 */
public class GestioneChat {

    public GestioneChat() {
    }

    public static String[] getComandi() {
        //Lista dei comandi consentiti dal prodotocollo di comunicazione
        return new String[]{"c", "y", "n", "m", "e"};
    }

    public void startConnessione() throws IOException {
        //L'utente invia una richiesta di connessione ad un altro dispositivo
        String s = Condivisa.frame.PopupInputString("Nuova connessione", "Inserisci indirizzo IP");
        Condivisa.connessione.setAddressFromString(s);
        Condivisa.client.send(Condivisa.connessione.getTempAddress(), new Messaggio("c", Condivisa.nickname.getNickname()));
        Condivisa.connessione.CanConnect(false);
    }

    public void accettaConnessione() throws IOException {
        //L'utente scegle se accettare una connessione in entrata
        Condivisa.client.send(Condivisa.connessione.getTempAddress(), new Messaggio("y", Condivisa.nickname.getNickname()));
    }

    public void rifiutaConnessione() throws IOException {
        //L'utente scegle se rifiutare una connessione in entrata
        Condivisa.client.send(Condivisa.connessione.getTempAddress(), new Messaggio("n", ""));
        Condivisa.connessione.setTempAddress(null);
        Condivisa.connessione.setTempNickname("");
        Condivisa.connessione.CanConnect(true);
    }

    public void inviaMessaggio(String dati) throws IOException {
        Condivisa.client.send(Condivisa.connessione.getAddress(), new Messaggio("m", dati));
    }

    public void chiudiConnessione() throws IOException {
        Condivisa.client.send(Condivisa.connessione.getAddress(), new Messaggio("e", ""));
    }

    public void connectionRequest(InetAddress lastAddress, String nickname) throws IOException {
        if (Condivisa.connessione.CanConnect()) {
            Condivisa.connessione.setTempAddress(lastAddress);
            Condivisa.connessione.setTempNickname(nickname);
            Condivisa.connessione.CanConnect(false);

            int index = Condivisa.frame.PopupConfermaConnessione();
            if (index == 0) {
                accettaConnessione();
            }
            if (index == 1) {
                rifiutaConnessione();
            }
        } else {
            Condivisa.client.send(lastAddress, new Messaggio("n", ""));
        }
    }

    public void connectionEstablishing(String nickname) {
        if (nickname.length() >= 1) {
            Condivisa.connessione.setConnectionNickname(nickname);
        } else {
            Condivisa.connessione.setConnectionNickname(Condivisa.connessione.getTempNickname());
        }
        Condivisa.connessione.setAddress(Condivisa.connessione.getTempAddress());
        Condivisa.frame.setConnectionStatus(Condivisa.connessione.getConnectionNickname(), Condivisa.connessione.getAddress().toString(), true);
        Condivisa.frame.PopupInformativo("Connessione stabilita con " + Condivisa.connessione.getConnectionNickname());
    }

    public void messageReceived(String message) {
        Condivisa.frame.addTextToList(Condivisa.connessione.getConnectionNickname(), message);
    }

    public void connectionClosed() {
        Condivisa.connessione.resetConnessione();
        Condivisa.frame.setConnectionStatus("nessuno", "", false);
    }
}
