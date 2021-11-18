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

    public Connessione() throws UnknownHostException {
        //this.address = InetAddress.getByName("localhost");
        this.address = null;
        nickname = null;
        pending = false;
    }
    
    public Connessione(InetAddress address, String nickname) {
        this.address = address;
        this.nickname = nickname;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
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
    
    public static Connessione get(String address, String nickname) throws UnknownHostException{
        InetAddress add = InetAddress.getByAddress(address.getBytes());
        return new Connessione(add, nickname);
    }
}
