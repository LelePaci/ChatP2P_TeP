/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public static Messaggio fromCSV(String csv) {
        int index = csv.indexOf(";");
        String c = csv.substring(0, index);
        String d = csv.substring(index + 1, csv.length());
        return new Messaggio(c, d);
    }
}
