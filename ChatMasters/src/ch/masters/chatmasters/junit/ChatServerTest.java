package ch.masters.chatmasters.junit;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.masters.chatmasters.model.Message;
import ch.masters.chatmasters.model.User;
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

	@BeforeClass
	public static void beforeClass(){
		chatServer = ChatServer.createServer(1257);
	}
	
	@After
	public void after() throws RemoteException {
		chatServer.resetServer();
	}
	
	@Test
	public void checkSend() throws RemoteException {
		int iterrations = 600;
		int startSize = chatServer.returnMessages().size();
		
		for(int i = 0; i < iterrations; i++){
			Message message = new Message();
			message.setMsg(Integer.toString(i));
			chatServer.send(message);
		}
		
		int endSize = chatServer.returnMessages().size()-iterrations;
		Assert.assertEquals(endSize, startSize);
	}
	
	@Test
	public void checkRmvClient() throws RemoteException {
		int addClients = 400;
		int removeClients = 200;
		ArrayList<User> users = new ArrayList<User>();
		
		for(int i = 0; i < addClients; i++){
			String username = "User"+(i+1);
			User user = new User(username, true, new Timestamp(Calendar.getInstance().getTimeInMillis()));
			chatServer.setClient(user);
			users.add(user);
		}
		
		for(int i = 0; i < removeClients; i++){
			chatServer.rmvClient(users.get(i));
		}
		
		int totClientsAftRmv = chatServer.returnClients().size();
		Assert.assertEquals(addClients-removeClients, totClientsAftRmv);
	}
	
	@Test
	public void checkSetClient() throws RemoteException {
		int addClients = 900;
		int startSize = chatServer.returnClients().size();
		
		for(int i = 0; i < addClients; i++){
			String username = "User"+(i+1);
			chatServer.setClient(new User(username, true, new Timestamp(Calendar.getInstance().getTimeInMillis())));
		}
		
		int endSize = chatServer.returnClients().size();
		Assert.assertEquals(startSize + addClients, endSize);
	}
	
}
