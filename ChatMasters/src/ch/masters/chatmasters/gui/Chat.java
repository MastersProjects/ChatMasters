package ch.masters.chatmasters.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import ch.masters.chatmasters.actionlistener.LogoutListener;
import ch.masters.chatmasters.actionlistener.SendListener;
import ch.masters.chatmasters.model.User;
import ch.masters.chatmasters.rmi.ChatInterface;

public class Chat extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField message;
	private JLabel lblWelcome;
	private ChatInterface server;
	private User user;

	public Chat(ChatInterface server, User user) {
		this.server = server;
		this.user = user; 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 278);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblWelcome = new JLabel("Welcome " + user.getName());
		lblWelcome.setBounds(10, 11, 309, 14);
		contentPane.add(lblWelcome);
		
		JTextPane online_textArea = new JTextPane();
		online_textArea.setBounds(329, 36, 95, 154);
		contentPane.add(online_textArea);
		
		JLabel lblOnline = new JLabel("Online");
		lblOnline.setBounds(329, 11, 46, 14);
		contentPane.add(lblOnline);
		
		JTextPane chat = new JTextPane();
		chat.setBounds(10, 36, 309, 125);
		chat.setEditable(false);
		contentPane.add(chat);
		
		message = new JTextField();
		message.setBounds(10, 172, 309, 52);
		message.addKeyListener(new SendListener(this.server, message, this.user));
		contentPane.add(message);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(329, 201, 95, 23);
		btnLogout.addActionListener(new LogoutListener(this.server, this.user));
		contentPane.add(btnLogout);
		
		addWindowListener(new LogoutListener(this.server, this.user));
		setVisible(true);
	}
}
