package ch.masters.chatmasters.actionlistener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.swing.JTextField;

import ch.masters.chatmasters.model.Message;
import ch.masters.chatmasters.model.User;
import ch.masters.chatmasters.rmi.ChatInterface;

/**
 * This is an ActionListener class for send 
 * a Message from the chat. It contains an 
 * KeyListener on Enter. 
 * @author Chiramed Phong Penglerd, Luca Marti, Elia Perenzin
 * @version 1.0
 * ChatMasters 2016
 */
public class SendListener implements KeyListener {

	//Instanzvariablen
	private Message message;
	private JTextField textPane;
	private ChatInterface server;

	/**
	 * Constructor
	 * @param server
	 * @param message
	 * @param user
	 */
	public SendListener(ChatInterface server, JTextField message, User user, Timestamp timestamp) {
		this.server = server;
		this.message = new Message();
		this.message.setTime(timestamp);
		this.message.setSender(user);
		this.textPane = message; 
		this.message.setTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!this.textPane.getText().isEmpty()) {
				this.message.setMsg(this.textPane.getText());
				this.textPane.setText("");
				try {
					server.send(this.message);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
