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

    public void startConnessione() throws IOException {
        String s = "c;" + nickname;
        client.invia(s);
    }

    public static String inviaMessaggio(String messaggio) {
        return "m;" + messaggio;
    }

    public static String[] getComandi() {
        return new String[]{"c", "y", "n", "m", "e"};
    }
}
