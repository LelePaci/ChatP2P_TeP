package chatp2p;

/**
 *
 * @author pacie
 */
public class Nickname {

    private String nickname;
    private boolean editable;

    public Nickname() {
        nickname = "guest";
        editable = true;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isEditable() {
        return editable;
    }
}
