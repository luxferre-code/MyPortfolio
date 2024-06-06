package fr.valentinthuillier.portfolio.dto;

public class Message {

    private final int id;
    private String name;
    private String mail;
    private String message;
    private boolean repondu;

    public Message(int id, String name, String mail, String message, boolean repondu) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.message = message;
        this.repondu = repondu;
    }

    public Message(int id, String name, String mail, String message) {
        this(id, name, mail, message, false);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRepondu() {
        return repondu;
    }

    public void setRepondu(boolean repondu) {
        this.repondu = repondu;
    }
    
    
}
