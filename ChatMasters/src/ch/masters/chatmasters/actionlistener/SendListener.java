package ch.masters.chatmasters.actionlistener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;

import javax.swing.JTextField;
import javax.swing.JTextPane;

import ch.masters.chatmasters.model.Message;
import ch.masters.chatmasters.model.User;
import ch.masters.chatmasters.rmi.ChatInterface;

public class SendListener implements KeyListener {

	private Message message;
	private JTextField textPane;
	private ChatInterface server;

	public SendListener(ChatInterface server, JTextField message, User user) {
		this.server = server;
		this.message = new Message();
		this.message.setSender(user);
		this.textPane = message;
		// message.setTime(new Timestamp());
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
