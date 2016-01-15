package ch.masters.chatmasters.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Model class for a Message
 * @author Chiramed Phong Penglerd, Luca Marti, Elia Perenzin
 * @version 1.0
 * ChatMasters 2016
 */
public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Instanzvariablen
    private User sender;
    private String msg;
    Date date = new java.util.Date();
    private Timestamp time = new Timestamp(date.getTime());

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender +
                ", msg='" + msg + '\'' +
                ", time='" + time + '\'' + 
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
