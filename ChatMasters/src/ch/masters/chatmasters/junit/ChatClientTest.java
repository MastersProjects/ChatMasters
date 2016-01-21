package ch.masters.chatmasters.junit;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ch.masters.chatmasters.rmi.ChatClient;
import junit.framework.Assert;

/**
 * JUnit test for the RegEx of ip
 * @author Chiramed Phong Penglerd, Elia Perenzin, Luca Marti
 * @version 1.0
 */
@SuppressWarnings("deprecation")
@RunWith(Parameterized.class)
public class ChatClientTest {
	private static ChatClient chatClient;
	private String ip;
	private boolean result;

	public ChatClientTest(String ip, boolean result) {
		this.ip = ip;
		this.result = result;
	}

	@Parameters
	public static Collection<Object[]> values() {
		return Arrays.asList(new Object[][] { { "192.168.1.1", true }, { "1.1.1.1", true }, { "This is an ip", false },
				{ "256.256.256.256", false }, { "10.10.10.10", true }, { "127.0.0.1", true }, { "000.0000.00.00", false}, 
				{"912.456.123.123", false}, { "10.127.127.260", false }, { "123.23.1.2", true }});
	}

	@SuppressWarnings("static-access")
	@Test
	public void test() {
		boolean check = chatClient.checkIP(ip);
		Assert.assertEquals(check, result);
	}
}
