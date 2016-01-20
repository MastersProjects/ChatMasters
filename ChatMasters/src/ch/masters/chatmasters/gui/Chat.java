package ch.masters.chatmasters.gui;


import java.awt.GridLayout;
import java.awt.Point;
import java.sql.Timestamp;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import ch.masters.chatmasters.actionlistener.LogoutListener;
import ch.masters.chatmasters.actionlistener.SendListener;
import ch.masters.chatmasters.model.User;
import ch.masters.chatmasters.rmi.ChatInterface;
import ch.masters.chatmasters.thread.LoadMessagesThread;
import ch.masters.chatmasters.thread.LoadOnlineThread;

/**
 * GUI Class for the Chat
 * @author Chiramed Phong Penglerd, Luca Marti, Elia Perenzin
 * @version 1.0
 * ChatMasters 2016
 */
public class Chat extends JFrame {

	//Instanzvariablen
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField message;
	private JLabel lblWelcome;
	private ChatInterface server;
	private Timestamp timestamp;
	private User user;

	/**
	 * Constructor
	 * @param server
	 * @param user
	 */
	public Chat(ChatInterface server, User user) {
		this.server = server;
		this.user = user; 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 278);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		
		lblWelcome = new JLabel("Welcome " + user.getName());
		lblWelcome.setBounds(10, 11, 309, 14);
		contentPane.add(lblWelcome);
		
		JTextPane online_textArea = new JTextPane();
		online_textArea.setEditable(false);
		online_textArea.setBounds(329, 36, 95, 154);
		contentPane.add(online_textArea);
		
		new LoadOnlineThread(online_textArea, server);
		
		JLabel lblOnline = new JLabel("Online");
		lblOnline.setBounds(329, 11, 46, 14);
		contentPane.add(lblOnline);
		
		JTextPane chat = new JTextPane();
		chat.setEditable(false);
		JScrollPane scrollpane = new JScrollPane(chat, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setBounds(10, 36, 309, 125);

		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollpane);
		
		new LoadMessagesThread(chat, server, user, scrollpane);
		
		message = new JTextField();
		message.addKeyListener(new SendListener(this.server, message, this.user, this.timestamp));
		JScrollPane scrollMessagePane = new JScrollPane(message,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollMessagePane.setBounds(10, 172, 309, 52);
		contentPane.add(scrollMessagePane);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(329, 201, 95, 23);
		btnLogout.addActionListener(new LogoutListener(this.server, this.user));
		contentPane.add(btnLogout);
		
		addWindowListener(new LogoutListener(this.server, this.user));
		setVisible(true);
	}
}
