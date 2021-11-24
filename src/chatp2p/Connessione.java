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
    private InetAddress tempAddress;
    private String connectionNickname;
    private String tempNickname;
    private boolean canConnect;

    public Connessione() throws UnknownHostException {
        this.address = null;
        this.tempAddress = null;
        this.connectionNickname = "";
        this.tempNickname = "";
        this.canConnect = true;
    }
    
    public Connessione(InetAddress address, String nickname) {
        this.address = address;
        this.connectionNickname = nickname;
    }
    
    public void setAddress(InetAddress address) {
        this.address = address;
    }
    
    public void setTempAddress(InetAddress tempAddress) {
        this.tempAddress = tempAddress;
    }
    
    public void setAddressFromString(String address) throws UnknownHostException{
        this.tempAddress = InetAddress.getByName(address);
    }
    
    public void setConnectionNickname(String nickname) {
        this.connectionNickname = nickname;
    }

    public InetAddress getAddress() {
        return address;
    }

    public String getConnectionNickname() {
        return connectionNickname;
    }

    public InetAddress getTempAddress() {
        return tempAddress;
    }

    public void CanConnect(boolean canConnect) {
        this.canConnect = canConnect;
    }
    
    public boolean CanConnect() {
        return canConnect;
    }

    public String getTempNickname() {
        return tempNickname;
    }

    public void setTempNickname(String tempNickname) {
        this.tempNickname = tempNickname;
    }
    
    public static Connessione get(String address, String nickname) throws UnknownHostException{
        InetAddress add = InetAddress.getByAddress(address.getBytes());
        return new Connessione(add, nickname);
    }
}
