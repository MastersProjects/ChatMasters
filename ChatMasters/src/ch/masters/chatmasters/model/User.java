package ch.masters.chatmasters.model;

import java.sql.Timestamp;

/**
 * Model class for a User
 * @author Chiramed Phong Penglerd, Luca Marti, Elia Perenzin
 * @version 1.0
 * ChatMasters 2016
 */
public class User{

	//Instanzvariablen
    private String name;
    private boolean online;
    private Timestamp onlineTime;

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

}
