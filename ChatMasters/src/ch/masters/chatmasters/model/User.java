package ch.masters.chatmasters.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Model class for a User
 * @author Chiramed Phong Penglerd, Luca Marti, Elia Perenzin
 * @version 1.0
 * ChatMasters 2016
 */
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	//Instanzvariablen
    private String name;
    private boolean online;
    private Timestamp onlineTime;
    private ArrayList<Message> messageList = new ArrayList<Message>();

    public User(String name, Boolean online, Timestamp time) {
        this.setOnlineTime(time);
        this.setName(name);
        this.setOnline(online);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", online=" + online +
                '}';
    }

    //Getter and Setter
    public Timestamp getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Timestamp onlineTime) {
        this.onlineTime = onlineTime;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public ArrayList<Message> getMessageList() {
		return messageList;
	}

	public void setMessageList(ArrayList<Message> messageList) {
		this.messageList = messageList;
	}

}
