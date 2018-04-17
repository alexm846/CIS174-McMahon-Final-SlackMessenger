package stu.amcmahon.slackmessenger;

/**
 * Channel Messenger GUI - Final project -- send messages to Slack channels
 * 
 * 
 * @author alexMcMahon
 *
 */

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChannelMessengerGUI {

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
	public ChannelMessengerGUI() {
		initialize();
		messengerFrame.setVisible(true);
	}
	public void getContent() {
		username = usernameField.getText();
		channel = (String) channelBox.getSelectedItem();
		message = messageArea.getText();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		messengerFrame = new JFrame();
		messengerFrame.setTitle("Slack Messenger");
		messengerFrame.setBounds(100, 100, 650, 503);
		messengerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		messengerFrame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Californian FB", Font.BOLD, 18));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(70, 11, 202, 40);
		messengerFrame.getContentPane().add(lblUsername);
		
		JLabel lblChannel = new JLabel("Channel");
		lblChannel.setFont(new Font("Californian FB", Font.BOLD, 18));
		lblChannel.setHorizontalAlignment(SwingConstants.CENTER);
		lblChannel.setBounds(322, 11, 302, 40);
		messengerFrame.getContentPane().add(lblChannel);
		
		usernameField = new JTextField();
		usernameField.setBounds(46, 48, 266, 33);
		messengerFrame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		channelBox = new JComboBox<String>();
		channelBox.setBounds(348, 48, 248, 33);
		messengerFrame.getContentPane().add(channelBox);
		channelBox.setEditable(true);
		channelBox.addItem("#computersaretalking");
		channelBox.addItem("#general");
		channelBox.addItem("#integration");
		channelBox.addItem("enter Channel Name");
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setFont(new Font("Californian FB", Font.BOLD, 18));
		lblMessage.setBounds(46, 105, 266, 40);
		messengerFrame.getContentPane().add(lblMessage);
		
		messageArea = new JTextArea();
		messageArea.setBounds(46, 144, 550, 241);
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
		btnSend.setBounds(473, 396, 120, 57);
		messengerFrame.getContentPane().add(btnSend);
		
		JButton attachmentButton = new JButton("Add Attachment");
		attachmentButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		attachmentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
				
			}
		});
		attachmentButton.setBounds(66, 396, 137, 33);
		messengerFrame.getContentPane().add(attachmentButton);
	}
}