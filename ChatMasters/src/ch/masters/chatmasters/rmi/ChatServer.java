package ch.masters.chatmasters.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import ch.masters.chatmasters.model.Message;
import ch.masters.chatmasters.model.User;

/**
 * Actual chatServer contains all implemented Methodes from 
 * ChatInterface
 * @author Chiramed Phong Penglerd, Luca Marti, Elia Perenzin
 * @version 1.0
 * ChatMasters 2016
 */
public class ChatServer extends UnicastRemoteObject implements ChatInterface {

	//Instanzvariablen
	private static final long serialVersionUID = 1L;
	private ArrayList<Message> messageList = new ArrayList<Message>();
	private ArrayList<User> userList = new ArrayList<User>();

	public ChatServer() throws RemoteException {
		super(0);
	}

	public static void main(String[] arg) {
		createServer(1257);
	}

	/**
	 * Creates the Server
	 * @param port
	 * @return 
	 */
	public static ChatServer createServer(int port) {
		Registry reg = null;
		ChatServer server = null;
		try {
			System.out.println("RMI server started");
			try {
				reg = LocateRegistry.createRegistry(port);
				System.out.println("java RMI registry created at port " + port);
				
			} catch (RemoteException e) {
				System.out.println("RMI registry failed.");
				e.printStackTrace();
			}
			server = new ChatServer();
			reg.rebind("RmiChat", server);
			System.out.println("[System] Server ready");
		
		} catch (Exception e) {
			System.out.println("[System] Server failed: " + e);
			e.printStackTrace();
		}
		return server;
	}

	@Override
	public void send(Message msg) throws RemoteException {
		messageList.add(msg);
		System.out.println(msg);
	}

	@Override
	public ArrayList<Message> returnMessages() throws RemoteException {
		return this.messageList;
	}

	@Override
	public ArrayList<User> returnClients() throws RemoteException {
		return this.userList;
	}

	@Override
	public void setClient(User client) throws RemoteException {
		this.userList.add(client);
		System.out.println(client.getName());
	}

	@Override
	public void rmvClient(User client) throws RemoteException {
		System.out.println("logout " + client.getName());
		for(int i=0; i<userList.size(); i++){
			if(userList.get(i).getName().equals(client.getName())){
				userList.remove(i);
				break;
			}
		}
		System.out.println(userList.toString());
	}
	
	@Override
	public void closeServer(int status) throws RemoteException {
		System.out.println("Shut down server...");
		System.out.println("bye bye");
		System.exit(status);
	}

	@Override
	public void resetServer() throws RemoteException {
		messageList.clear();
		userList.clear();
		System.out.println("Server restarted...");
	}
}
