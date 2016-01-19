package ch.masters.chatmasters.junit;

import java.rmi.RemoteException;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

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
	private Message message;
	private int endSize;
	private int startSize;


	public ChatServerTest() {
		chatServer = ChatServer.createServer(1257);
		message = new Message();
	}

	@Before
	public void before() throws RemoteException {
		int iterrations = 6;
		startSize = chatServer.returnMessages().size();
		
		for (int i = 0; i<iterrations; i++){
			chatServer.send(message);
		}
		
		endSize = chatServer.returnMessages().size()-iterrations;
	}
	
	@Test
	public void checkSend() throws RemoteException {
		Assert.assertEquals(endSize, startSize);
	}
}
