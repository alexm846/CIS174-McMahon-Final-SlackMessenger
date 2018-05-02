package stu.amcmahon.slackmessenger;
/**
 * Direct Messenger GUI - send direct messages to Slack users
 * 
 * @author alexMcMahon
 *
 */

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class DirectMessengerGUI {


	private JFrame messengerFrame;	
	private JTextField usernameField;
	private JComboBox<String> channelBox;
	private JTextArea messageArea;
	private String username;
	private String channel;
	private String message;
	AttachmentFrame frame = new AttachmentFrame();

	
	public static void main(String[] args) {
		
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public DirectMessengerGUI() {
		initialize();
		messengerFrame.setVisible(true);
	}
	public void getContent() {
		username = usernameField.getText();
		channel = (String) channelBox.getSelectedItem();
		message = messageArea.getText();
		channel = map(channel);
	}
	//preload user channels into hashmap
	public String map( String channel) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Alex McMahon", "D8XLXEANM");
		map.put("Michael Bourgeois", "D8ZSGE25Q");
		map.put("Richard Yorke", "DA71CAAQM");
		map.put("Aaron", "DA81XNGQN");
		map.put("Ben", "DA6TQMYCU");
		map.put("Daniel", "DA6JGA2L9");
		map.put("Mami Muratake", "DA724A73M");
		map.put("Vince", "DA6JGH7T3");
		map.put("Kyle O'Brian", "DA6TR7D2Q");
		
		channel = map.get(channel);

		return channel;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		messengerFrame = new JFrame();
		messengerFrame.setTitle("Direct Messenger");
		messengerFrame.setBounds(100, 100, 650, 398);
		messengerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		messengerFrame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Californian FB", Font.BOLD, 18));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(70, 11, 202, 40);
		messengerFrame.getContentPane().add(lblUsername);
		
		JLabel lblChannel = new JLabel("Send to user:");
		lblChannel.setFont(new Font("Californian FB", Font.BOLD, 18));
		lblChannel.setHorizontalAlignment(SwingConstants.CENTER);
		lblChannel.setBounds(322, 11, 302, 40);
		messengerFrame.getContentPane().add(lblChannel);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		usernameField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		usernameField.setBounds(46, 48, 266, 33);
		messengerFrame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		channelBox = new JComboBox<String>();
		channelBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		channelBox.setBounds(348, 48, 248, 33);
		messengerFrame.getContentPane().add(channelBox);
		channelBox.setEditable(false);
		channelBox.addItem("Alex McMahon");
		channelBox.addItem("Michael Bourgeois");
		channelBox.addItem("Richard Yorke");
		channelBox.addItem("Aaron");
		channelBox.addItem("Ben");
		channelBox.addItem("Daniel");
		channelBox.addItem("Mami Muratake");
		channelBox.addItem("Vince");
		channelBox.addItem("Kyle O'Brian");
		
		JLabel lblMessage = new JLabel("Message:");
		lblMessage.setFont(new Font("Californian FB", Font.BOLD, 18));
		lblMessage.setBounds(46, 105, 266, 40);
		messengerFrame.getContentPane().add(lblMessage);
		
		messageArea = new JTextArea();
		messageArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		messageArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		messageArea.setBounds(46, 144, 550, 110);
		messengerFrame.getContentPane().add(messageArea);
		//JButton: Inheritance & interface! extends AbstractButton & implements accessible
		JButton btnSend = new JButton("Send"); 
		btnSend.addActionListener(new ActionListener() { //What? ActionListener is an interface!
			public void actionPerformed(ActionEvent arg0) {//interface method
				getContent(); //get input text
				messengerFrame.setVisible(false);
				System.out.println("Success: Username: " + username + "  Channel: " +channel + "  Message: " 
				+ message + ".\n");
				SlackMessengerApp go = new SlackMessengerApp();
				if(frame.enableAttachment == true) {//check for attachment
					go.sendMessageWithAttachment(username, channel, message, frame.getAttachment());
				}
				else {
				    go.sendMessage(username, channel, message); //send message thru SlackService
				}
			}
		});
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSend.setBounds(476, 279, 120, 57);
		messengerFrame.getContentPane().add(btnSend);
		
		JButton attachmentButton = new JButton("Add Attachment");
		attachmentButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		attachmentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
				
			}
		});
		attachmentButton.setBounds(46, 265, 140, 33);
		messengerFrame.getContentPane().add(attachmentButton);
	}
}
