package ch.masters.chatmasters.model;

import java.security.Timestamp;

/**
 * Model class for a Message
 * @author Chiramed Phong Penglerd, Luca Marti, Elia Perenzin
 * @version 1.0
 * ChatMasters 2016
 */
public class Message {

	//Instanzvariablen
    private User sender;
    private String msg;
    private Timestamp time;

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender +
                ", msg='" + msg + '\'' +
                '}';
    }

    //Getter and Setter
    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp timestamp) {
        this.time = timestamp;
    }
}
