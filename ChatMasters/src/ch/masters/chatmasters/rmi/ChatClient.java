package ch.masters.chatmasters.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.swing.JOptionPane;

import ch.masters.chatmasters.gui.Chat;
import ch.masters.chatmasters.model.User;

public class ChatClient {
	private static ChatInterface server;
	private static User user;

	private ChatClient(String name) {
		user = new User(name, false, null);
		ChatClient.setUser(user);
	}

	public static User getUser() {
		return ChatClient.user;
	}

	public static void setUser(User user) {
		ChatClient.user = user;
	}

	public static void main(String[] args) {
		String[] buttons = { "Cancel", "Change IP", "Join Server" };

		int rc = JOptionPane.showOptionDialog(null, "Welcome to ChatMasters", "Welcome",
				JOptionPane.INFORMATION_MESSAGE, 1, null, buttons, buttons[2]);
		if (rc == 1) {
			String ip = JOptionPane.showInputDialog(null, "Enter Server IP", "Change IP",
					JOptionPane.INFORMATION_MESSAGE);
			connectServer(ip);
		} else if (rc == 2) {
			connectServer("localhost");
		}
	}

	private static void connectServer(String ip) {
		new ChatClient(
				JOptionPane.showInputDialog(null, "Enter your Username!", "Welcome!", JOptionPane.INFORMATION_MESSAGE));
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
				e.printStackTrace();
			}
		}
	}

	static ChatInterface getServer(String ip) throws MalformedURLException, RemoteException, NotBoundException {
		ChatInterface server = (ChatInterface) Naming.lookup("rmi://" + ip + ":1257/RmiChat");
		return server;
	}
}
