package ch.masters.chatmasters.thread;

import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JTextPane;

import ch.masters.chatmasters.model.Message;
import ch.masters.chatmasters.rmi.ChatInterface;

public class LoadMessagesThread {
	private List<Message> msgarray;
	private JTextPane chat; 
	private ChatInterface server;
	
	public LoadMessagesThread(JTextPane chat, ChatInterface server) {
		this.chat = chat;
		this.server = server;
		loadMessages();
	}
	
	private void loadMessages(){
		new Thread(new Runnable() {
			  public void run() {
			    while (true) {
			    	try {
						msgarray = server.returnMessages();
						chat.setText(msgarray.toString());
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			      try {
			    	  Thread.sleep(250);
			    	 } catch (InterruptedException e) {
			    		 e.printStackTrace();
			    	 }
			    }
			  }}).start();
	}
	
	

}
