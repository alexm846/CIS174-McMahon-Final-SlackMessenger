package stu.amcmahon.games;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * ConnectFour: Two-player Graphics version with Simple-OO
 * @author https://www.ntu.edu.sg/home/ehchua/programming/java/J4b_CustomGraphics.html
 * @author alexjmcmahon
 */
@SuppressWarnings("serial")
public class ConnectFour extends JFrame {
   // Named-constants for the game board
   public static final int ROWS = 6;  // ROWS by COLS cells
   public static final int COLS = 6;
 
   // Named-constants of the various dimensions used for graphics drawing
   public static final int CELL_SIZE = 100; // cell width and height (square)
   public static final int CANVAS_WIDTH = CELL_SIZE * COLS;  // the drawing canvas
   public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;
   public static final int GRID_WIDTH = 8;                   // Grid-line's width
   public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2; // Grid-line's half-width
   // Symbols (cross/nought) are displayed inside a cell, with padding from border
   public static final int CELL_PADDING = CELL_SIZE / 6;
   public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2; // width/height
   public static final int SYMBOL_STROKE_WIDTH = 8; // pen's stroke width
 
   // Use an enumeration (inner class) to represent the various states of the game
   public enum GameState {
      PLAYING, DRAW, RED_WON, ORANGE_WON
   }
   private GameState currentState;  // the current game state
 
   // Use an enumeration (inner class) to represent the seeds and cell contents
   public enum Seed {
      EMPTY, RED, ORANGE
   }
   private Seed currentPlayer;  // the current player
 
   private Seed[][] board   ; // Game board of ROWS-by-COLS cells
   private DrawCanvas canvas; // Drawing canvas (JPanel) for the game board
   private JLabel statusBar;  // Status Bar
 
   /** Constructor to setup the game and the GUI components */
   public ConnectFour() {
      canvas = new DrawCanvas();  // Construct a drawing canvas (a JPanel)
      canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
 
      // The canvas (JPanel) fires a MouseEvent upon mouse-click
      canvas.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {  // mouse-clicked handler
            int mouseX = e.getX();
            int mouseY = e.getY();
            // Get the row and column clicked
            int rowSelected = mouseY / CELL_SIZE;
            int colSelected = mouseX / CELL_SIZE;
 
            if (currentState == GameState.PLAYING) {
            	if (colSelected >= 0 && colSelected < COLS) {
            		   // Look for an empty cell starting from the bottom row
            		   for (int row = ROWS -1; row >= 0; row--) {
            		      if (board[row][colSelected] == Seed.EMPTY) {
            		         board[row][colSelected] = currentPlayer; // Make a move
            		         updateGame(currentPlayer, row, colSelected); // update state
            		         // Switch player
            		         currentPlayer = (currentPlayer == Seed.RED) ? Seed.ORANGE : Seed.RED;
            		         break;
            		      }
            		   }
            		}
            } else {       // game over
               initGame(); // restart the game
            }
            // Refresh the drawing canvas
            repaint();  // Call-back paintComponent().
         }
      });
 
      // Setup the status bar (JLabel) to display status message
      statusBar = new JLabel("  ");
      statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
      statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));
 
      Container cp = getContentPane();
      cp.setLayout(new BorderLayout());
      cp.add(canvas, BorderLayout.CENTER);
      cp.add(statusBar, BorderLayout.PAGE_END); // same as SOUTH
 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();  // pack all the components in this JFrame
      setTitle("Connect Four!");
      setVisible(true);  // show this JFrame
 
      board = new Seed[ROWS][COLS]; // allocate array
      initGame(); // initialize the game board contents and game variables
   }
 
   /** Initialize the game-board contents and the status */
   public void initGame() {
      for (int row = 0; row < ROWS; ++row) {
         for (int col = 0; col < COLS; ++col) {
            board[row][col] = Seed.EMPTY; // all cells empty
         }
      }
      currentState = GameState.PLAYING; // ready to play
      currentPlayer = Seed.RED;       // cross plays first
   }
 
   /** Update the currentState after the player with "theSeed" has placed on
       (rowSelected, colSelected). */
   public void updateGame(Seed theSeed, int rowSelected, int colSelected) {
      if (hasWon(theSeed, rowSelected, colSelected)) {  // check for win
         currentState = (theSeed == Seed.RED) ? GameState.RED_WON : GameState.ORANGE_WON;
      } else if (isDraw()) {  // check for draw
         currentState = GameState.DRAW;
      }
      // Otherwise, no change to current state (still GameState.PLAYING).
   }
 
   /** Return true if it is a draw (i.e., no more empty cell) */
   public boolean isDraw() {
      for (int row = 0; row < ROWS; ++row) {
         for (int col = 0; col < COLS; ++col) {
            if (board[row][col] == Seed.EMPTY) {
               return false; // an empty cell found, not draw, exit
            }
         }
      }
      return true;  // no more empty cell, it's a draw
   }
 
   /** Return true if the player with "theSeed" has won after placing at
       (rowSelected, colSelected) */
   public boolean hasWon(Seed theSeed, int rowSelected, int colSelected) {
	// Check for 4-in-a-line on the rowSelected
	   int count = 0;
	   for (int col = 0; col < COLS; ++col) {
	      if (board[rowSelected][col] == theSeed) {
	         ++count;
	         if (count == 4) return true;  // found
	      } else {
	         count = 0; // reset and count again if not consecutive
	      }
	   }
	   //check 4 in a row columns
	   for (int row = 0; row < ROWS; ++row) {
		      if (board[row][colSelected] == theSeed) {
		         ++count;
		         
		         if (count == 4) return true;  // found
		      } else {
		         count = 0; // reset and count again if not consecutive
		      }
		   }
	   //check 4 in a row diagonal
	    for(int col=5; col>2; col--) {
	    	for(int row=0; row<4; row++) {
	    		if (board[col][row] == theSeed && board[col - 1][row + 1] == theSeed
	    		&& board[col - 2][row + 2] == theSeed && board[col - 3][row + 3] == theSeed) {		         
	    			return true;
	    		} else {
	    			count = 0; 
	    		}	
	    	}
	    }
		//check four in a row diagonal other direction   
	    for(int col=5; col>2; col--) {
	    	for(int row=5; row>2; row--) {
	    		if (board[col][row] == theSeed && board[col - 1][row - 1] == theSeed
	    		&& board[col - 2][row - 2] == theSeed && board[col - 3][row - 3] == theSeed) {		         
	    			return true;
	    		} else {
	    			count = 0; 
	    		}	
	    	}
	    }
	
	   
	   return false; 
   }
 
   /**
    *  Inner class DrawCanvas (extends JPanel) used for custom graphics drawing.
    */
   class DrawCanvas extends JPanel {
      @Override
      public void paintComponent(Graphics g) {  // invoke via repaint()
         super.paintComponent(g);    // fill background
         setBackground(Color.WHITE); // set its background color
 
         // Draw the grid-lines
         g.setColor(Color.BLACK);
         for (int row = 1; row < ROWS; ++row) {
            g.fillRoundRect(0, CELL_SIZE * row - GRID_WIDHT_HALF,
                  CANVAS_WIDTH-1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
         }
         for (int col = 1; col < COLS; ++col) {
            g.fillRoundRect(CELL_SIZE * col - GRID_WIDHT_HALF, 0,
                  GRID_WIDTH, CANVAS_HEIGHT-1, GRID_WIDTH, GRID_WIDTH);
         }
 
         // Draw the Seeds of all the cells if they are not empty
         // Use Graphics2D which allows us to set the pen's stroke
         Graphics2D g2d = (Graphics2D)g;
         g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND,
               BasicStroke.JOIN_ROUND));  // Graphics2D only
         for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
               int x1 = col * CELL_SIZE + CELL_PADDING;
               int y1 = row * CELL_SIZE + CELL_PADDING;
               if (board[row][col] == Seed.RED) {
                  g2d.setColor(Color.RED);
                  int x2 = (col + 1) * CELL_SIZE - CELL_PADDING;
                  int y2 = (row + 1) * CELL_SIZE - CELL_PADDING;
                  g2d.fillOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
               } else if (board[row][col] == Seed.ORANGE) {
                  g2d.setColor(Color.ORANGE);
                  g2d.fillOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
               }
            }
         }
 
         // Print status-bar message
         if (currentState == GameState.PLAYING) {
            statusBar.setForeground(Color.BLACK);
            if (currentPlayer == Seed.RED) {
               statusBar.setText("Red's Turn");
            } else {
               statusBar.setText("Oranges's Turn");
            }
         } else if (currentState == GameState.DRAW) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("It's a Draw! Click to play again.");
         } else if (currentState == GameState.RED_WON) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("'Red' Won! Click to play again.");
         } else if (currentState == GameState.ORANGE_WON) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("'Orange' Won! Click to play again.");
         }
      }
   }
 
   /** The entry main() method */
   public static void main(String[] args) {
      // Run GUI codes in the Event-Dispatching thread for thread safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new ConnectFour(); // Let the constructor do the job
         }
      });
   }
}
