
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

class Difficulty extends JPanel{
  private JLabel header;
  private JButton easy, medium, hard, back, start, mistakeToggle;
 

  public Start() {
	  //Sets the content pane
	  this.setBackground(new Color (229,229,229,100));
  	this.setLayout(new GridBagLayout());
	  GridBagConstraints c = new GridBagConstraints();
		
	  //Create label
	  title = new JLabel("Difficulty Selection");
	  logo = new JLabel(new ImageIcon ("GUI/logo.png"));
	  //set constraints and add title
	  c.gridx = 1;
	  c.gridy = 0;
	  this.add(title, c);
	  //set constraints and add logo;
  	c.gridx = 1;
	  c.gridy = 2;
	  this.add(logo, c);
		
  	//Create buttons
  	playGame = new JButton("Play New Game");
  	playGame.setActionCommand()
   	savedGame = new JButton ("Continue Saved Game");
  	//set constraints and add playGame;
  	c.gridx = 0;
  	c.gridy = 1;
	  this.add (playGame, c);
  	//set constraints and add savedGame;
  	c.gridx = 2;
	  c.gridy = 1;
  	this.add (savedGame, c);
	}
  
}
