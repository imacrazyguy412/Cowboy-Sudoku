
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

class Confirm {
	private static JLabel header;
	private static JButton back, conf, cancel;
	 static JFrame frame = new JFrame("Cowboy Sudoku");
 
	public static void addToPane (Container pane) {
		//Sets the content pane
		pane.setBackground(new Color (207, 176, 100));
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Create header label
		header = new JLabel("Are you sure?");
		header.setOpaque(true);
		header.setBackground(new Color (207, 176, 100));
		header.setForeground(Color.white);
		
		
		//set constraints and add header
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		pane.add(header, c);
		
		//Create buttons
		back = new JButton("Back");
		back.setOpaque(true);
		back.setBackground(new Color (36, 44, 61));
		back.setForeground (Color.white);
		
		//set constraints and add back;
		c.gridx = 0;
		c.gridy = 2;
		pane.add (easy, c);
		
		conf = new JButton ("Confirm");
		conf.setOpaque(true);
		conf.setBackground(new Color (54, 54, 54));
		conf.setForeground (Color.white);
		
		//set constraints and add conf;
		c.gridx = 1;
		c.gridy = 1;
		pane.add (conf, c);
		
		cancel = new JButton ("Cancel");
		cancel.setOpaque(true);
		cancel.setBackground(new Color (36, 44, 61));
		cancel.setForeground (Color.white);
		//set constraints and add hard;
		c.gridx = 3;
		c.gridy = 1;
		pane.add (cancel, c);
	}
/**
	* Create the GUI and show it.  For thread safety,
 	* this method should be invoked from the
  	* event-dispatching thread.
*/
    private static void createAndShowGUI() {
        //Create and set up the window.
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