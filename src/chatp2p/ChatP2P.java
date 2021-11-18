package chatp2p;

import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

/**
 *
 * @author pacie
 */
public class ChatP2P {

    /**
     * @param args the command line arguments
     */
    private static Scanner sc;
    private static Nickname nickname;

    public static void main(String[] args) throws SocketException, IOException {
        ClientUDP client = new ClientUDP();
        client.start();
        GestioneChat chat = new GestioneChat(client, "guest1");
        nickname = new Nickname();
        sc = new Scanner(System.in);
        //Start connessione
        //Chat
        //Chiusura connessione

        while (true) {
            String input = sc.nextLine();
            switch (input) {
                case "setup":
                    //Inserimento nickname
                    setNickname();
                    break;
                case "start":
                    nickname.setEditable(false);
                    chat.startConnessione();
                    
                    break;
            }
        }
    }

    public static void setNickname() {
        if (nickname.isEditable()) {
            System.out.println("Inserisci nuovo nickname:");
            nickname.setNickname(sc.nextLine());
        } else {
            System.out.println("Adesso non è possibile modificare il nickname");
        }
    }
}
