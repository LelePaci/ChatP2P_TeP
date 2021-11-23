/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatp2p;

import java.io.IOException;

/**
 *
 * @author pacie
 */
public class GestioneChat {

    private ClientUDP client;
    private String nickname;
    

    public GestioneChat(ClientUDP client, String nickname) {
        this.client = client;
        this.nickname = nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public void startConnessione() throws IOException {
        client.invia(Messaggio.toCSV("c", nickname));
    }
    
    public void accettaConnessione() throws IOException {
        System.out.println(nickname);
        client.invia(Messaggio.toCSV("y", nickname));
        System.out.println(Messaggio.toCSV("y", nickname));
    }
    
    public void rifiutaConnessione() throws IOException {
        client.invia(Messaggio.toCSV("n", ""));
    }
    
    public void inviaMessaggio(String comando, String messaggio) throws IOException {
        
        client.invia(Messaggio.toCSV(comando, messaggio));
    }

    public static String[] getComandi() {
        return new String[]{"c", "y", "n", "m", "e"};
    }
}