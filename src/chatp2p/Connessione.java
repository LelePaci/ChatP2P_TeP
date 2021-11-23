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
    private String nickname;
    private boolean pending;
    private boolean canText;

    public Connessione() throws UnknownHostException {
        //this.address = InetAddress.getByName("localhost");
        this.address = null;
        nickname = null;
        pending = false;
        canText = false;
    }
    
    public Connessione(InetAddress address, String nickname) {
        this.address = address;
        this.nickname = nickname;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }
    
    public void setAddressFromString(String address) throws UnknownHostException{
        this.address = InetAddress.getByName(address);
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public void setCanText(boolean canText) {
        this.canText = canText;
    }
    
    public InetAddress getAddress() {
        return address;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isPending() {
        return pending;
    }
    
    public boolean canText(){
        return canText;
    }
    
    public static Connessione get(String address, String nickname) throws UnknownHostException{
        InetAddress add = InetAddress.getByAddress(address.getBytes());
        return new Connessione(add, nickname);
    }
}
