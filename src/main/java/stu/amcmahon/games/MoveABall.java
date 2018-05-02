package stu.amcmahon.games;

import java.awt.*;       // Using AWT's Graphics and Color
import java.awt.event.*; // Using AWT's event classes and listener interface
import java.util.Random;

import javax.swing.*;    // Using Swing's components and containers
/**
 * @author https://www.ntu.edu.sg/home/ehchua/programming/java/J4b_CustomGraphics.html
 * @author alexjmcmahon
 */
@SuppressWarnings("serial")
public class MoveABall extends JFrame {
   // Define constants for the various dimensions
   public static final int CANVAS_WIDTH = 1800;
   public static final int CANVAS_HEIGHT = 700;
   public static final Color CANVAS_BACKGROUND = Color.GREEN;
   public static Color circleColor = Color.BLACK;
   private static final int UPDATE_PERIOD = 50; // milliseconds
   
 
   // The moving line from (x1, y1) to (x2, y2), initially position at the center
   private int x1 = 100;
   private int y1 = CANVAS_HEIGHT - 300;
   private int x2 = 200;
   private int y2 = 200;
   private int xSpeed = 10, ySpeed = 10;
   int count = 0;
   
   Random rand = new Random();
 
   private DrawCanvas canvas; // The custom drawing canvas (an inner class extends JPanel)
 
   // Constructor to set up the GUI components and event handlers
   public MoveABall() {
      // Set up a panel for the buttons
      JPanel btnPanel = new JPanel(new FlowLayout());
      //adds button to change color of ball
      JButton btnColor = new JButton("Change Color ");
      btnPanel.add(btnColor);
      btnColor.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
 
        	switch(count) {
        	case 0: circleColor = Color.BLUE;
        			break;
        	case 1: circleColor = Color.ORANGE;
    				break;
        	case 2: circleColor = Color.YELLOW;
    				break;
        	case 3: circleColor = Color.WHITE;
    				break;
        	case 4: circleColor = Color.CYAN;
    				break;
        	case 5: circleColor = Color.RED;
    				break;
        	}
        	count++;
        	if(count == 6) {
        		count = 0;
        	}
            canvas.repaint();
            requestFocus(); // change the focus to JFrame to receive KeyEvent
         }
      });
 
      // Set up a custom drawing JPanel
      canvas = new DrawCanvas();
      canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
 
      // Add both panels to this JFrame's content-pane
      Container cp = getContentPane();
      cp.setLayout(new BorderLayout());
      cp.add(canvas, BorderLayout.CENTER);
      cp.add(btnPanel, BorderLayout.SOUTH);
 
      // "super" JFrame fires KeyEvent
      //use arrow keys to move ball
      addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent evt) {
            switch(evt.getKeyCode()) {
               case KeyEvent.VK_LEFT:
                  x1 -= 10;
                  
                  repaint();
                  break;
               case KeyEvent.VK_RIGHT:
                  x1 += 10;
                  
                  repaint();
                  break;
               case KeyEvent.VK_UP:
                   y1 -= 10;
                   
                   repaint();
                   break;
               case KeyEvent.VK_DOWN:
                   y1 += 10;
                   
                   repaint();
                   break;
                  
            }
         }
      });
      //continuous ball movement -- changes direction at border
      ActionListener autoMove = new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evt) {
        	 x1 += xSpeed;
             y1 += ySpeed;
             if (x1 > CANVAS_WIDTH - 700 || x1 < 0) {
                xSpeed = -xSpeed;
             }
             if (y1 > CANVAS_HEIGHT - 200 || y1 < 0) {
                ySpeed = -ySpeed;
             }
             repaint(); 
          }
       };
       
  
      addKeyListener(new KeyAdapter() {
          @Override
          public void keyPressed(KeyEvent evt) {
             switch(evt.getKeyCode()) {
                case KeyEvent.VK_SPACE:
                	// Allocate a Timer to run updateTask's actionPerformed() after every delay msec
                   new Timer(UPDATE_PERIOD, autoMove).start();
                   //will speed up ball w/ space bar pressed
                  
             }
          }
       });
 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Handle the CLOSE button
      setTitle("Move a Ball");
      pack();           // pack all the components in the JFrame
      setVisible(true); // show it
      requestFocus();   // set the focus to JFrame to receive KeyEvent
   }
   
 
   /**
    * Define inner class DrawCanvas, which is a JPanel used for custom drawing.
    */
   class DrawCanvas extends JPanel {
      @Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         setBackground(CANVAS_BACKGROUND);
         g.setColor(circleColor);
         g.fillOval(x1, y1, x2, y2); // Draw the ball  
      }
   }
 
   // The entry main() method
   public static void main(String[] args) {
      // Run GUI codes on the Event-Dispatcher Thread for thread safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new MoveABall(); // Let the constructor do the job
         }
      });
   }
}