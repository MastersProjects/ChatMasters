package ch.masters.chatmasters.thread;

import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import ch.masters.chatmasters.model.User;
import ch.masters.chatmasters.rmi.ChatInterface;

/**
 * Interface with all Methodes that are used for the chat
 * @author Chiramed Phong Penglerd, Luca Marti, Elia Perenzin
 * @version 1.0 ChatMasters 2016
 */
public class LoadOnlineThread {

	// Instanzvariablen
	private List<User> userarray;
	private JTextPane online;
	private ChatInterface server;

	/**
	 * Consturctor
	 * @param online
	 * @param server
	 */
	public LoadOnlineThread(JTextPane online, ChatInterface server) {
		this.online = online;
		this.server = server;
		loadUsers();
	}

	/**
	 * Thread which loads all online users from the server Sleeps 500ms
	 */
	private void loadUsers() {
		new Thread(new Runnable() {
			public void run() {
				usersThred();
			}
		}).start();
	}

	/**
	 * Contains the implementation of the Thread
	 */
	public void usersThred() {
		while (true) {
			try {
				userarray = server.returnClients();
				online.setText("");
				for (int i = 0; i < userarray.size(); i++) {
					StyledDocument document = (StyledDocument) online.getDocument();
					document.insertString(document.getLength(), userarray.get(i).getName() + System.lineSeparator(),
							null);
				}
			} catch (RemoteException | BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
