package ch.masters.chatmasters.junit;

import java.rmi.RemoteException;
import java.sql.Timestamp
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import ch.masters.chatmasters.model.Message;
import ch.masters.chatmasters.rmi.ChatClient;
import ch.masters.chatmasters.rmi.ChatServer;
import junit.framework.Assert;

/**
 * 
 * @author Chiramed Phong Penglerd, Elia Perenzin, Luca Marti
 * @version 1.0
 */
@SuppressWarnings("deprecation")
public class ChatServerTest {
	private static ChatServer chatServer;
	private int totalUser;
	private ArrayList<Users> users;

	public ChatServerTest() {
		totalUsers = 4;
		users = new ArrayList<User>();
	}

	@Before
	public void before() throws RemoteException {
		chatServer = ChatServer.createServer(1257);
		
		for(int i = 0; i < totalUsers; i++){
			String username = "User"+(i+1);
			User user new User(username, true, new Timestamp(Calendar.getInstance().getTimeInMilis()));
			user.add(user);
		}
	}
	
	@Test
	public void checkSend() throws RemoteException {
		
		int iterrations = 6;
		int startSize = chatServer.returnMessages().size();
		
		for(int i = 0; i < iterrations; i++){
			chatServer.send(new Message());
		}
		
		int endSize = chatServer.returnMessages().size()-iterrations;
		Assert.assertEquals(endSize, startSize);
	}
	
	@Test
	public void checkRmvClient() throws RemoteException {
		
		int removeClients = 2;
		
		for(int i = 0; i < removeClients; i++){
			chatServer.rmvClient(users.get(i));
		}
		
		int totClientsAftRmv = chatServer.returnClients().size();
		Assert.assertEquals(totalUsers-removeClients, totClientsAftRmv);
	}
}
