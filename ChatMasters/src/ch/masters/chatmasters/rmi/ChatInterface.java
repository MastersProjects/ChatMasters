package ch.masters.chatmasters.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import ch.masters.chatmasters.model.Message;
import ch.masters.chatmasters.model.User;

/**
 * Interface with all Methodes that are used for the chat
 * @author Chiramed Phong Penglerd, Luca Marti, Elia Perenzin
 * @version 1.0
 * ChatMasters 2016
 */
public interface ChatInterface extends Remote {

	/**
	 * send message to server
	 * @param msg
	 * @throws RemoteException
	 */
    void send(Message msg) throws RemoteException;

    /**
     * Returns all messages
     * @return List{@link Message}
     * @throws RemoteException
     */
    ArrayList<Message> returnMessages() throws RemoteException;

    /**
     * Returns all clients on the server
     * @return @return List{@link User}
     * @throws RemoteException
     */
    ArrayList<User> returnClients() throws RemoteException;

    /**
     * Add client to Server
     * @param client
     * @throws RemoteException
     */
    void setClient(User client) throws RemoteException;
    
    /**
     * Removes client from Server
     * @param client
     * @throws RemoteException
     */
    void rmvClient(User client) throws RemoteException;
    
    /**
     * Shuts down the server
     * @param status
     * @throws RemoteException
     */
     void closeServer(int status) throws RemoteException;
     
     /**
      * Resets the Server
      * @param status
      * @throws RemoteException
      */
      void resetServer() throws RemoteException;
}
