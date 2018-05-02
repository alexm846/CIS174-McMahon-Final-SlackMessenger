package stu.amcmahon.slackmessenger;

/**
 * AttachmentFrame: Swing app that lets user specify attachment information.
 * 
 * @author alexjmcmahon
 */


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.gpedro.integrations.slack.SlackAttachment;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

public class AttachmentFrame extends JFrame {

	private JPanel contentPane;
	private JTextField fallbackField;
	private JTextField textField;
	private JLabel lblTitle;
	private JTextField titleField;
	private JLabel lblTitleLink;
	private JLabel lblImageUrlfull;
	private JTextField titleURL;
	private JTextField imageURL;
	private JButton btnNewButton;
	
	private String fallback;
	private String text;
	private String title;
	private String titleLink;
	private String imgURL;
	SlackAttachment attachment = new SlackAttachment();
	public boolean enableAttachment = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AttachmentFrame frame = new AttachmentFrame();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AttachmentFrame() {
		
		setTitle("Attachments");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 435, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescription.setBounds(26, 11, 98, 34);
		contentPane.add(lblDescription);
		
		fallbackField = new JTextField();
		fallbackField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		fallbackField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fallbackField.setBounds(113, 17, 271, 26);
		contentPane.add(fallbackField);
		fallbackField.setColumns(10);
		
		JLabel lblAttachmentText = new JLabel("Attachment Text:");
		lblAttachmentText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAttachmentText.setBounds(26, 98, 138, 34);
		contentPane.add(lblAttachmentText);
		
		textField = new JTextField();
		textField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(26, 135, 358, 26);
		contentPane.add(textField);
		
		lblTitle = new JLabel("Attachment Title:");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitle.setBounds(26, 56, 129, 34);
		contentPane.add(lblTitle);
		
		titleField = new JTextField();
		titleField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		titleField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		titleField.setColumns(10);
		titleField.setBounds(146, 61, 238, 26);
		contentPane.add(titleField);
		
		lblTitleLink = new JLabel("Title URL Link (full qualified address):");
		lblTitleLink.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitleLink.setBounds(26, 172, 271, 34);
		contentPane.add(lblTitleLink);
		
		lblImageUrlfull = new JLabel("Image URL (full qualified address):");
		lblImageUrlfull.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblImageUrlfull.setBounds(26, 243, 264, 34);
		contentPane.add(lblImageUrlfull);
		
		titleURL = new JTextField();
		titleURL.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		titleURL.setFont(new Font("Tahoma", Font.PLAIN, 12));
		titleURL.setColumns(10);
		titleURL.setBounds(26, 206, 358, 26);
		contentPane.add(titleURL);
		
		imageURL = new JTextField();
		imageURL.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		imageURL.setFont(new Font("Tahoma", Font.PLAIN, 12));
		imageURL.setColumns(10);
		imageURL.setBounds(26, 278, 358, 26);
		contentPane.add(imageURL);
		
		btnNewButton = new JButton("Attach");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContent();
				setVisible(false);
				//set Slack attachment information
				attachment.setFallback(fallback);
				attachment.setText(text);
				attachment.setTitle(title);
				attachment.setTitleLink(titleLink);
				attachment.setImageUrl(imgURL);
				System.out.println(attachment);
				enableAttachment = true; //boolean to alert messenger of attachment
			}
		});
		btnNewButton.setBounds(146, 325, 129, 39);
		contentPane.add(btnNewButton);
	}
	//method to return Attachment object to messenger app
	public SlackAttachment getAttachment() {
		return attachment;
	}
	public void getContent() {
		fallback = fallbackField.getText();
		text = textField.getText();
		title = titleField.getText();
		titleLink = titleURL.getText();
		imgURL = imageURL.getText();
	}
	
}
