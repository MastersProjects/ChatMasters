package ch.masters.chatmasters.thread;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
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
	private ArrayList<Message> serverMessageList;
	private ArrayList<Message> clientMessageList;
	private JTextPane chat; 
	private ChatInterface server;
	private User user;
	private JScrollPane scrollpane;
	
	/**
	 * Constructor
	 * @param chat
	 * @param server
	 */
	public LoadMessagesThread(JTextPane chat, ChatInterface server, User user, JScrollPane scrollpane) {
		this.chat = chat;
		this.server = server;
		this.user = user;
		this.scrollpane = scrollpane;
		
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
			    		serverMessageList = server.returnMessages();
			    		clientMessageList = user.getMessageList();
			    		
			    		int difference = serverMessageList.size() - clientMessageList.size();			    		
			    		int end = serverMessageList.size() - 1;
			    		
			    		for(int start = serverMessageList.size()-difference; start<=end; start++){
			    			SimpleAttributeSet styleAttribute = new SimpleAttributeSet();
			    			StyledDocument document = (StyledDocument) chat.getDocument();
			    			String message;		  
			    			
			    			if (serverMessageList.get(start).getSender().getName().equals(user.getName())){
			    				message = serverMessageList.get(start).getMsg() + " | " + serverMessageList.get(start).getSender().getName() + " " + serverMessageList.get(start).getTime().getHours() + ":" + serverMessageList.get(start).getTime().getMinutes();
			    				StyleConstants.setAlignment(styleAttribute, StyleConstants.ALIGN_RIGHT);
			    				document.setParagraphAttributes(document.getLength()+1, 1, styleAttribute, false);
			    			} else {
			    				message = serverMessageList.get(start).getTime().getHours() + ":" + serverMessageList.get(start).getTime().getMinutes() + " " + serverMessageList.get(start).getSender().getName() + " | " + serverMessageList.get(start).getMsg();
			    				StyleConstants.setAlignment(styleAttribute, StyleConstants.ALIGN_LEFT);
			    				document.setParagraphAttributes(document.getLength()+1, 1, styleAttribute, false);
			    			}
			    			
			    			document.insertString(document.getLength(), message + System.lineSeparator(), null);
			    			scrollpane.getVerticalScrollBar().setValue(scrollpane.getVerticalScrollBar().getMaximum());
			    		}
			    		
			    		user.setMessageList(serverMessageList);
			    		
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
