package stu.amcmahon.slackmessenger;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.gpedro.integrations.slack.SlackAttachment;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setBounds(100, 100, 450, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescription.setBounds(26, 11, 98, 34);
		contentPane.add(lblDescription);
		
		fallbackField = new JTextField();
		fallbackField.setBounds(10, 41, 414, 26);
		contentPane.add(fallbackField);
		fallbackField.setColumns(10);
		
		JLabel lblAttachmentText = new JLabel("Attachment Text");
		lblAttachmentText.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAttachmentText.setBounds(26, 77, 209, 34);
		contentPane.add(lblAttachmentText);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 110, 414, 26);
		contentPane.add(textField);
		
		lblTitle = new JLabel("Attachment Title");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitle.setBounds(26, 144, 209, 34);
		contentPane.add(lblTitle);
		
		titleField = new JTextField();
		titleField.setColumns(10);
		titleField.setBounds(10, 180, 414, 26);
		contentPane.add(titleField);
		
		lblTitleLink = new JLabel("Title URL Link (full qualified address)");
		lblTitleLink.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitleLink.setBounds(26, 217, 271, 34);
		contentPane.add(lblTitleLink);
		
		lblImageUrlfull = new JLabel("Image URL (full qualified address)");
		lblImageUrlfull.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblImageUrlfull.setBounds(26, 293, 233, 34);
		contentPane.add(lblImageUrlfull);
		
		titleURL = new JTextField();
		titleURL.setColumns(10);
		titleURL.setBounds(10, 256, 414, 26);
		contentPane.add(titleURL);
		
		imageURL = new JTextField();
		imageURL.setColumns(10);
		imageURL.setBounds(10, 326, 414, 26);
		contentPane.add(imageURL);
		
		btnNewButton = new JButton("Attach");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContent();
				setVisible(false);
				
				attachment.setFallback(fallback);
				attachment.setText(text);
				attachment.setTitle(title);
				attachment.setTitleLink(titleLink);
				attachment.setImageUrl(imgURL);
				System.out.println(attachment);
				enableAttachment = true;
				
			}
		});
		btnNewButton.setBounds(146, 373, 129, 39);
		contentPane.add(btnNewButton);
	}
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
