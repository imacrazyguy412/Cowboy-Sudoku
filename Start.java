import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

class Start extends JPanel {
  private JLabel title, cowboy;
  private JButton playGame, savedGame;

  public Start() {
	//Sets the content pane
	this.setBorder(BorderFactory.createEmptyBorder (10, 10, 10, 10));
	this.setBackground(Color.white);
	this.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
	//Create and add both labels
	title = new JLabel("Cowboy Sudoku");
	cowboy = new JLabel(new ImageIcon (""));
		
	//Create and add button
	playGame = new JButton("Play New Game");
   	savedGame = new JButton ("Continue Saved Game");
				
				
	}
  
}
