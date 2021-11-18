package chatp2p;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author pacie
 */
public class AddrAndNick {
    private InetAddress address;
    private String nickname;

    public AddrAndNick(InetAddress address, String nickname) {
        this.address = address;
        this.nickname = nickname;
    }

    public InetAddress getAddress() {
        return address;
    }

    public String getNickname() {
        return nickname;
    }
    
    public static AddrAndNick get(String address, String nickname) throws UnknownHostException{
        InetAddress add = InetAddress.getByAddress(address.getBytes());
        return new AddrAndNick(add, nickname);
    }
}
