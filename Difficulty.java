
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

class Difficulty {
	private JLabel header;
	private JButton easy, medium, hard, back, start, mistakeToggle;
 
	public static void addToPane (Container pane) {
		//Sets the content pane
		pane.setBackground(new Color (229,229,229,100));
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Create header label
		header = new JLabel("Difficulty Selection");
		//set constraints and add title
		c.gridx = 1;
		c.gridy = 0;
		pane.add(header, c);
		
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
		pane.add (mistakeToggle, c);
		
		//set constraints and add easy;
		c.gridx = 1;
		c.gridy = 1;
		pane.add (easy, c);
		
		//set constraints and add medium;
		c.gridx = 1;
		c.gridy = 2;
		pane.add (medium, c);
		
		//set constraints and add hard;
		c.gridx = 1;
		c.gridy = 3;
		pane.add (hard, c);
		
		//set constraints and add back;
		c.gridx = 0;
		c.gridy = 4;
		pane.add (back, c);
		
		//set constraints and add start;
		c.gridx = 2;
		c.gridy = 4;
		pane.add (start, c);
	}
}
