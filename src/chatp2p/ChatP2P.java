package chatp2p;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
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
    private static Connessione connessione;
    private static GestioneChat chat;

    public static void main2(String[] args) throws SocketException, IOException {
        nickname = new Nickname();
        connessione = new Connessione();
        ClientUDP client = new ClientUDP(nickname, connessione);
        client.start();
        chat = new GestioneChat(client, "guest1");

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
                    startConnessione();
                    break;
                default:
                    if (connessione.isPending()) {
                        System.out.println("Vuoi accettare la richeista da "+connessione.getConnectionNickname()+"?");
                        if (input.equals("y")) {
                            System.out.println("Connessione accettata");
                            chat.accettaConnessione();
                        }
                        if (input.equals("n")) {
                            System.out.println("Connessione rifiutata");
                            chat.rifiutaConnessione(); 
                        }
                    }
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

    public static void startConnessione() throws UnknownHostException, IOException {
        System.out.println("inserisci indirizzo IP");
        connessione.setAddressFromString(sc.nextLine());
        nickname.setEditable(false);
        chat.setNickname(nickname.getNickname());
        chat.startConnessione();
    }
}
