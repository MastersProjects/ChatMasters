package ch.masters.chatmasters.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import ch.masters.chatmasters.gui.Chat;
import ch.masters.chatmasters.model.User;

/**
 * Chat Client contains Main methode for the chat.
 * @author Chiramed Phong Penglerd, Luca Marti, Elia Perenzin
 * @version 1.0 ChatMasters 2016
 */
public class ChatClient {

	// Instanzvariablen
	private static ChatInterface server;
	private static User user;

	/**
	 * Constuctor
	 * @param name
	 */
	public ChatClient(String name) {
		user = new User(name, false, null);
		ChatClient.setUser(user);
	}

	public static void main(String[] args) {
		String[] buttons = { "Cancel", "Change IP", "Join Server" };

		int rc = JOptionPane.showOptionDialog(null, "Welcome to ChatMasters", "Welcome",
				JOptionPane.INFORMATION_MESSAGE, 1, null, buttons, buttons[2]);
		if (rc == 1) {
			String ip = JOptionPane.showInputDialog(null, "Enter Server IP", "Change IP",
					JOptionPane.INFORMATION_MESSAGE);
			if (checkIP(ip)) {
				connectServer(ip);
			} else {
				JOptionPane.showMessageDialog(null, "Invalid IP address", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (rc == 2) {
			connectServer("localhost");
		}
	}

	/**
	 * Tries to connect to the Server if successful creates new GUI.
	 * @param ip
	 */
	public static void connectServer(String ip) {
		new ChatClient(
				JOptionPane.showInputDialog(null, "Enter your Username!", "Welcome!", JOptionPane.INFORMATION_MESSAGE));
		// Check if user isn't already joined
		if (ChatClient.getUser().getName().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Username can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				server = ChatClient.getServer(ip);
				user.setOnline(true);
				user.setOnlineTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				server.setClient(user);
				new Chat(server, user);

			} catch (MalformedURLException | RemoteException | NotBoundException e) {
				JOptionPane.showMessageDialog(null, "Server not found!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Gets the Server Interface for communication between Client and Server
	 * @param ip
	 * @return ChatInterface
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	static ChatInterface getServer(String ip) throws MalformedURLException, RemoteException, NotBoundException {
		ChatInterface server = (ChatInterface) Naming.lookup("rmi://" + ip + ":1257/RmiChat");
		return server;
	}

	/**
	 * checks if an ip has an valid formatt
	 * @param ip
	 * @return boolean
	 */
	public static Boolean checkIP(String ip) {
		final String IPADDRESS_PATTERN = "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
				+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

		Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
		if (pattern.matcher(ip).matches()) {
			return true;
		} else {
			return false;
		}
	}

	// Getter and Setter
	public static User getUser() {
		return ChatClient.user;
	}

	public static void setUser(User user) {
		ChatClient.user = user;
	}
}
