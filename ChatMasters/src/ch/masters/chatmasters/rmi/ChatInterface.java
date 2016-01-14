package ch.masters.chatmasters.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import ch.masters.chatmasters.model.Message;
import ch.masters.chatmasters.model.User;

public interface ChatInterface extends Remote {

    void send(Message msg) throws RemoteException;

    List<Message> returnMessages() throws RemoteException;

    List<User> returnClients() throws RemoteException;

    void setClient(User client) throws RemoteException;
    
    void rmvClient(User client) throws RemoteException;
}