package pl.olapp.chat.dto;

public class ChatMessage {
    private String user;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ChatMessage(String user, String value){
        this.user = user;
        this.value = value;
    }
    public ChatMessage(){ }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}