package chatp2p;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author pacie
 */
public class Connessione {
    private InetAddress address;
    private String connectionNickname;
    private boolean pending;
    private boolean canText;

    public Connessione() throws UnknownHostException {
        this.address = null;
        connectionNickname = null;
        pending = false;
        canText = false;
    }
    
    public Connessione(InetAddress address, String nickname) {
        this.address = address;
        this.connectionNickname = nickname;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }
    
    public void setAddressFromString(String address) throws UnknownHostException{
        this.address = InetAddress.getByName(address);
    }

    public void setConnectionNickname(String nickname) {
        this.connectionNickname = nickname;
    }

    public void setCanText(boolean canText) {
        this.canText = canText;
    }
    
    public InetAddress getAddress() {
        return address;
    }

    public String getConnectionNickname() {
        return connectionNickname;
    }
    
    public boolean canText(){
        return canText;
    }
    
    public static Connessione get(String address, String nickname) throws UnknownHostException{
        InetAddress add = InetAddress.getByAddress(address.getBytes());
        return new Connessione(add, nickname);
    }
}
