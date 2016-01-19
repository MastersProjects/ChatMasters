package ch.masters.chatmasters.junit;

import java.rmi.RemoteException;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ch.masters.chatmasters.rmi.ChatClient;
import ch.masters.chatmasters.rmi.ChatServer;
import junit.framework.Assert;

/**
 * JUnit test for the RegEx of ip
 * @author Chiramed Phong Penglerd, Elia Perenzin, Luca Marti
 * @version 1.0
 */
@SuppressWarnings("deprecation")
@RunWith(Parameterized.class)
public class ChatServerTest {
	private static ChatServer chatServer;
	private static ChatClient chatClient;


	public ChatServerTest() {
		try {
			chatServer = new ChatServer();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chatServer.createServer(1257);
		
		chatClient = new ChatClient("1257");
		chatClient.connectServer("localhost");
		chatClient.getUser().setName("Test");
		
		
		
		
		
	}

	@Parameters
	public static Collection<Object[]> values() {
		return null;
	}

	@Test
	public void test() {
		Assert.assertEquals(1, 1);
	}
}
