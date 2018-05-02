package stu.amcmahon.slackmessenger;
/**
 * Controller: Swing app that lets user choose which type of Slack message to send -- or to which game to
 *  play.  Utilizes Card layout; buttons for Slack; radio selection for games.
 *
 * @author alexjmcmahon
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import stu.amcmahon.games.ConnectFour;
import stu.amcmahon.games.MoveABall;

import javax.swing.JSeparator;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;

public class Controller implements ActionListener {

	private JFrame frame;
	private CardLayout myCardLayout = new CardLayout(0, 0);
	final static String SLACKPANEL = "Card with Slack Messenger";
	final static String GAMEPANEL = "Card with Games";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller window = new Controller();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Controller() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 384);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(myCardLayout);
		
		JPanel slackCard = new JPanel();
		JPanel gameCard = new JPanel();
		frame.getContentPane().add(slackCard, SLACKPANEL);
		slackCard.setLayout(null);
		
		JLabel label = new JLabel("Welcome to the Slack Messenger!");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setBounds(23, 11, 385, 42);
		slackCard.add(label);
		
		JLabel label_1 = new JLabel("What kind of message would you like to send?");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(33, 64, 375, 36);
		slackCard.add(label_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(73, 129, 292, 2);
		slackCard.add(separator);
		
		JButton button = new JButton("Channel Message");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChannelMessengerGUI go = new ChannelMessengerGUI();
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setBounds(48, 159, 145, 67);
		slackCard.add(button);
		
		JButton button_1 = new JButton("Private Message");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DirectMessengerGUI go = new DirectMessengerGUI();
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_1.setBounds(244, 159, 145, 67);
		slackCard.add(button_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(73, 258, 292, 2);
		slackCard.add(separator_1);
		
		JButton btnPlayAGame = new JButton("I Want to Play a Game Instead!");
		btnPlayAGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myCardLayout.next(frame.getContentPane());//get next card
			}
		});
		btnPlayAGame.setBounds(48, 304, 341, 23);
		slackCard.add(btnPlayAGame);
		frame.getContentPane().add(gameCard, GAMEPANEL);
		gameCard.setLayout(null);
		//add radio buttons
		JRadioButton rdbtnMoveABall = new JRadioButton("Move-A-Ball");
		rdbtnMoveABall.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdbtnMoveABall.setBounds(29, 85, 367, 52);
		gameCard.add(rdbtnMoveABall);
		rdbtnMoveABall.setActionCommand("Move-A-Ball");
		rdbtnMoveABall.addActionListener(this);
		
		JRadioButton rdbtnConnectFour = new JRadioButton("Connect Four");
		rdbtnConnectFour.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdbtnConnectFour.setBounds(29, 152, 182, 52);
		gameCard.add(rdbtnConnectFour);
		rdbtnConnectFour.setActionCommand("ConnectFour");
		rdbtnConnectFour.addActionListener(this);
		//add to button group
		ButtonGroup group = new ButtonGroup();
	    group.add(rdbtnMoveABall);
	    group.add(rdbtnConnectFour);
		
		JButton playButton = new JButton("PLAY");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String game = group.getSelection().getActionCommand();
					//check to see which is selected
					if (game == "Move-A-Ball") {
						
						MoveABall go = new MoveABall();
					}
					if (game == "ConnectFour") {
						
						ConnectFour go = new ConnectFour();
					}
				}
				catch (NullPointerException i) {	
				}
			}
		});
		playButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		playButton.setBounds(169, 241, 117, 66);
		gameCard.add(playButton);
		
		JLabel lblChooseAGame = new JLabel("Choose a Game:");
		lblChooseAGame.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblChooseAGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseAGame.setBounds(29, 22, 367, 29);
		gameCard.add(lblChooseAGame);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(29, 62, 367, 2);
		gameCard.add(separator_2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		e.getActionCommand();
	}
}
