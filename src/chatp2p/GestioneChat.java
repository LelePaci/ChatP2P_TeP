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
    
    public static String startConnessione(String nickname) throws IOException{
        return "c;" + nickname;
    }
    
    public static String inviaMessaggio(String messaggio){
        return "m;" + messaggio;
    }
}
