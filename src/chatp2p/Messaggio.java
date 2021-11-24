package chatp2p;

/**
 *
 * @author pacie
 */
public class Messaggio {

    String comando;
    String dati;

    public Messaggio(String comando, String dati) {
        this.comando = comando;
        this.dati = dati;
    }

    public static Messaggio fromCSV(String csv) throws Exception {
        int index = csv.indexOf(";");
        if (index == -1) {
            throw new Exception("Messaggio ricevuto non corretto");
        }
        String cmd = csv.substring(0, index);
        boolean err = true;
        for (int i = 0; i < GestioneChat.getComandi().length; i++) {
            String c = GestioneChat.getComandi()[i];
            if (cmd.equals(c)) {
                err = false;
                break;
            }
        }
        if (err) {
            throw new Exception("Richiesta operazione non esistente");
        }
        String dat = csv.substring(index + 1, csv.length());
        return new Messaggio(cmd, dat);
    }
    
    public static String toCSV(String comando, String dati){
        return comando + ";" + dati;
    }
    
    public static String toCSV(Messaggio messaggio){
        return messaggio.comando + ";" + messaggio.dati;
    }
}
