package stu.amcmahon.slackmessenger;

import java.awt.*;       // Using AWT's Graphics and Color
import java.awt.event.*; // Using AWT's event classes and listener interface
import java.util.Random;

import javax.swing.*;    // Using Swing's components and containers
/**
 * Custom Graphics Example: Using key/button to move a line left or right.
 */
@SuppressWarnings("serial")
public class MoveABall extends JFrame {
   // Define constants for the various dimensions
   public static final int CANVAS_WIDTH = 1800;
   public static final int CANVAS_HEIGHT = 800;
   public static final Color CANVAS_BACKGROUND = Color.GREEN;
   public static final Color CIRCLE_FILL_COLOR = Color.BLACK;
 
   // The moving line from (x1, y1) to (x2, y2), initially position at the center
   private int x1 = 100;
   private int y1 = CANVAS_HEIGHT - 300;
   private int x2 = 200;
   private int y2 = 200;
   
   Random rand = new Random();
 
   private DrawCanvas canvas; // The custom drawing canvas (an inner class extends JPanel)
 
   // Constructor to set up the GUI components and event handlers
   public MoveABall() {
      // Set up a panel for the buttons
      JPanel btnPanel = new JPanel(new FlowLayout());
      JButton btnColor = new JButton("Change Color ");
      btnPanel.add(btnColor);
      btnColor.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
        	System.out.println("DO SOMETHING");
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
      
      addKeyListener(new KeyAdapter() {
          @Override
          public void keyPressed(KeyEvent evt) {
             switch(evt.getKeyCode()) {
                case KeyEvent.VK_SPACE:
                
                	y1 = rand.nextInt(50);
                	x1 = rand.nextInt(100);
                   repaint();
                   break;
             }
          }
       });
 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Handle the CLOSE button
      setTitle("Move a Line");
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
         g.setColor(CIRCLE_FILL_COLOR);
         g.fillOval(x1, y1, x2, y2); // Draw the line
         g.drawLine(0, CANVAS_HEIGHT - 100, CANVAS_WIDTH, CANVAS_HEIGHT - 100);
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