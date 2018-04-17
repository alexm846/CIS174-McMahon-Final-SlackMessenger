package stu.amcmahon.slackmessenger;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ControllerGUI {

	private JFrame controllerFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControllerGUI window = new ControllerGUI();
					//window.controllerFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ControllerGUI() {
		initialize();
		controllerFrame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		controllerFrame = new JFrame();
		controllerFrame.setTitle("Slack Messenger");
		controllerFrame.setBounds(100, 100, 450, 300);
		controllerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		controllerFrame.getContentPane().setLayout(null);
		
		JLabel lblWelcomeTo = new JLabel("Welcome to the Slack Messenger!");
		lblWelcomeTo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblWelcomeTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeTo.setBounds(20, 11, 385, 42);
		controllerFrame.getContentPane().add(lblWelcomeTo);
		
		JLabel lblWhatKindOf = new JLabel("What kind of message would you like to send?");
		lblWhatKindOf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblWhatKindOf.setHorizontalAlignment(SwingConstants.CENTER);
		lblWhatKindOf.setBounds(30, 64, 375, 36);
		controllerFrame.getContentPane().add(lblWhatKindOf);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(75, 126, 292, 2);
		controllerFrame.getContentPane().add(separator);
		
		JButton channelButton = new JButton("Channel Message");
		channelButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		channelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChannelMessengerGUI go = new ChannelMessengerGUI();
				
			}
		});
		channelButton.setBounds(60, 166, 145, 48);
		controllerFrame.getContentPane().add(channelButton);
		
		JButton privateButton = new JButton("Private Message");
		privateButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		privateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DirectMessengerGUI go = new DirectMessengerGUI();
			}
		});
		privateButton.setBounds(239, 166, 145, 48);
		controllerFrame.getContentPane().add(privateButton);
	}
}
