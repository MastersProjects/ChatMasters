package ch.masters.chatmasters.thread;

import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import ch.masters.chatmasters.model.Message;
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
	
	/**
	 * Constructor
	 * @param chat
	 * @param server
	 */
	public LoadMessagesThread(JTextPane chat, ChatInterface server) {
		this.chat = chat;
		this.server = server;
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
						     document.insertString(document.getLength(), msgarray.get(i).getMsg() + System.lineSeparator(), null);
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
