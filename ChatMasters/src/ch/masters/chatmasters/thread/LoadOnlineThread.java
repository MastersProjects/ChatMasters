package ch.masters.chatmasters.thread;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTextPane;

import ch.masters.chatmasters.model.User;
import ch.masters.chatmasters.rmi.ChatInterface;

public class LoadOnlineThread {

	private List<User> userarray;
	private JTextPane online; 
	private ChatInterface server;
	
	public LoadOnlineThread(JTextPane online, ChatInterface server) {
		this.online = online;
		this.server = server;
		loadUsers();
	}
	
	private void loadUsers(){
	new Thread(new Runnable() {
		  public void run() {
		    while (true) {
		    	try {
					userarray = server.returnClients();
					for (Iterator<User> iterator = userarray.iterator(); iterator.hasNext();) {
						User user = iterator.next();
						online.setText(user.getName());
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		      try {
		    	  Thread.sleep(500);
		    	 } catch (InterruptedException e) {
		    		 e.printStackTrace();
		    	 }
		    }
		  }}).start();
	}
}
