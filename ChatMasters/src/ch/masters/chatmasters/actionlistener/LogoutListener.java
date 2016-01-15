package ch.masters.chatmasters.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;

import ch.masters.chatmasters.model.User;
import ch.masters.chatmasters.rmi.ChatInterface;

/**
 * This is an ActionListener class for Logout 
 * a user from the chat. It contains an 
 * ActionListener and WindowListener.
 * @author Chiramed Phong Penglerd, Luca Marti, Elia Perenzin
 * @version 1.0
 * ChatMasters 2016
 */
public class LogoutListener implements ActionListener, WindowListener{
	
	//Instanzvariablen
	private ChatInterface server;
	private User user;
	
	/**
	 * Constructor 
	 * @param server
	 * @param user
	 */
	public LogoutListener(ChatInterface server, User user){
		this.user = user;
		this.server = server;
	}

	/**
	 * Function for logout calls the 
	 * Methode rmvClint on Server
	 */
	private void logout(){
		try {
			server.rmvClient(user);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.exit(0);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		logout();
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		logout();
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
