package chatp2p;

import java.util.Scanner;

/**
 *
 * @author pacie
 */
public class ControlloConsole extends Thread {

    private final Scanner scanner = new Scanner(System.in);
    private boolean chat = false;

    @Override
    public void run() {
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("chat")) {
                chat = !chat;
                if (chat) {
                    System.out.println("Chat sbloccata per test, non invierà messaggi ad altri utenti");
                } else {
                    System.out.println("Chat bloccata");
                }
                Condivisa.frame.setObjectsStatus(chat);
            }
        }
    }
}
