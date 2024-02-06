
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
		
		//Create header label
		header = new JLabel("Difficulty Selection");
		//set constraints and add title
		c.gridx = 1;
		c.gridy = 0;
		this.add(header, c);
		
		//Create buttons
		easy = new JButton("Easy");
		medium = new JButton ("Medium");
		hard = new JButton ("Hard");
		back = new JButton ("Back");
		start = new JButton ("Start");
		mistakeToggle = new JButton ("Toggle Mistakes Limiter");
		
		//set constraints and add mistakeToggle;
		c.gridx = 0;
		c.gridy = 0;
		this.add (mistakeToggle, c);
		//set constraints and add easy;
		c.gridx = 1;
		c.gridy = 1;
		this.add (easy, c);
		//set constraints and add medium;
		c.gridx = 1;
		c.gridy = 1;
		this.add (easy, c);
	}
}
