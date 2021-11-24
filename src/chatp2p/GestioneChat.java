package chatp2p;

import java.io.IOException;

/**
 *
 * @author pacie
 */
public class GestioneChat {

    public GestioneChat() {
    }
    public void startConnessione() throws IOException {
        //L'utente invia una richiesta di connessione ad un altro dispositivo
        Condivisa.client.send(Condivisa.connessione.getTempAddress(), new Messaggio("c", Condivisa.nickname.getNickname()));
        Condivisa.connessione.CanConnect(false);
    }
    
    public void accettaConnessione() throws IOException {
        //L'utente scegle se accettare una connessione in entrata
        Condivisa.client.send(Condivisa.connessione.getTempAddress(), new Messaggio("y", Condivisa.nickname.getNickname()));
    }
    
    public void rifiutaConnessione() throws IOException {
        //L'utente scegle se rifiutare una connessione in entrata
        Condivisa.client.send(Condivisa.connessione.getTempAddress(), new Messaggio("n",""));
        Condivisa.connessione.setTempAddress(null);
        Condivisa.connessione.setTempNickname("");
        Condivisa.connessione.CanConnect(true);
    }
    
    public void inviaMessaggio(String comando, String dati) throws IOException { 
        Condivisa.client.send(Condivisa.connessione.getAddress(), new Messaggio(comando, dati));
    }
    
    public void chiudiConnessione() throws IOException{
        Condivisa.client.send(Condivisa.connessione.getAddress(), new Messaggio("e", ""));
    }

    public static String[] getComandi() {
        //Lista dei comandi consentiti dal prodotocollo di comunicazione
        return new String[]{"c", "y", "n", "m", "e"};
    }
}
