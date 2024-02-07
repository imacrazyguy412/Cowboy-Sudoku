
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

/*
	Constraints reference
 	c.fill = GridBagConstraints.HORIZONTAL;
  	c.ipady
   	c.ipadx
   	c.gridwidth
    	c.gridheight
     	c.gridy
      	c.gridx
    	c.anchor
     	c.insets
  	c.weightx = 0.0;
	c.weighty = 0.0;
*/

class Difficulty {
	private static JLabel header;
	private static JButton easy, medium, hard, back, start, mistakeToggle;
 
	public static void addToPane (Container pane) {
		//Sets the content pane
		pane.setBackground(new Color (229,229,229,100));
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Create header label
		header = new JLabel("Difficulty Selection");
		header.setBackground(new Color (36, 44, 61, 100));
		header.setForeground(Color.white);
		header.setOpaque(true);
		//set constraints and add title
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(header, c);
		
		//Create buttons
		easy = new JButton("Easy");
		easy.setBackground(new Color (207, 176, 100, 100));
		easy.setForeground (Color.black);
		easy.setOpaque(true);
		
		medium = new JButton ("Medium");
		medium.setBackground(new Color (36, 44, 61, 100));
		medium.setForeground (Color.white);
		medium.setOpaque(true);
		
		hard = new JButton ("Hard");
		hard.setBackground(new Color (207, 176, 100, 100));
		hard.setForeground (Color.black);
		hard.setOpaque(true);
		
		back = new JButton ("Back");
		back.setBackground(new Color (36, 44, 61, 100));
		back.setForeground (Color.white);
		back.setOpaque(true);
		
		start = new JButton ("Start");
		start.setBackground(new Color (36, 44, 61, 100));
		start.setForeground (Color.white);
		start.setOpaque(true);
		
		mistakeToggle = new JButton ("Toggle Mistakes");
		mistakeToggle.setBackground(new Color (36, 44, 61, 100));
		mistakeToggle.setForeground (Color.white);
		mistakeToggle.setOpaque(true);
		
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
/**
	* Create the GUI and show it.  For thread safety,
 	* this method should be invoked from the
  	* event-dispatching thread.
*/
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("GridBagLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
