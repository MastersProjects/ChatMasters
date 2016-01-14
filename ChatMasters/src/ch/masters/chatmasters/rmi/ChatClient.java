package ch.masters.chatmasters.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ch.masters.chatmasters.gui.Chat;
import ch.masters.chatmasters.model.User;

public class ChatClient {
	private static ChatInterface server;
	private static User user;
	private static ChatClient instance = null;
	
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
	
	public static void main (String[] args){
		new ChatClient(JOptionPane.showInputDialog(null, "Gib deinen Chatnamen ein!", "Willkommen!", JOptionPane.INFORMATION_MESSAGE));
		try {
			server = ChatClient.getServer();
			user.setOnline(true);
			user.setOnlineTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			server.setClient(user);
			
			Chat chat = new Chat(server, user);
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			JOptionPane.showMessageDialog(null, "Server not found!", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	static ChatInterface getServer() throws MalformedURLException, RemoteException, NotBoundException {
		ChatInterface server = (ChatInterface) Naming.lookup("rmi://localhost:1257/RmiChat");
		return server;
	}
}
