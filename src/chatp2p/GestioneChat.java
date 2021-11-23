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
        Condivisa.client.invia(Messaggio.toCSV("c", Condivisa.nickname.getNickname()));
    }
    
    public void accettaConnessione() throws IOException {
        Condivisa.client.invia(Messaggio.toCSV("y", Condivisa.nickname.getNickname()));
    }
    
    public void rifiutaConnessione() throws IOException {
        Condivisa.client.invia(Messaggio.toCSV("n", ""));
    }
    
    public void inviaMessaggio(String comando, String messaggio) throws IOException { 
        Condivisa.client.invia(Messaggio.toCSV(comando, messaggio));
    }

    public static String[] getComandi() {
        return new String[]{"c", "y", "n", "m", "e"};
    }
}
