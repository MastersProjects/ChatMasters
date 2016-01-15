package ch.masters.chatmasters.thread;

import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import ch.masters.chatmasters.model.Message;
import ch.masters.chatmasters.model.User;
import ch.masters.chatmasters.rmi.ChatInterface;

/**
 * Thread for loading the messages from the Server
 * @author Chiramed Phong Penglerd, Luca Marti, Elia Perenzin
 * @version 1.0
 * ChatMasters 2016
 */
public class LoadMessagesThread {
	
	//Instanzvariablen
	private List<Message> msgarray;
	private JTextPane chat; 
	private ChatInterface server;
	private User user;
	
	/**
	 * Constructor
	 * @param chat
	 * @param server
	 */
	public LoadMessagesThread(JTextPane chat, ChatInterface server, User user) {
		this.chat = chat;
		this.server = server;
		this.user = user;
		loadMessages();
	}
	
	
	/**
	 * Thread which loads the messages from the server
	 * sleeps 250ms
	 */
	private void loadMessages(){
		new Thread(new Runnable() {
			  public void run() {
			    while (true) {
			    	try {
						msgarray = server.returnMessages();
						chat.setText("");
						for(int i=0; i<msgarray.size(); i++){
							StyledDocument document = (StyledDocument) chat.getDocument();
							String message = msgarray.get(i).getTime().getHours() + ":" + msgarray.get(i).getTime().getMinutes() + " " + msgarray.get(i).getSender().getName() + " | " + msgarray.get(i).getMsg();
							document.insertString(document.getLength(), message + System.lineSeparator(), null);

						}
					} catch (RemoteException | BadLocationException e1) {
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
